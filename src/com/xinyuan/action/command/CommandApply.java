package com.xinyuan.action.command;

import com.xinyuan.Util.AppModelsHelper;
import com.xinyuan.Util.ApprovalsDAOHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.model.BaseOrder;



public class CommandApply extends CommandModify {

	
	@Override
	protected void handleApprovals(SuperDAO dao, String appKey, String forwardUser, BaseOrder persistence) throws Exception {
		// Handle The Pending Approvals
		ApprovalsDAOHelper.handlePendingApprovals(dao, appKey, forwardUser, persistence);
		
		// Handle The Final Approval Stuff
		String finalAppKey = AppModelsHelper.getFinalApprovalKey(persistence);
		if (finalAppKey != null && finalAppKey.equals(appKey)) {
			handleFinalApprovalProcess(dao, persistence);
		}
	}

	
	
	protected void handleFinalApprovalProcess(SuperDAO dao, Object persistence) throws Exception {} 
}
