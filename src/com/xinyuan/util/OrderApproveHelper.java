package com.xinyuan.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.xinyuan.model.BaseOrderModel;
import com.xinyuan.model.LevelAPP_5;
import com.xinyuan.model.LevelApp_1;
import com.xinyuan.model.LevelApp_2;
import com.xinyuan.model.LevelApp_3;
import com.xinyuan.model.LevelApp_4;
import com.xinyuan.model.LevelApp_6;

public class OrderApproveHelper {

	// Utility Methods
	public static void approve(BaseOrderModel model, String username) {
		try {
			
			int level = 0;
			
			if (model instanceof LevelApp_6) {
				level = 6;
			} else if (model instanceof LevelAPP_5) {
				level = 5;
			} else if (model instanceof LevelApp_4) {
				level = 4;
			} else if (model instanceof LevelApp_3) {
				level = 3;
			} else if (model instanceof LevelApp_2) {
				level = 2;
			} else if (model instanceof LevelApp_1) {
				level = 1;
			}
		
			for (int i = 0; i < level; i++) {
				Method readMethod = model.getClass().getMethod( "get" + "LevelApp_" + (i + 1));
				Object value = readMethod.invoke(model);
				if(value == null) {
					Method writeMethod = model.getClass().getMethod("set" + "LevelApp_" + (i + 1) , String.class);
					writeMethod.invoke(model, username);
					break;
				}
			}
		
			
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
