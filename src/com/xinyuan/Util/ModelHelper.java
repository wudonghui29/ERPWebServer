package com.xinyuan.Util;

import java.lang.reflect.Method;

import com.xinyuan.model.App1;
import com.xinyuan.model.App2;
import com.xinyuan.model.App3;
import com.xinyuan.model.App4;
import com.xinyuan.model.BaseOrder;

public class ModelHelper {
	
	// in BaseAction Apply() method 
	public static boolean approve(BaseOrder model, String username) throws Exception {
		boolean isAllApproved = false;
		
		try {
			
			int level = 0;
			
			if (model instanceof App4) {
				level = 4;
			} else if (model instanceof App3) {
				level = 3;
			} else if (model instanceof App2) {
				level = 2;
			} else if (model instanceof App1) {
				level = 1;
			}
		
			for (int i = 0; i < level; i++) {
				Method readMethod = model.getClass().getMethod( "get" + "App" + (i + 1));
				Object value = readMethod.invoke(model);
				if(value == null) {
					Method writeMethod = model.getClass().getMethod("set" + "App" + (i + 1) , String.class);
					writeMethod.invoke(model, username);
					if ( i == level -1 ) isAllApproved = true;
					break;
				}
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return isAllApproved;
	}
	
}
