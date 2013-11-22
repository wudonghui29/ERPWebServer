package com.modules.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xinyuan.Util.ApnsHelper;
import com.xinyuan.Util.JsonHelper;
import com.xinyuan.action.HumanResourceAction;
import com.xinyuan.action.SettingAction;
import com.xinyuan.action.SuperAction;
import com.xinyuan.dao.impl.HumanResourceDAOIMP;
import com.xinyuan.interceptor.PermissionInterceptor;
import com.xinyuan.message.ConfigFormat;

public class Test extends HashSet {

	public class Worker  {
		private String name;  
		  
		private java.util.Date dob;  
		  
		private java.util.Date inTime;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public java.util.Date getDob() {
			return dob;
		}

		public void setDob(java.util.Date dob) {
			this.dob = dob;
		}

		public java.util.Date getInTime() {
			return inTime;
		}

		public void setInTime(java.util.Date inTime) {
			this.inTime = inTime;
		}
	}
	
	// Test delete order in approve
	public static void main__(String[] args) {
//		String json = "{\"name\":\"改过\",\"dob\":1247626770406,\"inTime\":1247626770406}";  
//	    Worker w4 = (Worker) new Gson().fromJson(json,Worker.class); 
		
//		String approveUserPendingOrder = "JH201203040506,JH201293940596,JH0002,JH0003" ;
		String approveUserPendingOrder = "" ;
		Boolean isEmpty = approveUserPendingOrder.isEmpty();
		String[] pendingList = approveUserPendingOrder.split(",");
		
		List<String> list = new ArrayList<String>(Arrays.asList(pendingList));
		list.removeAll(Arrays.asList("JH201203040506"));
		
		list.toArray(pendingList);
		
		String result = "";
		for (int i = 0; i < list.size(); i++) {
			String orderString = list.get(i);
			if (i != 0) result += ",";
			result += orderString ;
		}
		
//		String resultString = list.toString();
		String className = new Test().getClass().getName();
		String shortNameString = className.replace("com.modules.util.", "");
		
		
		System.out.println("dddd");
	}
	
	
	// Test Permission check 
	public static void main000(String[] args) {
		
		String perssionStr = "{\"Business\":{\"BusinessClient\":[\"read\",\"create\"]},\"Assistant\":{\"ASDeviceBatteryMROrder\":[\"read\",\"create\",\"modify\"]}}";

		JsonObject jsonObject = (JsonObject)(new JsonParser()).parse(perssionStr);
		Map<String, Object> permissions = JsonHelper.translateElementToMap(jsonObject);
		
		String modelsStr = ".Assistant.ASDeviceBatteryMROrder, .Business.BusinessClient";
		String[] array = modelsStr.split(",");
		List<String> models = Arrays.asList(array);
		
//		boolean allowed = PermissionInterceptor.checkPermission("Business", "read", models, permissions);
		boolean alloweda = PermissionInterceptor.checkPermission("read", models, permissions);  // for action = "super"
		
		System.out.println("");

	}
	
	public static void main_________(String[] args) {
//		String perssionStr = null;
		
		
//		boolean isEmpty = perssionStr.isEmpty();
//		JsonObject jsonObject = (JsonObject)(new JsonParser()).parse(perssionStr);
//		Map<String, Object> permissions = JsonHelper.translateElementToMap(jsonObject);
		
//		Employee employee = new Employee();
//		ModelHelper.setOrderBasicCreateDetail(employee);
		
//		String str = "YG201312011212";
//		String strDigit = str.replaceAll("\\D+","");
//		int digitCount = strDigit.length();
//		int formatCount = FormatConfig.DATESTRING_WITH_SECOND_FORMAT.length();
//		int formatWithoutSecondCount = FormatConfig.DATESTRING_WITHOUT_SECOND_FORMAT.length();
		
		String dateString = "20130408123005";
		String lastStr = dateString.substring(dateString.length() - 1);
		int i = Integer.valueOf(lastStr);
		dateString = dateString.substring(0, dateString.length() - 1) + (i+1) ;
		
		System.out.println(dateString);
	}
	
	// Test filter models
	public static void main_(String[] args) {
		
		HumanResourceDAOIMP humanResourceDAOIMP = new HumanResourceDAOIMP();
		
		System.err.println("");
	}
	
	
	public static void main___(String[] args) {
		SuperAction humanResourceAction = new HumanResourceAction();
		SuperAction superAction = new SuperAction();
		
		
		if (superAction.getClass() == SuperAction.class) {
			System.err.println("OK");
		} else {
			System.err.println("NOT OK");
		}
		
		
		Date date = new Date(System.currentTimeMillis());
		
		SimpleDateFormat sdf = new SimpleDateFormat(ConfigFormat.STRING_TO_DATE_FORMAT);
		
		String dateString = sdf.format(date);
		
		
		System.out.println("");
		
		
	}
	
	// TEST APNS
	 public static void main00000000000000(String[] args) {
//       String[] devices = {"7df340181160dcb82607885e332e770b497a754758592047646396cebc9ab913",  "9ab941ea30f5cc4db41fc0a5dbbeae2dfe6a9d0f8c3bca1b97cc5c043aff6be0"}; 
		 String[] devices = {"7df34018 1160dcb8 2607885e 332e770b 497a7547 58592047 646396ce bc9ab913",  "9ab941ea 30f5cc4d b41fc0a5 dbbeae2d fe6a9d0f 8c3bca1b 97cc5c04 3aff6be0"}; 
		 Map<String, Object> map = new HashMap<String, Object>();
		map.put("Alert",
				"Fucking!!!!!!!!!!!-Fucking-----!!!!Fucking!!!===sos os os os so so=Fucking====");
		map.put("Badge", 3 + "");
		map.put("Sound", "");
		map.put("ATTENDTIONS:", "YOU JUST A FUCKING GUY , DO YOU KNOW ?");

		try {
			ApnsHelper.push(map, devices);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	 
	 
	 public static void main____________(String[] args) {
		 SettingAction settingAction = new SettingAction();
		 settingAction.getApplicationModelsStructures();
	}
	 
	 
	 public static void main_2_(String[] args) {
		 List<String> list = null;
		 for (String string : list) {
			 System.out.println(string);
		 }
	}
	 
	 
}
