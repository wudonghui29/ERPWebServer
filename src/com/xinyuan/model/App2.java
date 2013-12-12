package com.xinyuan.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class App2 extends App1 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String app2 ;		// username , employeeNO
	
	public String getApp2() {
		return app2;
	}
	public void setApp2(String app2) {
		this.app2 = app2;
	}

}
