package com.xinyuan.model.SharedOrder;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.xinyuan.model.LevelApp_2;
import com.xinyuan.model.HumanResource.Employee;


/**
 * Employee Going Out Order (员工外出单)
 * 
 *
 */

@Entity
@Table
public class SharedOutOrder extends LevelApp_2 {
	
	private Employee employee;  // the employee ask for going out 
	
	private Date applyDate; 		// 申请日期
	
	private String outDestination ; // the place header to 
	private String outReason;		// the reason of going out
	
	private String outCarrying; 	// the carrying stuff when going out
	private String entryCarrying;   // the carrying stuff when coming back
	
	private Date outDate ; 	// the date of going out 
	private Date entryDate; // the date of coming back
	
	
	@ManyToOne
	@JoinColumn(name="employeeId")
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	public String getOutDestination() {
		return outDestination;
	}
	public void setOutDestination(String outDestination) {
		this.outDestination = outDestination;
	}
	public String getOutReason() {
		return outReason;
	}
	public void setOutReason(String outReason) {
		this.outReason = outReason;
	}
	public String getOutCarrying() {
		return outCarrying;
	}
	public void setOutCarrying(String outCarrying) {
		this.outCarrying = outCarrying;
	}
	public String getEntryCarrying() {
		return entryCarrying;
	}
	public void setEntryCarrying(String entryCarrying) {
		this.entryCarrying = entryCarrying;
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
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
}
