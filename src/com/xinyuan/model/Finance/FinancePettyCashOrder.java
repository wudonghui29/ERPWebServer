package com.xinyuan.model.Finance;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.App4;

@Entity
@Table
public class FinancePettyCashOrder extends App4 {

	private static final long serialVersionUID = 1L;
	
	private String employeeNO ;			// 领款人
	private String feeDescription;		// 费用说明
	
	
	private String biilOrderNOs;		// 单号


	public String getEmployeeNO() {
		return employeeNO;
	}


	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
	}


	public String getFeeDescription() {
		return feeDescription;
	}


	public void setFeeDescription(String feeDescription) {
		this.feeDescription = feeDescription;
	}


	public String getBiilOrderNOs() {
		return biilOrderNOs;
	}


	public void setBiilOrderNOs(String biilOrderNOs) {
		this.biilOrderNOs = biilOrderNOs;
	}

	
}
