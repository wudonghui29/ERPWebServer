package com.xinyuan.model.Warehouse;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.BaseBill;

@Entity
@Table
public class WHPurchaseBill extends BaseBill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String productCode;         //产品代码
	private String productName;         //品名
	private String purchaseQC;          //进货质检单

	private float  num;                 //数量
	private String unit;                //单位
	private float  unitPrice;           //单价
	private float  subTotal;            //小计
	
	private float  storageNum;          //入库数量
	private String storageUnit;         //入库单位
	private float  storageUnitPrice;    //入库单价
	private float  storageSubTotal;     //入库小计
	
	
	
	
	
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

	public String getPurchaseQC() {
		return purchaseQC;
	}
	public void setPurchaseQC(String purchaseQC) {
		this.purchaseQC = purchaseQC;

	}
	public float getNum() {
		return num;
	}
	public void setNum(float num) {
		this.num = num;
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
	public float getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}
	public float getStorageNum() {
		return storageNum;
	}
	public void setStorageNum(float storageNum) {
		this.storageNum = storageNum;
	}
	public String getStorageUnit() {
		return storageUnit;
	}
	public void setStorageUnit(String storageUnit) {
		this.storageUnit = storageUnit;
	}
	public float getStorageUnitPrice() {
		return storageUnitPrice;
	}
	public void setStorageUnitPrice(float storageUnitPrice) {
		this.storageUnitPrice = storageUnitPrice;
	}
	public float getStorageSubTotal() {
		return storageSubTotal;
	}
	public void setStorageSubTotal(float storageSubTotal) {
		this.storageSubTotal = storageSubTotal;
	}

	
	
}
