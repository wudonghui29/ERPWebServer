package com.xinyuan.Query;

import java.util.List;

public class QueryLimitsHelper {

	// For Have Joined Clause
	public static boolean isJoinedNeedLimits(List<List<String>> outterLimits) {
		boolean isLimitPage = outterLimits != null && outterLimits.size() != 0 && outterLimits.get(0).size() == 2;
		return isLimitPage;
	}
	
	// For Have Joined Clause
	// [[0,10]] -> " LIMIT 0, 10"
	public static String assembleJoinedLimitsClause(List<List<String>> outterLimits) {
		if (!isJoinedNeedLimits(outterLimits)) return "";
		String fromIndex = outterLimits.get(0).get(0);
		String number = outterLimits.get(0).get(1);
		String limitClause = " LIMIT " + fromIndex + ", " + number;
		return limitClause;
	}
	
	
	// For the strange result , when the first time "SELECT FOUND_ROWS()" , it always get the wrong result
	// So , send the "SELECT SQL_CALC_FOUND_ROWS " clause twice , to fix this weird thing
	// Here , use the from index == 0 as the first time , but sometimes it's not the first time
	public static boolean isFirstTimeGetLimitsNumber(List<String> limits) {
		if (limits == null || limits.size() == 0) return false;
		String fromIndex = limits.get(0);
		return Integer.valueOf(fromIndex) == 0 ;
	}
	
	
	/**
	 * For No Join Claus HQL
	 * @param limits
	 * @return
	 */
	public static boolean isNeedLimit(List<String> limits) {
		return limits != null && limits.size() == 2 ;
	}
	
}
