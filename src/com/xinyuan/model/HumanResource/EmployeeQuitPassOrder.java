package com.xinyuan.model.HumanResource;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp4;

/**
 * 
 * 离职放行单
 * 
 *
 */
@Entity
@Table
public class EmployeeQuitPassOrder extends OrderApp4 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String employeeNO;  	// the freedom employee 
	
	private Date passDate ; 		// 实际离厂日期
	private String carryingStuff; 	// 携带物品
	
	private EmployeeQuitOrder quitOrder;
	
	
	public String getEmployeeNO() {
		return employeeNO;
	}
	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
	}
	public Date getPassDate() {
		return passDate;
	}
	public void setPassDate(Date passDate) {
		this.passDate = passDate;
	}
	public String getCarryingStuff() {
		return carryingStuff;
	}
	public void setCarryingStuff(String carryingStuff) {
		this.carryingStuff = carryingStuff;
	}
	
	//  http://www.blogjava.net/freeman1984/archive/2011/09/30/359857.html
	@OneToOne(cascade=CascadeType.ALL,optional=false,mappedBy="quitPassOrder")
	public EmployeeQuitOrder getQuitOrder() {
		return quitOrder;
	}
	public void setQuitOrder(EmployeeQuitOrder quitOrder) {
		this.quitOrder = quitOrder;
	}
}
