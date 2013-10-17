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
import com.modules.util.DLog;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.action.SuperAction;
import com.xinyuan.message.ConstantsConfig;
import com.xinyuan.model.BaseOrderModel;
import com.xinyuan.util.JsonHelper;

public class JsonInterpretInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		DLog.log(" Ready");
		
		SuperAction superAction = (SuperAction)invocation.getAction();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String json = request.getParameter(ConstantsConfig.JSON);
		DLog.log(json);
		JsonObject jsonObject = (JsonObject)(new JsonParser()).parse(json);
		
		
		List<Set<String>> voKeys = new ArrayList<Set<String>>();
		List<Object> vos = new ArrayList<Object>();
		
		JsonArray modelsArray = (JsonArray) jsonObject.get(ConstantsConfig.MODELS);			// MODELS
		JsonArray objectsArray = (JsonArray) jsonObject.get(ConstantsConfig.OBJECTS);		// OBJECTS
		
		if(modelsArray.size() != objectsArray.size()) {
			DLog.log("Reject : models.size not match objects.size");
			return Action.NONE;
		}
		
		for (int i = 0; i < modelsArray.size(); i++) {
			JsonElement modelElement = modelsArray.get(i);
			JsonElement objectElement = objectsArray.get(i);
			
			// get model
			String modelString = modelElement.getAsString();
			String objectString = JsonHelper.getGson().toJson(objectElement);
			String className = ConstantsConfig.MODELPACKAGE + (superAction.getClass() == SuperAction.class ?  modelString : getContextAction() + modelString);
			Class<?> modelClass = Class.forName(className);
			Object vo = JsonHelper.getGson().fromJson(objectString, modelClass);
			
			// get keys
			Map<String, Object> map = JsonHelper.translateElementToMap(objectElement);
			Set<String> keys = map.keySet();
			
			vos.add(vo);				// MODELS
			voKeys.add(keys);			// KEYS
		}
		
		
		superAction.setModels(vos);
		superAction.setObjectKeys(voKeys);
		superAction.setAllJsonObject(jsonObject);
		
		
		return invocation.invoke();
	}
	
	public static String getContextName() {
		return ActionContext.getContext().getName();
	}
	public static String getContextAction() {
		return getContextName().split("__")[0];
	}
	public static String getContextMethod() {
		return getContextName().split("__")[1];
	}

}
