package com.xinyuan.interceptor;

import j2se.modules.Helper.DLog;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.Util.GsonHelper;
import com.xinyuan.action.ActionBase;
import com.xinyuan.message.ConfigJSON;
import com.xinyuan.message.RequestMessage;
import com.xinyuan.message.ResponseMessage;

public class JsonInterpretInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
//		Action action = (Action)ActionContext.getContext().getActionInvocation().getAction();		// the same as follow
		ActionBase baseAction = (ActionBase)invocation.getAction();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String json = request.getParameter(ConfigJSON.JSON);
//		json = new String(new BASE64Decoder().decodeBuffer(json));
		DLog.log("Json String : " + json);
		
		RequestMessage requestMessage = GsonHelper.getGson().fromJson(json, RequestMessage.class);
		baseAction.setRequestMessage(requestMessage);
		
		ResponseMessage responseMessage = new ResponseMessage();
		baseAction.setResponseMessage(responseMessage);
		
		String url = ServletActionContext.getRequest().getRequestURL().toString();
		responseMessage.action = url.substring(url.lastIndexOf("/") + 1);
		responseMessage.models = requestMessage.getMODELS();
		
//		DLog.log("RequestMessage : " + requestMessage.toString());
		
		return invocation.invoke();
	}
	
}
