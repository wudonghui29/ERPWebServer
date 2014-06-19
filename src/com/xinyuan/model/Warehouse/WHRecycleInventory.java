package com.xinyuan.model.Warehouse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.BaseOrder;

@Entity
@Table
public class WHRecycleInventory extends BaseOrder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String productCode;         //产品代码
	private String productName;         //品   名
	private String productCategory;     //产品分类
	
	private float totalAmount;          //总数量
	private String basicUnit;           //基本单位
	
	private float amount;               //数量
	private String unit;                //单位
	
	private float priceBasicUnit;       //平均价格_基本单位
	private String productLocation;     //产品位置
	
	private String productDesc;         //产品描述
	private String productDescPDF;      //产品描述PDF
	
	
	
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
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getBasicUnit() {
		return basicUnit;
	}
	public void setBasicUnit(String basicUnit) {
		this.basicUnit = basicUnit;
	}
	
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public float getPriceBasicUnit() {
		return priceBasicUnit;
	}
	public void setPriceBasicUnit(float priceBasicUnit) {
		this.priceBasicUnit = priceBasicUnit;
	}
	
	@Column(columnDefinition = "TEXT")
	public String getProductLocation() {
		return productLocation;
	}
	public void setProductLocation(String productLocation) {
		this.productLocation = productLocation;
	}
	
	@Column(columnDefinition = "TEXT")
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductDescPDF() {
		return productDescPDF;
	}
	public void setProductDescPDF(String productDescPDF) {
		this.productDescPDF = productDescPDF;
	}
	
	
	

}
