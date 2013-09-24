package com.xinyuan.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.xinyuan.message.ResponseMessage;

public class ActionBase extends ActionSupport {

	protected HttpServletRequest request = ServletActionContext.getRequest();  	// for subclass
	protected ResponseMessage message = new ResponseMessage();					// for subclass
	

	public ResponseMessage getMessage() {						// for intercepter
		return message;
	}
	
}
