package com.xinyuan.model.HumanResource;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp4;

/**
 * Employee Ask for Leave Order (员工请假单)
 * 
 */

@Entity
@Table
public class EmployeeLeaveOrder extends OrderApp4 {
	
	private static final long serialVersionUID = 1L;

	private String employeeNO;  		// the employee ask for leave
	private String agentEmployeeNO ; 	// the agent of this leaving employee
	
	private Date startDate ; 			// start leaving date
	private Date endDate ;   			// end of leaving date
	
	private String reason ; 			// reason
	
	private String leaveType;			// the type ["Personal_Leave","Sick_Leave", "Annual_Leave","OTHERS"]

	
	public String getEmployeeNO() {
		return employeeNO;
	}

	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
	}

	public String getAgentEmployeeNO() {
		return agentEmployeeNO;
	}

	public void setAgentEmployeeNO(String agentEmployeeNO) {
		this.agentEmployeeNO = agentEmployeeNO;
	}

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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	
}
