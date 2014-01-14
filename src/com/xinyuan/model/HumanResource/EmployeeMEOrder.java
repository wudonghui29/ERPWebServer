package com.xinyuan.model.HumanResource;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp4;

/**
 * 员工月终考核表
 * 
 * ME: Monthly Examine
 *
 */

@Entity
@Table
public class EmployeeMEOrder extends OrderApp4 {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String employeeNO ; // 考核的员工
	
	
	private Date examineDate; 	// 考核日期
	
	
	private int policyObeyingPointA ; 	// 初审 政策配合得分
	private int policyObeyingPointB ; 	// 复审 政策配合得分
	private int policyObeyingPointC ; 	// 覆审 政策配合得分
	private int policyObeyingPointD ;	// 审定 政策配合得分
	
	private int qualityAwarePointA ; 	// 初审 品质意识得分
	private int qualityAwarePointB ; 	// 复审 品质意识得分
	private int qualityAwarePointC ; 	// 覆审 品质意识得分
	private int qualityAwarePointD ; 	// 审定 品质意识得分
	
	private int jobArrangePointA ; 		// 初审 作业安排得分
	private int jobArrangePointB ; 		// 复审 作业安排得分
	private int jobArrangePointC ; 		// 覆审 作业安排得分
	private int jobArrangePointD ; 		// 审定 作业安排得分
	
	private int capabilityPointA ; 		// 初审 才能学识得分
	private int capabilityPointB ; 		// 复审 才能学识得分
	private int capabilityPointC ; 		// 覆审 才能学识得分
	private int capabilityPointD ; 		// 审定 才能学识得分
	
	private int scheduleControlPointA ;	// 初审 进度掌握得分
	private int scheduleControlPointB ; // 复审 进度掌握得分
	private int scheduleControlPointC ; // 覆审 进度掌握得分
	private int scheduleControlPointD ; // 审定 进度掌握得分
	
	private int teamworkPointA ;		 // 初审 协调合作得分
	private int teamworkPointB ; 		// 复审 协调合作得分
	private int teamworkPointC ; 		// 覆审 协调合作得分
	private int teamworkPointD ; 		// 审定 协调合作得分
	
	
	private String storyDescription ; 	// 优劣事绩
	
