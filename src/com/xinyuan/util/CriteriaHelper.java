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
	
	private static String SPILT_DUPLICATEDKEY_VALUE = "\\*";
	
	private static String OR = "or";
	private static String AND = "and";
	
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
	
	
	
	/**
	 * 
	 * @param criterias		The keys-values use the sql query
	 * 
     *   {
     *      "and": {"height": "BT.2_TO_20" ,"birthday":"BT.2012-03-04 00:00:00_TO_2012-03-06 10:00:00"}
     *      ,"or": {"employeeNO":"EQ.WQ0008*EQ.AE0009"}
     *   }
     *
     *	Translate to : Where height BETWEEN :_BTheight0 AND :_BTheight_0 and birthday BETWEEN :_BTbirthday0 AND :_BTbirthday_0 and ( employeeNO = :_EQemployeeNO0 or employeeNO = :_EQemployeeNO1)
     *	except 'Where' character
     *
	 * @return
	 */
	public static String assembleCriteriaClause(Map<String, Map> criterias) {
		if (criterias == null || criterias.size() == 0) return "";
		
		String criterialClause = "";
		
		for (Map.Entry<String, Map> entry : criterias.entrySet()) {
			String key = entry.getKey();
			
			if (! (key.equalsIgnoreCase(OR) || key.equalsIgnoreCase(AND)) ) continue;
			
			Map value = entry.getValue();
			
			String subClause = getSubClause(value, key);
			
			if (!subClause.isEmpty()) {
				if (key.equalsIgnoreCase(OR)) subClause = " (" + subClause + ") "; 
				criterialClause += criterialClause.isEmpty() ? subClause : " and" + subClause ;
			}
		}
		
		return criterialClause;
	}
	
	/**
	 * 
	 * @param map
	 * @param subKey   may be "AND" / "OR"
	 * @return
	 */
	private static String getSubClause(Map<String, String> map, String subKey) {
		if (map == null || map.size() == 0) return "";
		
		String subClause = "";
		int count = 0 , size = map.size();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			count ++ ;
			
			String key = entry.getKey();			// "age"
			
			String value = entry.getValue().toString();		// "BT.18.25" : age between 18 and 25 
			String[] conditions = value.split(SPILT_DUPLICATEDKEY_VALUE) ; 
			
			for (int i = 0; i < conditions.length; i++) {
				
				String duplicatedKeyValues[] = conditions[i].split(SPILT_FLAG_VALUE_CONNECTOR);
				
				String criteriaFLAG = duplicatedKeyValues[0];		// "BT", "EQ" , "GT" and so on... 
				String criteriaVALUE = duplicatedKeyValues[1];
				
				String flag = criteriasMap.get(criteriaFLAG);
				
				
				if (flag.equals(BETWEEN)) {
					subClause += (" " + key + " " + flag + " " + ":" + MARK + criteriaFLAG + key + i  + BETWEEN_AND + ":" + MARK + criteriaFLAG + key  + MARK + i );
				} else {
					subClause += (" " + key + " " + flag + " " + ":" + MARK + criteriaFLAG + key + i);
				}
				if (i != conditions.length -1) subClause += " " + subKey;
				
			}
			
			
			if (count != size) subClause += " " + subKey; 
			
		}
		
		return subClause;
	}
	
	
	/**
	 * 
	 * @param object		the vo object has some of the properties
	 * @param query			the query with hql
	 * @param criterias		the values in it
	 * @throws Exception
	 */
	public static <E extends Object> void setCriteriaValues(E object, Query query, Map<String, Map> criterias) throws Exception {
		if (criterias == null || criterias.size() == 0) return;
		
		Map<String, Class<?>> map = IntrospectHelper.getPropertiesTypes(object);
		Set<String> allProperties = map.keySet();
		
		
		for (Map.Entry<String, Map> entry : criterias.entrySet()) {
			Map<String, String> valueMap = entry.getValue();
			
			
			for (Map.Entry<String, String> subEntry : valueMap.entrySet()) {
				String key = subEntry.getKey();			// "age"
				
				if(! IntrospectHelper.isContains(allProperties, key)) continue;
				
				String value = subEntry.getValue();		// "BT.18-25" : age between 18 and 25 
				String[] conditions = value.split(SPILT_DUPLICATEDKEY_VALUE);
				
				for (int i = 0; i < conditions.length; i++) {
					String duplicatedKeyValues[] = conditions[i].split(SPILT_FLAG_VALUE_CONNECTOR);		// "employeeNO":"EQ.WQ0008*EQ.AE0009" -> "employeeNO":"EQ.WQ0008" OR "employeeNO":"EQ.AE0009"
					
					String criteriaFLAG = duplicatedKeyValues[0];		// "BT", "EQ" , "GT" and so on... 
					String criteriaVALUE = duplicatedKeyValues[1];		// "18-25"
					
					String flag = criteriasMap.get(criteriaFLAG);
					
					if (flag.equals(BETWEEN)) {
						String[] betweenValues = criteriaVALUE.split(SPILT_BETWEEN_CONNECTOR);
						query.setParameter( MARK + criteriaFLAG + key + i, betweenValues[0]);
						query.setParameter( MARK + criteriaFLAG + key + MARK + i, betweenValues[1]);
					} else {
						if (flag.equals(LIKE)) criteriaVALUE = criteriaVALUE.replaceAll(REPLACE_LIKE_PERCENT, "%"); 
						query.setParameter( MARK + criteriaFLAG + key + i, criteriaVALUE);
					}
				}
				
			}
		}
		
		
	}
	
	
}
