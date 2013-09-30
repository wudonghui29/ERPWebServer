package com.xinyuan.action;

import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xinyuan.dao.BaseDAO;
import com.xinyuan.model.BaseOrderModel;

public abstract class ActionModelBase extends ActionBase {

	protected BaseDAO dao = getDao() ;
	protected abstract BaseDAO getDao() ;
	
	
	protected JsonObject allJsonObject;
	protected List<BaseOrderModel> models;
	protected List<JsonElement> objects;
	
	
	public List<BaseOrderModel> getModels() {
		return models;
	}
	
	
	public void setModels(List<BaseOrderModel> models) {
		this.models = models;
	}
	

	public List<JsonElement> getObjects() {
		return objects;
	}


	public void setObjects(List<JsonElement> objects) {
		this.objects = objects;
	}
	
	public JsonObject getAllJsonObject() {
		return allJsonObject;
	}

	public void setAllJsonObject(JsonObject allJsonObject) {
		this.allJsonObject = allJsonObject;
	}

}
