package com.xinyuan.dao.impl;

import java.util.List;

import com.xinyuan.dao.HumanResourceDAO;

public class HumanResourceDAOIMP extends ModelDAOIMP implements HumanResourceDAO {

	@Override
	public List<Object> getUsersNOPairs() {
		String hqlString = "select employee.employeeNO , employee.name, employee.jobLevel ,employee.ownApproval,employee.department, employee.subDepartment from Employee as employee ";
		List<Object> results = super.getObjects(hqlString);
		return results;
	}

}
