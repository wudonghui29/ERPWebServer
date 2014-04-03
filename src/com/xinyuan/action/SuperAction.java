package com.xinyuan.action;

import com.modules.Introspector.IntrospectHelper;
import com.opensymphony.xwork2.Action;
import com.xinyuan.action.command.Command;
import com.xinyuan.action.command.CommandApply;
import com.xinyuan.action.command.CommandCreate;
import com.xinyuan.action.command.CommandDelete;
import com.xinyuan.action.command.CommandModify;
import com.xinyuan.action.command.CommandRead;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.message.ConfigConstants;

public class SuperAction extends ActionBase {
	
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() { return Action.NONE; }
	
	@Override
	protected SuperDAO getDao() { return new SuperDAOIMP(); }
	
	
	public String read() throws Exception {
		Command readCommand = new CommandRead();
		readCommand.execute(dao, responseMessage, requestMessage, models, modelsKeys);
		return Action.NONE;
	}
	
	
	public String create() throws Exception {
		Command createCommand = new CommandCreate();
		createCommand.execute(dao, responseMessage, requestMessage, models, modelsKeys);
		return Action.NONE;
	}
	
	
	
	public String delete() throws Exception {
		Command deleteCommand = new CommandDelete();
		deleteCommand.execute(dao, responseMessage, requestMessage, models, modelsKeys);
		return Action.NONE;
	}
	
	
	
	public String modify() throws Exception {
		Command modifyCommand = new CommandModify();
		modifyCommand.execute(dao, responseMessage, requestMessage, models, modelsKeys);
		return Action.NONE;
	}
	

	public String apply() throws Exception {
		Command applyCommand = new CommandApply();
		applyCommand.execute(dao, responseMessage, requestMessage, models, modelsKeys);
		return Action.NONE;
	}
	
	
	private Command getCommand(String command) {
		String actionClassName = this.getClass().getName();			// com.xinyuan.action.
		
		String commandClassNameTemp = actionClassName.replace(ConfigConstants.ACTION_CLASS_SUFFIX, "Command"+command);
		
		String commandClassName = commandClassNameTemp.replace(".action", ".action.command");
		
//		Class<?> commandClass
		return null;
	}

}
