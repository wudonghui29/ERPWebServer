package com.xinyuan.dao.impl;

import java.util.Set;

import com.xinyuan.dao.HumanResourceDAO;
import com.xinyuan.model.BaseOrderModel;
import com.xinyuan.model.HumanResource.Employee;

public class HumanResourceDAOIMP extends BaseModelDAOIMP implements HumanResourceDAO {

	@Override
	protected void setUniqueResultKeys(BaseOrderModel model, Set<String> keys) {
		super.setUniqueResultKeys(model, keys);
		
		if (model.getClass() == Employee.class) {			// TODO: How to automatically get the unique column in Hibernate models?? check it out!!!
			Employee employee = (Employee) model;
			if (employee.getEmployeeNO() != null) keys.add("employeeNO"); 
		}
	}

}
