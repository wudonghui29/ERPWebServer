package com.xinyuan.action.command;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.modules.Util.DLog;
import com.xinyuan.Util.ApnsHelper;
import com.xinyuan.Util.OrderHelper;
import com.xinyuan.Util.ParametersHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.UserDAO;
import com.xinyuan.dao.impl.UserDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ConfigJSON;
import com.xinyuan.message.RequestMessage;
import com.xinyuan.message.ResponseMessage;
import com.xinyuan.model.BaseOrder;

public abstract class CommandAlter implements Command {

	@Override
	public void execute(SuperDAO dao, ResponseMessage responseMessage, RequestMessage requestMessage, List<Object> models, List<Set<String>> modelsKeys) throws Exception {
		if (models.size() != 1) return ;		// Forbid multi-
		
		List<Map<String, String>> identityList = requestMessage.getIDENTITYS();	
		
		UserDAO userDAO = new UserDAOIMP();
		List<String> forwardsList = requestMessage.getAPNS_FORWARDS();
		List<Map<String, String>> forwardsContents = requestMessage.getAPNS_CONTENTS();
		
		for (int i = 0; i < models.size(); i++) {
			Object model = models.get(i);
			Set<String> modelkeys = modelsKeys.get(i);
			Object persistence = OrderHelper.getPersistenceByUniqueKeyValue(dao, identityList.get(i), model.getClass());
			
			// subclass
			handlePersistence(dao, model, modelkeys, persistence);
			
			// apns & pendings
			if (persistence instanceof BaseOrder) {
				BaseOrder order = (BaseOrder)persistence;
				
				// add pending
				String forwardUser = null;
				if (forwardsList != null && forwardsList.size() > i) {
					forwardUser = forwardsList.get(i);
				}
				
				// subclass
				String appKey = ParametersHelper.getParameter(requestMessage, ConfigJSON.APPLEVEL);
				handleApprovals(dao, appKey, forwardUser, order);
				
				// send apns
				String tokenString = userDAO.getUserApnsToken(forwardUser);
				if (tokenString != null) {
					String[] apnsTokens =  tokenString.split(ConfigConstants.CONTENT_DIVIDER);
					if (apnsTokens.length != 0) {		// get the token
						Map<String, String> apnsMap = forwardsContents.get(i);
						ApnsHelper.push(apnsMap, apnsTokens);
					}
				} else {
					DLog.log("Token is null");
				}
			}
		}
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
	}
	
	
	protected abstract void handlePersistence(SuperDAO dao, Object model, Set<String> modelkeys, Object persistence) throws Exception ;
	protected abstract void handleApprovals(SuperDAO dao, String appKey, String forwardUser, BaseOrder persistence) throws Exception ;
	
}


