package com.xinyuan.action;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.Action;
import com.xinyuan.dao.BaseDAO;
import com.xinyuan.dao.UserDAO;
import com.xinyuan.dao.impl.UserDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ResponseMessage;
import com.xinyuan.model.BaseOrderModel;
import com.xinyuan.model.LevelApp_6;
import com.xinyuan.model.User;
import com.xinyuan.util.ApnsPushNotificatioin;
import com.xinyuan.util.OrderApproveHelper;

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
		JsonElement objectElement = jsonObject.get(ConfigConstants.OBJECTS);
		String objectString = new Gson().toJson(objectElement);
		Map<String, Object> map = new Gson().fromJson(objectString, Map.class);

		List<BaseOrderModel> results = dao.read(model, map);
		
		message.object = results;
		message.status = ResponseMessage.STATUS_SUCCESS;
		
		return Action.NONE;
	}
	
	public String create() throws Exception {
		model.setCreateDate(new Date(System.currentTimeMillis()));
		Integer identifier = dao.create(model);
		
		message.status = ResponseMessage.STATUS_SUCCESS ;
		Map map = new HashMap();
		map.put(ConfigConstants.IDENTIFIER, identifier);
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
		
//		int id = model.getId();
		User user = (User)UserAction.sessionGet(ConfigConstants.SIGNIN_USER);
		OrderApproveHelper.approve(model, "Test user"/*user.getUsername()*/);  		// TODO: replace here
		
		// specified to notify who
		JsonElement forwardElement = jsonObject.get(ConfigConstants.APNS_FORWARDS);
		String forwards = forwardElement.getAsString();
		JsonElement apnsContents = jsonObject.get(ConfigConstants.APNS);
		String apnsString = new Gson().toJson(apnsContents);
		Map<String, Object> map = new Gson().fromJson(apnsString, Map.class);
		
		UserDAO userDAO = new UserDAOIMP();
		
		String apnsToken = userDAO.getUserApnsToken(forwards);  // "9ab941ea30f5cc4db41fc0a5dbbeae2dfe6a9d0f8c3bca1b97cc5c043aff6be0"
																// "14191179 b550d568 9692a340 95009826 67146cc6 37f5689b d360804f 8de4cd47"
		try {
			ApnsPushNotificatioin.push(map, apnsToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		message.status = ResponseMessage.STATUS_SUCCESS;
		return Action.NONE;
	}
	
	
	// Getter and Setter Methods
	
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
