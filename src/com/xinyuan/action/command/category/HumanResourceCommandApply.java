package com.xinyuan.action.command.category;

import com.xinyuan.Util.AppCryptoHelper;
import com.xinyuan.Util.AppModelsHelper;
import com.xinyuan.action.command.CommandApply;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.dao.impl.UserDAOIMP;
import com.xinyuan.model.Approval.Approvals;
import com.xinyuan.model.HumanResource.Employee;
import com.xinyuan.model.HumanResource.EmployeeCHOrder;
import com.xinyuan.model.User.User;

public class HumanResourceCommandApply extends CommandApply {

	@Override
	protected void handleFinalApprovalProcess(SuperDAO dao, Object persistence) throws Exception {
		super.handleFinalApprovalProcess(dao, persistence);
		
		SuperDAOIMP daoImp = (SuperDAOIMP)dao;
		
		// When Create Employee , create the user and approvals
		if (persistence instanceof Employee) {
			Employee employee = (Employee) persistence;
			String password = employee.getWordMask();
			String employeeNO = employee.getEmployeeNO();

			// create user
			User user = new User();
			String decryptPassword = AppCryptoHelper.decodeWithRSA(password);
			user.setPassword(AppCryptoHelper.encodeWithMD5(decryptPassword));
			user.setUsername(employeeNO);

			// For test now
//			FileHelper.saveKeyValueToPropertiesFile( ConfigConstants.userInfomations_PropertiesPath, user.getId() + "", decryptPassword);

			// create approval
			Approvals approval = new Approvals();
			approval.setEmployeeNO(employeeNO);

			// save to database
            if (! new UserDAOIMP().isSignup(user.getUsername())) {      // for old version
                SuperDAO withoutSubClassDAO = new SuperDAOIMP();
                withoutSubClassDAO.create(user);
                withoutSubClassDAO.create(approval);
            }

			// modify the employee wordMask property
			employee.setWordMask(decryptPassword.replaceAll("\\w", "*") + "**");
			dao.modify(employee);
		} else
		
		//  EmployeeCHOrder
		if (persistence instanceof EmployeeCHOrder) {
			EmployeeCHOrder employeeCHOrder = (EmployeeCHOrder)persistence;
			Employee employee = (Employee) daoImp.getObject(Employee.class, "employeeNO", employeeCHOrder.getEmployeeNO());
			
			// modify the information
			AppModelsHelper.copyNewValueToPersistence(employeeCHOrder, employee);
			dao.modify(employee);
			
			// modify the password
			if (employeeCHOrder.getPassword_N() != null && !employeeCHOrder.getPassword_N().trim().equals("")) {
				User user = (User)daoImp.getObject(User.class, "username", employee.getEmployeeNO());
				String password = employeeCHOrder.getPassword_N();
				String decryptPassword = AppCryptoHelper.decodeWithRSA(password);
				user.setPassword(AppCryptoHelper.encodeWithMD5(decryptPassword));
				dao.modify(user);
				
				employeeCHOrder.setPassword_N(decryptPassword.replaceAll("\\w", "*") + "***");
				dao.modify(employeeCHOrder);
				
				// For test now
//				FileHelper.saveKeyValueToPropertiesFile(ConfigConstants.userInfomations_PropertiesPath, user.getId() + "", decryptPassword);
			}
		}
		
		
		
		
		
		
	}

	
}
