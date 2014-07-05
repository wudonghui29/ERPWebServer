package com.xinyuan.Util;

import java.util.List;
import java.util.Map;

import com.xinyuan.model.Approval.Approvals;

public class ApporvalsHelper {
	
	public static boolean isUserHavePendingApprovals(Approvals approvals) {
	    if (approvals == null) {
	        return false;
	    }
		return isHavePendingApprovals(GsonHelper.translateJsonStringToMap(approvals.getPendingApprovals()));
	}

	public static boolean isHavePendingApprovals(Map<String, Object> pendingApprovals) {
		for(Object object : pendingApprovals.values()){
			Map<String, List<String>> pendingCategories = (Map<String, List<String>>)object;
			for(List<String> list : pendingCategories.values()) {
				if (list.size() > 0) {
					return true;
				}
			}
		}
		return false;
	}
}
