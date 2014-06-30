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
	private String name ;
	private String department;
	private String jobTitle;
	
	private String agentEmployeeNO ; 	// the agent of this leaving employee
	
	private Date startDate ; 			// start leaving date
	private Date endDate ;   			// end of leaving date
	
	private float day;
	private float hour;
	
	private String reason ; 			// reason
	private String leaveType;			// the type ["Personal_Leave","Sick_Leave", "Annual_Leave","OTHERS"]

	
	public String getEmployeeNO() {
		return employeeNO;
	}

	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
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

    public float getDay() {
        return day;
    }

    public void setDay(float day) {
        this.day = day;
    }

    public float getHour() {
        return hour;
    }

    public void setHour(float hour) {
        this.hour = hour;
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
