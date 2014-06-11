package com.xinyuan.model.Warehouse;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp2;


/**
 * 借出单
 *
 */

@Entity
@Table
public class WHLendOutOrder extends OrderApp2 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date lendDate;                //借出日期
	private Date planReturnDate;          //预计还入日期
	
	private String staffCategory;		  //人员类别
	private String staffNO;				  //借出人员 
	
	private String productCode;           //产品代码
	private String productName;           //产品名称
	private float lendAmount;             //借出数量
	private String unit;                  //单位
	
	private String remark;                //备注
	
	
	
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

	public float getLendAmount() {
		return lendAmount;
	}

	public void setLendAmount(float lendAmount) {
		this.lendAmount = lendAmount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(columnDefinition="TEXT")
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getLendDate() {
		return lendDate;
	}

	public void setLendDate(Date lendDate) {
		this.lendDate = lendDate;
	}

	public Date getPlanReturnDate() {
		return planReturnDate;
	}

	public void setPlanReturnDate(Date planReturnDate) {
		this.planReturnDate = planReturnDate;
	}

	public String getStaffCategory() {
		return staffCategory;
	}

	public void setStaffCategory(String staffCategory) {
		this.staffCategory = staffCategory;
	}

	public String getStaffNO() {
		return staffNO;
	}

	public void setStaffNO(String staffNO) {
		this.staffNO = staffNO;
	}
	
	

//	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)	// LAZY : http://stackoverflow.com/a/2192256/1749293
//	@JoinColumn(name="WHLendOutOrder_id")						// This will add a column "WHLendOutOrder_id" in WHLendOutBill table, if not written, DB will create a middle-join table .
//	public Set<WHLendOutBill> getBills() {
//		return bills;
//	}
//
//	public void setBills(Set<WHLendOutBill> bills) {
//		this.bills = bills;
//	}
	
}
