package com.xinyuan.model.Approval;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * User Pending approvals
 *
 */

@Entity
@Table
public class Approvals implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String employeeNO;		// TODO: all employeeNO can not be modified again once it has value . In the set method , check it .
	private String pendingApprovals = "";  	// the pending approval orders , split with "," e.g. "JH201304050901.HumanResource.Employee,JH201304050901.HumanResource.EmployeeOutOrder"
	private String pendingInforms = "";		// the pending inform APNS. Some times the APNS would failed , so put it here .
	private String apnsToken = "";
	
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "assigned")
	@GeneratedValue(generator = "idGenerator")
	@Column(updatable=false)
	public String getEmployeeNO() {
		return employeeNO;
	}
	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
	}
	
	
	@Column(columnDefinition="TEXT")
	public String getPendingApprovals() {
		return pendingApprovals;
	}
	public void setPendingApprovals(String pendingApprovals) {
		this.pendingApprovals = pendingApprovals;
	}
	
	@Column(columnDefinition="TEXT")
	public String getPendingInforms() {
		return pendingInforms;
	}
	public void setPendingInforms(String pendingInforms) {
		this.pendingInforms = pendingInforms;
	}
	
	@Column(length=255, columnDefinition="varchar(255) default ''")
	public String getApnsToken() {
		return apnsToken;
	}
	public void setApnsToken(String apnsToken) {
		this.apnsToken = apnsToken;
	}
}
