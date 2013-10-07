package com.xinyuan.util;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Query;

import com.modules.introspector.IntrospectHelper;

public class QueryObjectsHelper {
	
	private static String MARK = "_";
	
	public static String assembleObjectsWhereClause(Set<String> keys, String alias) {
		if (keys == null || keys.size() == 0) return null;
		
		String whereString = "";
		
		// assemble the hql string
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			whereString += " " + (alias != null ? alias + "." + key : key) + " = " + ":" + MARK + (alias != null ? alias + key : key);
			if (iterator.hasNext()) whereString += " and";
		}
		
		return whereString.isEmpty() ? null : whereString;
	}
	
	
	public static <E extends Object> void setObjectsWhereValues(Query query, E object, Set<String> keys, String alias) throws Exception {
		// set values
		for (PropertyDescriptor pd : Introspector.getBeanInfo(object.getClass()).getPropertyDescriptors()) {
			if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
				String propertyname = pd.getName() ;
				if (IntrospectHelper.isContains(keys, propertyname)){
					Object propertyvalue =  pd.getReadMethod().invoke(object);
					query.setParameter(MARK + (alias != null ? alias + propertyname : propertyname), propertyvalue);
				}
			}
		}
		
	}

}
