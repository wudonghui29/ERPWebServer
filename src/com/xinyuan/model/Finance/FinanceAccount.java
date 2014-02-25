package com.xinyuan.model.Finance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.xinyuan.model.BaseEntity;

@Table
@Entity
public class FinanceAccount extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String number ;				// 帐户编号
	private String name; 				// 户名
	private String category ; 			// 银行类别
	
	private String address;				// 地址
	
	private String currency;			// 币别
	private String subBranch;			// 分支行
	private float amount;				// 账户金额
	
	private String bankAccountNumber; 	// 账号

	
	@NotEmpty
	@Column(unique=true)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@NotEmpty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@NotEmpty
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSubBranch() {
		return subBranch;
	}

	public void setSubBranch(String subBranch) {
		this.subBranch = subBranch;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@NotEmpty
	@Column(unique=true)
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	
}
