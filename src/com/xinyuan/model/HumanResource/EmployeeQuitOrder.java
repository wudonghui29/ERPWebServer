package com.xinyuan.model.HumanResource;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.LevelAPP_5;

/**
 * Employee Quit His Job Order (离职申请表)
 * 
 *
 */

@Entity
@Table
public class EmployeeQuitOrder extends LevelAPP_5 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String employeeNO;  // the employee want to quit his job
	
	private Date applyDate ; // 申请日期
	
	private Date planQuitDate ;  // the date plan to quit  拟定离职日期
	private Date approvedQuitDate ; // the date approve to quit  核定离职日期
	
	private int annualVacation ; // the count of the days of annual vacation 已休年假天数

	
	public String getEmployeeNO() {
		return employeeNO;
	}

	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
	}

	public Date getPlanQuitDate() {
		return planQuitDate;
	}

	public void setPlanQuitDate(Date planQuitDate) {
		this.planQuitDate = planQuitDate;
	}

	public Date getApprovedQuitDate() {
		return approvedQuitDate;
	}

	public void setApprovedQuitDate(Date approvedQuitDate) {
		this.approvedQuitDate = approvedQuitDate;
	}

	public int getAnnualVacation() {
		return annualVacation;
	}

	public void setAnnualVacation(int annualVacation) {
		this.annualVacation = annualVacation;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	
	
}
