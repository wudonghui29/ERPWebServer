package com.xinyuan.model.HumanResource;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xinyuan.model.BaseBill;


/**
 * 保安人员用餐明细表 - 备注 值班明细 表
 * SD : Security Detail
 */

@Entity
@Table
public class EmployeeSMBill extends BaseBill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String employeeNO ; // 员工编号
	
	// 日期范围
	private Date startDate;
	private Date endDate;
	
	
	private boolean morningWork;	// 早班
	private boolean noonWork;		// 午班
	private boolean eveningWork;	// 晚班
	
	private EmployeeSMOrder order;
	
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

	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public EmployeeSMOrder getOrder() {
		return order;
	}
	public void setOrder(EmployeeSMOrder order) {
		this.order = order;
	}
}