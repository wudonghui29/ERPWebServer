package com.xinyuan.action.command.category;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Iterator;
import java.util.Set;

import com.modules.Introspector.IntrospectHelper;
import com.modules.Introspector.ObjectIntrospector;
import com.xinyuan.Util.AppModelsHelper;
import com.xinyuan.Util.ApprovalsDAOHelper;
import com.xinyuan.Util.OrderNOGenerator;
import com.xinyuan.action.command.CommandApply;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.model.BaseModel;
import com.xinyuan.model.Warehouse.WHInventory;
import com.xinyuan.model.Warehouse.WHInventoryCHOrder;
import com.xinyuan.model.Warehouse.WHLendOutBill;
import com.xinyuan.model.Warehouse.WHLendOutOrder;
import com.xinyuan.model.Warehouse.WHPickingOrder;
import com.xinyuan.model.Warehouse.WHPurchaseBill;
import com.xinyuan.model.Warehouse.WHPurchaseOrder;
import com.xinyuan.model.Warehouse.WHRecycleInventory;
import com.xinyuan.model.Warehouse.WHScrapOrder;

public class WarehouseCommandApply extends CommandApply {
	protected void handleFinalApprovalProcess(SuperDAO dao, Object persistence) throws Exception {
		super.handleFinalApprovalProcess(dao, persistence);
		
		SuperDAOIMP daoImp = (SuperDAOIMP)dao;
		
		if (persistence instanceof WHScrapOrder) {
			WHScrapOrder order = (WHScrapOrder)persistence;
			String codeValue = order.getProductCode();
			if (codeValue == null) return;
			float amount =  order.getScrapAmount();
			
			WHInventory inventoryPO= (WHInventory)daoImp.getObject(WHInventory.class, "productCode", codeValue);
			float IVTotalAmount =  inventoryPO.getTotalAmount();
			inventoryPO.setTotalAmount(IVTotalAmount - amount);
			
		    dao.modify(inventoryPO);

		}
		
		if (persistence instanceof WHPickingOrder) {
			WHPickingOrder order = (WHPickingOrder)persistence;
			String codeValue = order.getProductCode();
			if (codeValue == null) return;
			
			float pickingAmount =  order.getPickingAmount();
			WHInventory inventoryPO= (WHInventory)daoImp.getObject(WHInventory.class, "productCode", codeValue);

			float IVLendAmout = inventoryPO.getLendAmount();
			inventoryPO.setLendAmount(IVLendAmout + pickingAmount);
			
		    float recycleAmount = order.getRecycleAmount();
		    if (recycleAmount != 0) {
				WHRecycleInventory recycleInventory = new WHRecycleInventory();
					
				OrderNOGenerator.setOrderBasicCreateDetail(recycleInventory);
				
				recycleInventory.setProductCode(inventoryPO.getProductCode());
				recycleInventory.setProductName(inventoryPO.getProductName());
				recycleInventory.setProductCategory(inventoryPO.getProductCategory());
				recycleInventory.setBasicUnit(inventoryPO.getBasicUnit());
				recycleInventory.setAmount(inventoryPO.getAmount());
				recycleInventory.setUnit(inventoryPO.getUnit());
				recycleInventory.setPriceBasicUnit(inventoryPO.getPriceBasicUnit());
				recycleInventory.setProductLocation(inventoryPO.getProductLocation());
				recycleInventory.setProductDesc(inventoryPO.getProductDesc());
				recycleInventory.setProductDescPDF(inventoryPO.getProductDescPDF());
				recycleInventory.setTotalAmount(recycleAmount);
				
				
				dao.create(recycleInventory);
				
			}
		    
		    dao.modify(inventoryPO);
		    
		  

		}
		
		if (persistence instanceof WHLendOutOrder) {
			WHLendOutOrder order = (WHLendOutOrder)persistence;
			String codeValue = order.getProductCode();
			if (codeValue == null) return;
			
			float lendAmount = order.getLendAmount();
			
			WHInventory inventoryPO= (WHInventory)daoImp.getObject(WHInventory.class, "productCode", codeValue);

			float IVLendAmout = inventoryPO.getLendAmount();
			inventoryPO.setLendAmount(IVLendAmout + lendAmount);
			
			dao.modify(inventoryPO);
			
		}
		
		if (persistence instanceof WHLendOutBill) {
			WHLendOutBill bill = (WHLendOutBill)persistence;
			WHLendOutOrder order = AppModelsHelper.getOrderByBill(daoImp, bill);
			
			String codeValue = order.getProductCode();
			if (codeValue == null) return;
			float returnAmount = bill.getReturnAmount();

			
			WHInventory inventoryPO= (WHInventory)daoImp.getObject(WHInventory.class, "productCode", codeValue);
			
			float IVLendAmout = inventoryPO.getLendAmount();
			inventoryPO.setLendAmount(IVLendAmout - returnAmount);
			
			
			String department = IntrospectHelper.getParentPackageName(bill);
			String orderType = "WHLendOutOrder";
			String orderNO = bill.getBillNO();
			ApprovalsDAOHelper.addPendingApprove(bill.getForwardUser(),department , orderType, orderNO);
			
			dao.modify(inventoryPO);
			
		}
		
		if(persistence instanceof WHPurchaseOrder){
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
					
					WHInventory inventoryPO= (WHInventory)daoImp.getObject(WHInventory.class, "productCode", codeValue);
					
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
		
		if (persistence instanceof WHInventoryCHOrder) {
			WHInventoryCHOrder order = (WHInventoryCHOrder)persistence;
			String codeValue = order.getProductCode_O();
			WHInventory inventoryPO= (WHInventory)daoImp.getObject(WHInventory.class, "productCode", codeValue); 
			
			for (PropertyDescriptor pd : Introspector.getBeanInfo(order.getClass()).getPropertyDescriptors()) {
				
				if (pd.getReadMethod() != null && !IntrospectHelper.isClassPropertyName(pd.getName())) {
					
					String propertyNameNew = pd.getName() ;
					if (propertyNameNew.indexOf("_N") >=0 ) 
					{
						
						String propertyNames[] = propertyNameNew.split("_");
						String originPropertyName = propertyNames[0];
						
						
						Object value = ObjectIntrospector.getProperty(order, propertyNameNew);
						
						if (value == null) continue; 		// ... null
						
						if (value instanceof Number) {
							Number judgeNumber = (Number)value;
							if (judgeNumber.floatValue() != -1) {
								ObjectIntrospector.setProperty(inventoryPO, originPropertyName, value);
							} 
						}else {
							ObjectIntrospector.setProperty(inventoryPO, originPropertyName, value);
						}
						
					}
				}
						
			}
						
			dao.modify(inventoryPO);
			
		}
		
	}
}
