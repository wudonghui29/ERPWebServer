package com.xinyuan.action;

import com.opensymphony.xwork2.Action;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.ApprovalDAOIMP;

public class ApprovalAction extends SuperAction {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected SuperDAO getDao() {
		return new ApprovalDAOIMP();
	}

	@Override
	public String create() throws Exception {
		return Action.NONE;
	}

	@Override
	public String modify() throws Exception {
		return Action.NONE;
	}

	@Override
	public String delete() throws Exception {
		return Action.NONE;
	}

	@Override
	public String apply() throws Exception {
		return Action.NONE;
	}
	
	
	
}
