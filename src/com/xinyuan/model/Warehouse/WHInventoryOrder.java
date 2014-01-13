package com.xinyuan.model.Warehouse;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.NotEmpty;

import com.xinyuan.model.BaseOrder;

@Entity
@Table
public class WHInventoryOrder extends BaseOrder {

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
	private float remainAmount;         //剩余库存
	
	private String oneUnit;             //一个单位
	private float amount;               //数量
	private String unit;                //单位
	
	private String productDesc;         //产品描述
	private float priceBasicUnit;       //平均价格_基本单位
	
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
	public float getRemainAmount() {
		return remainAmount;
	}
	public void setRemainAmount(float remainAmount) {
		this.remainAmount = remainAmount;
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
	public String getOneUnit() {
		return oneUnit;
	}
	public void setOneUnit(String oneUnit) {
		this.oneUnit = oneUnit;
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
	
	
	

}
