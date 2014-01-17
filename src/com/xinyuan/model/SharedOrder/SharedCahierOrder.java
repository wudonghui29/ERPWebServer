package com.xinyuan.model.SharedOrder;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp3;

@Entity
@Table
public class SharedCahierOrder extends OrderApp3 {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String meetName;			//会议名称
	private Date meetDate;				//会议日期
	private String personalEmployeeNOs;	//出席人员
	private String absentEmployeeNOs;	//缺席人员
	private String location;			//地点
	
	private String meetContent;			//发言主要内容
	private String meetTodo;			//议决待办事项
	
	
	
	public String getMeetName() {
		return meetName;
	}
	public void setMeetName(String meetName) {
		this.meetName = meetName;
	}
	public Date getMeetDate() {
		return meetDate;
	}
	public void setMeetDate(Date meetDate) {
		this.meetDate = meetDate;
	}
	
	@Column(columnDefinition="TEXT")
	public String getPersonalEmployeeNOs() {
		return personalEmployeeNOs;
	}
	public void setPersonalEmployeeNOs(String personalEmployeeNOs) {
		this.personalEmployeeNOs = personalEmployeeNOs;
	}
	
	@Column(columnDefinition="TEXT")
	public String getAbsentEmployeeNOs() {
		return absentEmployeeNOs;
	}
	public void setAbsentEmployeeNOs(String absentEmployeeNOs) {
		this.absentEmployeeNOs = absentEmployeeNOs;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMeetContent() {
		return meetContent;
	}
	public void setMeetContent(String meetContent) {
		this.meetContent = meetContent;
	}
	public String getMeetTodo() {
		return meetTodo;
	}
	public void setMeetTodo(String meetTodo) {
		this.meetTodo = meetTodo;
	}
}
