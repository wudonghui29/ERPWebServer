package com.modules.introspector;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class PoIntrospector {
	
	
	
	

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
	  
}
