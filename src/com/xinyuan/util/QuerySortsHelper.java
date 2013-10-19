package com.xinyuan.util;

import java.util.List;

public class QuerySortsHelper {

	/**
	 * 
	 * @param sorts   e.g ["employeeNO DESC"]
	 * @return
	 */
	public static String assembleOrderByClause(List<String> sorts, String alias) {
		if (sorts == null ||  sorts.size() == 0) return null;
		
		String orderByString = "";
		
		int size = sorts.size();
		for(int i = 0; i < size ; i++) {
			String column = sorts.get(i);
			orderByString += column.contains(".") ? column : alias + "." + column;
			if (i != size-1) orderByString += ", "; 
		}
		
		return orderByString.isEmpty() ? null : orderByString;
	}
	
}