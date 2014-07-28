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
			String columnSorteString = sorts.get(i);            // employeeNO.DESC , Employee.resign.DESC
			int ascOrDescIndex = columnSorteString.lastIndexOf("."); // ["employeeNO", "DESC"]
			
			String column = columnSorteString.substring(0, ascOrDescIndex);
			String ascAndDescAndChar = columnSorteString.substring(ascOrDescIndex + 1) ;
			String ascAndDesc = ascAndDescAndChar;
			
			int characterIndex = ascAndDescAndChar.lastIndexOf(":");
			String character = null;
			if (characterIndex != -1) {
			    ascAndDesc = ascAndDescAndChar.substring(0 , characterIndex);
			    character = ascAndDescAndChar.substring(characterIndex + 1);
            }
			
			
			String columnElement = column.contains(".") ? column : alias + "." + column;
			columnElement = columnElement + " ";
			
			if (character != null) {
			    columnElement = "convert(" + columnElement + "using " + character + ") ";
			}
			orderByString += columnElement;
			orderByString += ascAndDesc;			// "Employee.employeeNO DESC"
			
			if (i != size-1) orderByString += ", "; 
		}
		
		return orderByString;
	}
	
}
