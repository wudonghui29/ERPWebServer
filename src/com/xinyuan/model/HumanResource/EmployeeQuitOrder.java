package com.xinyuan.model.HumanResource;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.xinyuan.model.Level_Five_Approve;

/**
 * Employee Quit His Job Order (离职申请表)
 * 
 *
 */

@Entity
@Table
public class EmployeeQuitOrder extends Level_Five_Approve {

	private Employee employee;  // the employee want to quit his job
	
	private Date applyDate ; // 申请日期
	
	private Date planQuitDate ;  // the date plan to quit  拟定离职日期
	private Date approvedQuitDate ; // the date approve to quit  核定离职日期
	
	private int annualVacation ; // the count of the days of annual vacation 已休年假天数

	@OneToOne
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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
