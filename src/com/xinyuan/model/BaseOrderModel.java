package com.xinyuan.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;



@MappedSuperclass
public class BaseOrderModel {
	
	protected int id ;
	protected String orderNO ; 	// the NO. of this order
	protected Date createDate; 	// the date of creating this order
	protected String createUser;	// the username of creating this order
	
	protected String forwardUser; 	// the username current forwarding to this order
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(unique=true, updatable=false)
	public String getOrderNO() {
		return orderNO;
	}
	public void setOrderNO(String orderNO) {
		this.orderNO = orderNO;
	}
	
	@Column(updatable=false)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(updatable=false)
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	public String getForwardUser() {
		return forwardUser;
	}
	public void setForwardUser(String forwardUser) {
		this.forwardUser = forwardUser;
	}
	
}
