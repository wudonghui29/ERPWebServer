package com.xinyuan.model.HumanResource;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.xinyuan.model.LevelAPP_5;

@Entity
@Table
public class Employee extends LevelAPP_5 {
	
	private String name ;		// 姓名
	private String idCard ;		// 身份证号码
	private boolean gender;  	// 0 , female ; 1 , male  // boolean respect as "true" in json
	private Date birthday;
	
	private float height;
	private float weight;
	private String photoPath;

	
	private String phoneNO;
	private String urgencyContact;  // his name and relationship and his phone NO. e.g. "Sam.Brother.13828899987"
	private String education;		// e.g.  "广州美术学院.本科.2013-06.毕业"
	private String country;			// 国籍
	private String nationality ;  	// human race 民族
	private String nativePlace;  	// native place 籍贯
	private String homeAddress;		// 家地址
	private String livingAddress;	//现住址

	private String employeeNO; 		// employee NO. unique  工号
	private Date employDate;  	 	// entry date , sign in date
	private boolean employing;		// 是否在职  do not use "isEmploying" , cause in mysql , the column name will be "employing" , "is" is gone
	private boolean inVisitList; 	// 是否列入拜访名单
	private String experience;		// 经历   e.g. "腾讯公司.2013-06~2013-08.CEO"
	
	private String departmentName;
	private String subDepartmentName;
	private int jobLevel = 10;			//级别
	private String jobTitle;		//职称
	
	private String wordMask;		//password mask 
	
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
	
	
	@NotNull
	@NotEmpty
	@Column(unique=true, updatable=false)
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
	
	public String getSubDepartmentName() {
		return subDepartmentName;
	}

	public void setSubDepartmentName(String subDepartmentName) {
		this.subDepartmentName = subDepartmentName;
	}

	@Column(nullable=false, columnDefinition="int default 10")
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

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public boolean isEmploying() {
		return employing;
	}

	public void setEmploying(boolean employing) {
		this.employing = employing;
	}

	public boolean isInVisitList() {
		return inVisitList;
	}

	public void setInVisitList(boolean inVisitList) {
		this.inVisitList = inVisitList;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getWordMask() {
		return wordMask;
	}

	public void setWordMask(String wordMask) {
		this.wordMask = wordMask;
	}
}
