package com.xinyuan.action.command;

import java.util.List;
import java.util.Set;

import com.xinyuan.dao.SuperDAO;
import com.xinyuan.message.RequestMessage;
import com.xinyuan.message.ResponseMessage;

public interface Command {
	
	void execute(SuperDAO dao,ResponseMessage responseMessage, RequestMessage requestMessage, List<Object> models, List<Set<String>> modelsKeys ) throws Exception;
	
}
