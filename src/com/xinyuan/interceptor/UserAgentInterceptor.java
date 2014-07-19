package com.xinyuan.interceptor;

import j2se.modules.Helper.DLog;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.Global.SessionManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.Util.GsonHelper;
import com.xinyuan.dao.UserDAO;
import com.xinyuan.dao.impl.UserDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.model.User.User;

public class UserAgentInterceptor extends AbstractInterceptor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String USER_AGENT = "user-agent";
	
	private static final String CFNETWORK = "cfnetwork";
	
//	private static final String DEVICE_IOS = "ios";
	
	private static final String APP_NAME = "XinYuanERP";
	
	private static final String APP_TEST_NAME = "SteelERP";
	

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		
		String userAgent = request.getHeader(USER_AGENT);
		
		DLog.log("User Agent: " + userAgent);
		
		boolean isUserAgentEmpty = userAgent == null || userAgent.isEmpty();
		
		if (isUserAgentEmpty) return Action.NONE;
		
		boolean isIOSAPP = (
						userAgent.toLowerCase().contains(CFNETWORK) 		// in Simulator : User Agent: XinYuanERP/1.0 CFNetwork/672.0.2 Darwin/12.5.0
//						&& userAgent.toLowerCase().contains(DEVICE_IOS)		// TODO : check it in device
						&& (userAgent.contains(APP_NAME) || userAgent.contains(APP_TEST_NAME))
						);
		
		if (!isIOSAPP) return Action.NONE;
		
		
		
		
		// TODO: for test, remove it in production
		if(userAgent.contains(APP_TEST_NAME)) {
			DLog.log("In Test Mode (select the user) : ");
			UserDAO userDAO = new UserDAOIMP();
			User userTest =  userDAO.getUser("xinyuanTMD");
//		invocation.getInvocationContext().getSession().put(ConfigConstants.SIGNIN_USER, userTest);
			Map<String, Object> permissions = GsonHelper.translateJsonStringToMap(userTest.getPermissions());
			SessionManager.put(ConfigConstants.SIGNIN_USER_PERMISSIONS, permissions);
			SessionManager.put(ConfigConstants.SIGNIN_USER, userTest);
		}
		
		
		
		
		return invocation.invoke();
	}

}
