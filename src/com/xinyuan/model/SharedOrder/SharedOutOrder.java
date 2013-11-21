package com.xinyuan.model.SharedOrder;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.xinyuan.model.LevelApp_1;


/**
 * Employee Going Out Order (员工外出单)
 * 
 *
 */

@Entity
@Table
public class SharedOutOrder extends LevelApp_1 {
	
	private String employeeNO; 		// employee NO. the employee ask for going out 
	
	private String outReason;		// the reason of going out  事由
	
	private String outCompanyCarrying; 		// the carrying stuff when going out 外携公司物品
	private String outPersonalCarrying;   	// the carrying stuff when coming back 外携私人物品
	
	private Date outDate ; 	// the date of going out  出厂时间 
	private Date entryDate; // the date of coming back  入厂时间
	
	private String outSecurityEmployeeNO;		// 保安出厂放行
	private String entrySecurityEmployeeNO;		// 保安入厂放行
	
	private String sendAppEmployeeNO;		// 发关主管核准
	private String notAppEmployeeNO;		// 主管不核准
	
	@NotEmpty
	public String getEmployeeNO() {
		return employeeNO;
	}
	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
	}
	@Column(columnDefinition="TEXT")
	public String getOutReason() {
		return outReason;
	}
	public void setOutReason(String outReason) {
		this.outReason = outReason;
	}
	@Column(columnDefinition="TEXT")
	public String getOutCompanyCarrying() {
		return outCompanyCarrying;
	}
	public void setOutCompanyCarrying(String outCompanyCarrying) {
		this.outCompanyCarrying = outCompanyCarrying;
	}
	@Column(columnDefinition="TEXT")
	public String getOutPersonalCarrying() {
		return outPersonalCarrying;
	}
	public void setOutPersonalCarrying(String outPersonalCarrying) {
		this.outPersonalCarrying = outPersonalCarrying;
	}
	public Date getOutDate() {
		return outDate;
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public String getOutSecurityEmployeeNO() {
		return outSecurityEmployeeNO;
	}
	public void setOutSecurityEmployeeNO(String outSecurityEmployeeNO) {
		this.outSecurityEmployeeNO = outSecurityEmployeeNO;
	}
	public String getEntrySecurityEmployeeNO() {
		return entrySecurityEmployeeNO;
	}
	public void setEntrySecurityEmployeeNO(String entrySecurityEmployeeNO) {
		this.entrySecurityEmployeeNO = entrySecurityEmployeeNO;
	}
	public String getSendAppEmployeeNO() {
		return sendAppEmployeeNO;
	}
	public void setSendAppEmployeeNO(String sendAppEmployeeNO) {
		this.sendAppEmployeeNO = sendAppEmployeeNO;
	}
	public String getNotAppEmployeeNO() {
		return notAppEmployeeNO;
	}
	public void setNotAppEmployeeNO(String notAppEmployeeNO) {
		this.notAppEmployeeNO = notAppEmployeeNO;
	}
	
}
