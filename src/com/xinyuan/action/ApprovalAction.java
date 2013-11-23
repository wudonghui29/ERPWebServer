package com.xinyuan.action;

import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.ApprovalDAOIMP;

public class ApprovalAction extends SuperAction {
	
	
	@Override
	protected SuperDAO getDao() {
		return new ApprovalDAOIMP();
	}
	
}
