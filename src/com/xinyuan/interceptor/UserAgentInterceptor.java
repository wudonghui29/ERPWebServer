package com.xinyuan.interceptor;

import j2se.modules.Helper.DLog;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserAgentInterceptor extends AbstractInterceptor {
	
	private static final long serialVersionUID = 1L;

	private static final String USER_AGENT = "user-agent";
	
	private static final String CFNETWORK = "cfnetwork";
	
//	private static final String DEVICE_IOS = "ios";
	
	private static final String APP_NAME = "ERP";
	

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
						&& (userAgent.contains(APP_NAME))
						);
		
		if (!isIOSAPP) return Action.NONE;
		
		return invocation.invoke();
	}

}
