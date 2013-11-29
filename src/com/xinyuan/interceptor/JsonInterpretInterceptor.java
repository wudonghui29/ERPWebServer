package com.xinyuan.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.modules.Introspector.IntrospectHelper;
import com.modules.Util.DLog;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.Util.JsonHelper;
import com.xinyuan.action.ActionModelBase;
import com.xinyuan.action.SuperAction;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ConfigJSON;
import com.xinyuan.message.RequestMessage;

public class JsonInterpretInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		DLog.log("");
//		Action action = (Action)ActionContext.getContext().getActionInvocation().getAction();		// the same as follow
		ActionModelBase baseAction = (ActionModelBase)invocation.getAction();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String json = request.getParameter(ConfigJSON.JSON);
		RequestMessage requestMessage = JsonHelper.getGson().fromJson(json, RequestMessage.class);
		DLog.log("RequestMessage : " + requestMessage.toString());
		
//		DLog.log(json);
//		JsonObject jsonObject = (JsonObject)(new JsonParser()).parse(json);
		
		
		List<Set<String>> voKeys = new ArrayList<Set<String>>();
		List<Object> vos = new ArrayList<Object>();
		
		List<String> modelsArray = requestMessage.getMODELS();						// MODELS
		List<Map<String, Object>> objectsArray = requestMessage.getOBJECTS();		// OBJECTS
		
		if(modelsArray.size() != objectsArray.size()) {
			DLog.log("Reject : models.size not match objects.size");
			return Action.NONE;
		}
		
		// "HumanResource__delete" -> "HumanResource"
		String actionName = IntrospectHelper.getShortClassName(baseAction);
		String catagory = actionName.replace(ConfigConstants.ACTION, ConfigConstants.EMPTY_STRING);
		
		for (int i = 0; i < modelsArray.size(); i++) {
			String modelStr = modelsArray.get(i);		// e.g : ".Employee"
			Map<String, Object> objectMap = objectsArray.get(i);
			
			// get model
			String objectJsonStr = JsonHelper.getGson().toJson(objectMap);
			String className = ConfigConstants.MODELPACKAGE + (baseAction.getClass() == SuperAction.class ?  modelStr : ConfigConstants.PACKAGE_CONNECTOR + catagory + modelStr);
			Class<?> modelClass = Class.forName(className);
			Object vo = JsonHelper.getGson().fromJson(objectJsonStr, modelClass);
			
			// get keys
			Set<String> keys = objectMap.keySet();
			
			vos.add(vo);				// MODELS
			voKeys.add(keys);			// KEYS
		}
		
		
		baseAction.setModels(vos);
		baseAction.setObjectKeys(voKeys);
		baseAction.setRequestMessage(requestMessage);
		
		return invocation.invoke();
	}
	
}
