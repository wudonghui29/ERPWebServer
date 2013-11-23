package com.xinyuan.dao.impl;

import java.util.List;

import com.xinyuan.dao.BusinessDAO;

public class BusinessDAOIMP extends ModelDAOIMP implements BusinessDAO {

	@Override
	public List getClientsNOPairs() {
		String hqlString = "select client.clientNO , client.clientName from Client as client ";
		return super.getObjects(hqlString);
	}

	
}
