package com.xinyuan.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opensymphony.xwork2.Action;
import com.xinyuan.dao.BaseDAO;
import com.xinyuan.message.MessageConstants;
import com.xinyuan.message.ResponseMessage;
import com.xinyuan.model.BaseOrderModel;

public abstract class BaseAction extends ActionBase {
	
	private JsonObject jsonObject;		// for JsonInterpretInterceptor
	private BaseOrderModel model;		// for JsonInterpretInterceptor
	
	protected BaseDAO dao = getDao() ;							// for subclass initial
	protected abstract BaseDAO getDao() ;
	
	@Override
	public String execute() {
		return Action.NONE;
	}
	
	public String read() throws Exception {
		JsonElement objectElement = jsonObject.get(MessageConstants.OBJECTS);
		String objectString = new Gson().toJson(objectElement);
		Map<String, Object> map = new Gson().fromJson(objectString, Map.class);

		List<BaseOrderModel> results = dao.read(model, map);
		
		message.object = results;
		message.status = ResponseMessage.STATUS_SUCCESS;
		
		return Action.NONE;
	}
	
	public String create() throws Exception {
//		model.setCreateDate(createDate);
		
		Integer identifier = dao.create(model);
		
		message.status = ResponseMessage.STATUS_SUCCESS ;
		Map map = new HashMap();
		map.put(MessageConstants.IDENTIFIER, identifier);
		message.object = map;
		
		return Action.NONE;
	}
	
	public String modify() throws Exception {
		return Action.NONE;
	}
	
	public String delete() throws Exception {
		return Action.NONE;
	}
	
	public String apply() throws Exception {
		return Action.NONE;
	}

	
	
	public JsonObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JsonObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public BaseOrderModel getModel() {
		return model;
	}

	public void setModel(BaseOrderModel model) {
		this.model = model;
	}
	
}
