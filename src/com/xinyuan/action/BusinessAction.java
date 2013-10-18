package com.xinyuan.action;

import com.xinyuan.dao.BaseDAO;
import com.xinyuan.dao.impl.BusinessDAOIMP;

public class BusinessAction extends SuperAction {

	@Override
	protected BaseDAO getDao() {
		return new BusinessDAOIMP();
	}
}
