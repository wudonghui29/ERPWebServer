package com.xinyuan.action;

import com.opensymphony.xwork2.ActionSupport;
import com.xinyuan.message.ResponseMessage;

public abstract class ActionBase extends ActionSupport {

	protected ResponseMessage message = new ResponseMessage();					// for subclass

	public ResponseMessage getMessage() {						// for intercepter
		return message;
	}
	
	
	
}
