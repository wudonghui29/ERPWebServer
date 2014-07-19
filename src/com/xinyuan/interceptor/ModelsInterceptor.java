package com.xinyuan.interceptor;

import j2se.modules.Helper.DLog;
import j2se.modules.Helper.FileHelper;
import j2se.modules.Introspector.IntrospectHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.Util.GsonHelper;
import com.xinyuan.action.ActionBase;
import com.xinyuan.action.AdministratorAction;
import com.xinyuan.action.SuperAction;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.RequestMessage;

public class ModelsInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		DLog.log("");
		
		ActionBase baseAction = (ActionBase)invocation.getAction();
		RequestMessage requestMessage = baseAction.getRequestMessage();
		
		List<String> modelsArray = requestMessage.getMODELS();						// MODELS
		List<Map<String, Object>> objectsArray = requestMessage.getOBJECTS();		// OBJECTS
		if(modelsArray.size() != objectsArray.size()) {
			DLog.log("Reject : models.size not match objects.size");
			return Action.NONE;
		}
		
		List<Set<String>> voKeys = new ArrayList<Set<String>>();
		List<Object> vos = new ArrayList<Object>();
		
		// "HumanResource__delete" -> "HumanResource" , "HumanResourceAction" -> "HumanResource"
		String actionName = IntrospectHelper.getShortClassName(baseAction);
		String catagory = actionName.replace(ConfigConstants.ACTION_CLASS_SUFFIX, ConfigConstants.EMPTY_STRING);
		
		for (int i = 0; i < modelsArray.size(); i++) {
			String modelStr = modelsArray.get(i);		// e.g : ".Employee" or for super ".HumanResource.Employee"
			Map<String, Object> objectMap = objectsArray.get(i);
			
			// get model
			String objectJsonStr = GsonHelper.getGson().toJson(objectMap);
			String className = getModelClassWholeName(baseAction, catagory, modelStr);
			Object vo = GsonHelper.getGson().fromJson(objectJsonStr, Class.forName(className));
			
			// get keys
			Set<String> keysSet = new HashSet<String>();
			keysSet.addAll(objectMap.keySet());
			
			vos.add(vo);					// MODELS
			voKeys.add(keysSet);			// KEYS
		}
		
		
		baseAction.setModels(vos);
		baseAction.setModelsKeys(voKeys);
		
		return invocation.invoke();
	}
	
	
	private String getModelClassWholeName(ActionBase action, String category, String model) {
		boolean isSuper = action.getClass() == SuperAction.class || action.getClass() == AdministratorAction.class;
		
		String className = ConfigConstants.MODELPACKAGE + ( isSuper ?  model : FileHelper.JAVA_PACKAGE_CONNECTOR + category + model);
		
		return className;
	}

}
