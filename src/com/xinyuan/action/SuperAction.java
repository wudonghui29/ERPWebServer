package com.xinyuan.action;

import j2se.modules.Introspector.IntrospectHelper;

import com.opensymphony.xwork2.Action;
import com.xinyuan.Util.ActionHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.message.ConfigConstants;

public class SuperAction extends ActionBase {
	
	private static final long serialVersionUID = 1L;
	
	public static final String ACTION_READ 		= "Read";
	public static final String ACTION_CREATE 	= "Create";
	public static final String ACTION_DELETE 	= "Delete";
	public static final String ACTION_MODIFY	= "Modify";
	public static final String ACTION_APPLY 	= "Apply";
	
	@Override
	public String execute() { return Action.NONE; }
	
	@Override
	protected SuperDAO getDao() { return new SuperDAOIMP(); }
	
	
	public String read() throws Exception {
		return runCommand(ACTION_READ);
	}
	
	
	public String create() throws Exception {
		return runCommand(ACTION_CREATE);
	}
	
	
	public String delete() throws Exception {
		return runCommand(ACTION_DELETE);
	}
	
	
	public String modify() throws Exception {
		return runCommand(ACTION_MODIFY);
	}
	

	public String apply() throws Exception {
		return runCommand(ACTION_APPLY);
	}
	
	
	
	
	
	
	
	private String runCommand(String type) throws Exception {
		String categoryName = IntrospectHelper.getShortClassName(this).replace(ConfigConstants.ACTION_CLASS_SUFFIX, "");
		String preferredModelName = IntrospectHelper.getShortClassName(models.get(0));
		ActionHelper.getCommand(categoryName, preferredModelName, type).execute(dao, responseMessage, requestMessage, models, modelsKeys);
		return Action.NONE;
	}
}
