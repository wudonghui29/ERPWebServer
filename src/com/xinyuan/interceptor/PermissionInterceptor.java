package com.xinyuan.interceptor;

import java.util.List;
import java.util.Map;

import com.Global.SessionManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.modules.Util.DLog;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.Util.JsonHelper;
import com.xinyuan.action.ActionModelBase;
import com.xinyuan.action.SuperAction;
import com.xinyuan.message.ConstantsConfig;
import com.xinyuan.message.ResponseMessage;
import com.xinyuan.model.User.User;

public class PermissionInterceptor extends AbstractInterceptor {
	
//	private static List<String> methods = new ArrayList<String>();
//	{
//		methods.add(ConstantsConfig.METHOD_READ);
//		methods.add(ConstantsConfig.METHOD_CREATE);
//		methods.add(ConstantsConfig.METHOD_MODIFY);
//		methods.add(ConstantsConfig.METHOD_DELETE);
//		methods.add(ConstantsConfig.METHOD_APPLY);
//	}
	
	public static String getContextName() {
		return ActionContext.getContext().getName();
	}
	public static String getContextAction() {
		return getContextName().split("__")[0];
	}
	public static String getContextMethod() {
		return getContextName().split("__")[1];
	}
	

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		DLog.log("");

		// Get the permission the user have
		Map session = invocation.getInvocationContext().getSession();
		Map<String, Object> permissions = (Map<String, Object>)session.get(ConstantsConfig.PERMISSIONS);
		
		// Get the struts Action
		ActionModelBase baseAction = (ActionModelBase)invocation.getAction();
		ResponseMessage message = baseAction.getMessage();
		
		// Get the permission needed
		String method = PermissionInterceptor.getContextMethod().trim();   		// A . method needeD  // TODO: Be careful of ActionModelBase's method !! important !!
		JsonObject jsonObject = baseAction.getAllJsonObject();
		JsonArray jsonArray = (JsonArray) jsonObject.get(ConstantsConfig.MODELS);
		List<String> models = JsonHelper.translateJsonArrayToList(jsonArray); 	// B. models needed
		
		message.models = models;
		
		
		// ok , check if administrator  	//TODO: For test , remove it , in production, give all the permission to administrator, or , just leave it?
		boolean isAdministrator = AdministratorInterceptor.isAdmin((User)SessionManager.get(ConstantsConfig.SIGNIN_USER));
		if (isAdministrator) return invocation.invoke();	// ok , let it pass
		
		boolean isAllowable = false;
		
		// check if super action
		if (baseAction.getClass() == SuperAction.class) {
			isAllowable = isCrossActions(models) ? checkPermission(method, models, permissions) : false;	// URL:Super__read, MODELS:["HumanResource.Employee","Finance.FinancePayWarrantOrder"]
		// not the super action
		} else {
			String action = PermissionInterceptor.getContextAction().trim();   // C. action needed
			isAllowable = checkPermission(action, method, models, permissions); 			// URL:HumanResource__read, MODELS:[".Employee",".EmplyeeOutOrder"]
		}
		
		if (isAllowable) return invocation.invoke();	// ok , let it pass
		
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
	public static boolean checkPermission(String action, String method, List<String> models, Map<String, Object> permissions) {
		int throughCount = 0;
		int modelsSize = models.size();
		
		for (int i = 0; i < modelsSize; i++) {
			String model = models.get(i).substring(1);	// ".Employee" remove the '.'
			if (check(permissions, action, model, method)) throughCount++; 
		}
		
		return throughCount == modelsSize ;
	}
	
	/**
	 * This interface is for "Super" Action
	 * @param method		"read"
	 * @param models		[".HumanResource.Employee",".Finance.FinancePayWarrantOrder"]
	 * @param permissions	[".HumanResource.Employee.read",".HumanResource.Employee.create"]
	 * @return
	 */
	public static boolean checkPermission(String method, List<String> models, Map<String, Object> permissions) {
		int throughCount = 0;
		int modelsSize = models.size();
		
		try {
			
			for (int i = 0; i < modelsSize; i++) {
				String[] modelCouple = models.get(i).split("\\.");	// "HumanResource.Employee"
				String action = modelCouple[1].trim();				// "HumanResource"
				String model = modelCouple[2].trim();				// "Employee"
				if (check(permissions, action, model, method)) throughCount++; 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return throughCount == modelsSize ;
	}
	
	/**
	 * 
	 * @param permissions		the permissions signined user had
	 * @param action			be checked action
	 * @param model				be checked model
	 * @param method			be checked method
	 * @return
	 */
	private static boolean check(Map<String, Object> permissions, String action, String model, String method) {
		if(action.equals(ConstantsConfig.CATEGORIE_APPROVAL) && method.equals(ConstantsConfig.METHOD_READ)) return true;	// Let "read" the Approval package permission go through
		
//		if (! methods.contains(method)) return true;		// for SecurityAction.inform ... TODO: ...
		
		try {
			
			Map<String, List<String>> modelsPermissions = (Map<String, List<String>>)permissions.get(action);
			if (modelsPermissions == null) return false;
			List<String> methodsPermissions = modelsPermissions.get(model);
			
			for (int i = 0; i < methodsPermissions.size(); i++) {
				if (methodsPermissions.get(i).equals(method)) return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
			String action = modelCouple[1].trim();
			
			if (i == 0) {
				compare = action;
			} else {
				if (! compare.equals(action)) return true;
			}
		}
		
		return false;
	}
	
}
