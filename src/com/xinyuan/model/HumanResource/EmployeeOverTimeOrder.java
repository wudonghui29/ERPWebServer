package com.xinyuan.model.HumanResource;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.xinyuan.model.LevelApp_4;

/**
 * 加班申请确认单
 * 
 *
 */

@Entity
@Table
public class EmployeeOverTimeOrder extends LevelApp_4 {
	
	private Date applyDate ;	 // 申请日期
	private Date overTimeDate; 	// 加班日期
	
	private Employee groupLeader; // 组长
//	private Employee employee ;  // 加班的员工们   		// List<Employee> employee?????    // TODO: list ???  // or employeeId & employeeName??
	private String overTimeContents; // 加班工作内容
	
	private Time startTime; // 加班开始时间
	private Time endTime ;  // 加班结束时间
	
	private boolean isHaveDinner; // 是否用晚餐

	
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Date getOverTimeDate() {
		return overTimeDate;
	}

	public void setOverTimeDate(Date overTimeDate) {
		this.overTimeDate = overTimeDate;
	}

	@OneToOne
	public Employee getGroupLeader() {
		return groupLeader;
	}

	public void setGroupLeader(Employee groupLeader) {
		this.groupLeader = groupLeader;
	}

//	public Employee getEmployee() {
//		return employee;
//	}
//
//	public void setEmployee(Employee employee) {
//		this.employee = employee;
//	}

	public String getOverTimeContents() {
		return overTimeContents;
	}

	public void setOverTimeContents(String overTimeContents) {
		this.overTimeContents = overTimeContents;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public boolean isHaveDinner() {
		return isHaveDinner;
	}

	public void setHaveDinner(boolean isHaveDinner) {
		this.isHaveDinner = isHaveDinner;
	}
	
}
