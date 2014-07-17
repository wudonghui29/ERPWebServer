package com.xinyuan.Query;

import j2se.modules.Introspector.IntrospectHelper;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Query;


public class QueryObjectsHelper {
	
	private static String MARK = "_";
	
	public static String assembleObjectsWhereClause(String alias, Set<String> keys) {
		if (keys == null || keys.size() == 0) return "";
		
		String whereString = "";
		
		// assemble the hql string
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			whereString += " " + (alias != null ? alias + "." + key : key) + " = " + ":" + MARK + (alias != null ? alias + key : key);
			if (iterator.hasNext()) whereString += " AND";
		}
		
		return whereString;
	}
	
	
	public static <E extends Object> void setObjectsWhereValues(Query query, E object, Set<String> keys) throws Exception {
		if (keys == null || keys.size() == 0) return;
		
		String alias = IntrospectHelper.getShortClassName(object);
		for (PropertyDescriptor pd : Introspector.getBeanInfo(object.getClass()).getPropertyDescriptors()) {
			if (pd.getReadMethod() != null && !IntrospectHelper.isClassPropertyName(pd.getName())) {
				String propertyname = pd.getName() ;
				// set values
				if (IntrospectHelper.isContains(keys, propertyname)){
					Object propertyvalue =  pd.getReadMethod().invoke(object);
					query.setParameter(MARK + (alias != null ? alias + propertyname : propertyname), propertyvalue);
				}
			}
		}
		
	}

}
