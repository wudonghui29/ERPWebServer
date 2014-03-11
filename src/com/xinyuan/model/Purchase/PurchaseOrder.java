package com.xinyuan.model.Purchase;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp4;

@Table
@Entity
public class PurchaseOrder extends OrderApp4 {

	private static final long serialVersionUID = 1L;
	
	private String bookPurhaseNO;		// 请购单号
	private Date bookDate;				// 订购日期
	
	
	private String vendorNumber;		// 厂商编号
	private String contact;				// 联系人
	private String phoneNO;				// 电话

	
	private String payCondition;		// 付款条件
	private String payWay;				// 付款方式
	private float freight;				// 运费
	
	private Date deliveryDate;			// 交货日期
	private String deliveryPlace;		// 交货地址
	
	
	
	
	
	public String getBookPurhaseNO() {
		return bookPurhaseNO;
	}
	public void setBookPurhaseNO(String bookPurhaseNO) {
		this.bookPurhaseNO = bookPurhaseNO;
	}
	public Date getBookDate() {
		return bookDate;
	}
	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}
	public String getVendorNumber() {
		return vendorNumber;
	}
	public void setVendorNumber(String vendorNumber) {
		this.vendorNumber = vendorNumber;
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
	public String getPayCondition() {
		return payCondition;
	}
	public void setPayCondition(String payCondition) {
		this.payCondition = payCondition;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public float getFreight() {
		return freight;
	}
	public void setFreight(float freight) {
		this.freight = freight;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDeliveryPlace() {
		return deliveryPlace;
	}
	public void setDeliveryPlace(String deliveryPlace) {
		this.deliveryPlace = deliveryPlace;
	}
	
}
