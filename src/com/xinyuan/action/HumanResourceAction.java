package com.xinyuan.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.xinyuan.dao.BaseDAO;
import com.xinyuan.dao.UserDAO;
import com.xinyuan.dao.impl.BaseDAOIMP;
import com.xinyuan.dao.impl.HumanResourceDAOIMP;
import com.xinyuan.dao.impl.UserDAOIMP;
import com.xinyuan.message.ConstantsConfig;
import com.xinyuan.model.Approval.Approvals;
import com.xinyuan.model.HumanResource.Employee;
import com.xinyuan.model.User.User;
import com.xinyuan.util.JsonHelper;


public class HumanResourceAction extends SuperAction {
	
	@Override
	protected BaseDAO getDao() {
		return new HumanResourceDAOIMP();
	}
	
	public String create() throws Exception {
		super.create();
		
		if (models.size() != 1) return Action.NONE;		// Forbid create multi-
		
		Map<String, Object> map = JsonHelper.translateElementToMap(allJsonObject);
		List<String> passwordList = (List<String>)map.get(ConstantsConfig.PASSWORDS);
		
		for (int i = 0; i < models.size(); i++) {
			Object model = models.get(i);
			
			if (model instanceof Employee) {
				Employee newEmployee = (Employee)model;
				
				String password = passwordList.get(0);
				String username = newEmployee.getEmployeeNO();
				
				// create user
				User newUser = new User();
				newUser.setPassword(password);
				newUser.setUsername(username);
				
				// create approval
				Approvals approval = new Approvals();
				approval.setUsername(username);
				
				BaseDAO baseDAO = new BaseDAOIMP();
				baseDAO.create(newUser);
				baseDAO.create(approval);
			}
			
		}
		
		return Action.NONE;
	}
	
	@Override
	public String modify() throws Exception {
		super.modify();
		
		if (models.size() != 1) return Action.NONE;		// Forbid modified multi-
		
		Map<String, Object> map = JsonHelper.translateElementToMap(allJsonObject);
		List<String> passwordList = (List<String>)map.get(ConstantsConfig.PASSWORDS);
		
		for (int i = 0; i < models.size() && passwordList != null ; i++) {
			Object model = models.get(i);
			
			if (model instanceof Employee) {
				Employee employee = (Employee)model;
				
				String password = passwordList.get(0);
				String username = employee.getEmployeeNO();
				
				// modify user
				UserDAO userDao = new UserDAOIMP();
				User user = userDao.getUser(username);
				if (! employee.getWordMask().equals(password)) {
					user.setPassword(password);
					userDao.modify(user);
				}
			}
			
		}
		
		return Action.NONE;
		
		
	}
	
}
