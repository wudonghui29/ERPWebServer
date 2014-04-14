package com.xinyuan.model.Warehouse;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp4;


/**
 * 进货单
 *
 */


@Entity
@Table
public class WHPurchaseOrder extends OrderApp4 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String buyNO;           //采购单号
	private Date purchaseDate;      //进货日期
	
	private String company;         //厂   商
	private String contactPeople;   //联 系 人
	private String companyTel;      //厂商电话
	
	private String payCondition;    //付款条件
	private String payMode;         //付款方式
	private float freight;          //运   费
	
	private float deliveryTotal;    //进货总计
	private float totalPay;         //应   付
	private float storageTotal;     //入库总计
	
	private Set<WHPurchaseBill> WHPurchaseBills;    //进货的Bill
	
	public String getBuyNO() {
		return buyNO;
	}
	public void setBuyNO(String buyNO) {
		this.buyNO = buyNO;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getContactPeople() {
		return contactPeople;
	}
	public void setContactPeople(String contactPeople) {
		this.contactPeople = contactPeople;
	}
	public String getCompanyTel() {
		return companyTel;
	}
	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}
	public String getPayCondition() {
		return payCondition;
	}
	public void setPayCondition(String payCondition) {
		this.payCondition = payCondition;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public float getFreight() {
		return freight;
	}
	public void setFreight(float freight) {
		this.freight = freight;
	}
	
	public float getDeliveryTotal() {
		return deliveryTotal;
	}
	public void setDeliveryTotal(float deliveryTotal) {
		this.deliveryTotal = deliveryTotal;
	}
	
	public float getTotalPay() {
		return totalPay;
	}
	public void setTotalPay(float totalPay) {
		this.totalPay = totalPay;
	}
	public float getStorageTotal() {
		return storageTotal;
	}
	public void setStorageTotal(float storageTotal) {
		this.storageTotal = storageTotal;
	}
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="WHPurchaseOrder_id")							
	public Set<WHPurchaseBill> getWHPurchaseBills() {
		return WHPurchaseBills;
	}
	public void setWHPurchaseBills(Set<WHPurchaseBill> wHPurchaseBills) {
		WHPurchaseBills = wHPurchaseBills;
	}
	
	

}
