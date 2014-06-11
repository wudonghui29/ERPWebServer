package com.xinyuan.model.Approval;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User Pending approvals
 *
 */

@Entity
@Table
public class Approvals implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String employeeNO;					// TODO: all employeeNO can not be modified again once it has value . In the set method , check it .
	private String pendingApprovals = "{}";  	// the pending approval orders
	private String pendingInforms = "{}";		// the pending inform APNS. Some times the APNS would failed , so put it here .
	private String unReadApprovals = "{}";
	
	@Id
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
	
	@Column(columnDefinition="TEXT")
	public String getUnReadApprovals() {
		return unReadApprovals;
	}
	public void setUnReadApprovals(String unReadApprovals) {
		this.unReadApprovals = unReadApprovals;
	}
	
}
