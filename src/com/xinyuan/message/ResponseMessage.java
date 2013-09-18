package com.xinyuan.message;



public class ResponseMessage {
	
	public static final String STATUS_SUCCESS = "1";
	public static final String STATUS_FAILED = "0";
	public static final String NONE = "";
	
	public String status = STATUS_FAILED;   // 0 for failed , 1 for success
	public String action = NONE;
	public String description = NONE;
	public String exception = NONE;
	
	public Object object = null;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	
}
