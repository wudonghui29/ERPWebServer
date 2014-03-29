package com.xinyuan.Query;

import java.util.List;

public class QuerySortsHelper {

	/**
	 * 
	 * @param sorts   e.g ["employeeNO.DESC", "name.DESC"]
	 * @return
	 */
	public static String assembleOrderByClause(String alias, List<String> sorts) {
		if (sorts == null ||  sorts.size() == 0) return "";
		
		String orderByString = "";
		
		int size = sorts.size();
		for(int i = 0; i < size ; i++) {
			String columnSorteString = sorts.get(i);
			String[] elements = columnSorteString.split("\\."); // ["employeeNO", "DESC"]
			
			String column = elements[0];
			String ascOrDesc = elements[1];
			
			orderByString += column.contains(".") ? column : alias + "." + column;
			orderByString += " " + ascOrDesc;			// "Employee.employeeNO DESC"
			
			if (i != size-1) orderByString += ", "; 
		}
		
		return orderByString;
	}
	
}
