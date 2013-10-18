package com.modules.introspector;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.xinyuan.message.ConstantsConfig;

public class IntrospectHelper {
	
	private static final String DateTimeFormat = "yyyy-MM-dd HH:mm:ss";
	
	
	public static String getParentPackageName(Object object) {
		String wholeClassName = object.getClass().getName();
		String packageParts[] = wholeClassName.split("\\.");
		
		int parentPackageIndex = packageParts.length - 2;
		return parentPackageIndex >= 0 ? packageParts[parentPackageIndex] : null;
	}
	
	
	public static String getShortClassName(Object object) {
		String wholeClassName = object.getClass().getName();
		return wholeClassName.substring(wholeClassName.lastIndexOf(".") + 1);
	}
	
	
	public static String getLongClassName(Object object) {
		return object.getClass().getName();
	}
	
	
	
	/**
	 * 
	 * @param object
	 * @return 	the object's properties and their types , map , key for properties name, type for value.
	 * @throws Exception
	 */
	public static Map<String, Class<?>> getPropertiesTypes(Object object) throws Exception {
		Map<String, Class<?>> map = new HashMap<String, Class<?>>();
		
		for (PropertyDescriptor pd : Introspector.getBeanInfo(object.getClass()).getPropertyDescriptors()) {
			String propertyname = pd.getName() ;
			if (!"class".equals(propertyname)) {
				map.put(propertyname, pd.getPropertyType());
			}
		}
		
		if (map.size() != 0) return map;
		else return null;
	}
	
	
	/**
	 *  
	 * @param fields
	 * @param key
	 * @return
	 */
	public static boolean isContains(Set<String> keys , String key) {
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().equals(key)) return true; 
		}
		return false;
	}
	
	
	  /**
	   * 
	   * @param value
	   * @param classObj
	   * @return
	   * @throws ParseException
	   */
	public static Object convert(Object value, Class classObj) throws Exception {
		String valueString = (String) value;

		if (classObj == java.util.Date.class) {
			
			return new SimpleDateFormat(DateTimeFormat).parse(valueString);
			
		} else if (classObj == Boolean.class) {

			return valueString.equals("true");
			
		} else if (classObj == float.class) {
			
			return Float.parseFloat(valueString);
			
		} else if (classObj == int.class) {
			
			return Integer.parseInt(valueString);
			
		}

		return value;
	}

}
