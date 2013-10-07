package com.xinyuan.interceptor;

import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.modules.util.DLog;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sun.org.apache.regexp.internal.recompile;
import com.xinyuan.action.ActionBase;
import com.xinyuan.action.SettingAction;
import com.xinyuan.action.SuperAction;
import com.xinyuan.message.ConstantsConfig;
import com.xinyuan.message.ResponseMessage;
import com.xinyuan.util.JsonHelper;

public class PermissionInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		DLog.log(" Ready");
		
//		return invocation.invoke();  // TODO: FOR TEST

		
		// Get the permission the user have
		Map session = invocation.getInvocationContext().getSession();
		String[] permissions = (String[]) session.get(ConstantsConfig.PERMISSIONS);
		
		// Get the struts Action
		SuperAction superAction = (SuperAction)invocation.getAction();
		
		
		// Get the permission needed
		String method = JsonInterpretInterceptor.getContextMethod().trim();   // method needed
		JsonObject jsonObject = superAction.getAllJsonObject();
		JsonArray jsonArray = (JsonArray) jsonObject.get(ConstantsConfig.MODELS);
		List<String> models = JsonHelper.translateJsonArrayToList(jsonArray); // models needed
		
		
		// check if request super action
		boolean isAllowable = false;
		if (superAction.getClass() == SuperAction.class) {
			isAllowable = isCrossActions(models) ? checkPermission(method, models, permissions) : false;	// URL:Super__read, MODELS:["HumanResource.Employee","Finance.FinancePayWarrantOrder"]
		} else if (superAction.getClass() == SettingAction.class) {
			if (method.equals("read")) isAllowable = true;
		} else {
			String action = JsonInterpretInterceptor.getContextAction().trim();   // action
			isAllowable = checkPermission(action, method, models, permissions); 	// URL:HumanResource__read, MODELS:[".Employee",".EmplyeeOutOrder"]
		}
		
		
		if (isAllowable) return invocation.invoke();
		

		
		// write message
		ResponseMessage message = superAction.getMessage();
		message.description = ConstantsConfig.DENY;
		
		return Action.NONE;
		
	}
	
	
	
	
	
	
	
	
	

	/**
	 * 
	 * @param action	 	"HumanResource"	
	 * @param method		"read"
	 * @param models		the models user want to access , be careful with the <b> dot "."</b>, [".Employee",".EmplyeeOutOrder"]"
	 * @param permissions	the permission user had , ["HumanResource.Employee.read","HumanResource.Employee.create"]
	 * @return
	 * 
	 */
	public static boolean checkPermission(String action, String method, List<String> models, String[] permissions) {
		int throughCount = 0;
		int modelsSize = models.size();
		
		for (int i = 0; i < modelsSize; i++) {
			String model = models.get(i).substring(1);	// ".Employee" remove the '.'
			if (check(permissions, action, model, method)) throughCount++; 
		}
		
		return throughCount == modelsSize ;
	}
	
	/**
	 * 
	 * @param method		"read"
	 * @param models		["HumanResource.Employee","Finance.FinancePayWarrantOrder"]
	 * @param permissions	["HumanResource.Employee.read","HumanResource.Employee.create"]
	 * @return
	 */
	public static boolean checkPermission(String method, List<String> models, String[] permissions) {
		int throughCount = 0;
		int modelsSize = models.size();
		
		for (int i = 0; i < modelsSize; i++) {
			String[] modelCouple = models.get(i).split("\\.");	// "HumanResource.Employee"
			String action = modelCouple[0].trim();
			String model = modelCouple[1].trim();
			if (check(permissions, action, model, method)) throughCount++; 
		}
		
		return throughCount == modelsSize ;
	}
	
	/**
	 * 
	 * @param permissions		the permission user had , ["HumanResource.Employee.read","HumanResource.Employee.create"]
	 * @param action			be checked action
	 * @param model				be checked model
	 * @param method			be checked method
	 * @return
	 */
	private static boolean check(String[] permissions, String action, String model, String method) {
		for (String permission : permissions) {
			
			String[] subPermissions = permission.split("\\.");
			String sub0 = subPermissions[0].trim();
			String sub1 = subPermissions[1].trim();
			String sub2 = subPermissions[2].trim();
			
			if (sub0.equals(action) || sub0.equals("*")) { 			// TODO: REMOVE THE "*"
				if (sub2.equals(method) || sub2.equals("*")) {
					if (sub1.equals(model) || sub1.equals("*")) {
						return true;
					}
				}
						
			}
		}
		return false;
	}
	
	/**
	 * check if has the different action, such as HumanResource and Finance couple are cross
	 * @param models	MODELS:["HumanResource.Employee","Finance.FinancePayWarrantOrder"]
	 * @return
	 */
	private boolean isCrossActions(List<String> models) {
		int modelsSize = models.size();
		if (modelsSize < 2) return false;
		
		String compare = null;
		for (int i = 0; i < modelsSize; i++) {
			String[] modelCouple = models.get(i).split("\\.");	// "HumanResource.Employee"
			String action = modelCouple[0].trim();
			
			if (i == 0) {
				compare = action;
			} else {
				if (! compare.equals(action)) return true;
			}
		}
		
		return false;
	}
	
}
