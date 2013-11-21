package com.xinyuan.interceptor;


import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.modules.httpWriter.ResponseWriter;
import com.modules.util.DLog;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.action.ActionBase;
import com.xinyuan.message.ConstantsConfig;
import com.xinyuan.message.ResponseMessage;
import com.xinyuan.util.JsonHelper;

public class WriteMessageInterceptor extends AbstractInterceptor {
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionBase action = (ActionBase)invocation.getAction();
		ResponseMessage message = action.getMessage();
		String url = ServletActionContext.getRequest().getRequestURL().toString();
		message.action = url.substring(url.lastIndexOf("/") + 1);
		
		DLog.log("");
		
		Exception exceptionInvoke = null;
		try {
			invocation.invoke();
		} catch (Exception e) {
			exceptionInvoke = e;
			e.printStackTrace();
			
			String description = message.description == null || message.description.isEmpty() ? getDescription(e) : message.description;
			
			message.status = ConstantsConfig.STATUS_FAILED;
			message.description = description == null || description.isEmpty() ? ConstantsConfig.REQUEST_ERROR : description;
			message.objects = null;
			message.exception += (new Date()).toString() + " : " + e.getClass().getName() ;
		}
		
		
		
		// Write data to client
		
		try {
			String json = JsonHelper.getGson().toJson(message);
			ResponseWriter.write(json.getBytes("UTF-8"));
			DLog.log("Response JSON : " + json);
		} catch (IOException e) {
			exceptionInvoke = e;
			e.printStackTrace();
			DLog.log("ResponseWriter.write() Error Important!!! Cause DB Will Roll Back");
		} finally {
			ResponseWriter.close();
		}
		
		
		
		
		
		if (exceptionInvoke != null) {
//		if (true) {		 // for test transaction roll back 
			throw new RuntimeException(exceptionInvoke);
		}
		
		return Action.NONE;
	}
	
	
	
	
	
	/**
	 * 
	 * @param e private method
	 * @return	return the description
	 */
	private String getDescription(Exception e) {
		String message = e.getLocalizedMessage();
		
		StringBuilder messageBuilder = new StringBuilder();
		if (message != null) messageBuilder.append(message);
		
		Throwable cause = null;
		if ((cause = e.getCause()) != null) {
			String causeMessage = cause.getLocalizedMessage();
			if (causeMessage != null) messageBuilder.append(" | " + causeMessage);
		}
		
		return messageBuilder.toString().replaceAll("\\'", "|");
	}

}
