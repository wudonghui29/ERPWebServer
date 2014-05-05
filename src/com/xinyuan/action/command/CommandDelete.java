package com.xinyuan.action.command;

import java.util.Set;

import com.modules.Introspector.IntrospectHelper;
import com.opensymphony.xwork2.ActionContext;
import com.xinyuan.Util.ApprovalsDAOHelper;
import com.xinyuan.action.ActionBase;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.message.MessagesKeys;
import com.xinyuan.model.BaseOrder;
import com.xinyuan.model.IApp;

public class CommandDelete extends CommandAlter {

	@Override
	protected void handlePersistence(SuperDAO dao, Object model, Set<String> modelkeys, Object persistence) throws Exception {
		dao.delete(persistence);
		
		((ActionBase)ActionContext.getContext().getActionInvocation().getAction()).getResponseMessage().descriptions = MessagesKeys.DES;
		
		// check if delete successfully
		if (dao.readUnique(persistence, IntrospectHelper.getAllProperties(persistence)) != null) throw new Exception();
	}

	@Override
	protected void handleApprovals(SuperDAO dao, String appKey, String forwardUser, BaseOrder persistence) throws Exception {
		if (persistence instanceof IApp) ApprovalsDAOHelper.deletePendingApprove(((IApp)persistence).getForwardUser(), persistence);
	}

	
}
