package com.xinyuan.action;

import java.util.Map;

import com.google.gson.JsonElement;
import com.opensymphony.xwork2.Action;
import com.xinyuan.dao.BaseDAO;
import com.xinyuan.dao.impl.HumanResourceDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.model.UserApproval;
import com.xinyuan.model.User;
import com.xinyuan.model.HumanResource.Employee;
import com.xinyuan.util.JsonHelper;


public class HumanResourceAction extends SuperAction {
	
	@Override
	protected BaseDAO getDao() {
		return new HumanResourceDAOIMP();
	}
	
	public String create() throws Exception {
		super.create();
		
		Object model = models.get(0);
		if (model instanceof Employee) {
			Employee newEmployee = (Employee)model;
			
			Map<String, Object> map = JsonHelper.translateElementToMap(allJsonObject);
			
			String password = (String)map.get(ConfigConstants.PASSWORDS);
			String username = newEmployee.getEmployeeNO();
			
			User newUser = new User();
			newUser.setPassword(password);
			newUser.setUsername(username);
			
			UserApproval approval = new UserApproval();
			approval.setUsername(username);
			
			dao.create(newUser);
			dao.create(approval);
			
		}
		
		return Action.NONE;
	}
}
