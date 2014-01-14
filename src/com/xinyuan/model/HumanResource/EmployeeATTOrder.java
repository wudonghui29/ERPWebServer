package com.xinyuan.model.HumanResource;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp4;

/**
 * 员工考勤表
 *
 */

@Entity
@Table
public class EmployeeATTOrder extends OrderApp4 {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String employeeNO ; // 考核的员工
	
	// 考核日期的范围
	private Date attendanceStarDate; 
	private Date attendanceEndDate;
	
	private String otherDescription ; // 其它
	
	private int jobQualityPoint ; // 作业品质得分
	private int jobPerformancePoint ; // 生产质效得分
	private int serviceSpiritPoint ; // 服务精神得分
	private int lawObeyingPoint ; // 守法重纪得分
	private int jobPassionatelyPoint ; // 工作热诚得分
	private int learningAttitudePoint ; // 学习态度得分
	
	private String personalSpeciality ; // 个人专长习惯
	
	private String specialAchievement ; // A级优良事绩
	
	private String sumupAdvantage ; // 综合评语优点
	private String sumupDisadvantage ; // 综合评语缺点
	private String sumupSuggestLevel ; // 综合建议等级
	private String sumupConfirmedLevel ; // 最终核定等级
	

	public Date getAttendanceStarDate() {
		return attendanceStarDate;
	}
	public void setAttendanceStarDate(Date attendanceStarDate) {
		this.attendanceStarDate = attendanceStarDate;
	}
	public Date getAttendanceEndDate() {
		return attendanceEndDate;
	}
	public void setAttendanceEndDate(Date attendanceEndDate) {
		this.attendanceEndDate = attendanceEndDate;
	}
	
	public String getEmployeeNO() {
		return employeeNO;
	}
	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
	}
	public String getOtherDescription() {
		return otherDescription;
	}
	public void setOtherDescription(String otherDescription) {
		this.otherDescription = otherDescription;
	}
	public int getJobQualityPoint() {
		return jobQualityPoint;
	}
	public void setJobQualityPoint(int jobQualityPoint) {
		this.jobQualityPoint = jobQualityPoint;
	}
	public int getJobPerformancePoint() {
		return jobPerformancePoint;
	}
	public void setJobPerformancePoint(int jobPerformancePoint) {
		this.jobPerformancePoint = jobPerformancePoint;
	}
	public int getServiceSpiritPoint() {
		return serviceSpiritPoint;
	}
	public void setServiceSpiritPoint(int serviceSpiritPoint) {
		this.serviceSpiritPoint = serviceSpiritPoint;
	}
	public int getLawObeyingPoint() {
		return lawObeyingPoint;
	}
	public void setLawObeyingPoint(int lawObeyingPoint) {
		this.lawObeyingPoint = lawObeyingPoint;
	}
	public int getJobPassionatelyPoint() {
		return jobPassionatelyPoint;
	}
	public void setJobPassionatelyPoint(int jobPassionatelyPoint) {
		this.jobPassionatelyPoint = jobPassionatelyPoint;
	}
	public int getLearningAttitudePoint() {
		return learningAttitudePoint;
	}
	public void setLearningAttitudePoint(int learningAttitudePoint) {
		this.learningAttitudePoint = learningAttitudePoint;
	}
	public String getPersonalSpeciality() {
		return personalSpeciality;
	}
	public void setPersonalSpeciality(String personalSpeciality) {
		this.personalSpeciality = personalSpeciality;
	}
	public String getSpecialAchievement() {
		return specialAchievement;
	}
	public void setSpecialAchievement(String specialAchievement) {
		this.specialAchievement = specialAchievement;
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
	public String getSumupSuggestLevel() {
		return sumupSuggestLevel;
	}
	public void setSumupSuggestLevel(String sumupSuggestLevel) {
		this.sumupSuggestLevel = sumupSuggestLevel;
	}
	public String getSumupConfirmedLevel() {
		return sumupConfirmedLevel;
	}
	public void setSumupConfirmedLevel(String sumupConfirmedLevel) {
		this.sumupConfirmedLevel = sumupConfirmedLevel;
	}
	
}
