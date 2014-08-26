package com.xinyuan.action;

import j2se.modules.Helper.CollectionHelper;
import j2se.modules.Helper.DLog;
import j2se.modules.Helper.SecurityCode;
import j2se.modules.Helper.VerifyCode;
import j2se.modules.Introspector.ObjectIntrospector;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.Global.SessionManager;
import com.modules.HttpWriter.ResponseWriter;
import com.opensymphony.xwork2.Action;
import com.xinyuan.Util.ApnsHelper;
import com.xinyuan.Util.AppModelsHelper;
import com.xinyuan.Util.GsonHelper;
import com.xinyuan.Util.ParametersHelper;
import com.xinyuan.Util.QueueHelper;
import com.xinyuan.Util.SettingHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.interceptor.AdministratorInterceptor;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ConfigJSON;
import com.xinyuan.message.MessagesKeys;
import com.xinyuan.model.Setting.APPSettings;

/**
 * 
 * This Action is No need to check permission, but need check signed or not .
 * 		So put the no permission checked user interface here .
 * 
 * With get* prefix , the not signed user can get
 * 
 * With read* prefix , it needs to signed 
 * 
 *
 */
public class SettingAction extends ActionBase {
	
	private static final long serialVersionUID = 1L;
	
	public static boolean isInitilized = false;
	
	
	@Override
	protected SuperDAO getDao() { return null; }
	
	/**
	 * connect the server , get the cookie, no db operation here now
	 * @return
	 * @throws Exception
	 */
	public String getConnection() throws Exception {
		
		if (!isInitilized) {
			Boolean isUserTableEmpty = SettingHelper.isUserTableEmpty();
			if (isUserTableEmpty) {
				responseMessage.descriptions = MessagesKeys.SystemNeedInitialed;
			} else {
				isInitilized = true;
			}
		} 
		
		this.sendVerifyCode();
		Map<String, Map<String, Map<String, String>>> map = this.sendModelsStructures();
		responseMessage.results = map;
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		return Action.NONE;
	}
	
	private void sendVerifyCode() throws Exception {
		String VERIFYCODE_TYPE = ParametersHelper.getParameter(requestMessage, ConfigJSON.VERIFYCODE_TYPE);
		String VERIFYCODE_COUNT = ParametersHelper.getParameter(requestMessage, ConfigJSON.VERIFYCODE_COUNT);
		
		int count = 4;		// default
		boolean randomBool = new Random().nextBoolean() ;		// default
		try {
			if (VERIFYCODE_COUNT != null) count = Integer.valueOf(VERIFYCODE_COUNT) ;
			if (VERIFYCODE_TYPE != null) randomBool = Boolean.valueOf(VERIFYCODE_TYPE);
		} catch (NumberFormatException e) {
			// 
		}
		String verifyCode = randomBool ? VerifyCode.generateCode(count) : SecurityCode.getSecurityCode(count);
		byte[] imageBytes = randomBool ? VerifyCode.generateImageBytes(verifyCode) : SecurityCode.getImageAsBytes(verifyCode);
		
		// header
		HttpServletResponse response = ResponseWriter.getResponse();
		response.addIntHeader(ConfigJSON.BINARY_LENGHT, imageBytes.length);		// before write data

		// send binary data
		SessionManager.put(ConfigJSON.VERIFYCODE, verifyCode);
		ResponseWriter.write(imageBytes);
		
		DLog.log(ConfigJSON.VERIFYCODE + " : " + verifyCode + " . " + imageBytes.length );
	}
	
