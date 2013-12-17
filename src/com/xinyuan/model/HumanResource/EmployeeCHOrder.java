package com.xinyuan.model.HumanResource;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.App4;

@Entity
@Table
public class EmployeeCHOrder extends App4 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String employeeNO;
	
	// _O for Old , _N for New.
	
	private String department_O;
	private String subDepartment_O;
	private int jobLevel_O;
	private String jobTitle_O;
	private String phoneNO_O;
	
	
	private String department_N;
	private String subDepartment_N;
	private int jobLevel_N;
	private String jobTitle_N;
	private String phoneNO_N;
	
	private String password_N;
	
	public String getEmployeeNO() {
		return employeeNO;
	}
	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
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
	public String getPhoneNO_N() {
		return phoneNO_N;
	}
	public void setPhoneNO_N(String phoneNO_N) {
		this.phoneNO_N = phoneNO_N;
	}
	public String getPassword_N() {
		return password_N;
	}
	public void setPassword_N(String password_N) {
		this.password_N = password_N;
	}
}
