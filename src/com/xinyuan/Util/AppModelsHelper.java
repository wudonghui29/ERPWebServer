package com.xinyuan.Util;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;

import com.modules.Helper.FileHelper;
import com.modules.Introspector.IntrospectHelper;
import com.modules.Introspector.ObjectIntrospector;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.model.BaseOrder;
import com.xinyuan.model.IApp1;
import com.xinyuan.model.IApp2;
import com.xinyuan.model.IApp3;
import com.xinyuan.model.IApp4;
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
	
	
	public static String getFinalApprovalKey(Object object) {
		if (object instanceof IApp4) {
			return ConfigConstants.APPROVAL_4;
		} else if (object instanceof IApp3) {
			return ConfigConstants.APPROVAL_3;
		} else if (object instanceof IApp2) {
			return ConfigConstants.APPROVAL_2;
		} else if (object instanceof IApp1) {
			return ConfigConstants.APPROVAL_1;
		}
		return null;
	}
	
	public static String getPreviousApprovalKey(String appKey) {
        if (appKey.equals(ConfigConstants.APPROVAL_4)) {
            return ConfigConstants.APPROVAL_3;
        } else if (appKey.equals(ConfigConstants.APPROVAL_3)) {
            return ConfigConstants.APPROVAL_2;
        } else if (appKey.equals(ConfigConstants.APPROVAL_2)) {
            return ConfigConstants.APPROVAL_1;
        } else if (appKey.equals(ConfigConstants.APPROVAL_1)) {
            return ConfigConstants.APPROVAL_CreateUser;
        }
        return null;
    }
	
	public static String getNextApprovalKey(String appKey) {
        if (appKey.equals(ConfigConstants.APPROVAL_3)) {
            return ConfigConstants.APPROVAL_4;
        } else if (appKey.equals(ConfigConstants.APPROVAL_2)) {
            return ConfigConstants.APPROVAL_3;
        } else if (appKey.equals(ConfigConstants.APPROVAL_1)) {
            return ConfigConstants.APPROVAL_2;
        } else if (appKey.equals(ConfigConstants.APPROVAL_CreateUser)) {
            return ConfigConstants.APPROVAL_1;
        }
        return null;
    }
	
	
	public static boolean isFinalApproval(String appKey, BaseOrder persistence) {
		String finalAppKey = AppModelsHelper.getFinalApprovalKey(persistence);
		return finalAppKey != null && finalAppKey.equals(appKey) ;
	}
	
	
	public static void copyNewValueToPersistence(Object fromObject, Object toObject) throws Exception {
	    for (PropertyDescriptor pd : Introspector.getBeanInfo(fromObject.getClass()).getPropertyDescriptors()) {
            if (pd.getReadMethod() != null && !IntrospectHelper.isClassPropertyName(pd.getName())) {
                
                String propertyNameNew = pd.getName() ;
                if (propertyNameNew.indexOf("_N") >=0 ) {
                    Object newValue = ObjectIntrospector.getProperty(fromObject, propertyNameNew);
                    if (newValue == null) continue;
                    
                    String propertyNames[] = propertyNameNew.split("_");
                    String originPropertyName = propertyNames[0];
                    ObjectIntrospector.setProperty(toObject, originPropertyName, newValue);
                }
            }
        }
    }
}
