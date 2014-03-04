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

	private static final long serialVersionUID = 1L;

	private String employeeNO;  	// the freedom employee 
	
	private String humanResouceDesc;
	private String warehouseDesc;
	private String financeDesc;
	private String securityDesc;
	
	private EmployeeQuitOrder quitOrder;
	
	
	
	public String getEmployeeNO() {
		return employeeNO;
	}
	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
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
	//  http://www.blogjava.net/freeman1984/archive/2011/09/30/359857.html
	@OneToOne(cascade=CascadeType.ALL,optional=false,mappedBy="quitPassOrder")
	public EmployeeQuitOrder getQuitOrder() {
		return quitOrder;
	}
	public void setQuitOrder(EmployeeQuitOrder quitOrder) {
		this.quitOrder = quitOrder;
	}
}
