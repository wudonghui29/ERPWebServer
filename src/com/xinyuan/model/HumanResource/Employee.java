package com.xinyuan.model.HumanResource;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.xinyuan.model.LevelAPP_5;

@Entity
@Table
public class Employee extends LevelAPP_5 {
	
	private String name ;
	private String idCard ;
	private boolean gender;  // 0 , female ; 1 , male  // boolean respect as "true" in json
	private Date birthday;
	
	private float height;
	private float weight;
	private String photoPath;

	
	private String phoneNO;
	private String urgencyContact;  // relative and his phone NO.
	private String country;
	private String nationality ;  // human race 
	private String nativePlace;  // native place
	private String homeAddress;
	private String livingAddress;

	private String employeeNO; // employee NO. unique
	private Date employDate;   // entry date , sign in date
	
	
	private String departmentName; 
	private int jobLevel;
	private String jobTitle;
	
	private boolean drivingLicence ; // 0 , have ; 1 , do not have


//	@NotEmpty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@NotEmpty
	@Column(unique=true)
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Column(nullable=false, columnDefinition="boolean default false")
	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getPhoneNO() {
		return phoneNO;
	}

	public void setPhoneNO(String phoneNO) {
		this.phoneNO = phoneNO;
	}

	public String getUrgencyContact() {
		return urgencyContact;
	}

	public void setUrgencyContact(String urgencyContact) {
		this.urgencyContact = urgencyContact;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getLivingAddress() {
		return livingAddress;
	}

	public void setLivingAddress(String livingAddress) {
		this.livingAddress = livingAddress;
	}
	
	
	@NotEmpty
	@Column(unique=true)
	public String getEmployeeNO() {
		return employeeNO;
	}
	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
	}

	public Date getEmployDate() {
		return employDate;
	}

	public void setEmployDate(Date employDate) {
		this.employDate = employDate;
	}
	
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(int jobLevel) {
		this.jobLevel = jobLevel;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	@Column(nullable=false, columnDefinition="boolean default false")  
	public boolean isDrivingLicence() {
		return drivingLicence;
	}

	public void setDrivingLicence(boolean drivingLicence) {
		this.drivingLicence = drivingLicence;
	}

}
