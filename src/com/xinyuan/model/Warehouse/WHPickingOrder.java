package com.xinyuan.model.Warehouse;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp2;

@Entity
@Table
public class WHPickingOrder extends OrderApp2 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date pickingDate;           //领出日期
	private String pickingStaff;        //领用人员
	
	private String productCategory;     //产品分类
	private String productCode;         //产品代码
	private String productName;         //品   名
	private String application;         //用   途
	private String applicationDesc;     //用途描述
	
	private float pickingAmount;        //领出数量
	private String unit;                //单   位
	private float unitPrice;            //单   价
	private float totalPrice;           //总   价
	private float recycleAmount;        //回收数量
	
	private String remark;              //备忘记事
	
	
	
	public Date getPickingDate() {
		return pickingDate;
	}
	public void setPickingDate(Date pickingDate) {
		this.pickingDate = pickingDate;
	}
	public String getPickingStaff() {
		return pickingStaff;
	}
	public void setPickingStaff(String pickingStaff) {
		this.pickingStaff = pickingStaff;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public float getPickingAmount() {
		return pickingAmount;
	}
	public void setPickingAmount(float pickingAmount) {
		this.pickingAmount = pickingAmount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public float getRecycleAmount() {
		return recycleAmount;
	}
	public void setRecycleAmount(float recycleAmount) {
		this.recycleAmount = recycleAmount;
	}
	
	@Column(columnDefinition="TEXT")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getApplicationDesc() {
		return applicationDesc;
	}
	public void setApplicationDesc(String applicationDesc) {
		this.applicationDesc = applicationDesc;
	}

	
	

}
