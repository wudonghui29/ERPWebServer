package com.xinyuan.action;

import com.xinyuan.dao.BaseDAO;
import com.xinyuan.dao.impl.SecurityDAOIMP;

public class SecurityAction extends SuperAction {

	@Override
	protected BaseDAO getDao() {
		return new SecurityDAOIMP();
	}
	

//	public String switchInform() throws Exception {
//		
//		return Action.NONE;
//	}
	
}
