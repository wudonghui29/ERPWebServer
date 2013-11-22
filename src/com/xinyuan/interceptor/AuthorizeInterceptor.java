package com.xinyuan.interceptor;

import com.Global.SessionManager;
import com.modules.Util.DLog;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.Config.ConfigConstants;
import com.xinyuan.Config.ResponseMessage;
import com.xinyuan.action.ActionBase;
import com.xinyuan.model.User.User;

/**
 *	Check Is Login Or Not
 */

public class AuthorizeInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		DLog.log("");
		
		User user = (User) SessionManager.get(ConfigConstants.SIGNIN_USER);
		
		if (user != null) {
			return invocation.invoke();
		} else {
			
			ActionBase action = (ActionBase)invocation.getAction();
			ResponseMessage message = action.getMessage();
			message.description = ConfigConstants.USER.UserNotSignIn;
			
			return Action.NONE;
		}
        
	}

}
