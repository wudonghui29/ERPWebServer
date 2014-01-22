package com.xinyuan.action.command;

import com.xinyuan.Util.ApprovalHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.model.BaseOrder;



public class CommandApply extends CommandModify {

	
	@Override
	protected void handleApprovals(SuperDAO dao, String appKey, String forwardUser, BaseOrder persistence) throws Exception {
		ApprovalHelper.handlePendingApprovals(dao, appKey, forwardUser, persistence);
	}

}
