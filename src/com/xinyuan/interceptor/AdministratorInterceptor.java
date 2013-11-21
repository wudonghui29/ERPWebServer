package com.xinyuan.interceptor;

import com.modules.util.DLog;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.message.ConstantsConfig;
import com.xinyuan.model.User.User;

public class AdministratorInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		DLog.log("");
		
		User user = (User)ActionContext.getContext().getSession().get(ConstantsConfig.SIGNIN_USER);
		if (isAdmin(user)) return invocation.invoke();
		
		return Action.NONE;
	}
	
	public static boolean isAdmin(User user) {
		return user.getId() <= 0 ;		// TODO: [CompanyName]TMD[1-9] for < 0 , xinyuanTMD for = 0
	}

}
