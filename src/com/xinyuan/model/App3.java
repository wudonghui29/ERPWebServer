package com.xinyuan.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class App3 extends App2 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String app3 ;		// username , employeeNO
	
	public String getApp3() {
		return app3;
	}
	public void setApp3(String app3) {
		this.app3 = app3;
	}

}
