package com.xinyuan.model.HumanResource;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.xinyuan.model.BaseOrderModel;


/**
 * 保安人员用餐明细表 - 备注 值班明细 表
 *
 */

@Entity
@Table
public class EmployeeSecurityDetailOrder extends BaseOrderModel {

	// 日期范围
	private Date startDate;
	private Date endDate;
	
	private String employeeNO ; // 员工编号
	
	private boolean morningWork;	// 早班
	private boolean noonWork;		// 午班
	private boolean eveningWork;	// 晚班
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getEmployeeNO() {
		return employeeNO;
	}
	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
	}
	public boolean isMorningWork() {
		return morningWork;
	}
	public void setMorningWork(boolean morningWork) {
		this.morningWork = morningWork;
	}
	public boolean isNoonWork() {
		return noonWork;
	}
	public void setNoonWork(boolean noonWork) {
		this.noonWork = noonWork;
	}
	public boolean isEveningWork() {
		return eveningWork;
	}
	public void setEveningWork(boolean eveningWork) {
		this.eveningWork = eveningWork;
	}
	
}