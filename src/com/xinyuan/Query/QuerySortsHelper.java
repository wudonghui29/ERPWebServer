package com.xinyuan.Query;

import java.util.List;

public class QuerySortsHelper {

	/**
	 * 
	 * @param sorts   e.g ["employeeNO DESC"]
	 * @return
	 */
	public static String assembleOrderByClause(String alias, List<String> sorts) {
		if (sorts == null ||  sorts.size() == 0) return "";
		
		String orderByString = "";
		
		int size = sorts.size();
		for(int i = 0; i < size ; i++) {
			String column = sorts.get(i);
			orderByString += column.contains(".") ? column : alias + "." + column;
			if (i != size-1) orderByString += ", "; 
		}
		
		return orderByString;
	}
	
}
