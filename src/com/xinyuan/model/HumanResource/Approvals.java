package com.xinyuan.model.HumanResource;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

/**
 * User Pending approvals
 *
 */

@Entity
@Table
public class Approvals implements Serializable {

	private String username;			// TODO: all employeeNO can not be modified again once it has value . In the set method , check it .
	private String pendingApprovals = "";  	// the pending approval orders , split with "," e.g. "JH201304050901.HumanResource.Employee,JH201304050901.HumanResource.EmployeeOutOrder"
	
	
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "assigned")
	@GeneratedValue(generator = "idGenerator")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(columnDefinition="varchar(255) default ''")
	public String getPendingApprovals() {
		return pendingApprovals;
	}
	public void setPendingApprovals(String pendingApprovals) {
		this.pendingApprovals = pendingApprovals;
	}
	
}