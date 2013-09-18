package com.xinyuan.interceptor;

import java.util.Map;

import com.modules.util.DLog;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.action.BaseAction;
import com.xinyuan.action.UserAction;
import com.xinyuan.message.MessageConstants;
import com.xinyuan.message.ResponseMessage;
import com.xinyuan.model.User;

public class AuthorizeInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		DLog.log("AuthorizeInterceptor Ready");
		
		//TODO: For test
		String result = invocation.invoke();
		return result;
		
		/**
		Map session = invocation.getInvocationContext().getSession();
		User user = (User) session.get(UserAction.SIGNIN_USER);
		
		if (user != null) {
			String result = invocation.invoke();
			return result;
		} else {
			
			ActionBase action = (ActionBase)invocation.getAction();
			ResponseMessage message = action.getMessage();
			message.description = MessageConstants.User.UserNotSignIn;
			
			return Action.NONE;
		}
		**/
        
	}

}
