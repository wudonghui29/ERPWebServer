package com.xinyuan.model.Warehouse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.BaseBill;

@Entity
@Table
public class WHLendOutBill extends BaseBill {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productCode ;	// 产品代码
	private String spec;			// 规格
	private float amount;			// 数量
	private String unit;			// 单位
	private String comment;			//备注

	@Column(unique=true)
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
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
	
}
