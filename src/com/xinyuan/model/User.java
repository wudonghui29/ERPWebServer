package com.xinyuan.model;

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
	
	private int uid ; 		// primary id , id == 1 is administrator for now
	private String username;// == employeeNO
	private String password;//
	private String permissions; // format "superModule.module.read,...." . i.e HumanResource.EmployeeInfo.read
	
	private String apnsToken ;
	private String deviceInfo; 		// ipad or iphone or ...
	private String deviceId;
	
	@Id
	@GeneratedValue
	public int getId() {
		return uid;
	}
	public void setId(int id) {
		this.uid = id;
	}
	
	@NotEmpty
	@Column(length=100, unique=true)
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
	
	
	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
	public String getApnsToken() {
		return apnsToken;
	}
	public void setApnsToken(String apnsToken) {
		this.apnsToken = apnsToken;
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

}
