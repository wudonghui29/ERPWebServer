package com.xinyuan.action;

import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.CardsDAOIMP;

public class CardsAction extends SuperAction {

	@Override
	protected SuperDAO getDao() {
		return new CardsDAOIMP();
	}
}
