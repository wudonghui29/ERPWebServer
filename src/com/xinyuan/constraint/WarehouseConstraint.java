package com.xinyuan.constraint;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.modules.Introspector.IntrospectHelper;
import com.modules.Introspector.ModelIntrospector;
import com.xinyuan.Util.AppModelsHelper;
import com.xinyuan.dao.impl.WarehouseDAOIMP;
import com.xinyuan.model.Warehouse.WHInventoryCHOrder;
import com.xinyuan.model.Warehouse.WHInventoryOrder;
import com.xinyuan.model.Warehouse.WHLendOutBill;
import com.xinyuan.model.Warehouse.WHLendOutOrder;
import com.xinyuan.model.Warehouse.WHPurchaseBill;
import com.xinyuan.model.Warehouse.WHPurchaseOrder;
import com.xinyuan.model.Warehouse.WHScrapOrder;

public class WarehouseConstraint {
	
	public static final Map<String, String> orderMap = new HashMap<String, String>();
	
	static {
		orderMap.put("WHLendOutOrder", "app2");
		orderMap.put("WHLendOutBill", "app2");
		orderMap.put("WHScrapOrder",  "app4");
		orderMap.put("WHPurchaseOrder", "app4");
		orderMap.put("WHInventoryCHOrder", "app4");
	}
		
	
	public static void applyModify(Object model, Map<String, String>identityList) throws Exception {
		
		String orderType = IntrospectHelper.getShortClassName(model);
		if (orderMap.containsKey(orderType)) {
			WarehouseDAOIMP dao = new WarehouseDAOIMP();
			
			Object persistence = AppModelsHelper.getPersistenceByUniqueKeyValue(dao,identityList, model.getClass());
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
				
			    dao.modify(inventoryPO);

			}
			
			if (model instanceof WHLendOutOrder) {
				WHLendOutOrder order = (WHLendOutOrder)persistence;
				String codeValue = order.getProductCode();
				if (codeValue == null) return;
				
				float lendAmount = order.getLendAmount();
				
				WHInventoryOrder inventoryPO= (WHInventoryOrder)dao.getObject(WHInventoryOrder.class, "productCode", codeValue);

				float IVLendAmout = inventoryPO.getLendAmount();
				inventoryPO.setLendAmount(IVLendAmout + lendAmount);
				
				dao.modify(inventoryPO);
				
			}
			
			if (model instanceof WHLendOutBill) {
				WHLendOutBill bill = (WHLendOutBill)persistence;
				WHLendOutOrder order = AppModelsHelper.getOrderByBill(dao, bill);
				
				String codeValue = order.getProductCode();
				if (codeValue == null) return;
				float returnAmount = bill.getReturnAmount();

				
				WHInventoryOrder inventoryPO= (WHInventoryOrder)dao.getObject(WHInventoryOrder.class, "productCode", codeValue);
				
				float IVLendAmout = inventoryPO.getLendAmount();
				inventoryPO.setLendAmount(IVLendAmout - returnAmount);
				
				dao.modify(inventoryPO);
				
			}
			
			if(model instanceof WHPurchaseOrder){
				WHPurchaseOrder order = (WHPurchaseOrder)persistence;
				Set<WHPurchaseBill>  bills = order.getWHPurchaseBills();
				if (bills == null || bills.size() == 0) return;
				
				Iterator<WHPurchaseBill> iterable = bills.iterator();
				while (iterable.hasNext()) {
					WHPurchaseBill aBill = (WHPurchaseBill) iterable.next();
					String codeValue = aBill.getProductCode();
					if (codeValue == null) continue;
					else {
						
						float storageAmount = aBill.getStorageNum();
						
						WHInventoryOrder inventoryPO= (WHInventoryOrder)dao.getObject(WHInventoryOrder.class, "productCode", codeValue);
						
						float IVTotalAmount =  inventoryPO.getTotalAmount();
						inventoryPO.setTotalAmount(IVTotalAmount + storageAmount);
						
						
						float IVTotal = inventoryPO.getPriceBasicUnit() * IVTotalAmount;
						float PHTotal = aBill.getStorageNum() * aBill.getStorageUnitPrice();
						float UnitPrice = (IVTotal+PHTotal)/(IVTotalAmount + storageAmount);
						inventoryPO.setPriceBasicUnit(UnitPrice);

						dao.modify(inventoryPO);
						
					}
				}
					
			}
			
			if (model instanceof WHInventoryCHOrder) {
				WHInventoryCHOrder order = (WHInventoryCHOrder)persistence;
				String codeValue = order.getProductCode_O();
				WHInventoryOrder inventoryPO= (WHInventoryOrder)dao.getObject(WHInventoryOrder.class, "productCode", codeValue); 
				
				for (PropertyDescriptor pd : Introspector.getBeanInfo(order.getClass()).getPropertyDescriptors()) {
					
					if (pd.getReadMethod() != null && !IntrospectHelper.isClassPropertyName(pd.getName())) {
						
						String propertyNameNew = pd.getName() ;
						if (propertyNameNew.indexOf("_N") >=0 ) 
						{
							
							String propertyNames[] = propertyNameNew.split("_");
							String originPropertyName = propertyNames[0];
							
							
							Object value = ModelIntrospector.getProperty(order, propertyNameNew);
							
							if (value == null) continue; 		// ... null
							
							if (value instanceof Number) {
								Number judgeNumber = (Number)value;
								if (judgeNumber.floatValue() != -1) {
									ModelIntrospector.setProperty(inventoryPO, originPropertyName, value);
								} 
							}else {
								ModelIntrospector.setProperty(inventoryPO, originPropertyName, value);
							}
							
						}
					}
							
				}
							
				dao.modify(inventoryPO);
				
			}
				
				
			}
		
		}
		


	private static String String(Object value) {
		// TODO Auto-generated method stub
		return null;
	}


	private static Number Number(Object value) {
		// TODO Auto-generated method stub
		return null;
	}

}
