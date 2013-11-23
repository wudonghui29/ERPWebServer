package com.xinyuan.message;

import java.util.HashMap;
import java.util.Map;

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
import com.xinyuan.model.HumanResource.EmployeeSMOrder;
import com.xinyuan.model.Security.SecurityVisitOrder;
import com.xinyuan.model.SharedOrder.SharedOutOrder;
import com.xinyuan.model.Warehouse.WHMaterialOrder;

public class ConfigFormat {

	public static final String STRING_TO_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATESTRING_WITH_SECOND_FORMAT = "yyyyMMddHHmmss";
	public static final String DATESTRING_WITHOUT_SECOND_FORMAT = "yyyyMMddHHmm";
	
	
	
	// Order NO Prefix
	public static final Map<String, String> orderNOPrefixMap = new HashMap<String, String>();
	public static final Map<String, String> previousOrderNOMap = new HashMap<String, String>();
	public static final Map<String, String> secondOrderTypeMap = new HashMap<String, String>();
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
		orderNOPrefixMap.put(EmployeeSMOrder.class.getName(), 			"YCD");
		
		
		// Security
		orderNOPrefixMap.put(SecurityVisitOrder.class.getName(), 		"FKD");
		secondOrderTypeMap.put("FKD", ConfigConstants.EMPTY_STRING);
		
		
		// Finanace
		
		
		
		// Warehouse
		orderNOPrefixMap.put(WHMaterialOrder.class.getName(), "CLD");
		
	}
	
}
