package com.xinyuan.interceptor;

import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.modules.util.DLog;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.action.ActionBase;
import com.xinyuan.action.SuperAction;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ResponseMessage;

public class PermissionInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		DLog.log(" Ready");
		
//		return invocation.invoke();  // TODO: FOR TEST

		
		
		// Get the permission the user have
		Map session = invocation.getInvocationContext().getSession();
		String[] permissions = (String[]) session.get(ConfigConstants.PERMISSIONS);
		
		// Get the permission needed
		String actionPrefix = JsonInterpretInterceptor.getContextAction().trim();   // action
		String methodSuffix = JsonInterpretInterceptor.getContextMethod().trim();   // method
		SuperAction action = (SuperAction)invocation.getAction();
		JsonObject jsonObject = action.getAllJsonObject();
		JsonArray models = (JsonArray) jsonObject.get(ConfigConstants.MODELS);	// all models
		
		
		boolean isAllowable = checkPermission(actionPrefix, methodSuffix, models, permissions); ;
		if (isAllowable) return invocation.invoke();
		

		// write message
		ResponseMessage message = action.getMessage();
		message.description = ConfigConstants.DENY;
		
		return Action.NONE;
		
	}

	/**
	 * 
	 * @param action	 	"HumanResource"
	 * @param method		"read"
	 * @param models		the models user want to access , be careful with the <b> dot "."</b>, "[.Employee,.EmplyeeOutOrder]"
	 * @param permissions	the permission user had , "HumanResource.Employee.read,HumanResource.Employee.create"
	 * @return
	 * 
	 */
	public static boolean checkPermission(String action, String method, JsonArray models, String[] permissions) {
		int throughCount = 0;
		int modelsSize = models.size();
		
		for (int i = 0; i < modelsSize; i++) {
		
			for (String permission : permissions) {
		
				String[] subPermissions = permission.split("\\.");
				String sub0 = subPermissions[0].trim();
				String sub1 = subPermissions[1].trim();
				String sub2 = subPermissions[2].trim();
				
				if (sub0.equals(action) || sub0.equals("*")) { 			// TODO: REMOVE THE "*"
					if (sub2.equals(method) || sub2.equals("*")) {
		
							String model = models.get(i).getAsString().substring(1);
							
							if (sub1.equals(model) || sub1.equals("*")) {
								throughCount ++;
								break;
							}
						}
							
					}
		
				}
		}
		
		return throughCount == modelsSize ;
	}
	
	
	
}
