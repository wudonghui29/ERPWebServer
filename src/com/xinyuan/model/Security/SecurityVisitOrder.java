package com.xinyuan.model.Security;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp1;


/**
 *  拜访单/访客单
 *
 */

@Entity
@Table
public class SecurityVisitOrder extends OrderApp1 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String employeeNO;			// 拜访人员(即拜访本公司的谁)
	private String agentEmployeeNO; 	// 转接人员(们)
	private String meetedEmployeeNO;	// 已接见人
	
	private String entrySecEmployeeNO ;	// 入厂放行 (保安NO)
	private String exitSecEmployeeNO;	// 出厂放行 (保安NO)
	
	private String visitorCompany;  // 拜访人公司
	private String visitorName;		// 拜访人名字
	private int accompanyCount;		// 随同人数
	
	private String carNO;			// 车牌号码
	private String visitReason;		// 事由
	
	
	
	private Date	entryDate;		// 入厂时间
	private Date	exitDate;		// 出厂时间
	
	
	private String carryStuff;		// 外携物品
	
	
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
	
	public String getMeetedEmployeeNO() {
		return meetedEmployeeNO;
	}
	public void setMeetedEmployeeNO(String meetedEmployeeNO) {
		this.meetedEmployeeNO = meetedEmployeeNO;
	}
	
	public String getVisitReason() {
		return visitReason;
	}
	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
	}
	
	public String getEntrySecEmployeeNO() {
		return entrySecEmployeeNO;
	}
	public void setEntrySecEmployeeNO(String entrySecEmployeeNO) {
		this.entrySecEmployeeNO = entrySecEmployeeNO;
	}
	public String getExitSecEmployeeNO() {
		return exitSecEmployeeNO;
	}
	public void setExitSecEmployeeNO(String exitSecEmployeeNO) {
		this.exitSecEmployeeNO = exitSecEmployeeNO;
	}
	
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public Date getExitDate() {
		return exitDate;
	}
	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}
	
	@Column(columnDefinition="TEXT")
	public String getCarryStuff() {
		return carryStuff;
	}
	public void setCarryStuff(String carryStuff) {
		this.carryStuff = carryStuff;
	}
	
}
