package com.xinyuan.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.modules.util.DLog;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserAgentInterceptor extends AbstractInterceptor {
	
	private static final String USER_AGENT = "user-agent";
	
	private static final String CFNETWORK = "cfnetwork";
	
	private static final String DEVICE_IOS = "ios";
	
	private static final String APP_NAME = "ios";  // TODO: Add when the app name finally decided.
	

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		
		String userAgent = request.getHeader(USER_AGENT);
		
		DLog.log("User Agent: " + userAgent);
		
		boolean isEmpty = userAgent == null || userAgent.isEmpty();
		
		boolean isIOS = (userAgent.toLowerCase().contains(CFNETWORK) 
						|| userAgent.toLowerCase().contains(DEVICE_IOS)
						|| userAgent.toLowerCase().contains(APP_NAME));
		
		if (isEmpty || !isIOS) return Action.NONE;
		
		String result = invocation.invoke();
		
		return result;
	}

}
