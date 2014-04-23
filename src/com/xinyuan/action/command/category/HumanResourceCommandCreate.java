package com.xinyuan.action.command.category;

import com.modules.Util.FileHelper;
import com.xinyuan.Util.AppCryptoHelper;
import com.xinyuan.action.command.CommandCreate;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.RequestMessage;
import com.xinyuan.message.ResponseMessage;
import com.xinyuan.model.Approval.Approvals;
import com.xinyuan.model.HumanResource.Employee;
import com.xinyuan.model.User.User;

public class HumanResourceCommandCreate extends CommandCreate {

	@Override
	protected boolean handleModelBeforeCreate(SuperDAO dao, Object model, int index, ResponseMessage responseMessage, RequestMessage requestMessage) throws Exception {
		
		// forbid multi create Employee
		if (model instanceof Employee) if (index != 0) return false;
		
		return super.handleModelBeforeCreate(dao, model, index, responseMessage, requestMessage);
	}

	@Override
	protected void handleModelAfterCreate(SuperDAO dao, Object model, int index, ResponseMessage responseMessage, RequestMessage requestMessage) throws Exception {
		super.handleModelAfterCreate(dao, model, index, responseMessage, requestMessage);
		
		// When Create Employee , create the user and approvals
		if (model instanceof Employee) {
			Employee employee = (Employee)model;
			String password = employee.getWordMask();
			String employeeNO = employee.getEmployeeNO();
			
			// create user
			User user = new User();
			user.setPassword(AppCryptoHelper.encode(password));
			user.setUsername(employeeNO);
			
			// For test now
			ConfigConstants.userInfomationProperties.setProperty(user.getId()+"", password);
			FileHelper.saveProperties(ConfigConstants.userInfomations_PropertiesPath, ConfigConstants.userInfomationProperties);
			
			// create approval
			Approvals approval = new Approvals();
			approval.setEmployeeNO(employeeNO);
			
			// save to database
			SuperDAO withoutSubClassDAO = new SuperDAOIMP();
			withoutSubClassDAO.create(user);
			withoutSubClassDAO.create(approval);
			
			// modify the employee wordMask property
			employee.setWordMask(password.replaceAll("\\w", "*"));
			dao.modify(employee);
		}
		
	}

	
}
