package com.xinyuan.interceptor;

import j2se.modules.Helper.DLog;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.model.User.User;

public class AdministratorInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		DLog.log("");
		
		if (currentUserIsAdministrator()) return invocation.invoke();
		
		return Action.NONE;
	}
	
	public static boolean currentUserIsAdministrator() {
		User user = (User)ActionContext.getContext().getSession().get(ConfigConstants.SIGNIN_USER);
		return isAdmin(user);
	}
	
	public static boolean isAdmin(User user) {
		return user.getId() <= 0 ;		// TODO: [CompanyName]TMD[1-9] for < 0 , xinyuanTMD for = 0
	}

}
