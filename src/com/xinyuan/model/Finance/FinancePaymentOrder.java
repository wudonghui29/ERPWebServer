package com.xinyuan.model.Finance;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp4;

/**
 * 
 * 零用金申请单
 * 
 */

@Entity
@Table
public class FinancePaymentOrder extends OrderApp4 {

	private static final long serialVersionUID = 1L;
	
	private String staffCategory;		 //人员类别
	private String staffNO;				 //申请人
	
	private String payWay;				// 付款方式
	private String bankAccount;			// 付款帐户
	
	private String feeDescription;		// 费用说明
	

	public String getStaffCategory() {
		return staffCategory;
	}

	public void setStaffCategory(String staffCategory) {
		this.staffCategory = staffCategory;
	}


	public String getStaffNO() {
		return staffNO;
	}


	public void setStaffNO(String staffNO) {
		this.staffNO = staffNO;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getBankAccount() {
		return bankAccount;
	}


	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}


	public String getFeeDescription() {
		return feeDescription;
	}


	public void setFeeDescription(String feeDescription) {
		this.feeDescription = feeDescription;
	}

}
