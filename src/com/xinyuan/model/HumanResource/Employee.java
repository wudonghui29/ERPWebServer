package com.xinyuan.model.HumanResource;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.xinyuan.model.OrderApp2;

@Entity
@Table
public class Employee extends OrderApp2 {
	
	private static final long serialVersionUID = 1L;
	
	// ----------------------------- Basic Info.
	private String employeeNO; 		// employee NO. unique  工号
	
	private String name ;			// 姓名
	
	private String department;		// 部门,工作岗位
	private String subDepartment;	// 子部门,制作组
	private String jobTitle;		// 职称
	private int jobLevel = 10;		// 级别
	
	private String phoneNO;			// 电话
	
	private boolean resign;			// 已离职  do not use "isEmploying" , cause in mysql , the column name will be "employing" , "is" is gone
	private boolean inDrives ; 		// 具有驾驶证  0 , have ; 1 , do not have
	private boolean ownApproval;    // 具有审核权限 
	
	private String idCard ;			// 身份证号码
	private boolean gender;  		// 0 , female ; 1 , male  // boolean respect as "true" in json
	private Date birthday;
	private Date employDate;  	 	// 到职日期  entry date , sign in date
	
	private float height;
	private float weight;
	
	private String country;			// 国籍
	private String nationality ;  	// human race 民族
	private String nativePlace;  	// native place 籍贯
	private String homeAddress;		// 家地址
	private String livingAddress;	// 现住址

	private String education_experience ;		// 教育经历   e.g.  "广州美术学院.本科.2013-06.毕业"
	private String work_experience 		;		// 工作经历   e.g.  "2013-06.2013-08.腾讯公司.CEO"
	
	
	// 紧急联系人 name.relationship.phoneNO.
	private String urgencyName;			
	private String urgencyPhone;
	
	private String wordMask;		//password mask 
	

	@NotNull
	@NotEmpty
	@Column(unique=true)
	public String getEmployeeNO() {
		return employeeNO;
	}
	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
	}
	
	@NotEmpty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotEmpty
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

	public String getPhoneNO() {
		return phoneNO;
	}

	public void setPhoneNO(String phoneNO) {
		this.phoneNO = phoneNO;
	}

	public String getUrgencyName() {
		return urgencyName;
	}
	public void setUrgencyName(String urgencyName) {
		this.urgencyName = urgencyName;
	}
	public String getUrgencyPhone() {
		return urgencyPhone;
	}
	public void setUrgencyPhone(String urgencyPhone) {
		this.urgencyPhone = urgencyPhone;
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

	public Date getEmployDate() {
		return employDate;
	}

	public void setEmployDate(Date employDate) {
		this.employDate = employDate;
	}
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getSubDepartment() {
		return subDepartment;
	}
	public void setSubDepartment(String subDepartment) {
		this.subDepartment = subDepartment;
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
	public boolean isInDrives() {
		return inDrives;
	}
	public void setInDrives(boolean inDrives) {
		this.inDrives = inDrives;
	}
	
	
	@Column(columnDefinition="boolean default false")
	public boolean isOwnApproval() {
		return ownApproval;
	}
	
	public void setOwnApproval(boolean ownApproval) {
		this.ownApproval = ownApproval;
	}
	
	
	public boolean isResign() {
		return resign;
	}
	public void setResign(boolean resign) {
		this.resign = resign;
	}

	public String getWordMask() {
		return wordMask;
	}

	public void setWordMask(String wordMask) {
		this.wordMask = wordMask;
	}
	public String getEducation_experience() {
		return education_experience;
	}
	public void setEducation_experience(String education_experience) {
		this.education_experience = education_experience;
	}
	public String getWork_experience() {
		return work_experience;
	}
	public void setWork_experience(String work_experience) {
		this.work_experience = work_experience;
	}
	
}
