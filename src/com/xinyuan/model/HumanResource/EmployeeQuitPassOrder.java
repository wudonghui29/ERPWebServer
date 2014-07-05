package com.xinyuan.model.HumanResource;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	private static final long serialVersionUID = 1L;

	private Date passDate;
	private String humanResouceDesc;
	private String warehouseDesc;
	private String financeDesc;
	private String securityDesc;
	
	private int employeeQuitOrderId;
	
	
	
	public Date getPassDate() {
		return passDate;
	}
	public void setPassDate(Date passDate) {
		this.passDate = passDate;
	}
	public String getHumanResouceDesc() {
		return humanResouceDesc;
	}
	public void setHumanResouceDesc(String humanResouceDesc) {
		this.humanResouceDesc = humanResouceDesc;
	}
	public String getWarehouseDesc() {
		return warehouseDesc;
	}
	public void setWarehouseDesc(String warehouseDesc) {
		this.warehouseDesc = warehouseDesc;
	}
	public String getFinanceDesc() {
		return financeDesc;
	}
	public void setFinanceDesc(String financeDesc) {
		this.financeDesc = financeDesc;
	}
	public String getSecurityDesc() {
		return securityDesc;
	}
	public void setSecurityDesc(String securityDesc) {
		this.securityDesc = securityDesc;
	}
	
	@Column(unique=true)
	public int getEmployeeQuitOrderId() {
		return employeeQuitOrderId;
	}
	public void setEmployeeQuitOrderId(int employeeQuitOrderId) {
		this.employeeQuitOrderId = employeeQuitOrderId;
	}
	
	
	
	//  http://www.blogjava.net/freeman1984/archive/2011/09/30/359857.html
//	@OneToOne(cascade=CascadeType.ALL,optional=false,mappedBy="employeeQuitPassOrder")
//	public EmployeeQuitOrder getEmployeeQuitOrder() {
//		return employeeQuitOrder;
//	}
//	public void setEmployeeQuitOrder(EmployeeQuitOrder employeeQuitOrder) {
//		this.employeeQuitOrder = employeeQuitOrder;
//	}
	
}
