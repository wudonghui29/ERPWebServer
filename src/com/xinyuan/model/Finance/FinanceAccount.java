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
	private String bank ; 				// 银行
	private String branch;				// 分支行
	
	private String address;				// 地址
	
	private String currency;			// 币别
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


	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
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
