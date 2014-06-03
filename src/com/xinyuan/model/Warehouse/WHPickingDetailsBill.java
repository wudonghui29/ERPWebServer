package com.xinyuan.model.Warehouse;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.BaseBill;

@Entity
@Table
public class WHPickingDetailsBill extends BaseBill {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String projectName;         //工程名称
	private String productCode;         //产品代码
	private String productName;         //品名
	
	private float pickingAmount;        //取用量
	private String unit;                //单位
	private String pickingStaff;        //领用人员
	
	private float recycleAmount;        //回收数量
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
	public String getPickingStaff() {
		return pickingStaff;
	}
	public void setPickingStaff(String pickingStaff) {
		this.pickingStaff = pickingStaff;
	}
	public float getRecycleAmount() {
		return recycleAmount;
	}
	public void setRecycleAmount(float recycleAmount) {
		this.recycleAmount = recycleAmount;
	}
	
	
	
}
