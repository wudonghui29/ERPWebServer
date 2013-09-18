package com.xinyuan.model.HumanResource;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.Level_Five_Approve;

/**
 * Employee Ask for Leave Order (员工请假单)
 * 
 *
 */

@Entity
@Table
public class EmployeeLeaveOrder extends Level_Five_Approve {
	
	private String employeeNO;  // the employee ask for leave
	
	private String agentEmployeeNO ; // the agent of this leaving employee
	
	private Date applyDate ; // 申请日期
	
	private int leaveType ;  // 0 for private affair leave (事假) , 1 for marriage leave (婚假) , 2 for funeral leave (丧假) , 3 for sick leave (病假) , 4 for others (其它假)
	private String leaveDescription ; // for Type 4 , write the description of your leave
	private String hospitalProofFile ; // for Type 3 , sick leave need the hospital diagnose
	
	private String leaveReason ; // the reason of your asking for leave (事由)
	
	private Date startDate ; // start leaving date
	private Date endDate ;   // end of leaving date
	
	
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
	
	
	public int getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(int leaveType) {
		this.leaveType = leaveType;
	}
	public String getLeaveDescription() {
		return leaveDescription;
	}
	public void setLeaveDescription(String leaveDescription) {
		this.leaveDescription = leaveDescription;
	}
	public String getHospitalProofFile() {
		return hospitalProofFile;
	}
	public void setHospitalProofFile(String hospitalProofFile) {
		this.hospitalProofFile = hospitalProofFile;
	}
	public String getLeaveReason() {
		return leaveReason;
	}
	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
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
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
}
