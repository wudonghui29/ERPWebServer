package com.xinyuan.model.Finance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.xinyuan.model.OrderApp1;

@Table
@Entity
public class FinanceAccountCHOrder extends OrderApp1 {

	private static final long serialVersionUID = 1L;
	
	private int financeAccountId;		// 对应帐户ID (因为它的编号也会变动)
	
	private String number_O ;			// 帐户编号
	private String name_O; 				// 户名
	private String bankAccountNumber_O;	// 账号
	private float amount_O;				// 账户金额
	private String address_O;			// 地址
	
	
	private String number_N ;			// 帐户编号
	private String name_N; 				// 户名
	private String bankAccountNumber_N;	// 账号
	private float amount_N;				// 账户金额
	private String address_N;			// 地址
	
	
	public int getFinanceAccountId() {
		return financeAccountId;
	}
	public void setFinanceAccountId(int financeAccountId) {
		this.financeAccountId = financeAccountId;
	}
	
	@NotEmpty
	@Column(unique=true)
	public String getNumber_O() {
		return number_O;
	}
	public void setNumber_O(String number_O) {
		this.number_O = number_O;
	}
	public String getName_O() {
		return name_O;
	}
	public void setName_O(String name_O) {
		this.name_O = name_O;
	}
	public String getBankAccountNumber_O() {
		return bankAccountNumber_O;
	}
	public void setBankAccountNumber_O(String bankAccountNumber_O) {
		this.bankAccountNumber_O = bankAccountNumber_O;
	}
	public float getAmount_O() {
		return amount_O;
	}
	public void setAmount_O(float amount_O) {
		this.amount_O = amount_O;
	}
	public String getAddress_O() {
		return address_O;
	}
	public void setAddress_O(String address_O) {
		this.address_O = address_O;
	}
	
	@NotEmpty
	@Column(unique=true)
	public String getNumber_N() {
		return number_N;
	}
	public void setNumber_N(String number_N) {
		this.number_N = number_N;
	}
	public String getName_N() {
		return name_N;
	}
	public void setName_N(String name_N) {
		this.name_N = name_N;
	}
	public String getBankAccountNumber_N() {
		return bankAccountNumber_N;
	}
	public void setBankAccountNumber_N(String bankAccountNumber_N) {
		this.bankAccountNumber_N = bankAccountNumber_N;
	}
	public float getAmount_N() {
		return amount_N;
	}
	public void setAmount_N(float amount_N) {
		this.amount_N = amount_N;
	}
	public String getAddress_N() {
		return address_N;
	}
	public void setAddress_N(String address_N) {
		this.address_N = address_N;
	}
	
}
