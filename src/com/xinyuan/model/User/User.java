package com.xinyuan.model.User;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int uid ; 				// primary id , id == 1 is administrator for now
	private String username;		// == employeeNO
	private String password;		//
	
	private String permissions = "{}"; 	// format "superModule.module.read,...." . i.e HumanResource.EmployeeInfo.read
	private String categories = "[]";		// "HumanResource", "Security" ...
	
	private String deviceInfo; 		// ipad or iphone or ...
	private String deviceId;
	private String apnsToken = "";
	
	@Id
	@GeneratedValue
	public int getId() {
		return uid;
	}
	public void setId(int id) {
		this.uid = id;
	}
	
	@NotEmpty
	@Column(length=50, unique=true, updatable=false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@NotEmpty
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
//	@Column(columnDefinition="varchar(500) default ''")
	@Column(columnDefinition="TEXT")
	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
	@Column(columnDefinition="varchar(255) default ''")
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Column(length=255, columnDefinition="varchar(255) default ''")
	public String getApnsToken() {
		return apnsToken;
	}
	public void setApnsToken(String apnsToken) {
		this.apnsToken = apnsToken;
	}
	
}
