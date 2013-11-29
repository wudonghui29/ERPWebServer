package com.xinyuan.action;

import com.opensymphony.xwork2.ActionSupport;
import com.xinyuan.message.ResponseMessage;

public abstract class ActionBase extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected ResponseMessage responseMessage = new ResponseMessage();					// for subclass use
	
	public ResponseMessage getResponseMessage() {										// for intercepter and ApnsHelper
		return responseMessage;
	}
	
}
