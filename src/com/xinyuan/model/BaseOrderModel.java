package com.xinyuan.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;



@MappedSuperclass
public class BaseOrderModel {
	
	protected int id ;
	protected String orderNO ; 	// the NO. of this order
	protected Date createDate; 	// the date of creating this order
	protected User createUser;	// the user of creating this order
	
	protected User forwardUser; // the user current forwarding to this order
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(unique=true)
	public String getOrderNO() {
		return orderNO;
	}
	public void setOrderNO(String orderNO) {
		this.orderNO = orderNO;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@OneToOne
	public User getCreateUser() {
		return createUser;
	}
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
	
	@OneToOne
	public User getForwardUser() {
		return forwardUser;
	}
	public void setForwardUser(User forwardUser) {
		this.forwardUser = forwardUser;
	}
	
}
