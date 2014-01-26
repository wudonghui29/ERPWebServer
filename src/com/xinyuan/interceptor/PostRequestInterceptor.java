package com.xinyuan.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class PostRequestInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	private static final String POST = "POST";
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
//		DLog.log("");
		
//		PrintHelper.printServeletRequestHeader();
		
        if (! request.getMethod().equalsIgnoreCase(POST)) return Action.NONE;
        
        String result = invocation.invoke();
        
        return result;
	}

}
