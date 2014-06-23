package com.xinyuan.action.command;

import com.xinyuan.Util.AppModelsHelper;
import com.xinyuan.Util.ApprovalsDAOHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.model.BaseOrder;



public class CommandApply extends CommandModify {

	
	@Override
	protected void handleApprovals(SuperDAO dao, String appKey, String forwardUser, BaseOrder persistence) throws Exception {
		
		// Check and Handle The Final Approval Stuff
		if (AppModelsHelper.isFinalApproval(appKey, persistence)) {
			handleFinalApprovalProcess(dao, persistence);
		} 
			
		// Handle The Pending Approvals
		ApprovalsDAOHelper.handlePendingApprovals(dao, appKey, forwardUser, persistence);
		
	}

	
	
	protected void handleFinalApprovalProcess(SuperDAO dao, Object persistence) throws Exception {} 
}
