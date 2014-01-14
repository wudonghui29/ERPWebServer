package com.xinyuan.model.HumanResource;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp4;

/**
 * 加班申请确认单
 * 
 * OT : Over Time
 *
 */

@Entity
@Table
public class EmployeeOTOrder extends OrderApp4 {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String overtimeEmployeeNOs ;   		// 加班的员工们 e.g. "EH001,EH002"
	private String leaderEmployeeNO; 	// 组长
	
	private Date applyDate ;	 // 申请日期
	private Date overTimeDate; 	// 加班日期
	
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

	
	public String getLeaderEmployeeNO() {
		return leaderEmployeeNO;
	}

	public void setLeaderEmployeeNO(String leaderEmployeeNO) {
		this.leaderEmployeeNO = leaderEmployeeNO;
	}

	public String getOvertimeEmployeeNOs() {
		return overtimeEmployeeNOs;
	}

	public void setOvertimeEmployeeNOs(String overtimeEmployeeNOs) {
		this.overtimeEmployeeNOs = overtimeEmployeeNOs;
	}

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
