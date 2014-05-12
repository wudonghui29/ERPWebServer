package com.xinyuan.model.HumanResource;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.xinyuan.model.OrderApp4;

@Entity
@Table
public class EmployeeCHOrder extends OrderApp4 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String employeeNO;
	
	private String name_O;
	private String name_N;
	
	// _O for Old , _N for New.
	
	private String department_O;
	private String subDepartment_O;
	private int jobLevel_O;
	private String jobTitle_O;
	
	private String livingAddress_O;
	private String urgencyName_O;
	private String urgencyPhone_O;
	private String phoneNO_O;
	
	private boolean ownApproval_O;
	private boolean inDrives_O; 
	private boolean resign_O; 
	
	private String department_N;
	private String subDepartment_N;
	private int jobLevel_N;
	private String jobTitle_N;
	
	private String livingAddress_N;
	private String urgencyName_N;
	private String urgencyPhone_N;
	private String phoneNO_N;
	
	private boolean ownApproval_N; 
	private boolean inDrives_N; 
	private boolean resign_N; 
	
	private String password_N;
	
	@NotEmpty
	public String getEmployeeNO() {
		return employeeNO;
	}
	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
	}
	
	public String getName_O() {
		return name_O;
	}
	public void setName_O(String name_O) {
		this.name_O = name_O;
	}
	public String getName_N() {
		return name_N;
	}
	public void setName_N(String name_N) {
		this.name_N = name_N;
	}
	public String getDepartment_O() {
		return department_O;
	}
	public void setDepartment_O(String department_O) {
		this.department_O = department_O;
	}
	public String getSubDepartment_O() {
		return subDepartment_O;
	}
	public void setSubDepartment_O(String subDepartment_O) {
		this.subDepartment_O = subDepartment_O;
	}
	public int getJobLevel_O() {
		return jobLevel_O;
	}
	public void setJobLevel_O(int jobLevel_O) {
		this.jobLevel_O = jobLevel_O;
	}
	public String getJobTitle_O() {
		return jobTitle_O;
	}
	public void setJobTitle_O(String jobTitle_O) {
		this.jobTitle_O = jobTitle_O;
	}
	public String getPhoneNO_O() {
		return phoneNO_O;
	}
	public void setPhoneNO_O(String phoneNO_O) {
		this.phoneNO_O = phoneNO_O;
	}
	public String getDepartment_N() {
		return department_N;
	}
	public void setDepartment_N(String department_N) {
		this.department_N = department_N;
	}
	public String getSubDepartment_N() {
		return subDepartment_N;
	}
	public void setSubDepartment_N(String subDepartment_N) {
		this.subDepartment_N = subDepartment_N;
	}
	public int getJobLevel_N() {
		return jobLevel_N;
	}
	public void setJobLevel_N(int jobLevel_N) {
		this.jobLevel_N = jobLevel_N;
	}
	public String getJobTitle_N() {
		return jobTitle_N;
	}
	public void setJobTitle_N(String jobTitle_N) {
		this.jobTitle_N = jobTitle_N;
	}
	
	public String getLivingAddress_O() {
		return livingAddress_O;
	}
	public void setLivingAddress_O(String livingAddress_O) {
		this.livingAddress_O = livingAddress_O;
	}
	public String getUrgencyName_O() {
		return urgencyName_O;
	}
	public void setUrgencyName_O(String urgencyName_O) {
		this.urgencyName_O = urgencyName_O;
	}
	public String getUrgencyPhone_O() {
		return urgencyPhone_O;
	}
	public void setUrgencyPhone_O(String urgencyPhone_O) {
		this.urgencyPhone_O = urgencyPhone_O;
	}
	public String getLivingAddress_N() {
		return livingAddress_N;
	}
	public void setLivingAddress_N(String livingAddress_N) {
		this.livingAddress_N = livingAddress_N;
	}
	public String getUrgencyName_N() {
		return urgencyName_N;
	}
	public void setUrgencyName_N(String urgencyName_N) {
		this.urgencyName_N = urgencyName_N;
	}
	public String getUrgencyPhone_N() {
		return urgencyPhone_N;
	}
	public void setUrgencyPhone_N(String urgencyPhone_N) {
		this.urgencyPhone_N = urgencyPhone_N;
	}
	public String getPhoneNO_N() {
		return phoneNO_N;
	}
	public void setPhoneNO_N(String phoneNO_N) {
		this.phoneNO_N = phoneNO_N;
	}
	
	public boolean isOwnApproval_O() {
		return ownApproval_O;
	}
	public void setOwnApproval_O(boolean ownApproval_O) {
		this.ownApproval_O = ownApproval_O;
	}
	public boolean isInDrives_O() {
		return inDrives_O;
	}
	public void setInDrives_O(boolean inDrives_O) {
		this.inDrives_O = inDrives_O;
	}
	public boolean isResign_O() {
		return resign_O;
	}
	public void setResign_O(boolean resign_O) {
		this.resign_O = resign_O;
	}
	public boolean isOwnApproval_N() {
		return ownApproval_N;
	}
	public void setOwnApproval_N(boolean ownApproval_N) {
		this.ownApproval_N = ownApproval_N;
	}
	public boolean isInDrives_N() {
		return inDrives_N;
	}
	public void setInDrives_N(boolean inDrives_N) {
		this.inDrives_N = inDrives_N;
	}
	public boolean isResign_N() {
		return resign_N;
	}
	public void setResign_N(boolean resign_N) {
		this.resign_N = resign_N;
	}
	public String getPassword_N() {
		return password_N;
	}
	public void setPassword_N(String password_N) {
		this.password_N = password_N;
	}
}
