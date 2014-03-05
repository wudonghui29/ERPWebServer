package com.xinyuan.model.HumanResource;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp4;

/**
 * Employee Quit His Job Order (离职申请表)
 * 
 *
 */

@Entity
@Table
public class EmployeeQuitOrder extends OrderApp4 {

	private static final long serialVersionUID = 1L;

	private String employeeNO; 		 	// the employee want to quit his job
	
	private Date planQuitDate ;  		// 拟定离职日期 the date plan to quit  
	private Date approvedQuitDate ; 	// 核定离职日期 the date approve to quit  
	
	private Date employDate;  	 		// 到职日期
	private Date filingDate ; 			// 申请日期
	private String quitReason; 			// 离职原因
	
	
//	private EmployeeQuitPassOrder employeeQuitPassOrder;

	
	
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
	
	public Date getEmployDate() {
		return employDate;
	}

	public void setEmployDate(Date employDate) {
		this.employDate = employDate;
	}

	public Date getFilingDate() {
		return filingDate;
	}

	public void setFilingDate(Date filingDate) {
		this.filingDate = filingDate;
	}

	public String getQuitReason() {
		return quitReason;
	}

	public void setQuitReason(String quitReason) {
		this.quitReason = quitReason;
	}


//	@OneToOne(cascade=CascadeType.ALL,optional=true)
//	public EmployeeQuitPassOrder getEmployeeQuitPassOrder() {
//		return employeeQuitPassOrder;
//	}
//	
//	public void setEmployeeQuitPassOrder(EmployeeQuitPassOrder employeeQuitPassOrder) {
//		this.employeeQuitPassOrder = employeeQuitPassOrder;
//	}
	
}
