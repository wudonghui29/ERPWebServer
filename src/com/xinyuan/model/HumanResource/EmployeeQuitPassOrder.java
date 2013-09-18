package com.xinyuan.model.HumanResource;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.xinyuan.model.Level_Four_Approve;

/**
 * 
 * 离职放行单
 * 
 *
 */
@Entity
@Table
public class EmployeeQuitPassOrder extends Level_Four_Approve {

	private Employee employee;  // the freedom employee 
	
	// 核准离职日期 来自于 EmployeeQuitOrder's  approvedQuitDate
	// ... Empty
	
	private Date actualQuitDate ; // 实际离职日期
	private String carryingStuff; // 携带物品
	
	
	@OneToOne
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Date getActualQuitDate() {
		return actualQuitDate;
	}
	public void setActualQuitDate(Date actualQuitDate) {
		this.actualQuitDate = actualQuitDate;
	}
	public String getCarryingStuff() {
		return carryingStuff;
	}
	public void setCarryingStuff(String carryingStuff) {
		this.carryingStuff = carryingStuff;
	}
	
	
	
	
}
