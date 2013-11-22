package com.xinyuan.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.Global.SessionManager;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ConfigFormat;
import com.xinyuan.model.BaseModel;
import com.xinyuan.model.HumanResource.Employee;
import com.xinyuan.model.HumanResource.EmployeeATTDROrder;
import com.xinyuan.model.HumanResource.EmployeeATTFixOrder;
import com.xinyuan.model.HumanResource.EmployeeATTOrder;
import com.xinyuan.model.HumanResource.EmployeeBMOrder;
import com.xinyuan.model.HumanResource.EmployeeDormitoryOrder;
import com.xinyuan.model.HumanResource.EmployeeLeaveOrder;
import com.xinyuan.model.HumanResource.EmployeeMEOrder;
import com.xinyuan.model.HumanResource.EmployeeOTOrder;
import com.xinyuan.model.HumanResource.EmployeeQuitOrder;
import com.xinyuan.model.HumanResource.EmployeeQuitPassOrder;
import com.xinyuan.model.HumanResource.EmployeeSDOrder;
import com.xinyuan.model.HumanResource.EmployeeSMOrder;
import com.xinyuan.model.Security.SecurityVisitOrder;
import com.xinyuan.model.SharedOrder.SharedOutOrder;
import com.xinyuan.model.User.User;

public class OrderNOGenerator {
	
	private static Map<String, String> orderNOPrefixMap = new HashMap<String, String>();
	private static Map<String, String> previousOrderNOMap = new HashMap<String, String>();
	private static Map<String, String> withSecondOrderMap = new HashMap<String, String>();
	
	static {
		
		// HumanResource
		orderNOPrefixMap.put(Employee.class.getName(), 					"YG");
		orderNOPrefixMap.put(EmployeeATTOrder.class.getName(), 			"KQD");
		orderNOPrefixMap.put(EmployeeBMOrder.class.getName(), 			"CCHWCD");
		orderNOPrefixMap.put(EmployeeATTDROrder.class.getName(), 		"CQD");
		orderNOPrefixMap.put(EmployeeDormitoryOrder.class.getName(), 	"SSD");
		
		orderNOPrefixMap.put(EmployeeLeaveOrder.class.getName(), 		"QJD");
		orderNOPrefixMap.put(EmployeeMEOrder.class.getName(), 			"KHD");
		orderNOPrefixMap.put(SharedOutOrder.class.getName(), 			"WCD");
		orderNOPrefixMap.put(EmployeeOTOrder.class.getName(), 			"JBD");
		orderNOPrefixMap.put(EmployeeQuitOrder.class.getName(), 		"LZD");
		
		orderNOPrefixMap.put(EmployeeQuitPassOrder.class.getName(), 	"FXD");
		orderNOPrefixMap.put(EmployeeATTFixOrder.class.getName(), 		"BQD");
		orderNOPrefixMap.put(EmployeeSDOrder.class.getName(), 			"ZBD");
		orderNOPrefixMap.put(EmployeeSMOrder.class.getName(), 			"YCD");
		
		
		// Security
		orderNOPrefixMap.put(SecurityVisitOrder.class.getName(), 		"FKD");
		withSecondOrderMap.put("FKD", ConfigConstants.EMPTY_STRING);
		
		
		// Finanace
		
		
		
		
		
	}
	
	// in BaseAction Create() method
	public static void setOrderBasicCreateDetail(BaseModel model) {
		Date date = new Date();
		model.setCreateDate(date);
		User user = (User)SessionManager.get(ConfigConstants.SIGNIN_USER);
		model.setCreateUser(user.getUsername());
		
		// Generate the orderNO
		String modelClassName = model.getClass().getName();
		String orederPrefix = orderNOPrefixMap.get(modelClassName);
		String previousOrderNO = previousOrderNOMap.get(modelClassName);
		
		String format = withSecondOrderMap.containsKey(orederPrefix) ? ConfigFormat.DATESTRING_WITH_SECOND_FORMAT : ConfigFormat.DATESTRING_WITHOUT_SECOND_FORMAT;
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);  
		String dateString = sdf.format(date);
		String orderNO = orederPrefix + dateString;
		if (previousOrderNO != null && previousOrderNO.contains(orderNO)) {
			orderNO = generateOrderNO(orederPrefix, sdf, date, previousOrderNO);
		}
		
		model.setOrderNO(orderNO);		// TODO: check the database if already have this no.
		previousOrderNOMap.put(modelClassName, orderNO);
	}
	
	private static String generateOrderNO(String orederPrefix, SimpleDateFormat sdf, Date date, String previousOrderNO) {
		String previousOrderDigit = previousOrderNO.replaceAll("\\D+","");
//		int previousDigitCount = previousOrderDigit.length();
//		int formatCount = FormatConfig.DATESTRING_WITH_SECOND_FORMAT.length();
//		boolean isPreviousFormatWithSecond = previousDigitCount >= formatCount;
		
		// digit add 1 to avoid same in second
		sdf.applyPattern(ConfigFormat.DATESTRING_WITH_SECOND_FORMAT);
		String dateString = sdf.format(date);
		
		// the second + 1
		if (dateString.equals(previousOrderDigit)) {
			String lastStr = dateString.substring(dateString.length() - 1);
			int i = Integer.valueOf(lastStr);
			dateString = dateString.substring(0, dateString.length() - 1) + (i+1) ;
		}
		
		String newOrderNO = orederPrefix + dateString;
		return newOrderNO;
	}

}
