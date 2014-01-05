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
	
	private int sequence;				// 顺序
	
	private String routine;				// 例行项目
	
	private String employeeNO;			// 检查人员
	
	private String repairOrderNO;		// 报修单

	private String configID;			//配置文件id
	
	private String photoURL;			//photo exists?
	
	
	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	public String getConfigID() {
		return configID;
	}

	public void setConfigID(String configID) {
		this.configID = configID;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getRoutine() {
		return routine;
	}

	public void setRoutine(String routine) {
		this.routine = routine;
	}

	public String getEmployeeNO() {
		return employeeNO;
	}

	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
	}

	public String getRepairOrderNO() {
		return repairOrderNO;
	}

	public void setRepairOrderNO(String repairOrderNO) {
		this.repairOrderNO = repairOrderNO;
	}

}
