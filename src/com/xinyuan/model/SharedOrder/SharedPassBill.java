package com.xinyuan.model.SharedOrder;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.xinyuan.model.BaseBill;


@Entity
@Table
public class SharedPassBill extends BaseBill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String productName ;		// 品名
	private float amount;				// 数量
	private String unit;				// 单位
	private String comment;				//备注
	
	private Set<SharedPassBill> bills;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="SharedPassOrder_id")
	public Set<SharedPassBill> getBills() {
		return bills;
	}

	public void setBills(Set<SharedPassBill> bills) {
		this.bills = bills;
	}
	
}
