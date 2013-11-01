package com.xinyuan.action;

import java.util.List;
import java.util.Set;

import com.google.gson.JsonObject;
import com.xinyuan.dao.SuperDAO;

public abstract class ActionModelBase extends ActionBase {

	protected SuperDAO dao = getDao() ;
	protected abstract SuperDAO getDao() ;
	
	
	protected JsonObject allJsonObject;
	
	protected List<Object> models;				// vos
	protected List<Set<String>> objectKeys;		// vos' keys
	
	
	public List<Object> getModels() {
		return models;
	}
	public void setModels(List<Object> models) {
		this.models = models;
	}
	
	public List<Set<String>> getObjectKeys() {
		return objectKeys;
	}
	public void setObjectKeys(List<Set<String>> objectKeys) {
		this.objectKeys = objectKeys;
	}
	
	public JsonObject getAllJsonObject() {
		return allJsonObject;
	}
	public void setAllJsonObject(JsonObject allJsonObject) {
		this.allJsonObject = allJsonObject;
	}

}
