package com.xinyuan.action;

import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.SharedOrderDAOIMP;

public class SharedOrderAction extends SuperAction {

	@Override
	protected SuperDAO getDao() {
		return new SharedOrderDAOIMP();
	}

	
}
