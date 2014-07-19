package com.xinyuan.interceptor;

import j2se.modules.Helper.DLog;

import com.Global.SessionManager;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.action.ActionBase;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.MessagesKeys;
import com.xinyuan.message.ResponseMessage;
import com.xinyuan.model.User.User;

/**
 *	Check Is Login Or Not
 */

public class AuthorizeInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		DLog.log("");
		
		User user = (User) SessionManager.get(ConfigConstants.SIGNIN_USER);
		
		if (user != null) {
			return invocation.invoke();
		} else {
			
			ActionBase action = (ActionBase)invocation.getAction();
			ResponseMessage message = action.getResponseMessage();
			message.descriptions = MessagesKeys.USER.UserNotSignIn;
			
			return Action.NONE;
		}
        
	}

}
