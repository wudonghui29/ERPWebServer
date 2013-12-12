package com.xinyuan.model.HumanResource;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.App4;

/**
 * 
 * 离职放行单
 * 
 *
 */
@Entity
@Table
public class EmployeeQuitPassOrder extends App4 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String employeeNO;  // the freedom employee 
	
	// 核准离职日期 来自于 EmployeeQuitOrder's  approvedQuitDate
	// ... Empty
	
	private Date actualQuitDate ; // 实际离职日期
	private String carryingStuff; // 携带物品
	
	
	
	public String getEmployeeNO() {
		return employeeNO;
	}
	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
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
