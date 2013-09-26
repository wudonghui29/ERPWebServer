package com.xinyuan.model.HumanResource;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.xinyuan.model.LevelApp_1;


/**
 * 月份出差误餐费已补贴明细表
 *
 */

@Entity
@Table
public class EmployeeBusinessMealOrder extends LevelApp_1 {
	
	private Date mealDate;		// 日期
	
	private String employeeNO ; // 员工编号
	
	private boolean morningMeal;	// 早
	private boolean noonMeal;		// 午
	private boolean eveningMeal;	// 晚
	
	public Date getMealDate() {
		return mealDate;
	}
	public void setMealDate(Date mealDate) {
		this.mealDate = mealDate;
	}
	public String getEmployeeNO() {
		return employeeNO;
	}
	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
	}
	public boolean isMorningMeal() {
		return morningMeal;
	}
	public void setMorningMeal(boolean morningMeal) {
		this.morningMeal = morningMeal;
	}
	public boolean isNoonMeal() {
		return noonMeal;
	}
	public void setNoonMeal(boolean noonMeal) {
		this.noonMeal = noonMeal;
	}
	public boolean isEveningMeal() {
		return eveningMeal;
	}
	public void setEveningMeal(boolean eveningMeal) {
		this.eveningMeal = eveningMeal;
	}
	
}
