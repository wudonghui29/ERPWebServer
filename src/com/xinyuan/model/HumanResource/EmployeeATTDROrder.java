package com.xinyuan.model.HumanResource;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp3;


/**
 * 员工出勤日报表
 * 
 * ATT: 出勤
 * 
 * DR: daily report 日报表
 *
 */

@Entity
@Table
public class EmployeeATTDROrder extends OrderApp3{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String askLeaveEmployeeNOs ; // 请假人员名单
	private String beLateEmployeeNOs ;   // 迟到人员名单

	private Date workDate; 		// 出勤日期
	
	private int presentCount ;	// 出勤人数
	private int beLateCount ;  	// 迟到人数
	private int absentCount ;	// 未到人数
	private int earlyLeaveCount;// 早退人数
	
	private int totalCount ;	// 总人数
	private int sleepOutCount ; // 外宿工地人数
	private int outContractCount ; // 外承包工地人数
	
	
	
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public int getPresentCount() {
		return presentCount;
	}
	public void setPresentCount(int presentCount) {
		this.presentCount = presentCount;
	}
	public int getBeLateCount() {
		return beLateCount;
	}
	public void setBeLateCount(int beLateCount) {
		this.beLateCount = beLateCount;
	}
	public int getAbsentCount() {
		return absentCount;
	}
	public void setAbsentCount(int absentCount) {
		this.absentCount = absentCount;
	}
	public int getEarlyLeaveCount() {
		return earlyLeaveCount;
	}
	public void setEarlyLeaveCount(int earlyLeaveCount) {
		this.earlyLeaveCount = earlyLeaveCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getSleepOutCount() {
		return sleepOutCount;
	}
	public void setSleepOutCount(int sleepOutCount) {
		this.sleepOutCount = sleepOutCount;
	}
	public int getOutContractCount() {
		return outContractCount;
	}
	public void setOutContractCount(int outContractCount) {
		this.outContractCount = outContractCount;
	}
	public String getAskLeaveEmployeeNOs() {
		return askLeaveEmployeeNOs;
	}
	public void setAskLeaveEmployeeNOs(String askLeaveEmployeeNOs) {
		this.askLeaveEmployeeNOs = askLeaveEmployeeNOs;
	}
	public String getBeLateEmployeeNOs() {
		return beLateEmployeeNOs;
	}
	public void setBeLateEmployeeNOs(String beLateEmployeeNOs) {
		this.beLateEmployeeNOs = beLateEmployeeNOs;
	}
	
}
