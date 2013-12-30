package com.xinyuan.model.Warehouse;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	 * wwwwwwwwwwwwww
	 */
	private static final long serialVersionUID = 1L;
	
	private String productCode; //产品代码
	private String productName; //品   名
	
	private int amout;//数量
	
	@Temporal(TemporalType.DATE)
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
	public int getAmout() {
		return amout;
	}
	public void setAmout(int amout) {
		this.amout = amout;
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
