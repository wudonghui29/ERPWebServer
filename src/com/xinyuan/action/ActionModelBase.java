package com.xinyuan.action;

import com.xinyuan.dao.BaseDAO;

public abstract class ActionModelBase extends ActionBase {

	protected BaseDAO dao = getDao() ;							// For Subclass Initial
	protected abstract BaseDAO getDao() ;

}
