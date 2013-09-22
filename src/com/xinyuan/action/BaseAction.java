package com.xinyuan.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;
import com.opensymphony.xwork2.Action;
import com.xinyuan.dao.BaseDAO;
import com.xinyuan.dao.UserDAO;
import com.xinyuan.dao.impl.UserDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ResponseMessage;
import com.xinyuan.model.BaseOrderModel;
import com.xinyuan.model.User;
import com.xinyuan.util.ApnsPushNotificatioin;
import com.xinyuan.util.JsonHelper;
import com.xinyuan.util.OrderHelper;

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
		Map<String, Object> map = JsonHelper.translateElementToMap(jsonObject.get(ConfigConstants.OBJECTS));

		List<BaseOrderModel> results = dao.read(model, map);
		
		message.object = results;
		message.status = ResponseMessage.STATUS_SUCCESS;
		
		return Action.NONE;
	}
	
	public String create() throws Exception {
		OrderHelper.setOrderBasicCreateDetail(model);
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
		UserDAO userDAO = new UserDAOIMP();
		String forwardUsername = jsonObject.get(ConfigConstants.APNS_FORWARDS).getAsString();
		User forwardUser = userDAO.getUser(forwardUsername);
		User approveUser = (User)UserAction.sessionGet(ConfigConstants.SIGNIN_USER);
		String approveUsername = approveUser.getUsername();
		
		model = dao.read(model);
		OrderHelper.approve(model, approveUsername); 
		model.setForwardUser(forwardUser);
		dao.modify(model);
		
		String orderNO = model.getOrderNO();
		
		OrderHelper.addPendingApprove(forwardUser, orderNO);
		userDAO.modify(forwardUser);
		
		OrderHelper.deletePendingApprove(approveUser, orderNO);
		userDAO.modify(approveUser);
		
		// specified to notify who
		Map<String, Object> map = JsonHelper.translateElementToMap(jsonObject.get(ConfigConstants.APNS));
		// "9ab941ea30f5cc4db41fc0a5dbbeae2dfe6a9d0f8c3bca1b97cc5c043aff6be0","14191179 b550d568 9692a340 95009826 67146cc6 37f5689b d360804f 8de4cd47"
		String apnsToken = userDAO.getUserApnsToken(forwardUser.getUsername());  
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
