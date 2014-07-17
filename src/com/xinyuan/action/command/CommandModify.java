package com.xinyuan.action.command;

import j2se.modules.Introspector.ObjectIntrospector;

import java.util.Set;

import com.xinyuan.Util.ApprovalsDAOHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.model.BaseOrder;

public class CommandModify extends CommandAlter {

	
	@Override
	protected void handlePersistence(SuperDAO dao, Object model, Set<String> modelkeys, Object persistence) throws Exception {
	    if (modelkeys.size() != 0) {
	        ObjectIntrospector.copyVoToPo(model, persistence, modelkeys);
	        dao.modify(persistence);
        }
	}

	
	@Override
	protected void handleApprovals(SuperDAO dao, String appKey, String forwardUser, BaseOrder persistence) throws Exception {
		ApprovalsDAOHelper.handlePendingApprovals(dao, null, forwardUser, persistence);
	}

	
}
