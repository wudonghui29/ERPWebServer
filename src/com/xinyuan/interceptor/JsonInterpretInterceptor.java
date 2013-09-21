package com.xinyuan.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.modules.util.DLog;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.action.ActionBase;
import com.xinyuan.action.BaseAction;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.model.BaseOrderModel;

public class JsonInterpretInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		DLog.log(" Ready");
		
		BaseAction action = (BaseAction)invocation.getAction();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String json = request.getParameter(ConfigConstants.JSON);
//		DLog.log(json);
		JsonObject jsonObject = (JsonObject)(new JsonParser()).parse(json);
		
		JsonElement modelElement =  jsonObject.get(ConfigConstants.MODELS);
		JsonElement objectElement = jsonObject.get(ConfigConstants.OBJECTS);
			
		String modelString = modelElement.getAsString();
		String objectString = new Gson().toJson(objectElement);
		
		Class<?> modelClass = Class.forName(ConfigConstants.MODELPACKAGE + "." + ActionBase.getActionNamePrefix() + modelString);
		Object model = new GsonBuilder().setDateFormat(ConfigConstants.DATE_FORMAT).create().fromJson(objectString, modelClass);
		
		action.setJsonObject(jsonObject);
		action.setModel((BaseOrderModel)model);
		
		return invocation.invoke();
	}

}
