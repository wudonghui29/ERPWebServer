package com.xinyuan.action;

import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.HumanResourceDAOIMP;


public class HumanResourceAction extends SuperAction {
	private static final long serialVersionUID = 1L;

	@Override
	protected SuperDAO getDao() { return new HumanResourceDAOIMP(); }

}
