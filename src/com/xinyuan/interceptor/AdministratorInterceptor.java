package com.xinyuan.interceptor;

import com.modules.util.DLog;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.model.User;

public class AdministratorInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		DLog.log(" Ready");
		
		if (isAdmin()) return invocation.invoke();
		
		return Action.NONE;
	}
	
	public static boolean isAdmin() {
		return true ; // TODO: for test
//		return ((User)ActionContext.getContext().getSession().get(MessageConstants.SIGNIN_USER)).getId() == 0 ;
	}

}
