package com.xinyuan.action.command.category;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import com.modules.Introspector.IntrospectHelper;
import com.modules.Introspector.ModelIntrospector;
import com.modules.Util.FileHelper;
import com.xinyuan.Util.AppCryptoHelper;
import com.xinyuan.action.command.CommandApply;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.model.HumanResource.Employee;
import com.xinyuan.model.HumanResource.EmployeeCHOrder;
import com.xinyuan.model.User.User;

public class HumanResourceCommandApply extends CommandApply {

	@Override
	protected void handleFinalApprovalProcess(SuperDAO dao, Object persistence) throws Exception {
		super.handleFinalApprovalProcess(dao, persistence);
		
		SuperDAOIMP daoimp = (SuperDAOIMP)dao;
		
		
		//  EmployeeCHOrder
		if (persistence instanceof EmployeeCHOrder) {
			EmployeeCHOrder employeeCHOrder = (EmployeeCHOrder)persistence;
			Employee employee = (Employee) daoimp.getObject(Employee.class, "employeeNO", employeeCHOrder.getEmployeeNO());
			
			for (PropertyDescriptor pd : Introspector.getBeanInfo(employeeCHOrder.getClass()).getPropertyDescriptors()) {
				if (pd.getReadMethod() != null && !IntrospectHelper.isClassPropertyName(pd.getName())) {
					
					String propertyNameNew = pd.getName() ;
					if (propertyNameNew.indexOf("_N") >=0 ) {
						Object newValue = ModelIntrospector.getProperty(employeeCHOrder, propertyNameNew);
						if (newValue == null) continue;
						
						String propertyNames[] = propertyNameNew.split("_");
						String originPropertyName = propertyNames[0];
						ModelIntrospector.setProperty(employee, originPropertyName, newValue);
					}
				}
			}
			// modify the information
			dao.modify(employee);
			
			// modify the password
			if (employeeCHOrder.getPassword_N() != null && !employeeCHOrder.getPassword_N().trim().equals("")) {
				User user = (User)daoimp.getObject(User.class, "username", employee.getEmployeeNO());
				String password = employeeCHOrder.getPassword_N();
				user.setPassword(AppCryptoHelper.encode(password));
				dao.modify(user);
				
				// For test now
				ConfigConstants.userInfomationProperties.setProperty(user.getId()+"", password);
				FileHelper.saveProperties(ConfigConstants.userInfomations_PropertiesPath, ConfigConstants.userInfomationProperties);
			}
		}
		
		
		
		
		
		
	}

	
}
