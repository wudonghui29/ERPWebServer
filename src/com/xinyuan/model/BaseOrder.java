package com.xinyuan.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;



@MappedSuperclass
public class BaseOrder extends BaseModel {
	
	private static final long serialVersionUID = 1L;
	protected String orderNO ; 		// the NO. of this order
	protected Date expiredDate;		// The expired date
	protected boolean exception;	// Exception or not
	
	@Column(unique=true)
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
	
	public boolean isException() {
		return exception;
	}
	public void setException(boolean exception) {
		this.exception = exception;
	}
	
}
