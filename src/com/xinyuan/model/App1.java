package com.xinyuan.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class App1 extends BaseOrder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String app1 ;		// username , employeeNO
	
	
	public String getApp1() {
		return app1;
	}

	public void setApp1(String app1) {
		this.app1 = app1;
	}

}