	private String sumupAdvantage ; 	// 评语优点
	private String sumupDisadvantage ; 	// 评语缺点
	private String sumupSugget ; 		// 建议
	
	
	public String getEmployeeNO() {
		return employeeNO;
	}
	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
	}
	public Date getExamineDate() {
		return examineDate;
	}
	public void setExamineDate(Date examineDate) {
		this.examineDate = examineDate;
	}
	public int getPolicyObeyingPointA() {
		return policyObeyingPointA;
	}
	public void setPolicyObeyingPointA(int policyObeyingPointA) {
		this.policyObeyingPointA = policyObeyingPointA;
	}
	public int getPolicyObeyingPointB() {
		return policyObeyingPointB;
	}
	public void setPolicyObeyingPointB(int policyObeyingPointB) {
		this.policyObeyingPointB = policyObeyingPointB;
	}
	public int getPolicyObeyingPointC() {
		return policyObeyingPointC;
	}
	public void setPolicyObeyingPointC(int policyObeyingPointC) {
		this.policyObeyingPointC = policyObeyingPointC;
	}
	public int getPolicyObeyingPointD() {
		return policyObeyingPointD;
	}
	public void setPolicyObeyingPointD(int policyObeyingPointD) {
		this.policyObeyingPointD = policyObeyingPointD;
	}
	public int getQualityAwarePointA() {
		return qualityAwarePointA;
	}
	public void setQualityAwarePointA(int qualityAwarePointA) {
		this.qualityAwarePointA = qualityAwarePointA;
	}
	public int getQualityAwarePointB() {
		return qualityAwarePointB;
	}
	public void setQualityAwarePointB(int qualityAwarePointB) {
		this.qualityAwarePointB = qualityAwarePointB;
	}
	public int getQualityAwarePointC() {
		return qualityAwarePointC;
	}
	public void setQualityAwarePointC(int qualityAwarePointC) {
		this.qualityAwarePointC = qualityAwarePointC;
	}
	public int getQualityAwarePointD() {
		return qualityAwarePointD;
	}
	public void setQualityAwarePointD(int qualityAwarePointD) {
		this.qualityAwarePointD = qualityAwarePointD;
	}
	public int getJobArrangePointA() {
		return jobArrangePointA;
	}
	public void setJobArrangePointA(int jobArrangePointA) {
		this.jobArrangePointA = jobArrangePointA;
	}
	public int getJobArrangePointB() {
		return jobArrangePointB;
	}
	public void setJobArrangePointB(int jobArrangePointB) {
		this.jobArrangePointB = jobArrangePointB;
	}
	public int getJobArrangePointC() {
		return jobArrangePointC;
	}
	public void setJobArrangePointC(int jobArrangePointC) {
		this.jobArrangePointC = jobArrangePointC;
	}
	public int getJobArrangePointD() {
		return jobArrangePointD;
	}
	public void setJobArrangePointD(int jobArrangePointD) {
		this.jobArrangePointD = jobArrangePointD;
	}
	public int getCapabilityPointA() {
		return capabilityPointA;
	}
	public void setCapabilityPointA(int capabilityPointA) {
		this.capabilityPointA = capabilityPointA;
	}
	public int getCapabilityPointB() {
		return capabilityPointB;
	}
	public void setCapabilityPointB(int capabilityPointB) {
		this.capabilityPointB = capabilityPointB;
	}
	public int getCapabilityPointC() {
		return capabilityPointC;
	}
	public void setCapabilityPointC(int capabilityPointC) {
		this.capabilityPointC = capabilityPointC;
	}
	public int getCapabilityPointD() {
		return capabilityPointD;
	}
	public void setCapabilityPointD(int capabilityPointD) {
		this.capabilityPointD = capabilityPointD;
	}
	public int getScheduleControlPointA() {
		return scheduleControlPointA;
	}
	public void setScheduleControlPointA(int scheduleControlPointA) {
		this.scheduleControlPointA = scheduleControlPointA;
	}
	public int getScheduleControlPointB() {
		return scheduleControlPointB;
	}
	public void setScheduleControlPointB(int scheduleControlPointB) {
		this.scheduleControlPointB = scheduleControlPointB;
	}
	public int getScheduleControlPointC() {
		return scheduleControlPointC;
	}
	public void setScheduleControlPointC(int scheduleControlPointC) {
		this.scheduleControlPointC = scheduleControlPointC;
	}
	public int getScheduleControlPointD() {
		return scheduleControlPointD;
	}
	public void setScheduleControlPointD(int scheduleControlPointD) {
		this.scheduleControlPointD = scheduleControlPointD;
	}
	public int getTeamworkPointA() {
		return teamworkPointA;
	}
	public void setTeamworkPointA(int teamworkPointA) {
		this.teamworkPointA = teamworkPointA;
	}
	public int getTeamworkPointB() {
		return teamworkPointB;
	}
	public void setTeamworkPointB(int teamworkPointB) {
		this.teamworkPointB = teamworkPointB;
	}
	public int getTeamworkPointC() {
		return teamworkPointC;
	}
	public void setTeamworkPointC(int teamworkPointC) {
		this.teamworkPointC = teamworkPointC;
	}
	public int getTeamworkPointD() {
		return teamworkPointD;
	}
	public void setTeamworkPointD(int teamworkPointD) {
		this.teamworkPointD = teamworkPointD;
	}
	public String getStoryDescription() {
		return storyDescription;
	}
	public void setStoryDescription(String storyDescription) {
		this.storyDescription = storyDescription;
	}
	public String getSumupAdvantage() {
		return sumupAdvantage;
	}
	public void setSumupAdvantage(String sumupAdvantage) {
		this.sumupAdvantage = sumupAdvantage;
	}
	public String getSumupDisadvantage() {
		return sumupDisadvantage;
	}
	public void setSumupDisadvantage(String sumupDisadvantage) {
		this.sumupDisadvantage = sumupDisadvantage;
	}
	public String getSumupSugget() {
		return sumupSugget;
	}
	public void setSumupSugget(String sumupSugget) {
		this.sumupSugget = sumupSugget;
	}
	
}
