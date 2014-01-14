package com.xinyuan.model.HumanResource;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp3;


/**
 * 考勤补签登记表
 *
 */

@Entity
@Table
public class EmployeeATTFixOrder extends OrderApp3 {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String employeeNO ; // 员工编号
	
	// 补签日期的范围
	private Date attendanceStarDate; 
	private Date attendanceEndDate;
	
	
	private Date repairDate ; // 补签日期时间
	private String reason ; 	// 补签事由
	private String proof ; 		// 证明
	
	private Date fillRepairOrderDate; // "考勤补签登记表" 中的 "填单日期"
	
	private String description ; 	// 备注

	public Date getAttendanceStarDate() {
		return attendanceStarDate;
	}

	public void setAttendanceStarDate(Date attendanceStarDate) {
		this.attendanceStarDate = attendanceStarDate;
	}
	public Date getAttendanceEndDate() {
		return attendanceEndDate;
	}

	public void setAttendanceEndDate(Date attendanceEndDate) {
		this.attendanceEndDate = attendanceEndDate;
	}
	public Date getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(Date repairDate) {
		this.repairDate = repairDate;
	}

	public String getEmployeeNO() {
		return employeeNO;
	}

	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getProof() {
		return proof;
	}

	public void setProof(String proof) {
		this.proof = proof;
	}

	public Date getFillRepairOrderDate() {
		return fillRepairOrderDate;
	}

	public void setFillRepairOrderDate(Date fillRepairOrderDate) {
		this.fillRepairOrderDate = fillRepairOrderDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
