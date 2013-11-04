package com.xinyuan.model.Security;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.LevelApp_1;


/**
 *  拜访单/访客单
 *
 */

@Entity
@Table
public class SecurityVisitOrder extends LevelApp_1 {

	private String visitorCompany;  // 拜访人公司
	private String visitorName;		// 拜访人名字
	private int accompanyCount;		// 随同人数
	
	private String carNO;			// 车牌号码
	private String visitReason;		// 事由
	private String employeeNO;		// 拜访人员(即拜访本公司的谁)
	private String agentEmployeeNO; // 转接人员(们)
	private String interviewNO;		// 已接见人
	
	
	private String enterAuthNO;		// 入厂放行 (保安NO)
	private String exitAuthNO;		// 出厂放行 (保安NO)
	private Date	enterDate;		// 入厂时间
	private Date	exitDate;		// 出厂时间
	
	
	private int imageCount ;		// 拍照图片数量
	
	
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
	public String getInterviewNO() {
		return interviewNO;
	}
	public void setInterviewNO(String interviewNO) {
		this.interviewNO = interviewNO;
	}
	public String getEnterAuthNO() {
		return enterAuthNO;
	}
	public void setEnterAuthNO(String enterAuthNO) {
		this.enterAuthNO = enterAuthNO;
	}
	public String getExitAuthNO() {
		return exitAuthNO;
	}
	public void setExitAuthNO(String exitAuthNO) {
		this.exitAuthNO = exitAuthNO;
	}
	public Date getEnterDate() {
		return enterDate;
	}
	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}
	public Date getExitDate() {
		return exitDate;
	}
	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}
	
	
	public int getImageCount() {
		return imageCount;
	}
	public void setImageCount(int imageCount) {
		this.imageCount = imageCount;
	}
	
}
