package com.xinyuan.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.action.ActionBase;
import com.xinyuan.action.UserAction;
import com.xinyuan.dao.UserDAO;
import com.xinyuan.dao.impl.UserDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ResponseMessage;
import com.xinyuan.model.User;

public class AuthorizeInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		// TODO: for test
//		UserDAO userDAO = new UserDAOIMP();
//		invocation.getInvocationContext().getSession().put(ConfigConstants.SIGNIN_USER, userDAO.getUser("Mike"));
		
		Map session = invocation.getInvocationContext().getSession();
		User user = (User) session.get(ConfigConstants.SIGNIN_USER);
		
		if (user != null) {
			String result = invocation.invoke();
			return result;
		} else {
			
			ActionBase action = (ActionBase)invocation.getAction();
			ResponseMessage message = action.getMessage(invocation);
			message.description = ConfigConstants.USER.UserNotSignIn;
			
			return Action.NONE;
		}
        
	}

}
