package com.xinyuan.model.Business;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.BaseBill;

@Entity
@Table
public class ContractReceiveBill extends BaseBill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String receiveOrder;   //收款单
	private Date realReceiveDate;  //实际收款日期
	private String receiveMan;     //收款人
	private float receiveAmount;   //收款金额
	
	public String getReceiveOrder() {
		return receiveOrder;
	}
	public void setReceiveOrder(String receiveOrder) {
		this.receiveOrder = receiveOrder;
	}
	public Date getRealReceiveDate() {
		return realReceiveDate;
	}
	public void setRealReceiveDate(Date realReceiveDate) {
		this.realReceiveDate = realReceiveDate;
	}
	public String getReceiveMan() {
		return receiveMan;
	}
	public void setReceiveMan(String receiveMan) {
		this.receiveMan = receiveMan;
	}
	public float getReceiveAmount() {
		return receiveAmount;
	}
	public void setReceiveAmount(float receiveAmount) {
		this.receiveAmount = receiveAmount;
	}
	
	
	
	

}
