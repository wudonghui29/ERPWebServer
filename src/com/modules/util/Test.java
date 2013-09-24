package com.modules.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.xinyuan.interceptor.PermissionInterceptor;

public class Test {

	private class Worker {
		private String name;  
		  
		private java.sql.Date dob;  
		  
		private java.util.Date inTime;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public java.sql.Date getDob() {
			return dob;
		}

		public void setDob(java.sql.Date dob) {
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
	public static void main(String[] args) {
		String havePermission = "H.E.read, H.E.create, H.J.*";
		String[] has = havePermission.split(",");
		
		String modelsStr = ".E";
		String[] array = modelsStr.split(",");
		Gson gson = new GsonBuilder().create();
		JsonArray models = gson.toJsonTree(array).getAsJsonArray();
		
		boolean allowed = PermissionInterceptor.checkPermission("H", "read", models, has);
		
		System.out.println("");
	}
	
}
