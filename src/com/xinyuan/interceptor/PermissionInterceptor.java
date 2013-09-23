package com.xinyuan.interceptor;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.modules.util.DLog;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.action.ActionBase;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ResponseMessage;

public class PermissionInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		DLog.log(" Ready");
		
		return invocation.invoke();  // TODO: FOR TEST

		
		/**

		// Get the permission needed
		String actionPrefix = ActionBase.getActionNamePrefix();   // action
		String actionSuffix = ActionBase.getActionNameSuffix();   // method
		String model = getModel().substring(1);	// model
//		String needPermission = actionPrefix + "." + model + "." + actionSuffix;
		
		// Get the permission the user have
		Map session = invocation.getInvocationContext().getSession();
		String[] permissions = (String[]) session.get(ConfigConstants.PERMISSIONS);
		
		// check it
		for (String permission : permissions) {
			
			String[] subPermissions = permission.split("\\.");
			
			if (subPermissions[0].equals(actionPrefix) || subPermissions[0].equals("*")) {  // TODO: FOR TEST 
				if (subPermissions[1].equals(model) || subPermissions[1].equals("*")) {
					if (subPermissions[2].equals(actionSuffix) || subPermissions[2].equals("*")) {
						DLog.log("Have the permission: " + permission);
						return invocation.invoke();
					}
				}
			}
//			if (permission.equals(needPermission)) return invocation.invoke();
		}
		
		// write message
		ActionBase action = (ActionBase)invocation.getAction();
		ResponseMessage message = action.getMessage(invocation);
		message.description = ConfigConstants.DENY;
		
		return Action.NONE;
		
		 **/
		
	}

	private String getModel() {
		String json = ServletActionContext.getRequest().getParameter(ConfigConstants.JSON);
		JsonObject jsonObject = (JsonObject)(new JsonParser()).parse(json);
		JsonElement modelElement =  jsonObject.get(ConfigConstants.MODELS);
		String model = modelElement.getAsString();
		return model;
	}
	
}
