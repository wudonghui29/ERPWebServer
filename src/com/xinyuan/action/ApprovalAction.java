package com.xinyuan.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.modules.Introspector.IntrospectHelper;
import com.modules.Util.FileHelper;
import com.opensymphony.xwork2.Action;
import com.xinyuan.Config.ConfigConstants;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.ApprovalDAOIMP;

public class ApprovalAction extends SuperAction {
	
	
	@Override
	protected SuperDAO getDao() {
		return new ApprovalDAOIMP();
	}
	
}
