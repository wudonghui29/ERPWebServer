package com.xinyuan.action;

import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.BusinessDAOIMP;

public class BusinessAction extends SuperAction {
	private static final long serialVersionUID = 1L;

	@Override
	protected SuperDAO getDao() {
		return new BusinessDAOIMP();
	}
}
