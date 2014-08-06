package com.xinyuan.interceptor;


import j2se.modules.Helper.DLog;

import java.io.IOException;

import com.modules.HttpWriter.ResponseWriter;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.Util.GsonHelper;
import com.xinyuan.action.ActionBase;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.MessagesKeys;
import com.xinyuan.message.ResponseMessage;

public class WriteMessageInterceptor extends AbstractInterceptor {
	
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		Exception exception = null;
		try {
			invocation.invoke();
		} catch (Exception e) {
			exception = e;
			e.printStackTrace();
		}
		
		ActionBase action = (ActionBase)invocation.getAction();
		ResponseMessage responseMessage = action.getResponseMessage();
		
		if (exception != null) {
			responseMessage.status = ConfigConstants.STATUS_NEGATIVE;
			
			responseMessage.descriptions = getDescription(exception) ;
			
			if (responseMessage.descriptions == null || responseMessage.descriptions.isEmpty()) {
				responseMessage.descriptions = MessagesKeys.DEFAULT;
			}
			
			responseMessage.results = null;
			responseMessage.exception = exception.getClass().getName();
		}
		
		
		// Write data to client
		
		try {
			String json = GsonHelper.getGson().toJson(responseMessage);
			ResponseWriter.write(json.getBytes("UTF-8"));
			DLog.log("Response JSON : " + json);
		} catch (IOException e) {
			exception = e;
			e.printStackTrace();
			DLog.log("ResponseWriter.write() Error Important!!! Cause DB Will Roll Back");
		} finally {
			ResponseWriter.close();
		}
		
		
		
		
		
		if (exception != null) {
//		if (true) {		 // for test transaction roll back 
			throw new RuntimeException(exception);
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
		return messageBuilder.toString();
//		return messageBuilder.toString().replaceAll("\\'", "|");
	}

}
