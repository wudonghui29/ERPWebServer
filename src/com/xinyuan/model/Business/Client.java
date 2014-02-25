package com.xinyuan.model.Business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.BaseEntity;

/**
 * 
 * 客户资料
 *
 */

@Entity
@Table
public class Client extends BaseEntity {


	private static final long serialVersionUID = 1L;
	
	private String number ;		// 客户编号
	private String category ; 	// 客户类别
	private String name ;		// 客户名称
	
	private String principal;	// 负责人
	private String phoneNO;		// 电话
	private String faxNO;		// 传真
	private String address;		// 地址
	private String postcode;	// 邮政编码
	private String webSite;		// 网址
	
	private String contactOne;	// 联络人1 "name.phoneNO" , i.e. "小明.1381123456", photo name "小明.1381123456.png"
	private String contactTwo;	// 联络人2
	
	// Same as Vendor Above
	//--------------------------------
	
	private String taxNumber;	// 纳税编号
	private String bankDeposit;	// 开户行
	private String bankAccount;	// 账号
	
	
	@Column(unique=true)
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getPhoneNO() {
		return phoneNO;
	}
	public void setPhoneNO(String phoneNO) {
		this.phoneNO = phoneNO;
	}
	public String getFaxNO() {
		return faxNO;
	}
	public void setFaxNO(String faxNO) {
		this.faxNO = faxNO;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public String getContactOne() {
		return contactOne;
	}
	public void setContactOne(String contactOne) {
		this.contactOne = contactOne;
	}
	public String getContactTwo() {
		return contactTwo;
	}
	public void setContactTwo(String contactTwo) {
		this.contactTwo = contactTwo;
	}
	public String getTaxNumber() {
		return taxNumber;
	}
	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}
	public String getBankDeposit() {
		return bankDeposit;
	}
	public void setBankDeposit(String bankDeposit) {
		this.bankDeposit = bankDeposit;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	
}
