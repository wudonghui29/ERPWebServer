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
import com.xinyuan.message.ConstantsConfig;

public class SettingAction extends ActionModelBase {
	
	@Override
	protected BaseDAO getDao() { return null; }

	public String readApplicationModelsStructures() {
		// get the file paths
		String contextPath = ServletActionContext.getServletContext().getRealPath(File.separator);
		String containerPath = System.getProperty("catalina.base");
		String projectPath = System.getProperty("user.dir");
		String fileSeperator = System.getProperty("file.separator");
		String classLoaderFilePath = getClass().getClassLoader().getResource(".").getPath();
		
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
	
}
