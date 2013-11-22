package com.xinyuan.Query;

import java.util.List;

public class QueryFieldsHelper {
	
	
	public static <E extends Object> String assembleFieldsSelectClause(String alias, List<String> fields) {
		if (fields == null || fields.size() == 0) return "";
		
		String hql  = "" ;
		int fieldSize = fields.size();
		for (int i = 0; i < fieldSize; i++) {
			String field = fields.get(i);
			hql += (alias + "." + field );
			if (i != fieldSize - 1) hql += ", ";
		}
		
		return hql;
	}

}
