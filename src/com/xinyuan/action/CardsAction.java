package com.xinyuan.action;

import com.xinyuan.dao.BaseDAO;
import com.xinyuan.dao.impl.CardsDAOIMP;

public class CardsAction extends SuperAction {

	@Override
	protected BaseDAO getDao() {
		return new CardsDAOIMP();
	}
}
