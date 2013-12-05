package com.xinyuan.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.Global.SessionManager;
import com.modules.HttpWriter.ResponseWriter;
import com.modules.Introspector.IntrospectHelper;
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
import com.xinyuan.dao.impl.HumanResourceDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ConfigJSON;

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
	 * connect the server , get the cookie
	 * @return
	 * @throws Exception
	 */
	public String getConnect() throws Exception {
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

		SessionManager.put(ConfigJSON.VERIFYCODE, verifyCode);
		ResponseWriter.write(imageBytes);
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		DLog.log(ConfigJSON.VERIFYCODE + " : " + verifyCode);

		return Action.NONE;
	}

	
	/**
	 * when client launch app
	 * @return
	 */
	public String getApplicationModelsStructures() {
		// get the file name list
		File folder = new File(ConfigConstants.Models_Class_Files_Path);
		List<String> modelsList = new ArrayList<String>();
		FileHelper.listFilesForSubFolder(folder, modelsList);
		
		// get the model package path class name list
		List<String> classesNamesList = new ArrayList<String>();
		for (Iterator<String> iterator = modelsList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();				// "Approval.Approvals.class" , ".class" not ".java" 
			
			if (string.contains(ConfigConstants.CATEGORIE_USER) || string.contains(ConfigConstants.CATEGORIE_APPROVAL)) continue;
			
			String className = string.replaceAll(ConfigConstants.SUFFIX_CLASS, ConfigConstants.EMPTY_STRING);
			String wholeClassName = ConfigConstants.MODELPACKAGE + ConfigConstants.PACKAGE_CONNECTOR + className;			// MODELPACKAGE + "Approval.Approvals"
			classesNamesList.add(wholeClassName);
		}
		
		// translate classes properties name to map
		 Map<String, Map<String, List<String>>> map = IntrospectHelper.translateToPropertiesMap(classesNamesList);
		 
		 responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		 responseMessage.objects = map;
		
		return Action.NONE;
	}
	
	
	/**
	 * When client singined
	 * @return
	 */
	public String readSignedIndData() {
		
		HumanResourceDAO humanResourceDAO = new HumanResourceDAOIMP();
		List<Object> hrList = humanResourceDAO.getUsersNOPairs();
		
		BusinessDAO businessDAO = new BusinessDAOIMP();
		List bsList = businessDAO.getClientsNOPairs();
		
		List<List> results = new ArrayList<List>();
		results.add(hrList);
		results.add(bsList);
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		responseMessage.objects = results;
		
		return Action.NONE;
	}
	
	
	/**
	 * Push notifications
	 * @return
	 */
	public String inform() {
		ApnsHelper.sendAPNS(requestMessage, responseMessage);
		return Action.NONE;
	}
	
	
	
}

