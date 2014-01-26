package com.xinyuan.Util;

import com.xinyuan.dao.impl.HumanResourceDAOIMP;
import com.xinyuan.model.User.User;


public class SettingHelper {
	
	public static boolean isUserTableEmpty() {
		String userClassString = User.class.getName();
		String userTableString = userClassString.substring(userClassString.lastIndexOf(".") + 1);
		return new HumanResourceDAOIMP().isTableEmpty(userTableString);
	}
	
}
