package com.xinyuan.model.Warehouse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.LevelApp_1;

/**
 * 
 * Bill of Material BOM  物料清单/材料清单
 *
 */

@Entity
@Table
public class WHMaterialOrder extends LevelApp_1 {

	private String productCode ;		// 产品代码
	private String productName ;		// 品名
	private String productCategory ;	// 产品分类
	
	private float storedAmount;			// 库存数量
	private String baseUnit;			// 基本单位
	
	
	private String supplierNO;			// 供应商
	
	private String description;			// 产品描述
	
	
	@Column(unique=true)
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
	public float getStoredAmount() {
		return storedAmount;
	}
	public void setStoredAmount(float storedAmount) {
		this.storedAmount = storedAmount;
	}
	public String getBaseUnit() {
		return baseUnit;
	}
	public void setBaseUnit(String baseUnit) {
		this.baseUnit = baseUnit;
	}
	public String getSupplierNO() {
		return supplierNO;
	}
	public void setSupplierNO(String supplierNO) {
		this.supplierNO = supplierNO;
	}
	@Column(columnDefinition="TEXT")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
