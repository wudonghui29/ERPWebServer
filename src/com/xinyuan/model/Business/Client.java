package com.xinyuan.model.Business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.App1;

@Entity
@Table
public class Client extends App1 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String employeeNO; 		// 业务员
	
	private String clientNO ;		// 厂商编号
	private String clientName ;		// 厂商名称
	private String clientAlias ; 	// 厂商简称
	private String clientCategory ; // 厂商类别
	
	private String unifiedCode ; 	// 统一编号
	private String contactOne  ;	// 联络人1
	private String contactTwo  ; 	// 联络人2
	private String address		;	// 地址
	
	
	private String principal;		// 负责人
	
	private String phoneNO ; 		// 电话
	private String faxNO;			// 传真
	private String homePage;  		// 网址
	private String postCode; 		// 邮政编号
	
	
	
	@Column(unique=true)
	public String getClientNO() {
		return clientNO;
	}
	public void setClientNO(String clientNO) {
		this.clientNO = clientNO;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientAlias() {
		return clientAlias;
	}
	public void setClientAlias(String clientAlias) {
		this.clientAlias = clientAlias;
	}
	public String getClientCategory() {
		return clientCategory;
	}
	public void setClientCategory(String clientCategory) {
		this.clientCategory = clientCategory;
	}
	public String getUnifiedCode() {
		return unifiedCode;
	}
	public void setUnifiedCode(String unifiedCode) {
		this.unifiedCode = unifiedCode;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getHomePage() {
		return homePage;
	}
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getEmployeeNO() {
		return employeeNO;
	}
	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
	}
}
