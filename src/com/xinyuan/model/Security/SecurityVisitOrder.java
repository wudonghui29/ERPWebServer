package com.xinyuan.model.Security;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.LevelApp_2;


/**
 *  拜访单
 *
 */

@Entity
@Table
public class SecurityVisitOrder extends LevelApp_2 {

	private String visitorCompany;  // 拜访人公司
	private String visitorName;		// 拜访人名字
	private int accompanyCount;		// 随同人数
	
	private String carNO;			// 车牌号码
	private String employeeNO;		// 拜访人员(即拜访本公司的谁)  	// level_one ??
	private String agentEmployeeNO;	// 转接人员					// level_two ??
	private String visitReason;		// 事由
	
	public String getVisitorCompany() {
		return visitorCompany;
	}
	public void setVisitorCompany(String visitorCompany) {
		this.visitorCompany = visitorCompany;
	}
	public String getVisitorName() {
		return visitorName;
	}
	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}
	public int getAccompanyCount() {
		return accompanyCount;
	}
	public void setAccompanyCount(int accompanyCount) {
		this.accompanyCount = accompanyCount;
	}
	public String getCarNO() {
		return carNO;
	}
	public void setCarNO(String carNO) {
		this.carNO = carNO;
	}
	public String getEmployeeNO() {
		return employeeNO;
	}
	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
	}
	public String getAgentEmployeeNO() {
		return agentEmployeeNO;
	}
	public void setAgentEmployeeNO(String agentEmployeeNO) {
		this.agentEmployeeNO = agentEmployeeNO;
	}
	public String getVisitReason() {
		return visitReason;
	}
	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
	}
	
}
