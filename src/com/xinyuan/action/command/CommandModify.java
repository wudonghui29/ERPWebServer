package com.xinyuan.action.command;

import java.util.Set;

import com.modules.Introspector.ModelIntrospector;
import com.modules.Util.CollectionHelper;
import com.xinyuan.Util.ApprovalHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.model.BaseOrder;

public class CommandModify extends CommandAlter {

	@Override
	protected void handlePersistence(SuperDAO dao, Object model, Set<String> modelkeys, Object persistence) throws Exception {
		CollectionHelper.removeStartWithElement(modelkeys, ConfigConstants.APP_PREFIX);
		ModelIntrospector.copyVoToPo(model, persistence, modelkeys);
		dao.modify(persistence);
	}

	@Override
	protected void handleApprovals(SuperDAO dao, String appKey, String forwardUser, BaseOrder persistence) throws Exception {
		ApprovalHelper.handlePendingApprovals(dao, null, forwardUser, persistence);
	}

	
}
