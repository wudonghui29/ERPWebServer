package com.xinyuan.Util;

import java.util.Map;

import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.model.Warehouse.WHLendOutBill;
import com.xinyuan.model.Warehouse.WHLendOutOrder;



public class OrderHelper {
	
	public static <E extends Object>  E getPersistenceByUniqueKeyValue(SuperDAO dao, Map<String, String> keyValues, Class<E> clazz) throws Exception {
		String identityJSON = GsonHelper.getGson().toJson(keyValues);
		E identityVo = GsonHelper.getGson().fromJson(identityJSON, clazz);
		E persistence = dao.readUnique(identityVo, keyValues.keySet());
		return persistence;
	}
	
	public static WHLendOutOrder getOrderByBill(SuperDAOIMP dao,WHLendOutBill bill) throws Exception{
		WHLendOutOrder order = (WHLendOutOrder )dao.getObject(WHLendOutOrder.class, "billNO", bill.getBillNO());
		return order;
	}
}
