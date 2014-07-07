package com.xinyuan.model.Business;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp4;

@Entity
@Table
public class Contract extends OrderApp4 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String number;		       // 客户编号
	private String name;		       // 客户名称
	private String contact;	           // 联络人
	private String phoneNO;		       // 电话
	private String faxNO;		       // 传真
	private String salesMan;           // 业务员

	private float totalAmount;         // 总金额
	private float shouldReceive;       // 应收
	private float unReceive;           // 未收
	
	private String attachFile;         // 合同附档
	
	/*建议*/                  
	private String generalMgrAdvise;   // 总经理
	private String viceMgrAdvise;      // 副总经理
	private String purchaseSprAdvise;  // 采购主管
	private String businessSprAdvise;  // 业务主管
	private String salesManAdvise;     // 业务员
	
	private Set<ContractPayBill> ContractPayBills;    //分期付款的Bill
	
	
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
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
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
	public String getSalesMan() {
		return salesMan;
	}
	public void setSalesMan(String salesMan) {
		this.salesMan = salesMan;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public float getShouldReceive() {
		return shouldReceive;
	}
	public void setShouldReceive(float shouldReceive) {
		this.shouldReceive = shouldReceive;
	}
	public float getUnReceive() {
		return unReceive;
	}
	public void setUnReceive(float unReceive) {
		this.unReceive = unReceive;
	}
	public String getAttachFile() {
		return attachFile;
	}
	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}
	@Column(columnDefinition="TEXT")
	public String getGeneralMgrAdvise() {
		return generalMgrAdvise;
	}
	public void setGeneralMgrAdvise(String generalMgrAdvise) {
		this.generalMgrAdvise = generalMgrAdvise;
	}
	@Column(columnDefinition="TEXT")
	public String getViceMgrAdvise() {
		return viceMgrAdvise;
	}
	public void setViceMgrAdvise(String viceMgrAdvise) {
		this.viceMgrAdvise = viceMgrAdvise;
	}
	@Column(columnDefinition="TEXT")
	public String getPurchaseSprAdvise() {
		return purchaseSprAdvise;
	}
	public void setPurchaseSprAdvise(String purchaseSprAdvise) {
		this.purchaseSprAdvise = purchaseSprAdvise;
	}
	@Column(columnDefinition="TEXT")
	public String getBusinessSprAdvise() {
		return businessSprAdvise;
	}
	public void setBusinessSprAdvise(String businessSprAdvise) {
		this.businessSprAdvise = businessSprAdvise;
	}
	@Column(columnDefinition="TEXT")
	public String getSalesManAdvise() {
		return salesManAdvise;
	}
	public void setSalesManAdvise(String salesManAdvise) {
		this.salesManAdvise = salesManAdvise;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="Contract_id")	
	public Set<ContractPayBill> getContractPayBills() {
		return ContractPayBills;
	}
	public void setContractPayBills(Set<ContractPayBill> contractPayBills) {
		ContractPayBills = contractPayBills;
	}
	
						
	
	
	


}
