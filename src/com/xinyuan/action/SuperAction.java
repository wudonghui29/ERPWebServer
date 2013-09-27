package com.xinyuan.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.Action;
import com.xinyuan.dao.BaseDAO;
import com.xinyuan.dao.BaseModelDAO;
import com.xinyuan.dao.UserDAO;
import com.xinyuan.dao.impl.BaseDAOIMP;
import com.xinyuan.dao.impl.UserDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ResponseMessage;
import com.xinyuan.model.BaseOrderModel;
import com.xinyuan.model.User;
import com.xinyuan.util.ApnsHelper;
import com.xinyuan.util.ApprovalHelper;
import com.xinyuan.util.JsonHelper;
import com.xinyuan.util.OrderHelper;

public class SuperAction extends ActionModelBase {
	
	protected JsonObject allJsonObject;
	
	protected List<Object> models;
	protected List<JsonElement> objects;
	protected List<List<String>> options;
	
	@Override
	protected BaseDAO getDao() {
		return new BaseDAOIMP();
	}

	
	@Override
	public String execute() {
		return Action.NONE;
	}
	
	public String read() throws Exception {
		
		List<List<Object>> results = new ArrayList<List<Object>>();
		
		for (int i = 0; i < models.size(); i++) {
			
			Object model = models.get(i);
			JsonElement object = objects.get(i);
			
			Map<String, Object> map = JsonHelper.translateElementToMap(object);
			
			Set<String> keys = map.keySet();
			
			
			List<Object> result = null;
			if (options == null) {
				result = dao.read(model, keys);
			} else {
				List<String> fields = options.get(i);
				result = dao.read(model, keys, fields);
			}
			
			
			results.add(result);
		}
		
		
		message.object = results;
		message.status = ResponseMessage.STATUS_SUCCESS;
		
		return Action.NONE;
	}
	
	public String create() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid create multi-
		BaseOrderModel model = (BaseOrderModel) models.get(0);
		
		OrderHelper.setOrderBasicCreateDetail(model);
		Integer identifier = (Integer) dao.create(model);
		
		message.status = ResponseMessage.STATUS_SUCCESS ;
		Map map = new HashMap();
		map.put(ConfigConstants.IDENTIFIER, identifier);
		map.put(ConfigConstants.ORDERNO, model.getOrderNO());
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
		if (models.size() != 1) return Action.NONE;		// Forbid create multi-
		BaseOrderModel model = (BaseOrderModel) models.get(0);
		model = ((BaseModelDAO)dao).read(model);
		
		String forwardUsername = allJsonObject.get(ConfigConstants.APNS_FORWARDS).getAsString();
		String approveUsername = ((User)UserAction.sessionGet(ConfigConstants.SIGNIN_USER)).getUsername();
		
		
//		if (!model.getForwardUser().equals(approveUsername)) DLog.log("not same , ask for leave???"); // TODO:
		boolean isAllApproved = OrderHelper.approve(model, approveUsername);  // TODO: Handle Exception
		
		model.setForwardUser(forwardUsername);
		dao.modify(model);
		
		String orderNO = model.getOrderNO();
		String modelType = OrderHelper.getModelType(model);
		
		ApprovalHelper.addPendingApprove(forwardUsername, orderNO, modelType);
		ApprovalHelper.deletePendingApprove(approveUsername, orderNO, modelType);
		
		// specified to notify who
		String[] apnsTokens = ApprovalHelper.getAPNSToken(forwardUsername).split(",");
		Map<String, Object> map = JsonHelper.translateElementToMap(allJsonObject.get(ConfigConstants.APNS));
		ApnsHelper.push(map, apnsTokens);
		
		message.status = ResponseMessage.STATUS_SUCCESS;
		return Action.NONE;
	}

	
	// Getter and Setter Methods
	
	public List<Object> getModels() {
		return models;
	}

	public void setModels(List<Object> models) {
		this.models = models;
	}

	public List<JsonElement> getObjects() {
		return objects;
	}

	public void setObjects(List<JsonElement> objects) {
		this.objects = objects;
	}
	
	public List<List<String>> getOptions() {
		return options;
	}


	public void setOptions(List<List<String>> options) {
		this.options = options;
	}


	public JsonObject getAllJsonObject() {
		return allJsonObject;
	}

	public void setAllJsonObject(JsonObject allJsonObject) {
		this.allJsonObject = allJsonObject;
	}

}
