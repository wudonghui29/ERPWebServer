package com.xinyuan.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;

import com.modules.introspector.IntrospectHelper;

public class CriteriaHelper {
	
	private static String MARK = "_";
	private static String SPILT_FLAG_VALUE_CONNECTOR = "\\.";
	
	private static String BETWEEN = "BETWEEN";
	private static String BETWEEN_AND = " AND ";
	private static String SPILT_BETWEEN_CONNECTOR = "_TO_";
	
	private static String LIKE = "LIKE";
	private static String REPLACE_LIKE_PERCENT = "_LK_";		// TODO: why '%' cannot post from ios to tomcat??
	
	
	private static Map<String, String> criteriasMap = new HashMap<String, String>();
	
	
	static {
		
		criteriasMap.put("EQ", "=");
		criteriasMap.put("NEQ", "!=");
		
		criteriasMap.put("GT", ">");
		criteriasMap.put("LT", "<");
		
		criteriasMap.put("GTEQ", ">=");
		criteriasMap.put("LTEQ", "<=");
		
		criteriasMap.put("BT", BETWEEN);
		
		criteriasMap.put("LK", LIKE);
		criteriasMap.put("NLK", "NOT " + LIKE);
		
	}
	
	
	public static String assembleCriteriaClause(Map<String, String> criterias) {
		if (criterias == null || criterias.size() == 0) return null;
		
		String criterialClause = "";
		
		int count = 0 , size = criterias.size();
		for (Map.Entry<String, String> entry : criterias.entrySet()) {
			String key = entry.getKey();			// "age"
			String value = entry.getValue().toString();		// "BT.18.25" : age between 18 and 25 
			String[] conditions = value.split(SPILT_FLAG_VALUE_CONNECTOR);
			
			String criteriaFLAG = conditions[0];		// "BT", "EQ" , "GT" and so on... 
//			String criteriaVALUE = values[1];		// "18"
			
			String symbol = criteriasMap.get(criteriaFLAG);
			
			if (symbol.equals(BETWEEN)) {
				criterialClause += (" " + key + " " + symbol + " " + ":" + MARK + criteriaFLAG + key  + BETWEEN_AND + ":" + MARK + criteriaFLAG + key  + MARK );
			} else {
				criterialClause += (" " + key + " " + symbol + " " + ":" + MARK + criteriaFLAG + key);
			}
			
			if (count != size-1) criterialClause += " and"; 
			count ++ ;
		}
		
		return criterialClause;
	}
	
	
	public static <E extends Object> void setCriteriaValues(E object, Query query, Map<String, String> criterias) throws Exception {
		if (criterias == null || criterias.size() == 0) return;
		
		Map<String, Class<?>> map = IntrospectHelper.getPropertiesTypes(object);
		Set<String> allProperties = map.keySet();
		
		for (Map.Entry<String, String> entry : criterias.entrySet()) {
			String key = entry.getKey();			// "age"
			
			if(! IntrospectHelper.isContains(allProperties, key)) continue;
			
			String value = entry.getValue();		// "BT.18-25" : age between 18 and 25 
			String[] values = value.split(SPILT_FLAG_VALUE_CONNECTOR);
			
			String criteriaFLAG = values[0];		// "BT", "EQ" , "GT" and so on... 
			String criteriaVALUE = values[1];		// "18-25"
			
			String symbol = criteriasMap.get(criteriaFLAG);
			
			if (symbol.equals(BETWEEN)) {
				String[] betweenValues = criteriaVALUE.split(SPILT_BETWEEN_CONNECTOR);
				query.setParameter( MARK + criteriaFLAG + key, betweenValues[0]);
				query.setParameter( MARK + criteriaFLAG + key  + MARK, betweenValues[1]);
			} else {
				if (symbol.equals(LIKE)) criteriaVALUE = criteriaVALUE.replaceAll(REPLACE_LIKE_PERCENT, "%"); 
				query.setParameter( MARK + criteriaFLAG + key, criteriaVALUE);
			}
			
		}
	}
	
	
}
