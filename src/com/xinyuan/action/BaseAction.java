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
	
	protected BaseDAO dao = getDao() ;							// for subclass initial
	protected abstract BaseDAO getDao() ;
	
	@Override
	public String execute() {
		return Action.NONE;
	}
	
	public String read() throws Exception {
		String json = request.getParameter(MessageConstants.JSON);
		JsonObject jsonObject = (JsonObject)(new JsonParser()).parse(json);
		
		JsonElement modelElement =  jsonObject.get(MessageConstants.MODELS);
		JsonElement objectElement = jsonObject.get(MessageConstants.OBJECTS);
			
		String model = modelElement.getAsString();
		String objectString = new Gson().toJson(objectElement);
		Map<String, Object> map = new Gson().fromJson(objectElement, Map.class);
		
		Class<?> modelClass = Class.forName(MessageConstants.MODELPACKAGE + "." + BaseAction.getActionNamePrefix() + model);
		Object object = new GsonBuilder().setDateFormat(MessageConstants.DATE_FORMAT).create().fromJson(objectString, modelClass);
		List<BaseOrderModel> results = dao.read((BaseOrderModel)object, map);
		
		message.object = results;
		message.status = ResponseMessage.STATUS_SUCCESS;
		
		return Action.NONE;
	}
	
	public String create() throws Exception {
		
		String json = request.getParameter(MessageConstants.JSON);
		JsonObject jsonObject = (JsonObject)(new JsonParser()).parse(json);
		
		JsonElement modelElement =  jsonObject.get(MessageConstants.MODELS);
		JsonElement objectElement = jsonObject.get(MessageConstants.OBJECTS);
			
		String model = modelElement.getAsString();
		String objectString = new Gson().toJson(objectElement);

		Class<?> modelClass = Class.forName(MessageConstants.MODELPACKAGE + "." + BaseAction.getActionNamePrefix() + model);
		Object object = new GsonBuilder().setDateFormat(MessageConstants.DATE_FORMAT).create().fromJson(objectString, modelClass);
//		BaseOrderModel orderModel = (BaseOrderModel)object;
//		orderModel.setCreateDate(createDate);
		
		Integer identifier = dao.create(object);
		
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
	
	
}
