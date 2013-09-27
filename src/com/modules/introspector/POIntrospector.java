package com.modules.introspector;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.xinyuan.message.FormatConfig;

public class POIntrospector {
	

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
	        BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());  
	        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();  
	        Object retValue = null;  
	        for (PropertyDescriptor pd : pds) {  
	            if (pd.getName().equals(propertyName)) {  
	                Method methodGetX = pd.getReadMethod();  
	                retValue = methodGetX.invoke(object);  
	                break;  
	            }
	        }
	        return retValue;  
	    }  
	  
	  
	  
	  /**
	   * 
	   * @param value
	   * @param classObj
	   * @return
	   * @throws ParseException
	   */
	private static Object convert(Object value, Class classObj) throws ParseException {
		String valueString = (String) value;

		if (classObj == java.util.Date.class) {
			
			return new SimpleDateFormat(FormatConfig.STRING_TO_DATE_FORMAT).parse(valueString);
			
		} else if (classObj == Boolean.class) {

			return new Boolean(Integer.parseInt(valueString) == 1);
			
		} else if (classObj == float.class) {
			
			return Float.parseFloat(valueString);
			
		} else if (classObj == int.class) {
			
			return Integer.parseInt(valueString);
			
		}

		return value;
	}
}
