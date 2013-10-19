package com.xinyuan.dao.impl;

import java.util.List;
import java.util.Set;

import com.xinyuan.dao.HumanResourceDAO;
import com.xinyuan.model.HumanResource.Employee;

public class HumanResourceDAOIMP extends ModelDAOIMP implements HumanResourceDAO {

	@Override
	protected void setUniqueResultKeys(Object model, Set<String> keys) {
		if (model.getClass() == Employee.class)  if (((Employee) model).getEmployeeNO() != null) keys.add("employeeNO");
		// TODO: How to automatically get the unique column in Hibernate models?? check it out!!!		// In Method.class , getDeclaredAnnotations , check it out
	}

	@Override
	public List getNameNOPairs() {
		String hqlString = "select employee.employeeNO , employee.name from Employee as employee ";
		return super.getObjects(hqlString);
	}

}
