package com.xinyuan.constraint;

import java.util.HashMap;
import java.util.Map;

import com.modules.Introspector.IntrospectHelper;
import com.modules.Introspector.ModelIntrospector;
import com.xinyuan.Util.OrderHelper;
import com.xinyuan.dao.impl.WarehouseDAOIMP;
import com.xinyuan.model.Warehouse.WHInventoryOrder;
import com.xinyuan.model.Warehouse.WHLendOutBill;
import com.xinyuan.model.Warehouse.WHLendOutOrder;
import com.xinyuan.model.Warehouse.WHScrapOrder;

public class WarehouseConstraint {
	
	public static final Map<String, String> orderMap = new HashMap<String, String>();
	
	static {
		orderMap.put("WHLendOutOrder", "app2");
		orderMap.put("WHLendOutBill", "app2");
		orderMap.put("WHScrapOrder", "app4");
	}
		
	
	public static void applyModify(Object model, Map<String, String>identityList) throws Exception {
		
		String orderType = IntrospectHelper.getShortClassName(model);
		if (orderMap.containsKey(orderType)) {
			WarehouseDAOIMP dao = new WarehouseDAOIMP();
			
			Object persistence = OrderHelper.getPersistenceByUniqueKeyValue(dao,identityList, model.getClass());
			String orderApp = orderMap.get(orderType);
			
			// check if app in db
			if(ModelIntrospector.getProperty(persistence, orderApp) == null) return;
			
			if (model instanceof WHScrapOrder) {
				WHScrapOrder order = (WHScrapOrder)persistence;
				String codeValue = order.getProductCode();
				if (codeValue == null) return;
				float amount =  order.getAmount();
				
				WHInventoryOrder inventoryPO= (WHInventoryOrder)dao.getObject(WHInventoryOrder.class, "productCode", codeValue);
				float IVTotalAmount =  inventoryPO.getTotalAmount();
				inventoryPO.setTotalAmount(IVTotalAmount - amount);
				float IVRemainAmout = inventoryPO.getRemainAmount();
				inventoryPO.setRemainAmount(IVRemainAmout - amount);
				
			    dao.modify(inventoryPO);

			}
			
			if (model instanceof WHLendOutOrder) {
				WHLendOutOrder order = (WHLendOutOrder)persistence;
				String codeValue = order.getProductCode();
				if (codeValue == null) return;
				
				float lendAmount = order.getLendAmount();
				
				WHInventoryOrder inventoryPO= (WHInventoryOrder)dao.getObject(WHInventoryOrder.class, "productCode", codeValue);

				float IVRemainAmout = inventoryPO.getRemainAmount();
				inventoryPO.setRemainAmount(IVRemainAmout - lendAmount);
				
				float IVLendAmout = inventoryPO.getLendAmount();
				inventoryPO.setLendAmount(IVLendAmout + lendAmount);
				
				dao.modify(inventoryPO);
				
			}
			
			if (model instanceof WHLendOutBill) {
				WHLendOutBill bill = (WHLendOutBill)persistence;
				WHLendOutOrder order = OrderHelper.getOrderByBill(dao, bill);
				
				String codeValue = order.getProductCode();
				if (codeValue == null) return;
				float returnAmount = bill.getReturnAmount();

				
				WHInventoryOrder inventoryPO= (WHInventoryOrder)dao.getObject(WHInventoryOrder.class, "productCode", codeValue);

				float IVRemainAmout = inventoryPO.getRemainAmount();
				inventoryPO.setRemainAmount(IVRemainAmout + returnAmount);
				
				float IVLendAmout = inventoryPO.getLendAmount();
				inventoryPO.setLendAmount(IVLendAmout - returnAmount);
				
				dao.modify(inventoryPO);
				
			}
		
		}
		
		
	}

}
