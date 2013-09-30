package com.modules.introspector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Set;

public class IntrospectHelper {
	
	private static final String DateTimeFormat = "yyyy-MM-dd HH:mm:ss";
	
	
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
