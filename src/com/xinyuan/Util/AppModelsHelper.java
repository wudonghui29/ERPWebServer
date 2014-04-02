package com.xinyuan.Util;

import java.util.List;
import java.util.Map;

import com.modules.Introspector.IntrospectHelper;
import com.modules.Util.FileHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.model.Warehouse.WHLendOutBill;
import com.xinyuan.model.Warehouse.WHLendOutOrder;



public class AppModelsHelper {
	
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
	
	
	public static Map<String, Map<String, List<String>>> getCategoriesModelsConstructs() {
		// get the java class name list in specified package
		List<String> classesNamesList = FileHelper.getClassesNames(ConfigConstants.Context_Classes_Path, ConfigConstants.MODELPACKAGE);
		
		// translate classes properties name to map
		Map<String, Map<String, List<String>>> categoriesModelsMap = IntrospectHelper.translateToPropertiesMap(classesNamesList);
		return categoriesModelsMap;
	}
	
	
}
