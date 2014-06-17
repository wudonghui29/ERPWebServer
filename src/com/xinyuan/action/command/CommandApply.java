package com.xinyuan.action.command;

import com.Global.SessionManager;
import com.xinyuan.Util.AppModelsHelper;
import com.xinyuan.Util.ApprovalsDAOHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.model.BaseOrder;
import com.xinyuan.model.User.User;



public class CommandApply extends CommandModify {

	
	@Override
	protected void handleApprovals(SuperDAO dao, String appKey, String forwardUser, BaseOrder persistence) throws Exception {
		
		// Check and Handle The Final Approval Stuff
		if (AppModelsHelper.isFinalApproval(appKey, persistence)) {
			String signinedUser = ((User)SessionManager.get(ConfigConstants.SIGNIN_USER)).getUsername();
			ApprovalsDAOHelper.deletePendingApprove(signinedUser, persistence);
			handleFinalApprovalProcess(dao, persistence);
			
			// Handle The Pending Approvals
		} else {
			
			ApprovalsDAOHelper.handlePendingApprovals(dao, appKey, forwardUser, persistence);
		}
	}

	
	
	protected void handleFinalApprovalProcess(SuperDAO dao, Object persistence) throws Exception {} 
}
