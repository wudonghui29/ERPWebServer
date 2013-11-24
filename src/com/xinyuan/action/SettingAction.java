package com.xinyuan.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.modules.Introspector.IntrospectHelper;
import com.modules.Util.FileHelper;
import com.opensymphony.xwork2.Action;
import com.xinyuan.Util.ApnsHelper;
import com.xinyuan.dao.BusinessDAO;
import com.xinyuan.dao.HumanResourceDAO;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.BusinessDAOIMP;
import com.xinyuan.dao.impl.HumanResourceDAOIMP;
import com.xinyuan.message.ConfigConstants;

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
public class SettingAction extends ActionModelBase {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	protected SuperDAO getDao() { return null; }

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
		 
		 message.status = ConfigConstants.STATUS_SUCCESS;
		 message.objects = map;
		
		
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
		
		message.status = ConfigConstants.STATUS_SUCCESS;
		message.objects = results;
		
		return Action.NONE;
	}
	
	
	/**
	 * Push notifications
	 * @return
	 */
	public String inform() {
		ApnsHelper.sendAPNS(allJsonObject, message);
		return Action.NONE;
	}
}
