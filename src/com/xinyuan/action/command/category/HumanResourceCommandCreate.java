package com.xinyuan.action.command.category;

import com.xinyuan.action.command.CommandCreate;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.message.RequestMessage;
import com.xinyuan.message.ResponseMessage;
import com.xinyuan.model.HumanResource.Employee;

public class HumanResourceCommandCreate extends CommandCreate {

	@Override
	protected boolean handleModelBeforeCreate(SuperDAO dao, Object model, int index, ResponseMessage responseMessage, RequestMessage requestMessage) throws Exception {
		
		// forbid multi create Employee
		if (model instanceof Employee) if (index != 0) return false;
		
		return super.handleModelBeforeCreate(dao, model, index, responseMessage, requestMessage);
	}

}
