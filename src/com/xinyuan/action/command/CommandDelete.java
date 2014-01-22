package com.xinyuan.action.command;

import java.util.Set;

import com.modules.Introspector.IntrospectHelper;
import com.xinyuan.Util.ApprovalHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.model.BaseOrder;
import com.xinyuan.model.IApp;

public class CommandDelete extends CommandAlter {

	@Override
	protected void handlePersistence(SuperDAO dao, Object model, Set<String> modelkeys, Object persistence) throws Exception {
		dao.delete(persistence);
		
		// check if delete successfully
		if (dao.readUnique(persistence, IntrospectHelper.getAllProperties(persistence)) != null) throw new Exception();
		
	}

	@Override
	protected void handleApprovals(SuperDAO dao, String appKey, String forwardUser, BaseOrder persistence) throws Exception {
		if (persistence instanceof IApp) {
			ApprovalHelper.deletePendingApprove(((IApp)persistence).getForwardUser(), persistence);
		}
	}

	
}
