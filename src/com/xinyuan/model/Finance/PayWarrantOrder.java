package com.xinyuan.model.Finance;

import java.sql.Date;

import com.xinyuan.model.Level_Five_Approve;

/**
 * 
 * 支付证明单
 *
 */

public class PayWarrantOrder extends Level_Five_Approve {
	
	private String catalog ; //科目
	private Date orderDate ; //日期
	private int number;		 //第几号
	private String abstracts; //摘要
	private float quantity; 	//数量
	private float unit;		//单位
	private float unitPrice;  //单价
	
	private String receiver; //收款人
	private String description; //备注
	
	
	public String getCatalog() {
		return catalog;
	}
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getAbstracts() {
		return abstracts;
	}
	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}
	public float getQuantity() {
		return quantity;
	}
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	public float getUnit() {
		return unit;
	}
	public void setUnit(float unit) {
		this.unit = unit;
	}
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
