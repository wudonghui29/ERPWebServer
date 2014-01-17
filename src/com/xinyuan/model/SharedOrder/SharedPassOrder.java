package com.xinyuan.model.SharedOrder;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp2;

@Entity
@Table
public class SharedPassOrder extends OrderApp2 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date orderDate;		//日期

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	

}
