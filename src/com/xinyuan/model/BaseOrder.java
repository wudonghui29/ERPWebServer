package com.xinyuan.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;



@MappedSuperclass
public class BaseOrder extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String orderNO ; 		// the NO. of this order
	protected Date expiredDate;		// The expired date
	protected String forwardUser; 	// the username current forwarding to this order
	
	
	@Column(unique=true, updatable=false)
	public String getOrderNO() {
		return orderNO;
	}
	public void setOrderNO(String orderNO) {
		this.orderNO = orderNO;
	}
	
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
