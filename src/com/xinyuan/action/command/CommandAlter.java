package com.xinyuan.action.command;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xinyuan.Util.ApnsHelper;
import com.xinyuan.Util.AppModelsHelper;
import com.xinyuan.Util.ParametersHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ConfigJSON;
import com.xinyuan.message.RequestMessage;
import com.xinyuan.message.ResponseMessage;
import com.xinyuan.model.BaseOrder;
import com.xinyuan.model.IApp;

public abstract class CommandAlter implements Command {

	@Override
	public void execute(SuperDAO dao, ResponseMessage responseMessage, RequestMessage requestMessage, List<Object> models, List<Set<String>> modelsKeys) throws Exception {
		
		List<String> forwardsList = requestMessage.getAPNS_FORWARDS();
		List<Map<String, String>> identityList = requestMessage.getIDENTITYS();	
		List<Map<String, Object>> forwardsContents = requestMessage.getAPNS_CONTENTS();
		
		for (int i = 0; i < models.size(); i++) {
			Object model = models.get(i);
			Set<String> modelkeys = modelsKeys.get(i);
			
			// get the persistence by identities
			Object persistence = AppModelsHelper.getPersistenceByUniqueKeyValue(dao, identityList.get(i), model.getClass());
			// subclass, handle the persistence
			handlePersistence(dao, model, modelkeys, persistence);
			
			// apns & pendings
			if (persistence instanceof BaseOrder) {
				String forwardUser = forwardsList != null && forwardsList.size() > i ? forwardUser = forwardsList.get(i) : null;
				if (forwardUser == null) {
					if (persistence instanceof IApp) {
						forwardUser = ((IApp) persistence).getForwardUser();
					}
				}
				// subclass, handle the pending approvals
				handleApprovals(dao, ParametersHelper.getParameter(requestMessage, ConfigJSON.APPLEVEL), forwardUser, (BaseOrder)persistence);
			}
			
		}
		
		ApnsHelper.infromApns(forwardsList, forwardsContents);
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
	}
	
	
	protected abstract void handlePersistence(SuperDAO dao, Object model, Set<String> modelkeys, Object persistence) throws Exception ;
	protected abstract void handleApprovals(SuperDAO dao, String appKey, String forwardUser, BaseOrder persistence) throws Exception ;
	
}


