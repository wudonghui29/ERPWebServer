package com.xinyuan.action;

import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.VehicleDAOIMP;

public class VehicleAction extends SuperAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected SuperDAO getDao() {
		return new VehicleDAOIMP();
	}

}
