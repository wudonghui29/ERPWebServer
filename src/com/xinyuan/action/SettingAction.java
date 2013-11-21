package com.xinyuan.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.modules.introspector.IntrospectHelper;
import com.modules.util.FileHelper;
import com.opensymphony.xwork2.Action;
import com.xinyuan.dao.BusinessDAO;
import com.xinyuan.dao.HumanResourceDAO;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.BusinessDAOIMP;
import com.xinyuan.dao.impl.HumanResourceDAOIMP;
import com.xinyuan.message.ConstantsConfig;
import com.xinyuan.util.ApnsHelper;

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
	
	@Override
	protected SuperDAO getDao() { return null; }

	/**
	 * when client launch app
	 * @return
	 */
	public String getApplicationModelsStructures() {
		// get the file paths
		String fileSeperator = System.getProperty("file.separator");
		String modelsFilesPath = ConstantsConfig.contextRealPath + "WEB-INF"
				+ fileSeperator + "classes" + fileSeperator + "com"
				+ fileSeperator + "xinyuan" + fileSeperator + "model" + fileSeperator;
		
		// get the file name list
		File folder = new File(modelsFilesPath);
		List<String> modelsList = new ArrayList<String>();
		FileHelper.listFilesForSubFolder(folder, modelsList);
		
		// get the model package path class name list
		List<String> classesNamesList = new ArrayList<String>();
		for (Iterator iterator = modelsList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			
			if (string.contains(ConstantsConfig.CATEGORIE_USER) || string.contains(ConstantsConfig.CATEGORIE_APPROVAL)) continue;
			
			String className = string.replaceAll(".class", "");
			String wholeClassName = ConstantsConfig.MODELPACKAGE + "." + className;
			classesNamesList.add(wholeClassName);
		}
		
		// translate classes properties name to map
		 Map<String, Map<String, List<String>>> map = IntrospectHelper.translateToPropertiesMap(classesNamesList);
		 
		 message.status = ConstantsConfig.STATUS_SUCCESS;
		 message.objects = map;
		
		
		return Action.NONE;
	}
	
	/**
	 * When client singined
	 * @return
	 */
	public String readEmployeeBasicData() {
		
		HumanResourceDAO humanResourceDAO = new HumanResourceDAOIMP();
		List hrList = humanResourceDAO.getUsersNOPairs();
		
		BusinessDAO businessDAO = new BusinessDAOIMP();
		List bsList = businessDAO.getClientsNOPairs();
		
		List<List> results = new ArrayList<List>();
		results.add(hrList);
		results.add(bsList);
		
		message.status = ConstantsConfig.STATUS_SUCCESS;
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
