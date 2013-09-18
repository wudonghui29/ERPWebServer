package com.modules.util;

import com.google.gson.Gson;

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
	
	public static void main(String[] args) {
		String json = "{\"name\":\"改过\",\"dob\":1247626770406,\"inTime\":1247626770406}";  
	    Worker w4 = (Worker) new Gson().fromJson(json,Worker.class);  
	}
	
}
