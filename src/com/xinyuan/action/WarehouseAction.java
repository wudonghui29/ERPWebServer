package com.xinyuan.action;

import j2se.modules.Introspector.IntrospectHelper;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.xinyuan.Util.AppModelsHelper;
import com.xinyuan.Util.ApprovalsDAOHelper;
import com.xinyuan.Util.ParametersHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.WarehouseDAOIMP;
import com.xinyuan.message.ConfigJSON;
import com.xinyuan.model.Warehouse.WHLendOutBill;

public class WarehouseAction extends SuperAction {

	private static final long serialVersionUID = 1L;

	@Override
	protected SuperDAO getDao() {
		return new WarehouseDAOIMP();
	}
	
//	@Override
//	public String apply() throws Exception {
//		// TODO Auto-generated method stub
//		super.apply();
//		
//
//		Object model = models.get(0);
//		
//		List<Map<String, String>> identityList = requestMessage.getIDENTITYS();
//		WarehouseConstraint.applyModify(model, identityList.get(0));
//		
//		String billKey = ParametersHelper.getParameter(requestMessage, ConfigJSON.ISBILL);
//		if (billKey == null || !Boolean.valueOf(billKey)) return Action.NONE;
//		
//		
//		
//		Object persistence = AppModelsHelper.getPersistenceByUniqueKeyValue(dao,requestMessage.getIDENTITYS().get(0), model.getClass());
//		
//		if (persistence instanceof WHLendOutBill) {
//			WHLendOutBill bill = (WHLendOutBill)persistence;
//			String department = IntrospectHelper.getParentPackageName(bill);
//			String orderType = "WHLendOutOrder";
//			String orderNO = bill.getBillNO();
//			ApprovalsDAOHelper.addPendingApprove(bill.getForwardUser(),department , orderType, orderNO);
//		}
//		return Action.NONE;
//	}
	
}
