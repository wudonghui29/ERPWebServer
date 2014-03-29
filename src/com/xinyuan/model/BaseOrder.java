package com.xinyuan.model;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;



@MappedSuperclass
public class BaseOrder extends BaseEntity {
	
	protected static final long serialVersionUID = 1L;
	protected String orderNO ; 		// the NO. of this order
	
	@Column(unique=true,updatable=false)
	public String getOrderNO() {
		return orderNO;
	}
	public void setOrderNO(String orderNO) {
		this.orderNO = orderNO;
	}
	
}
