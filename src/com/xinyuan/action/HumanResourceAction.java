package com.xinyuan.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.opensymphony.xwork2.Action;
import com.xinyuan.dao.BaseDAO;
import com.xinyuan.dao.impl.HumanResourceDAOIMP;
import com.xinyuan.message.MessageConstants;
import com.xinyuan.message.ResponseMessage;
import com.xinyuan.model.User;
import com.xinyuan.model.HumanResource.Employee;


public class HumanResourceAction extends BaseAction {
	
	@Override
	protected BaseDAO getDao() {
		return new HumanResourceDAOIMP();
	}
	
	public String create() throws Exception {
		super.create();
		if (message.status.equals(ResponseMessage.STATUS_SUCCESS)) {
			
			JsonElement objectElement = this.getJsonObject().get(MessageConstants.USERS);
			String objectString = new Gson().toJson(objectElement);
			
			Class<?> modelClass = Class.forName(MessageConstants.MODELPACKAGE + ".User");
			Object object = new GsonBuilder().setDateFormat(MessageConstants.DATE_FORMAT).create().fromJson(objectString, modelClass);

			// be careful , check the username == employeeNO
			if(((User)object).getUsername() == ((Employee)this.getModel()).getEmployeeNO()) dao.create(object);
			else message.description = MessageConstants.USER.UserCreateFailed;
		}
		
		return Action.NONE;
	}
}
