package com.modules.introspector;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import com.xinyuan.util.IntrospectorHelper;

public class ModelIntrospector {
	
	
	private static final String DateTimeFormat = "yyyy-MM-dd HH:mm:ss";
	
	
	
	
	/**
	 * 
	 * @param object
	 * @param propertyName
	 * @param value
	 * @throws Exception
	 */
	  public static void setProperty(Object object, String propertyName, Object value) throws Exception {  
	        BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());  
	        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();  
	        for (PropertyDescriptor pd : pds) {  
	            if (pd.getName().equals(propertyName)) {  
	                Method writeMethod = pd.getWriteMethod();
	                
	                Class<?> type = writeMethod.getParameterTypes()[0];
	                value = type != String.class ? convert(value, type) : value;
	                
	                writeMethod.invoke(object, value);  
	                break;  
	            }  
	        }  
	    }
	  
	  
	  
	  /**
	   * 
	   * @param object
	   * @param propertyName
	   * @return	return the property's value
	   * @throws Exception
	   */
	  public static Object getProperty(Object object, String propertyName)  throws Exception {  
		  return PoIntrospector.getProperty(object, propertyName);
	  }

	
	  
	  
	  /**
	   * 
	   * @param value
	   * @param classObj
	   * @return
	   * @throws ParseException
	   */
	private static Object convert(Object value, Class classObj) throws Exception {
		String valueString = (String) value;

		if (classObj == java.util.Date.class || classObj == java.sql.Date.class) {
			
			return new SimpleDateFormat(DateTimeFormat).parse(valueString);
			
		} else if (classObj == Boolean.class) {

			return new Boolean(Integer.parseInt(valueString) == 1);
			
		} else if (classObj == float.class) {
			
			return Float.parseFloat(valueString);
			
		} else if (classObj == int.class) {
			
			return Integer.parseInt(valueString);
			
		}

		return value;
	}
	
	
	
	/**
	 * 
	 * @param vo			the vo object
	 * @param po			the po object
	 * @param keys			specified the fields you want to copy
	 * @throws Exception
	 */
	public static void copyVoToPo(Object vo, Object po, Set<String> keys) throws Exception {
		
		for (PropertyDescriptor pd : Introspector.getBeanInfo(po.getClass()).getPropertyDescriptors()) {
			
			if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
				
				String propertyname = pd.getName() ;
				
				if (IntrospectorHelper.isContains(keys, propertyname)){
					Method readMethod = pd.getReadMethod();
					Object value =  readMethod.invoke(vo);
					
					Method writeMethod = pd.getWriteMethod();
					writeMethod.invoke(po, value);
				}
			}
		}
	}
}
