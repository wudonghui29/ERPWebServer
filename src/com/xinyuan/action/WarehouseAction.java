package com.xinyuan.action;

import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.WarehouseDAOIMP;

public class WarehouseAction extends SuperAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected SuperDAO getDao() {
		return new WarehouseDAOIMP();
	}

	
}
