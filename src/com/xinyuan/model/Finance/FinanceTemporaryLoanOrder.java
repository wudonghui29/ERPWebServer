package com.xinyuan.model.Finance;


import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.xinyuan.model.LevelApp_4;

/**
 * 
 * 临时借据单
 *
 */
public class FinanceTemporaryLoanOrder extends LevelApp_4 {

	private String employeeNO ; //借款人
	
	private String reason;		//借款原因
	private float nowAmount ;		//本次借款金额
	private float beforeAmount;		//之前借款金额
	
	private String estimateDescription; //预估费用内容说明
	
	private Date estimateReturnDate; //预定归还日期
	private Date autualReturnDate;	 //实际归还日期
	private float allReturnAmount;	//合计归还
	
	
	public String getEmployeeNO() {
		return employeeNO;
	}
	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public float getNowAmount() {
		return nowAmount;
	}
	public void setNowAmount(float nowAmount) {
		this.nowAmount = nowAmount;
	}
	public float getBeforeAmount() {
		return beforeAmount;
	}
	public void setBeforeAmount(float beforeAmount) {
		this.beforeAmount = beforeAmount;
	}
	public String getEstimateDescription() {
		return estimateDescription;
	}
	public void setEstimateDescription(String estimateDescription) {
		this.estimateDescription = estimateDescription;
	}
	public Date getEstimateReturnDate() {
		return estimateReturnDate;
	}
	public void setEstimateReturnDate(Date estimateReturnDate) {
		this.estimateReturnDate = estimateReturnDate;
	}
	public Date getAutualReturnDate() {
		return autualReturnDate;
	}
	public void setAutualReturnDate(Date autualReturnDate) {
		this.autualReturnDate = autualReturnDate;
	}
	public float getAllReturnAmount() {
		return allReturnAmount;
	}
	public void setAllReturnAmount(float allReturnAmount) {
		this.allReturnAmount = allReturnAmount;
	}
}
