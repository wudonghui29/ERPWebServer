package com.xinyuan.interceptor;

import java.util.ArrayList;
import java.util.List;

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
		
		
		List<JsonElement> ojects = new ArrayList<JsonElement>();
		List<Object> models = new ArrayList<Object>();
		
		JsonArray modelsArray = (JsonArray) jsonObject.get(ConstantsConfig.MODELS);			// MODELS
		JsonArray objectsArray = (JsonArray) jsonObject.get(ConstantsConfig.OBJECTS);		// OBJECTS
		
		if(modelsArray.size() != objectsArray.size()) return Action.NONE;
		
		for (int i = 0; i < modelsArray.size(); i++) {
			JsonElement modelElement = modelsArray.get(i);
			JsonElement objectElement = objectsArray.get(i);
			
			String modelString = modelElement.getAsString();
			String objectString = JsonHelper.getGson().toJson(objectElement);
			
			String className = ConstantsConfig.MODELPACKAGE + (superAction.getClass() == SuperAction.class ?  modelString : getContextAction() + modelString);
			Class<?> modelClass = Class.forName(className);
			Object model = JsonHelper.getGson().fromJson(objectString, modelClass);
			
			models.add(model);				// MODELS
			ojects.add(objectElement);		// OBJECTS
			
		}
		
		
		superAction.setModels(models);
		superAction.setObjects(ojects);
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