	private Map<String, Map<String, Map<String, String>>> sendModelsStructures() {
		// For the first time connect , send the structures
		String cookie = ServletActionContext.getRequest().getHeader("cookie");
		if (cookie != null) return null;				// make sure it is the first connecteion
		
		Map<String, Map<String, Map<String, String>>> categoriesModelsMap = AppModelsHelper.getCategoriesModelsConstructs();
		
		// clear the CATEGORIE_APPROVAL contents 
		categoriesModelsMap.get(ConfigConstants.CATEGORIE_APPROVAL).clear();
		
		// exclude user and approval, extensions
		categoriesModelsMap.remove(ConfigConstants.CATEGORIE_USER);
		categoriesModelsMap.remove(ConfigConstants.CATEGORIE_SETTING);
		
		return categoriesModelsMap;
	}
	
	
	/**
	 * Push notifications
	 * @return
	 */
	public String inform() {
		try {
			List<String> forwardList = requestMessage.getAPNS_FORWARDS();
			List<Map<String, Object>> forwardContents = requestMessage.getAPNS_CONTENTS();
			
			if (ApnsHelper.isNeedInfor(forwardList, forwardContents)) {
				boolean isAllSuccess = ApnsHelper.inform(forwardList, forwardContents);
				if (isAllSuccess){
					responseMessage.status = ConfigConstants.STATUS_POSITIVE;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.NONE;
	}
	
	public String refresh() {
		
		try {
			
			QueueHelper.flushClients();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Action.NONE;
	}
	
	/**
	 * About the APPSetting Table
	 * @return
	 * @throws Exception
	 */
	public String modifyType() throws Exception {
		
		SuperDAO superDao = new SuperDAOIMP();
		
		List<Map<String, String>> identities = requestMessage.getIDENTITYS();
		
		APPSettings appSettingVO = (APPSettings) models.get(0);
			
		// get PO
		Map<String, String> idenfier = identities.get(0);
		
		// when modify administrator's type , check is administrator
		String typeValue = idenfier.get("type");
		if (typeValue.startsWith(ConfigConstants.APPSettings_TYPE_PREFIX_ADMIN)) {
			if (! AdministratorInterceptor.currentUserIsAdministrator()) return Action.NONE;
		}
		
		ObjectIntrospector.setProperty(appSettingVO, idenfier);
		APPSettings appSettingPO =  superDao.readUnique(appSettingVO, idenfier.keySet());
			
		if (appSettingPO == null) {
			appSettingPO = appSettingVO;
			superDao.create(appSettingPO);
		} else {
			String approvalVoSettings = appSettingVO.getSettings();
			String approvalPoSettings = appSettingPO.getSettings();
			
				// if map 
			if (approvalPoSettings.startsWith("{") && approvalPoSettings.endsWith("}") && approvalVoSettings.startsWith("{") && approvalVoSettings.endsWith("}")) {
				//"{"HumanResource":{"Employee": {"app1":[], "app2":[]}} , ...}"
				@SuppressWarnings("unchecked")
				Map<String,Object> settingsPoMap = GsonHelper.getGson().fromJson(approvalPoSettings, Map.class);
				@SuppressWarnings("unchecked")
				Map<String,Object> settingsVoMap = GsonHelper.getGson().fromJson(approvalVoSettings, Map.class);
				CollectionHelper.combineTowMap(settingsVoMap, settingsPoMap);
				
				String newSettingsPoJSON = GsonHelper.getGson().toJson(settingsPoMap);
				appSettingPO.setSettings(newSettingsPoJSON);
				
				// if array or some else
			} else {
				ObjectIntrospector.copyVoToPo(appSettingVO, appSettingPO, modelsKeys.get(0));
			}
			
			// then save it to database
			superDao.modify(appSettingPO);
		}
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		return Action.NONE;
	}
	
	public String readType() throws Exception{
		
		SuperDAO superDao = new SuperDAOIMP();
		
		List<Map<String, String>> identities = requestMessage.getIDENTITYS();
		
		APPSettings appSettingVO = (APPSettings) models.get(0);
			
		// get PO
		Map<String, String> idenfier = identities.get(0);
		ObjectIntrospector.setProperty(appSettingVO, idenfier);
		APPSettings appSettingPO =  superDao.readUnique(appSettingVO, idenfier.keySet());
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		responseMessage.results = appSettingPO;
		
		return Action.NONE;
	}
	
	
}

