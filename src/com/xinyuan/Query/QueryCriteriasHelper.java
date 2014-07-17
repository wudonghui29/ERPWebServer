package com.xinyuan.Query;

import j2se.modules.Introspector.ObjectIntrospector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;


public class QueryCriteriasHelper {
	
	private static String MARK = "_";
	
	private static String CRITERIAS_PRE_OR = "or";
	private static String CRITERIAS_PRE_AND = "and";
	
	private static String SPILT_FLAG_VALUE_CONNECTOR = "<>";
	
	private static String BETWEEN = "BETWEEN";
	private static String BETWEEN_AND = " AND ";
	private static String SPILT_BETWEEN_CONNECTOR = "_TO_";
	
	private static String LIKE = "LIKE";
	private static String REPLACE_LIKE_PERCENT = "_LK_";		// TODO: why '%' character cannot post from ios to tomcat??
	
	private static String SPILT_DUPLICATEDKEY_VALUE = "\\*";
	
	
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
     *      "and*": {"height": "BT<>2_TO_20" ,"birthday":"BT<>2012-03-04 00:00:00_TO_2012-03-06 10:00:00"}
     *      ,"or*": {"employeeNO":"EQ<>WQ0008*EQ.AE0009"}
     *   }
     *
     *	Translate to : Where height BETWEEN :_BTheight0 AND :_BTheight_0 AND birthday BETWEEN :_BTbirthday0 AND :_BTbirthday_0 and ( employeeNO = :_EQemployeeNO0 OR employeeNO = :_EQemployeeNO1)
     *	except 'Where' character
     *
	 * @return
	 */
	public static String assembleCriteriasWhereClause(String alias, Map<String, Map<String,String>> criterias) {
		if (criterias == null || criterias.size() == 0) return "";
		
		String criterialClause = "";
		
		for (Map.Entry<String, Map<String,String>> entry : criterias.entrySet()) {
			String key = entry.getKey();
			
			if (! (key.startsWith(CRITERIAS_PRE_OR) || key.startsWith(CRITERIAS_PRE_AND)) ) continue;		// key "and*" , "or*"
			
			boolean isOR = key.startsWith(CRITERIAS_PRE_OR);
			String relation = isOR ? "OR" : "AND";
			
			Map<String,String> value = entry.getValue();
			String subClause = getSubClause(alias, value, relation);
			
			if (!subClause.isEmpty()) {
				if (isOR) subClause = " (" + subClause + ") "; 
				criterialClause += criterialClause.isEmpty() ? subClause : " and" + subClause ;
			}
		}
		
		return criterialClause;
	}
	
	
	
	/**
	 * 
	 * @param map
	 * @param relation   may be "and*" / "or*"
	 * @return
	 */
	private static String getSubClause(String alias, Map<String, String> map, String relation) {
		if (map == null || map.size() == 0) return "";
		
		String subClause = "";
		int count = 0 , size = map.size();
		for (Map.Entry<String, String> entry : map.entrySet()) {	// {"height": "BT<>2_TO_20" ,"birthday":"BT<>2012-03-04 00:00:00_TO_2012-03-06 10:00:00"}
			count ++ ;												// {"employeeNO":"EQ<>WQ0008*EQ.AE0009"}
			
			String key = entry.getKey();							//	"height", "birthday", "employeeNO" ...
			
			String value = entry.getValue().toString(); 
			String[] conditions = value.split(SPILT_DUPLICATEDKEY_VALUE) ; 
			
			for (int i = 0; i < conditions.length; i++) {		// if no duplicated Key Values, the just length = 1. e.g. {"height": "BT<>2_TO_20"} -> "[BT<>2_TO_20]",but {"employeeNO":"EQ<>WQ0008*EQ.AE0009"} -> [EQ<>WQ0008,EQ.AE0009]
				
				String duplicatedKeyValues[] = conditions[i].split(SPILT_FLAG_VALUE_CONNECTOR);
				
				String criteriaFLAG = duplicatedKeyValues[0];		// "BT", "EQ" , "GT" and so on... 
				String criteriaVALUE = duplicatedKeyValues[1];
				
				String flag = criteriasMap.get(criteriaFLAG);
				
				// Pair A
				if (flag.equals(BETWEEN)) {
					subClause += (" " + alias + "." + key + " " + flag + " " + ":" + getQueryParameterPlaceHoder(alias, criteriaFLAG, key, i)  + BETWEEN_AND + ":" + getQueryParameterPlaceHoder(alias, criteriaFLAG, key, i) + MARK );
				} else {
					subClause += (" " + alias + "." + key + " " + flag + " " + ":" + getQueryParameterPlaceHoder(alias, criteriaFLAG, key, i));
				}
				if (i != conditions.length -1) subClause += " " + relation;
				
			}
			
			
			if (count != size) subClause += " " + relation; 
			
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
	public static void setCriteriasWhereValues(String alias, Query query, Map<String, Map<String,String>> criterias) throws Exception {
		if (criterias == null || criterias.size() == 0) return;
		
		for (Map.Entry<String, Map<String,String>> entry : criterias.entrySet()) {			// keys : "and*",  "or*"
			Map<String, String> valueMap = entry.getValue();
			
			for (Map.Entry<String, String> subEntry : valueMap.entrySet()) {
				String key = subEntry.getKey();			// "age"
				
				String value = subEntry.getValue();		// "BT.18-25" : age between 18 and 25 
				String[] conditions = value.split(SPILT_DUPLICATEDKEY_VALUE);
				
				for (int i = 0; i < conditions.length; i++) {
					String duplicatedKeyValues[] = conditions[i].split(SPILT_FLAG_VALUE_CONNECTOR);		// "employeeNO":"EQ.WQ0008*EQ.AE0009" -> "employeeNO":"EQ.WQ0008" OR "employeeNO":"EQ.AE0009"
					
					String criteriaFLAG = duplicatedKeyValues[0];		// "BT", "EQ" , "GT" and so on... 
					String criteriaVALUE = duplicatedKeyValues[1];		// "18-25" , (2) -> 2 is int
					
					String flag = criteriasMap.get(criteriaFLAG);
					
					// Pair B
					if (flag.equals(BETWEEN)) {
						String[] betweenValues = criteriaVALUE.split(SPILT_BETWEEN_CONNECTOR);
						
						query.setParameter( getQueryParameterPlaceHoder(alias, criteriaFLAG, key, i), getQueryParameterValue(betweenValues[0]));
						query.setParameter( getQueryParameterPlaceHoder(alias, criteriaFLAG, key, i) + MARK, getQueryParameterValue(betweenValues[1]));
					} else {
						if (flag.equals(LIKE)) criteriaVALUE = criteriaVALUE.replaceAll(REPLACE_LIKE_PERCENT, "%"); 
						
						query.setParameter( getQueryParameterPlaceHoder(alias, criteriaFLAG, key, i), getQueryParameterValue(criteriaVALUE));
					}
				}
				
			}
		}
	}
	
	
	private static String getQueryParameterPlaceHoder(String alias, String criteriaFLAG, String key, int i){
		return MARK + criteriaFLAG + alias + key + i;
	}
	
	
	private static Object getQueryParameterValue(String criteriaVALUE) {
		Object value = criteriaVALUE;
		if (criteriaVALUE.startsWith("(") && criteriaVALUE.endsWith(")")) {
			criteriaVALUE = criteriaVALUE.substring(1, criteriaVALUE.length()-1);
			if (criteriaVALUE.contains(".")) {
				value = Float.parseFloat(criteriaVALUE);
			} else {
				value = Integer.parseInt(criteriaVALUE);
			}
		}
		return value;
	}
	
	
	/**
	 * 
	 * @param joins   {"Employee.employeeNO":"EQ<>Approvals.username"}
	 * 
	 * Translate to "Employee.employeeNO = Approvals.username" without "ON"
	 * 
	 * @return
	 * 
	 */
	public static String assembleCriteriasJoinOnClause(Map<String, String> joins) {
		if (joins == null || joins.size() == 0) return "";
		
		String joinOnClause = "";
		
		int size = joins.size(), count = 0;
		for (Map.Entry<String, String> entry : joins.entrySet()) {
			count ++ ;
			
			String key = entry.getKey();			// no need alias
			String value = entry.getValue();
			
			String flagValue[] = value.split(SPILT_FLAG_VALUE_CONNECTOR);
			
			String criteriaFLAG = flagValue[0];		// "BT", "EQ" , "GT" and so on... 
			String criteriaVALUE = flagValue[1];		// ""
			
			String flag = criteriasMap.get(criteriaFLAG);
			
			joinOnClause += (key + " " + flag + " " + criteriaVALUE);
			if (count != size) joinOnClause += " AND ";
			
		}
		
		
		return joinOnClause;
	}
	
	
	
	
	
	
	
	
	
	
	
	// ----------------------------------------------------------- Assemble Preconditions -----------------------------------------------------
	
	public static void readAssemblePreconditions(List<Object> results, Object model, Set<String> keys, Map<String, String> precondition) throws Exception {
		if (precondition.size() == 0) return;
		for (String toAttribute : precondition.keySet()) {
			String expressionValue = precondition.get(toAttribute);
			String indexAndAttribute[] = expressionValue.split("-");
			
			int outterIndex = Integer.parseInt(indexAndAttribute[0]);
			int innerIndex = Integer.parseInt(indexAndAttribute[1]);
			Object from = ((List<Object>)results.get(outterIndex)).get(innerIndex);
			
			String fromAttribute = indexAndAttribute[2];
			
			Object value = ObjectIntrospector.getProperty(from, fromAttribute);
			
			ObjectIntrospector.setProperty(model, toAttribute, value);
			keys.add(toAttribute);
		}
	}
	
	public static void createAssemblePreconditions(List<Object> models, Object persistence, Map<String, String> precondition) throws Exception {
		if (precondition.size() == 0) return;
		for (String toAttribute : precondition.keySet()) {
			String expressionValue = precondition.get(toAttribute);
			String indexAndAttribute[] = expressionValue.split("-");		// have two
			
			int index = Integer.parseInt(indexAndAttribute[0]);
			String fromAttribute = indexAndAttribute[1];
			Object from = models.get(index);
			
			Object value = ObjectIntrospector.getProperty(from, fromAttribute);
			ObjectIntrospector.setProperty(persistence, toAttribute, value);
		}
	}
	
	
}
