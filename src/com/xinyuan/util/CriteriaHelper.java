package com.xinyuan.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.modules.introspector.IntrospectHelper;

public class CriteriaHelper {
	
	
	private static Map<String, String> criteriasMap = new HashMap<String, String>();
	
	
	static {
		
		criteriasMap.put("EQ", "=");
		criteriasMap.put("NEQ", "!=");
		
		criteriasMap.put("GT", ">");
		criteriasMap.put("LT", "<");
		
		criteriasMap.put("GTEQ", ">=");
		criteriasMap.put("LTEQ", "<=");
		
		criteriasMap.put("OR", "or");
		criteriasMap.put("NOT", "not");
		criteriasMap.put("AND", "and");
		criteriasMap.put("BT", "between");
		
		criteriasMap.put("LIKE", "like");
		
	}
	
	
	public static <E> String createCriteria(E object , Map<String, String> criterias) throws Exception {
		
		for (Map.Entry<String, String> entry : criterias.entrySet()) {
//			System.out.println(entry.getKey() + "/" + entry.getValue());
			
			String key = entry.getKey();
			String value = entry.getValue();
			String[] values = value.split(".");
			
			String criteriaFLAG = values[0];
			String creteriaVALUE = values[1];
			
			
			
			
			/**
			BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());  
	        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();  
	        for (PropertyDescriptor pd : pds) {  
	            if (pd.getName().equals(key)) {
	            	
	            	Method readMethod = pd.getReadMethod();
	                Method writeMethod = pd.getWriteMethod();
	                
	                Class<?> returnType = readMethod.getReturnType();
	                Class<?> parameterType = writeMethod.getParameterTypes()[0];
	                
	                Object newValue = returnType != String.class ? IntrospectHelper.convert(value, returnType) : value;
	                
	                break;  
	            }  
	        } 
	        
	        **/
		    
		}
		
		return "";
	}
	
	
}
