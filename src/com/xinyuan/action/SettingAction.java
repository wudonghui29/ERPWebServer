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
import com.xinyuan.dao.BaseDAO;
import com.xinyuan.dao.HumanResourceDAO;
import com.xinyuan.dao.impl.HumanResourceDAOIMP;
import com.xinyuan.message.ConstantsConfig;

/**
 * 
 * This Action is No need to check permission, 
 * 		so put the no permission checked user interface here
 * 
 * With get* prefix , the not signed user can get
 * 
 * With read* prefix , it needs to signed 
 * 
 *
 */
public class SettingAction extends ActionModelBase {
	
	@Override
	protected BaseDAO getDao() { return null; }

	
	public String getApplicationModelsStructures() {
		// get the file paths
		String fileSeperator = System.getProperty("file.separator");
		String contextPath = ServletActionContext.getServletContext().getRealPath(File.separator);
		
		String modelsFilesPath = contextPath + "WEB-INF" + fileSeperator + "classes" + fileSeperator + "com" + fileSeperator + "xinyuan" + fileSeperator + "model" + fileSeperator;
		
		// get the file name list
		File folder = new File(modelsFilesPath);
		List<String> modelsList = new ArrayList<String>();
		FileHelper.listFilesForSubFolder(folder, modelsList);
		
		// get the model package path class name list
		List<String> classesNamesList = new ArrayList<String>();
		for (Iterator iterator = modelsList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			String className = string.replaceAll(".class", "");
			String wholeClassName = ConstantsConfig.MODELPACKAGE + className;
			classesNamesList.add(wholeClassName);
//			System.out.println(string);
		}
		
		// translate classes properties name to map
		 Map<String, Map<String, List<String>>> map = IntrospectHelper.translateToPropertiesMap(classesNamesList);
//		 System.out.println(map);
		 
		 
		 message.status = ConstantsConfig.STATUS_SUCCESS;
		 message.object = map;
		
		
		return Action.NONE;
	}
	
	public String readNameEmployeeNOPairs() {
		
		HumanResourceDAO humanResourceDAO = new HumanResourceDAOIMP();
		List list = humanResourceDAO.getNameNOPairs();
		
		message.status = ConstantsConfig.STATUS_SUCCESS;
		message.object = list;
		
		return Action.NONE;
	}
}
