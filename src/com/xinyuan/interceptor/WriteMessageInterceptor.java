package com.xinyuan.interceptor;


import java.util.Date;

import com.google.gson.Gson;
import com.modules.httpWriter.ResponseWriter;
import com.modules.util.DLog;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinyuan.action.ActionBase;
import com.xinyuan.message.ResponseMessage;

public class WriteMessageInterceptor extends AbstractInterceptor {
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionBase action = (ActionBase)invocation.getAction();
		ResponseMessage message = action.getMessage(invocation);
		
		DLog.log("WriteMessageInterceptor Ready");
		
		Exception exception = null;
		try {
			invocation.invoke();
		} catch (Exception e) {
			message.exception += (new Date()).toString() + ": \n" + e.toString() + "\n";
			StackTraceElement[] trace = e.getStackTrace();
            for (StackTraceElement traceElement : trace)
            	message.exception += ("\tat " + traceElement + "\n");   //TODO: For debug mode , in Production remove it
            		
			e.printStackTrace();
            exception = e;
		}
		
		try {
			String json = new Gson().toJson(message); 			// TODO: replace the password using regex
			ResponseWriter.write(json.getBytes("UTF-8"));
			DLog.log("Response JSON : " + json);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			ResponseWriter.close();
		}
		
//			if (exception != null) transaction roll back // TODO: roll back db session
		
		return Action.NONE;
	}

}
