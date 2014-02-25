package com.xinyuan.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;



@MappedSuperclass
public class BaseOrder extends BaseEntity {
	
	protected static final long serialVersionUID = 1L;
	protected String orderNO ; 		// the NO. of this order
	protected Date expiredDate;		// The expired date
	
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
	
}
