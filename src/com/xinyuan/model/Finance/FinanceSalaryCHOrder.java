package com.xinyuan.model.Finance;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp4;

@Entity
@Table
public class FinanceSalaryCHOrder extends OrderApp4 {

	private static final long serialVersionUID = 1L;

	private String employeeNO;
	
    private String name;        // 姓名
    private String jobTitle;    // 职称
    private String department;  // 部门,工作岗位
	    
	private Date adjustDate;			// 本次调整日期 
	private float adjustAmount;         // 本次调整金额
	
	private Date lastAdjustDate;           // 上次调整日期 
    private float lastAdjustAmount;        // 上次调整金额

    
	// _O for Old , _N for New.
	
	private float baseSalary_O;			// 本薪
	private float skillBenefit_O;		// 技术加给
	private float fullBenefit_O;		// 全勤资金
	private float dutyBenefit_O;		// 责任津贴
	private float dormBenefit_O;		// 住宿补贴
	private float foodBenefit_O;		// 伙食补贴
	
	
	private float baseSalary_N;
	private float skillBenefit_N;
	private float fullBenefit_N;
	private float dutyBenefit_N;
	private float dormBenefit_N;
	private float foodBenefit_N;
	
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
    public Date getAdjustDate() {
		return adjustDate;
	}
	public void setAdjustDate(Date adjustDate) {
		this.adjustDate = adjustDate;
	}
	public float getAdjustAmount() {
        return adjustAmount;
    }
    public void setAdjustAmount(float adjustAmount) {
        this.adjustAmount = adjustAmount;
    }
    public Date getLastAdjustDate() {
        return lastAdjustDate;
    }
    public void setLastAdjustDate(Date lastAdjustDate) {
        this.lastAdjustDate = lastAdjustDate;
    }
    public float getLastAdjustAmount() {
        return lastAdjustAmount;
    }
    public void setLastAdjustAmount(float lastAdjustAmount) {
        this.lastAdjustAmount = lastAdjustAmount;
    }
    public float getBaseSalary_O() {
		return baseSalary_O;
	}
	public void setBaseSalary_O(float baseSalary_O) {
		this.baseSalary_O = baseSalary_O;
	}
	public float getSkillBenefit_O() {
		return skillBenefit_O;
	}
	public void setSkillBenefit_O(float skillBenefit_O) {
		this.skillBenefit_O = skillBenefit_O;
	}
	public float getFullBenefit_O() {
		return fullBenefit_O;
	}
	public void setFullBenefit_O(float fullBenefit_O) {
		this.fullBenefit_O = fullBenefit_O;
	}
	public float getDutyBenefit_O() {
		return dutyBenefit_O;
	}
	public void setDutyBenefit_O(float dutyBenefit_O) {
		this.dutyBenefit_O = dutyBenefit_O;
	}
	public float getDormBenefit_O() {
		return dormBenefit_O;
	}
	public void setDormBenefit_O(float dormBenefit_O) {
		this.dormBenefit_O = dormBenefit_O;
	}
	public float getFoodBenefit_O() {
		return foodBenefit_O;
	}
	public void setFoodBenefit_O(float foodBenefit_O) {
		this.foodBenefit_O = foodBenefit_O;
	}
	public float getBaseSalary_N() {
		return baseSalary_N;
	}
	public void setBaseSalary_N(float baseSalary_N) {
		this.baseSalary_N = baseSalary_N;
	}
	public float getSkillBenefit_N() {
		return skillBenefit_N;
	}
	public void setSkillBenefit_N(float skillBenefit_N) {
		this.skillBenefit_N = skillBenefit_N;
	}
	public float getFullBenefit_N() {
		return fullBenefit_N;
	}
	public void setFullBenefit_N(float fullBenefit_N) {
		this.fullBenefit_N = fullBenefit_N;
	}
	public float getDutyBenefit_N() {
		return dutyBenefit_N;
	}
	public void setDutyBenefit_N(float dutyBenefit_N) {
		this.dutyBenefit_N = dutyBenefit_N;
	}
	public float getDormBenefit_N() {
		return dormBenefit_N;
	}
	public void setDormBenefit_N(float dormBenefit_N) {
		this.dormBenefit_N = dormBenefit_N;
	}
	public float getFoodBenefit_N() {
		return foodBenefit_N;
	}
	public void setFoodBenefit_N(float foodBenefit_N) {
		this.foodBenefit_N = foodBenefit_N;
	}
}
