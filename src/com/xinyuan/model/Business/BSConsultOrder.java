package com.xinyuan.model.Business;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.BaseOrder;

/**
 * 
 * 洽谈记录单
 * 
 *
 */

@Table
@Entity
public class BSConsultOrder extends BaseOrder {

	private static final long serialVersionUID = 1L;
	
	private String number ;				// 客户编号
	
	private String consult;				// 洽谈人
	private String phoneNO;				// 电话
		
	private String project;				// 工程名称
	private String projectAddr;			// 工程地址
	private String traceEmployeeNO;		// 业务跟进人
	
	private String structForm;			// 结构形式
	private String content;				// 洽谈内容
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getConsult() {
		return consult;
	}
	public void setConsult(String consult) {
		this.consult = consult;
	}
	public String getPhoneNO() {
		return phoneNO;
	}
	public void setPhoneNO(String phoneNO) {
		this.phoneNO = phoneNO;
	}
	
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getProjectAddr() {
		return projectAddr;
	}
	public void setProjectAddr(String projectAddr) {
		this.projectAddr = projectAddr;
	}
	public String getTraceEmployeeNO() {
		return traceEmployeeNO;
	}
	public void setTraceEmployeeNO(String traceEmployeeNO) {
		this.traceEmployeeNO = traceEmployeeNO;
	}
	public String getStructForm() {
		return structForm;
	}
	public void setStructForm(String structForm) {
		this.structForm = structForm;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
