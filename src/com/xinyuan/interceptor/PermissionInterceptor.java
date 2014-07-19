package com.xinyuan.interceptor;

import j2se.modules.Helper.DLog;

import java.util.List;
import java.util.Map;

import com.Global.SessionManager;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.action.ActionBase;
import com.xinyuan.action.SuperAction;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.RequestMessage;
import com.xinyuan.message.ResponseMessage;
import com.xinyuan.model.User.User;

public class PermissionInterceptor extends AbstractInterceptor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		@SuppressWarnings("unchecked")
		Map<String, Object> permissions = (Map<String, Object>) session.get(ConfigConstants.SIGNIN_USER_PERMISSIONS);
		
		// Get the struts Action
		ActionBase baseAction = (ActionBase)invocation.getAction();
		ResponseMessage responseMessage = baseAction.getResponseMessage();
		RequestMessage requestMessage = baseAction.getRequestMessage();
		
		// Get the permission needed
		String method = PermissionInterceptor.getContextMethod().trim();   		// A . method needeD  // TODO: Be careful of ActionModelBase's method !! important !!
		List<String> models = requestMessage.getMODELS(); 	// B. models needed
		
		responseMessage.models = models;
		
		
		// ok , check if administrator  	//TODO: For test , remove it , in production, give all the permission to administrator, or , just leave it?
		boolean isAdministrator = AdministratorInterceptor.isAdmin((User)SessionManager.get(ConfigConstants.SIGNIN_USER));
		if (isAdministrator) return invocation.invoke();	// ok , let it pass
		
		boolean isAllowable = false;
		
		// check if super action
		if (baseAction.getClass() == SuperAction.class) {
			isAllowable = isCrossActions(models) ? checkPermission(method, models, permissions) : false;	// URL:Super__read, MODELS:["HumanResource.Employee","Finance.Account"]
		// not the super action
		} else {
			String action = PermissionInterceptor.getContextAction().trim();   					// C. action needed
			isAllowable = checkPermission(action, method, models, permissions); 				// URL:HumanResource__read, MODELS:[".Employee",".EmplyeeOutOrder"]
		}
		
		// TODO : The 'apply' permission ... is not in user's permission
		if (isAllowable) return invocation.invoke();	// ok , let it pass
		
		responseMessage.denyStatus = ConfigConstants.STATUS_POSITIVE;
		
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
	private static boolean checkPermission(String action, String method, List<String> models, Map<String, Object> permissions) {

		if(!(  method.equals(ConfigConstants.METHOD_READ) || method.equals(ConfigConstants.METHOD_DELETE) )) return true;	// temporary check
		
		int throughCount = 0;
		int modelsSize = models.size();
		
		for (int i = 0; i < modelsSize; i++) {
			String model = models.get(i).substring(1);	// ".Employee" remove the '.'
			if (check(permissions, action, model, method)) {
				throughCount++; 
			}
		}
		
		return throughCount == modelsSize ;
	}
	
	/**
	 * This interface is for "Super" Action
	 * @param method		"read"
	 * @param models		[".HumanResource.Employee",".Finance.Account"]
	 * @param permissions	[".HumanResource.Employee.read",".HumanResource.Employee.create"]
	 * @return
	 */
	public static boolean checkPermission(String method, List<String> models, Map<String, Object> permissions) {
		int throughCount = 0;
		int modelsSize = models.size();
		
		try {
			
			for (int i = 0; i < modelsSize; i++) {
				String[] modelCouple = models.get(i).split("\\.");	// ".HumanResource.Employee"
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
		if(action.equals(ConfigConstants.CATEGORIE_APPROVAL) && method.equals(ConfigConstants.METHOD_READ)) return true;	// Let "read" the Approval package permission go through
		
		if(model.endsWith("Bill")){
			return true;
		}
		
		try {
			
			@SuppressWarnings("unchecked")
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
	 * @param models	MODELS:["HumanResource.Employee","Finance.Account"]
	 * @return
	 */
	private boolean isCrossActions(List<String> models) {
		int modelsSize = models.size();
		if (modelsSize < 2) return false;
		
		String compare = null;
		for (int i = 0; i < modelsSize; i++) {
			String[] modelCouple = models.get(i).split("\\.");	// ".HumanResource.Employee"
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
