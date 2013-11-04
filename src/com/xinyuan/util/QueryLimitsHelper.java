package com.xinyuan.util;

import java.util.List;

public class QueryLimitsHelper {

	public static boolean isLimits(List<List<String>> outterLimits) {
		boolean isLimitPage = outterLimits != null && outterLimits.size() == 1 && outterLimits.get(0).size() == 2;
		return isLimitPage;
	}
}
