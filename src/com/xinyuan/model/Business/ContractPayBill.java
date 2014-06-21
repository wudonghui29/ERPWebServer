package com.xinyuan.model.Business;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.BaseBill;

@Entity
@Table
public class ContractPayBill extends BaseBill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String installment;         //分期付款
	private Date willPayDate;           //预定付款日期
	private String payRate;             //付款比率
	private float payAmount;            //金额
	
	public String getInstallment() {
		return installment;
	}
	public void setInstallment(String installment) {
		this.installment = installment;
	}
	public Date getWillPayDate() {
		return willPayDate;
	}
	public void setWillPayDate(Date willPayDate) {
		this.willPayDate = willPayDate;
	}
	public String getPayRate() {
		return payRate;
	}
	public void setPayRate(String payRate) {
		this.payRate = payRate;
	}
	public float getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(float payAmount) {
		this.payAmount = payAmount;
	}
	
	

}
