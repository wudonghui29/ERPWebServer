package com.xinyuan.action;

import com.xinyuan.dao.BaseDAO;
import com.xinyuan.dao.impl.SettingDAOIMP;

public class SettingAction extends SuperAction {
	
	
	@Override
	protected BaseDAO getDao() {
		return new SettingDAOIMP();
	}
	

}
