package com.xinyuan.interceptor;

import java.util.Map;

import com.modules.util.DLog;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.action.ActionBase;
import com.xinyuan.action.UserAction;
import com.xinyuan.dao.UserDAO;
import com.xinyuan.dao.impl.UserDAOIMP;
import com.xinyuan.message.ConstantsConfig;
import com.xinyuan.message.ResponseMessage;
import com.xinyuan.model.User;

public class AuthorizeInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		DLog.log(" Ready");
		
		// TODO: for test
		UserDAO userDAO = new UserDAOIMP();
		User userTest =  userDAO.getUser("mike");
//		invocation.getInvocationContext().getSession().put(ConfigConstants.SIGNIN_USER, userTest);
		String perssionStr = userTest.getPermissions();
		UserAction.sessionPut(ConstantsConfig.PERMISSIONS, perssionStr.split(","));
		UserAction.sessionPut(ConstantsConfig.SIGNIN_USER, userTest);
		
		
		
		
		Map session = invocation.getInvocationContext().getSession();
		User user = (User) session.get(ConstantsConfig.SIGNIN_USER);
		
		if (user != null) {
			return invocation.invoke();
		} else {
			
			ActionBase action = (ActionBase)invocation.getAction();
			ResponseMessage message = action.getMessage();
			message.description = ConstantsConfig.USER.UserNotSignIn;
			
			return Action.NONE;
		}
        
	}

}
