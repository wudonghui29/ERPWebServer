package com.xinyuan.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
		DLog.log(json);
		RequestMessage requestMessage = JsonHelper.getGson().fromJson(json, RequestMessage.class);
		DLog.log("RequestMessage : " + requestMessage.toString());
		JsonObject jsonObject = (JsonObject)(new JsonParser()).parse(json);
		
		
		List<Set<String>> voKeys = new ArrayList<Set<String>>();
		List<Object> vos = new ArrayList<Object>();
		
		JsonArray modelsArray = (JsonArray) jsonObject.get(ConfigJSON.MODELS);			// MODELS
		JsonArray objectsArray = (JsonArray) jsonObject.get(ConfigJSON.OBJECTS);		// OBJECTS
		
		if(modelsArray.size() != objectsArray.size()) {
			DLog.log("Reject : models.size not match objects.size");
			return Action.NONE;
		}
		
		// "HumanResource__delete" -> "HumanResource"
		String actionName = IntrospectHelper.getShortClassName(baseAction);
		String catagory = actionName.replace(ConfigConstants.ACTION, ConfigConstants.EMPTY_STRING);
		
		for (int i = 0; i < modelsArray.size(); i++) {
			JsonElement modelElement = modelsArray.get(i);
			JsonElement objectElement = objectsArray.get(i);
			
			// get model
			String modelString = modelElement.getAsString();					// e.g : ".Employee"
			String objectString = JsonHelper.getGson().toJson(objectElement);
			String className = ConfigConstants.MODELPACKAGE + (baseAction.getClass() == SuperAction.class ?  modelString : ConfigConstants.PACKAGE_CONNECTOR + catagory + modelString);
			Class<?> modelClass = Class.forName(className);
			Object vo = JsonHelper.getGson().fromJson(objectString, modelClass);
			
			// get keys
			Map<String, Object> map = JsonHelper.translateElementToMap(objectElement);
			Set<String> keys = map.keySet();
			
			vos.add(vo);				// MODELS
			voKeys.add(keys);			// KEYS
		}
		
		
		baseAction.setModels(vos);
		baseAction.setObjectKeys(voKeys);
		baseAction.setAllJsonObject(jsonObject);
		baseAction.setRequestMessage(requestMessage);
		
		return invocation.invoke();
	}
	
}
