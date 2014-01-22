package com.xinyuan.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.Global.SessionManager;
import com.modules.HttpWriter.ResponseWriter;
import com.modules.Introspector.IntrospectHelper;
import com.modules.Introspector.ModelIntrospector;
import com.modules.Util.DLog;
import com.modules.Util.FileHelper;
import com.modules.Util.SecurityCode;
import com.modules.Util.VerifyCode;
import com.opensymphony.xwork2.Action;
import com.xinyuan.Util.ApnsHelper;
import com.xinyuan.Util.JsonHelper;
import com.xinyuan.dao.BusinessDAO;
import com.xinyuan.dao.HumanResourceDAO;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.BusinessDAOIMP;
import com.xinyuan.dao.impl.HibernateDAO;
import com.xinyuan.dao.impl.HumanResourceDAOIMP;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ConfigJSON;
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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected SuperDAO getDao() { return null; }
	
	/**
	 * connect the server , get the cookie, no db operation here now
	 * @return
	 * @throws Exception
	 */
	public String getConnection() throws Exception {
		String VERIFYCODE_TYPE = JsonHelper.getParameter(requestMessage, ConfigJSON.VERIFYCODE_TYPE);
		String VERIFYCODE_COUNT = JsonHelper.getParameter(requestMessage, ConfigJSON.VERIFYCODE_COUNT);
		
		int count = 7;
		try {
			count = VERIFYCODE_COUNT == null ? 4 : Integer.valueOf(VERIFYCODE_COUNT) ;
		} catch (NumberFormatException e) {
			//
		}
		
		boolean randomBool = (VERIFYCODE_TYPE == null) ? (new Random()).nextBoolean() : Boolean.valueOf(VERIFYCODE_TYPE);
		String verifyCode = randomBool ? VerifyCode.generateCode(count) : SecurityCode.getSecurityCode(count);
		byte[] imageBytes = randomBool ? VerifyCode.generateImageBytes(verifyCode) : SecurityCode.getImageAsBytes(verifyCode);
		
		HttpServletResponse response = ResponseWriter.getResponse();
		response.addIntHeader(ConfigJSON.BINARY_LENGHT, imageBytes.length);		// before write data

		SessionManager.put(ConfigJSON.VERIFYCODE, verifyCode);
		ResponseWriter.write(imageBytes);
		
		// For the first time connect , send the structures
		HttpServletRequest request = ServletActionContext.getRequest();
		String cookie = request.getHeader("cookie");
		if (cookie == null) this.applicationModelsStructures();
		
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		
		DLog.log(ConfigJSON.VERIFYCODE + " : " + verifyCode + " . " + imageBytes.length );

		
		return Action.NONE;
	}

	private void applicationModelsStructures() {
		// get the file name list
		File folder = new File(ConfigConstants.Models_Class_Files_Path);
		List<String> modelsList = new ArrayList<String>();
		FileHelper.listFilesForSubFolder(folder, modelsList);
		
		// get the model package path class name list
		List<String> classesNamesList = new ArrayList<String>();
		for (Iterator<String> iterator = modelsList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();				// "Approval.Approvals.class" , ".class" not ".java" 
			
			if (string.contains(ConfigConstants.CATEGORIE_USER) 
					|| string.contains(ConfigConstants.CATEGORIE_APPROVAL)
					|| string.contains(ConfigConstants.CATEGORIE_SETTING)) continue;		// exclude user and approval, extensions
			
			String className = string.replaceAll(ConfigConstants.SUFFIX_CLASS, ConfigConstants.EMPTY_STRING);
			String wholeClassName = ConfigConstants.MODELPACKAGE + ConfigConstants.PACKAGE_CONNECTOR + className;			// MODELPACKAGE + "Approval.Approvals"
			classesNamesList.add(wholeClassName);
		}
		
		// translate classes properties name to map
		 Map<String, Map<String, List<String>>> map = IntrospectHelper.translateToPropertiesMap(classesNamesList);
		 
		 responseMessage.results = map;
	}
	
	
	/**
	 * When client singined
	 * @return
	 */
	public String readSignedIndData() {			// need to refresh 
		
		HumanResourceDAO humanResourceDAO = new HumanResourceDAOIMP();
		List<Object> hrList = humanResourceDAO.getUsersNOPairs();
		
		BusinessDAO businessDAO = new BusinessDAOIMP();
		List<Object> bsList = businessDAO.getClientsNOPairs();
		
		HibernateDAO dao = new HibernateDAO();
		List<Object> settingList = dao.getObjects("select settings.settings from APPSettings settings where settings.type = '" + ConfigConstants.APPSETTINGS_APPROVALS +"'");
		
		List<Object> results = new ArrayList<Object>();
		results.add(hrList);
		results.add(bsList);
		results.add(settingList);
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		responseMessage.results = results;
		
		return Action.NONE;
	}
	
	
	/**
	 * Push notifications
	 * @return
	 */
	public String inform() {
		try {
			ApnsHelper.inform(requestMessage.getAPNS_FORWARDS(), requestMessage.getAPNS_CONTENTS());
		} catch (Exception e) {
			responseMessage.apnsStatus = ConfigConstants.STATUS_NEGATIVE;
		}
		return Action.NONE;
	}
	
	
	/**
	 * Product Category Modify
	 * @return
	 * @throws Exception
	 */
	public String modifyProductCategory() throws Exception {
        if (models.size() != 1) return Action.NONE;
		
		SuperDAO superDao = new SuperDAOIMP();
		
		List<Map<String, String>> identities = requestMessage.getIDENTITYS();
		
		APPSettings appSettingVO = (APPSettings) models.get(0);
			
		// get PO
		Map<String, String> idenfier = identities.get(0);
		ModelIntrospector.setProperty(appSettingVO, idenfier);
		APPSettings appSettingPO =  superDao.readUnique(appSettingVO, idenfier.keySet());
			
		if (appSettingPO == null) {
			appSettingPO = appSettingVO;
			superDao.create(appSettingPO);
		} else {
			ModelIntrospector.copyVoToPo(appSettingVO, appSettingPO, modelsKeys.get(0));
			superDao.modify(appSettingPO);
		}
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		
		return Action.NONE;
	}
	
	/** 
	 * Product Category Read
	 * @return
	 * @throws Exception
	 */
	public String readProductCategory() throws Exception{
		
		HibernateDAO productDAO = new HibernateDAO();
		List<Object> productCategoryList = productDAO.getObjects("select settings.settings from APPSettings settings where settings.type = '" + ConfigConstants.APPSETTINGS_PRODUCTCATEGORY +"'");
		
		List<Object> results = new ArrayList<Object>();
		results.add(productCategoryList);
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		responseMessage.results = results;
		
		return Action.NONE;
	}
	
	
}

