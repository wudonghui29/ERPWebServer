package com.xinyuan.model.Warehouse;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp4;

/**
 * 报废单 
 *
 */
@Entity
@Table
public class WHScrapOrder extends OrderApp4 {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private String productCode; //产品代码
	private String productName; //品   名
	
	private float scrapAmount;//数量
	
	private Date scrapTime;//报废时间
	
	private String scrapReason;//报废理由
	
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
	
	public float getScrapAmount() {
		return scrapAmount;
	}
	public void setScrapAmount(float scrapAmount) {
		this.scrapAmount = scrapAmount;
	}
	public Date getScrapTime() {
		return scrapTime;
	}
	public void setScrapTime(Date scrapTime) {
		this.scrapTime = scrapTime;
	}
	
	@Column(columnDefinition = "TEXT")
	public String getScrapReason() {
		return scrapReason;
	}
	public void setScrapReason(String scrapReason) {
		this.scrapReason = scrapReason;
	}
	
	


}
