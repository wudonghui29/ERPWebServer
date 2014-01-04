package com.xinyuan.model.Warehouse;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.Table;


import com.xinyuan.model.App4;

/**
 * 报废单
 * @author Xinyuan_iMAC_4
 *
 */
@Entity
@Table
public class WHScrapOrder extends App4 {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private String productCode; //产品代码
	private String productName; //品   名
	
	private int amount;//数量
	
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
	
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getScrapTime() {
		return scrapTime;
	}
	public void setScrapTime(Date scrapTime) {
		this.scrapTime = scrapTime;
	}
	public String getScrapReason() {
		return scrapReason;
	}
	public void setScrapReason(String scrapReason) {
		this.scrapReason = scrapReason;
	}
	
	


}