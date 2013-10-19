package com.xinyuan.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.modules.util.DLog;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class PostRequestInterceptor extends AbstractInterceptor {

	private static final String POST = "POST";
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		DLog.log("Request URL : " + request.getRequestURL());
		
		
//		Enumeration<String> enumeration = request.getHeaderNames();
//		while (enumeration.hasMoreElements()) {
//			String name = (String) enumeration.nextElement();
//			String value = request.getHeader(name);
//			System.out.println(name + " : " + value);
//		}
//		
//		HttpSession session = ServletActionContext.getRequest().getSession();
//		String sessionID = session.getId();
//		System.out.println("\n session id -> " + sessionID);
		
		
//		Enumeration<String> enumeration = request.getParameterNames();
//		while (enumeration.hasMoreElements()) {
//			String name = (String) enumeration.nextElement();
//			String value = request.getParameter(name);
//			System.out.println(name + " - " + value);
//		}
		
		
		
        if (! request.getMethod().equalsIgnoreCase(POST)) return Action.NONE;
        
        String result = invocation.invoke();
        
        return result;
	}

}
