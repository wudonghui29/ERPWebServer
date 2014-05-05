package com.xinyuan.action.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xinyuan.Query.QueryCriteriasHelper;
import com.xinyuan.Util.ApprovalsDAOHelper;
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
		List<Map<String,Object>> results = new ArrayList<Map<String,Object>>();
		
		List<Map<String, String>> outterPreconditions = requestMessage.getPRECONDITIONS();
		
		for (int i = 0; i < models.size(); i++) {
			Object model = models.get(i);
			
			// subclass
			if (! handleModelBeforeCreate(dao, model, i, responseMessage, requestMessage)) continue;
			
			// Precodition......
			Map<String, String> precondition = outterPreconditions == null ? null : outterPreconditions.get(i);
			if (precondition != null) QueryCriteriasHelper.createAssemblePreconditions(models, model, precondition);
			
			// set basic information
			if (model instanceof BaseModel) OrderNOGenerator.setOrderBasicCreateDetail((BaseModel)model);
			
			Map<String,Object> result = new HashMap<String,Object>();
			// create and add id to result
			result.put(ConfigJSON.IDENTIFIER, dao.create(model));
			
			if (model instanceof BaseOrder) {
				BaseOrder order = (BaseOrder)model;
				// add orderNO to result
				result.put(ConfigJSON.ORDERNO, order.getOrderNO());
				// add orderNO to PendingApprove
				if (model instanceof IApp)  ApprovalsDAOHelper.addPendingApprove(((IApp)model).getForwardUser(), order);
			}
			results.add(result);
			
			// subclass
			handleModelAfterCreate(dao, model, i, responseMessage, requestMessage);
		}
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE ;
		responseMessage.results = results;
	}
	
	
	
	protected boolean handleModelBeforeCreate(SuperDAO dao, Object model, int index, ResponseMessage responseMessage, RequestMessage requestMessage) throws Exception  { return true; }
	
	protected void handleModelAfterCreate(SuperDAO dao, Object model, int index, ResponseMessage responseMessage, RequestMessage requestMessage) throws Exception {}

}
