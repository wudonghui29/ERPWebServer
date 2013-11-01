package com.xinyuan.action;

import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.BusinessDAOIMP;

public class BusinessAction extends SuperAction {

	@Override
	protected SuperDAO getDao() {
		return new BusinessDAOIMP();
	}
}
