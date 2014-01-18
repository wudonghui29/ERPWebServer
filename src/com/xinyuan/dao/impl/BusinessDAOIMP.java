package com.xinyuan.dao.impl;

import java.util.List;

import com.xinyuan.dao.BusinessDAO;

public class BusinessDAOIMP extends ModelDAOIMP implements BusinessDAO {

	@Override
	public List<Object> getClientsNOPairs() {
		String hqlString = "select client.number , client.name from Client as client ";
		return super.getObjects(hqlString);
	}

	
}
