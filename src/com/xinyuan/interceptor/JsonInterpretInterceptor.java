package com.xinyuan.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.modules.Util.DLog;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.Util.JsonHelper;
import com.xinyuan.action.ActionBase;
import com.xinyuan.message.ConfigJSON;
import com.xinyuan.message.RequestMessage;

public class JsonInterpretInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
//		Action action = (Action)ActionContext.getContext().getActionInvocation().getAction();		// the same as follow
		ActionBase baseAction = (ActionBase)invocation.getAction();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String json = request.getParameter(ConfigJSON.JSON);
		RequestMessage requestMessage = JsonHelper.getGson().fromJson(json, RequestMessage.class);
		DLog.log("RequestMessage : " + requestMessage.toString());

		baseAction.setRequestMessage(requestMessage);
		
		return invocation.invoke();
	}
	
}
