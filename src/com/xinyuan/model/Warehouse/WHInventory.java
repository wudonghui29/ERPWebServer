package com.xinyuan.model.Warehouse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.xinyuan.model.OrderApp1;
/**
 * 仓库库存
 *
 */

@Entity
@Table
public class WHInventory extends OrderApp1 {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String productCode;         //产品代码
	private String productName;         //品   名
	private String productCategory;     //产品分类
	
	private float totalAmount;          //总数量
	private float lendAmount;           //借出数量
	private String basicUnit;           //基本单位
	
	private float amount;               //数量
	private String unit;                //单位
	
	private float priceBasicUnit;       //单价_基本单位
	private float priceUnit;            //单价_单位
	private String productLocation;     //产品位置
	
	private String productDesc;         //产品描述
	private String productDescPDF;      //产品描述PDF
	
	private String supplierDesc;        //供应商
	
	@NotNull
	@NotEmpty
	@Column(unique=true)
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	@NotEmpty
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
	
	public float getLendAmount() {
		return lendAmount;
	}
	
	public void setLendAmount(float lendAmount) {
		this.lendAmount = lendAmount;
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
	
	@Column(columnDefinition = "TEXT")
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
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
	public String getProductDescPDF() {
		return productDescPDF;
	}
	public void setProductDescPDF(String productDescPDF) {
		this.productDescPDF = productDescPDF;
	}
	
	@Column(columnDefinition = "TEXT")
	public String getSupplierDesc() {
		return supplierDesc;
	}
	public void setSupplierDesc(String supplierDesc) {
		this.supplierDesc = supplierDesc;
	}
	public float getPriceUnit() {
		return priceUnit;
	}
	public void setPriceUnit(float priceUnit) {
		this.priceUnit = priceUnit;
	}
	

	
}
