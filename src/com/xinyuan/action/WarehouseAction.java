package com.xinyuan.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.modules.Introspector.IntrospectHelper;
import com.opensymphony.xwork2.Action;
import com.xinyuan.Util.ApprovalHelper;
import com.xinyuan.Util.JsonHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.WarehouseDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ConfigJSON;
import com.xinyuan.model.Warehouse.WHLendOutBill;

public class WarehouseAction extends SuperAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected SuperDAO getDao() {
		return new WarehouseDAOIMP();
	}
	
	@Override
	public String apply() throws Exception {
		// TODO Auto-generated method stub
		super.apply();
		
		String billKey = JsonHelper.getParameter(requestMessage, ConfigJSON.ISBILL);
		if (billKey == null || !Boolean.valueOf(billKey))return Action.NONE;
		
		Object model = models.get(0);
		
		Object persistence = getPersistenceByUniqueKeyValue(requestMessage.getIDENTITYS().get(0), model.getClass());
		
		if (persistence instanceof WHLendOutBill) {
			WHLendOutBill bill = (WHLendOutBill)persistence;
			String department = IntrospectHelper.getParentPackageName(bill);
			String orderType = "WHLendOutOrder";
			String orderNO = bill.getBillNO();
			ApprovalHelper.addPendingApprove(bill.getForwardUser(),department , orderType, orderNO);
		}
		return Action.NONE;
	}
	
	
		
	
}
