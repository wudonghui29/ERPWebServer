package com.xinyuan.util;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.global.SessionManager;
import com.xinyuan.message.ConstantsConfig;
import com.xinyuan.message.FormatConfig;
import com.xinyuan.model.BaseOrderModel;
import com.xinyuan.model.LevelAPP_5;
import com.xinyuan.model.LevelApp_1;
import com.xinyuan.model.LevelApp_2;
import com.xinyuan.model.LevelApp_3;
import com.xinyuan.model.LevelApp_4;
import com.xinyuan.model.LevelApp_6;
import com.xinyuan.model.HumanResource.Employee;
import com.xinyuan.model.HumanResource.EmployeeAttendanceOrder;
import com.xinyuan.model.HumanResource.EmployeeBusinessMealOrder;
import com.xinyuan.model.HumanResource.EmployeeDailyWorkOrder;
import com.xinyuan.model.HumanResource.EmployeeDormitoryOrder;
import com.xinyuan.model.HumanResource.EmployeeLeaveOrder;
import com.xinyuan.model.HumanResource.EmployeeMonthlyExamineOrder;
import com.xinyuan.model.HumanResource.EmployeeOutOrder;
import com.xinyuan.model.HumanResource.EmployeeOverTimeOrder;
import com.xinyuan.model.HumanResource.EmployeeQuitOrder;
import com.xinyuan.model.HumanResource.EmployeeQuitPassOrder;
import com.xinyuan.model.HumanResource.EmployeeRepairAttendanceOrder;
import com.xinyuan.model.HumanResource.EmployeeSecurityDetailOrder;
import com.xinyuan.model.HumanResource.EmployeeSecurityMealOrder;
import com.xinyuan.model.Security.SecurityVisitOrder;
import com.xinyuan.model.User.User;

public class ModelHelper {
	
	private static Map<String, String> orderNOPrefixMap = new HashMap<String, String>();
	
	static {
		
		// HumanResource
		orderNOPrefixMap.put(Employee.class.getName(), "YG");
		orderNOPrefixMap.put(EmployeeAttendanceOrder.class.getName(), "KQ");
		orderNOPrefixMap.put(EmployeeBusinessMealOrder.class.getName(), "WC");
		orderNOPrefixMap.put(EmployeeDailyWorkOrder.class.getName(), "CQ");
		orderNOPrefixMap.put(EmployeeDormitoryOrder.class.getName(), "SS");
		
		orderNOPrefixMap.put(EmployeeLeaveOrder.class.getName(), "QJ");
		orderNOPrefixMap.put(EmployeeMonthlyExamineOrder.class.getName(), "KH");
		orderNOPrefixMap.put(EmployeeOutOrder.class.getName(), "WC");
		orderNOPrefixMap.put(EmployeeOverTimeOrder.class.getName(), "JB");
		orderNOPrefixMap.put(EmployeeQuitOrder.class.getName(), "LZ");
		
		orderNOPrefixMap.put(EmployeeQuitPassOrder.class.getName(), "FX");
		orderNOPrefixMap.put(EmployeeRepairAttendanceOrder.class.getName(), "BQ");
		orderNOPrefixMap.put(EmployeeSecurityDetailOrder.class.getName(), "ZB");
		orderNOPrefixMap.put(EmployeeSecurityMealOrder.class.getName(), "YC");
		
		
		// Security
		orderNOPrefixMap.put(SecurityVisitOrder.class.getName(), "FK");
		
		
		// Finanace
		
	}
	
	// in BaseAction Create() method
	public static void setOrderBasicCreateDetail(BaseOrderModel model) {
		Date date = new Date();
		model.setCreateDate(date);
		User user = (User)SessionManager.get(ConstantsConfig.SIGNIN_USER);
		model.setCreateUser(user.getUsername());
		
		SimpleDateFormat sdf = new SimpleDateFormat(FormatConfig.ORDER_DATE_TO_STRING_FORMAT);  
		String dateString = sdf.format(date);
		String orderNO = getOrderNOPrefix(model) + dateString;
		
		model.setOrderNO(orderNO);		// TODO: check the database if already have this no.
	}
	private static String getOrderNOPrefix(BaseOrderModel model) {
		String modelClassName = model.getClass().getName();
		return orderNOPrefixMap.get(modelClassName);
	}
	
	// in BaseAction Apply() method 
	public static boolean approve(BaseOrderModel model, String username) throws Exception {
		boolean isAllApproved = false;
		
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
