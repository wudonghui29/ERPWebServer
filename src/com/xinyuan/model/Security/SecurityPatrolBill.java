package com.xinyuan.model.Security;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.BaseBill;

@Entity
@Table
public class SecurityPatrolBill extends BaseBill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String routine;		// 例行项目
	
	private String checkEmployeeNO;	// 检查人员
	
	private String repairOrderNO;		// 报修单

	public String getRoutine() {
		return routine;
	}

	public void setRoutine(String routine) {
		this.routine = routine;
	}

	public String getCheckEmployeeNO() {
		return checkEmployeeNO;
	}

	public void setCheckEmployeeNO(String checkEmployeeNO) {
		this.checkEmployeeNO = checkEmployeeNO;
	}

	public String getRepairOrderNO() {
		return repairOrderNO;
	}

	public void setRepairOrderNO(String repairOrderNO) {
		this.repairOrderNO = repairOrderNO;
	}

}
