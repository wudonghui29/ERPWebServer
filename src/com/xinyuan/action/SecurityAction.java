package com.xinyuan.action;

import com.xinyuan.dao.BaseDAO;
import com.xinyuan.dao.SecurityDAOIMP;

public class SecurityAction extends BaseAction {

	@Override
	protected BaseDAO getDao() {
		return new SecurityDAOIMP();
	}
	
}
