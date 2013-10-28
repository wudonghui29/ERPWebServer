package com.xinyuan.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;



@MappedSuperclass
public class OrderModel extends BaseModel {
	
	protected Date expiredDate;		// The expired date
	
	protected String forwardUser; 	// the username current forwarding to this order
	
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	
	public String getForwardUser() {
		return forwardUser;
	}
	public void setForwardUser(String forwardUser) {
		this.forwardUser = forwardUser;
	}
	
}
