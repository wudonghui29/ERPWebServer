package com.xinyuan.model.SharedOrder;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.xinyuan.model.OrderApp1;


/**
 * 
 * Employee Going Out Order (员工外出单)
 * 
 *
 */

@Entity
@Table
public class SharedOutOrder extends OrderApp1 {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String employeeNO; 				// employee NO. the employee ask for going out 
	private String entrySecEmployeeNO;		// 保安入厂放行
	private String exitSecEmployeeNO;		// 保安出厂放行
	private String sendAppEmployeeNO;		// 发关主管核准
	private String notAppEmployeeNO;		// 主管不核准
	
	private String outReason;				// the reason of going out  事由
	
	private String outCompanyCarrying; 		// the carrying stuff when going out 外携公司物品
	private String outPersonalCarrying;   	// the carrying stuff when coming back 外携私人物品
	
	private Date exitDate;					// the date of going out  出厂时间 
	private Date entryDate; 				// the date of coming back  入厂时间
	
	
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
	public Date getExitDate() {
		return exitDate;
	}
	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public String getEntrySecEmployeeNO() {
		return entrySecEmployeeNO;
	}
	public void setEntrySecEmployeeNO(String entrySecEmployeeNO) {
		this.entrySecEmployeeNO = entrySecEmployeeNO;
	}
	public String getExitSecEmployeeNO() {
		return exitSecEmployeeNO;
	}
	public void setExitSecEmployeeNO(String exitSecEmployeeNO) {
		this.exitSecEmployeeNO = exitSecEmployeeNO;
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
