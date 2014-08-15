package com.xinyuan.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BillApp1 extends BaseBill implements IApp1 {

	private static final long serialVersionUID = 1L;

	
	private String app1 ;		// username , employeeNO
	protected String forwardUser; 	// the username current forwarding to this order
	
	
	public String getApp1() {
		return app1;
	}
	public void setApp1(String app1) {
		this.app1 = app1;
	}

	public String getForwardUser() {
		return forwardUser;
	}
	public void setForwardUser(String forwardUser) {
		this.forwardUser = forwardUser;
	}
	
}
