package com.xinyuan.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.modules.util.DLog;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.action.ActionBase;
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
		JsonObject jsonObject = (JsonObject)(new JsonParser()).parse(json);
		DLog.log(json);
		
		
		List<JsonElement> ojects = new ArrayList<JsonElement>();
		List<List<String>> options = new ArrayList<List<String>>();
		List<BaseOrderModel> models = new ArrayList<BaseOrderModel>();
		
		JsonArray modelsArray = (JsonArray) jsonObject.get(ConstantsConfig.MODELS);			// MODELS
		JsonArray objectsArray = (JsonArray) jsonObject.get(ConstantsConfig.OBJECTS);		// OBJECTS
		JsonArray optionsArray = (JsonArray) jsonObject.get(ConstantsConfig.FIELDS);		// OPTIONS
		
		if(modelsArray.size() != objectsArray.size()) return Action.NONE;
		
		for (int i = 0; i < modelsArray.size(); i++) {
			JsonElement modelElement = modelsArray.get(i);
			JsonElement objectElement = objectsArray.get(i);
			
			String modelString = modelElement.getAsString();
			String objectString = JsonHelper.getGson().toJson(objectElement);
			
			String className = ConstantsConfig.MODELPACKAGE + (superAction.getClass() == SuperAction.class ?  modelString : getContextAction() + modelString);
			Class<?> modelClass = Class.forName(className);
			BaseOrderModel model = (BaseOrderModel)JsonHelper.getGson().fromJson(objectString, modelClass);
			
			models.add(model);				// MODELS
			ojects.add(objectElement);		// OBJECTS
			
			// if have OPTIONS
			if (optionsArray != null) {
				JsonArray optionArray = optionsArray.get(i).getAsJsonArray();
				List<String> subOptions = JsonHelper.translateJsonArrayToList(optionArray);
				options.add(subOptions);	// OPTIONS
			}
			
			
		}
		
		
		superAction.setModels(models);
		superAction.setObjects(ojects);
		if (options.size() == models.size()) superAction.setOptions(options);
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
