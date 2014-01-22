package com.xinyuan.action;

import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionSupport;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.message.RequestMessage;
import com.xinyuan.message.ResponseMessage;

public abstract class ActionBase extends ActionSupport {

	private static final long serialVersionUID = 1L;
	protected SuperDAO dao = getDao() ;
	protected abstract SuperDAO getDao() ;
	
	// ---------
	protected List<Object> models;				// vos
	protected List<Set<String>> modelsKeys;		// vos' keys
	
	// ---------
	protected RequestMessage requestMessage;
	protected ResponseMessage responseMessage;
	
	public List<Object> getModels() {
		return models;
	}
	public void setModels(List<Object> models) {
		this.models = models;
	}
	
	public List<Set<String>> getModelsKeys() {
		return modelsKeys;
	}
	public void setModelsKeys(List<Set<String>> modelsKeys) {
		this.modelsKeys = modelsKeys;
	}
	
	public RequestMessage getRequestMessage() {
		return requestMessage;
	}
	public void setRequestMessage(RequestMessage requestMessage) {
		this.requestMessage = requestMessage;
	}
	
	public ResponseMessage getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(ResponseMessage responseMessage) {
		this.responseMessage = responseMessage;
	}
	
}
