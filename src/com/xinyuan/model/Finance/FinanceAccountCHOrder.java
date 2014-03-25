package com.xinyuan.model.Finance;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp1;

@Table
@Entity
public class FinanceAccountCHOrder extends OrderApp1 {

	private static final long serialVersionUID = 1L;
	
	private String number ;				// 帐户编号
	
	private String name_N; 				// 户名
	private String bankAccountNumber_N;	// 账号
	private float amount_N;				// 账户金额
	
	
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
	
	public String getName_N() {
		return name_N;
	}
	public void setName_N(String name_N) {
		this.name_N = name_N;
	}
	public float getAmount_N() {
		return amount_N;
	}
	public void setAmount_N(float amount_N) {
		this.amount_N = amount_N;
	}
	public String getBankAccountNumber_N() {
		return bankAccountNumber_N;
	}
	public void setBankAccountNumber_N(String bankAccountNumber_N) {
		this.bankAccountNumber_N = bankAccountNumber_N;
	}
	
}
