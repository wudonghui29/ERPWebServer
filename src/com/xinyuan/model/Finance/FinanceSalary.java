package com.xinyuan.model.Finance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;

import com.xinyuan.model.OrderApp2;

/**
 * 
 * 薪资表
 *
 */

@Table
@Entity
public class FinanceSalary extends OrderApp2 {

	private static final long serialVersionUID = 1L;
	
	private String employeeNO;
	
	private String name ;          // 姓名
	private String jobTitle;       // 职称
	private String department;     // 部门,工作岗位
	
	
	private float baseSalary;		// 本薪
	private float skillBenefit;		// 技术加给
	private float fullBenefit;		// 全勤资金
	private float dutyBenefit;		// 责任津贴
	private float dormBenefit;		// 住宿补贴
	private float foodBenefit;		// 伙食补贴
	
	private String salaryType;      // 薪资算法
	
	private Integer Version;
	
	
	@NotEmpty
	@Column(unique=true)
	public String getEmployeeNO() {
		return employeeNO;
	}
	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
	}
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public float getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(float baseSalary) {
		this.baseSalary = baseSalary;
	}
	public float getSkillBenefit() {
		return skillBenefit;
	}
	public void setSkillBenefit(float skillBenefit) {
		this.skillBenefit = skillBenefit;
	}
	public float getFullBenefit() {
		return fullBenefit;
	}
	public void setFullBenefit(float fullBenefit) {
		this.fullBenefit = fullBenefit;
	}
	public float getDutyBenefit() {
		return dutyBenefit;
	}
	public void setDutyBenefit(float dutyBenefit) {
		this.dutyBenefit = dutyBenefit;
	}
	public float getDormBenefit() {
		return dormBenefit;
	}
	public void setDormBenefit(float dormBenefit) {
		this.dormBenefit = dormBenefit;
	}
	public float getFoodBenefit() {
		return foodBenefit;
	}
	public void setFoodBenefit(float foodBenefit) {
		this.foodBenefit = foodBenefit;
	}
    public String getSalaryType() {
        return salaryType;
    }
    public void setSalaryType(String salaryType) {
        this.salaryType = salaryType;
    }
    @Version
	public Integer getVersion() {
		return Version;
	}
	public void setVersion(Integer version) {
		Version = version;
	}
}
