package com.xinyuan.dao.impl;

import java.util.List;

import com.xinyuan.dao.BusinessDAO;

public class BusinessDAOIMP extends ModelDAOIMP implements BusinessDAO {

	@Override
	public List getClientsNOPairs() {
		String hqlString = "select businessClient.clientNO , businessClient.clientName from BusinessClient as businessClient ";
		return super.getObjects(hqlString);
	}

	
}
