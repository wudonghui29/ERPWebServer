package com.xinyuan.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BillApp4 extends BillApp3 implements IApp4 {

	private static final long serialVersionUID = 1L;
	
	private String app4 ;		// username , employeeNO
	
	public String getApp4() {
		return app4;
	}
	public void setApp4(String app4) {
		this.app4 = app4;
	}
	
	
}
