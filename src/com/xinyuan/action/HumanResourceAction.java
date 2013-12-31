package com.xinyuan.action;

import com.opensymphony.xwork2.Action;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.HumanResourceDAOIMP;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.model.Approval.Approvals;
import com.xinyuan.model.HumanResource.Employee;
import com.xinyuan.model.User.User;


public class HumanResourceAction extends SuperAction {
	private static final long serialVersionUID = 1L;

	@Override
	protected SuperDAO getDao() { return new HumanResourceDAOIMP(); }
	
	@Override
	public String create() throws Exception {
		
		if (models.size() != 1) return Action.NONE;		// Forbid create multi-
		
		for (int i = 0; i < models.size(); i++) {
			Object model = models.get(i);
			
			if (model instanceof Employee) {
				Employee newEmployee = (Employee)model;
				
				String password = newEmployee.getWordMask();
				String employeeNO = newEmployee.getEmployeeNO();
				
				// create user
				User newUser = new User();
				newUser.setPassword(password);
				newUser.setUsername(employeeNO);
				newUser.setPermissions(ConfigConstants.DEFAULT_PERMISSION);
				
				// create approval
				Approvals approval = new Approvals();
				approval.setEmployeeNO(employeeNO);
				
				SuperDAO baseDAO = new SuperDAOIMP();
				baseDAO.create(newUser);
				baseDAO.create(approval);
				
				String wordMask = password.replaceAll("\\w", "*");
				newEmployee.setWordMask(wordMask);
			}
		}
		
		return super.create();
	}
	
}
