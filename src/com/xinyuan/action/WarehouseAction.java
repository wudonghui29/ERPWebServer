package com.xinyuan.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.modules.Introspector.IntrospectHelper;
import com.opensymphony.xwork2.Action;
import com.xinyuan.Util.ApprovalHelper;
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
	
	public String createBill() throws Exception{
		
	if (models.size() != 1) return Action.NONE;		
		
		List<Map<String,String>> results = new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < models.size(); i++) {
			WHLendOutBill model = (WHLendOutBill)models.get(i);
			Integer identifier = (Integer) dao.create(model);
			
			Map result = new HashMap();
			result.put(ConfigJSON.IDENTIFIER, identifier);
			result.put(ConfigJSON.ORDERNO, model.getBillNO());
			results.add(result);
		}
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE ;
		responseMessage.objects = results;
		
		return Action.NONE;
	}

	@Override
	public String apply() throws Exception {
		// TODO Auto-generated method stub
		super.apply();
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
