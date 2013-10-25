package com.xinyuan.message;



public class ResponseMessage {
	
	public String status = ConstantsConfig.STATUS_FAILED;   // 0 for failed , 1 for success
	public String action = ConstantsConfig.NONE;
	public String description = ConstantsConfig.NONE;
	public String exception = ConstantsConfig.NONE;
	
	public Object object = null;
	
	public String models ;
	
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
	public String getModels() {
		return models;
	}
	public void setModels(String models) {
		this.models = models;
	}
	
}
