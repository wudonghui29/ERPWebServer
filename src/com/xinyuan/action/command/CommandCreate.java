package com.xinyuan.action.command;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xinyuan.Util.ApprovalHelper;
import com.xinyuan.Util.OrderNOGenerator;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ConfigJSON;
import com.xinyuan.message.RequestMessage;
import com.xinyuan.message.ResponseMessage;
import com.xinyuan.model.BaseModel;
import com.xinyuan.model.BaseOrder;
import com.xinyuan.model.IApp;

public class CommandCreate implements Command {

	@Override
	public void execute(SuperDAO dao, ResponseMessage responseMessage, RequestMessage requestMessage, List<Object> models, List<Set<String>> modelsKeys) throws Exception {
		if (models.size() != 1) return ;		// Forbid create multi-
		
		List<Map<String,Object>> results = new ArrayList<Map<String,Object>>();
		
		for (int i = 0; i < models.size(); i++) {
			Object persistence = models.get(i);
			
			// set basic info.
			if (persistence instanceof BaseModel) OrderNOGenerator.setOrderBasicCreateDetail((BaseModel)persistence);
			
			// create
			Serializable serializableId = dao.create(persistence);
			
			// add id to result
			Map<String,Object> result = new HashMap<String,Object>();
			result.put(ConfigJSON.IDENTIFIER, serializableId);
			
			if (persistence instanceof BaseOrder) {
				
				// add orderNO to result
				BaseOrder order = (BaseOrder)persistence;
				result.put(ConfigJSON.ORDERNO, order.getOrderNO());
				
				// add orderNO to PendingApprove
				if (persistence instanceof IApp) {
					IApp iApp = (IApp)persistence ;
					ApprovalHelper.addPendingApprove(iApp.getForwardUser(), order);
				}
			}
			results.add(result);
		}
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE ;
		responseMessage.results = results;
	}

}
