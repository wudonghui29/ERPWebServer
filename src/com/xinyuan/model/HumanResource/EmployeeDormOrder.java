package com.xinyuan.model.HumanResource;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp4;


/**
 * 员工宿舍明细表
 *
 */

@Entity
@Table
public class EmployeeDormOrder extends OrderApp4 {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String liveInEmployeeNOs ; 		// 住进的员工的编号 比如住了2人: "EH001,EH002"
	
	private Date dormitoryDate; // 实体表中比如: "2013年9月一楼宿舍明细表" 中的 "2013年9月"
	private int floor ; 		// 实体表中比如: "2013年9月一楼宿舍明细表" 中的 "一楼"
	
	private int roomNO ; 		// 实体表中的 "序号" 中的 "102-1" 中的 "102"  // not use string for easy to sort
	private int subRoomNO ; 	// 实体表中的 "序号" 中的 "102-1" 中的 "-1" 中的 "1"
	
	private String roomType ;			// 实体表中的"规格": 单人 , 夫妻房 , 4人房
	private int population ;			// 人数
	
	private float expense ; 		// 应缴纳住宿费用
	
	private String description; 	// 备注
	
	
	public String getLiveInEmployeeNOs() {
		return liveInEmployeeNOs;
	}

	public void setLiveInEmployeeNOs(String liveInEmployeeNOs) {
		this.liveInEmployeeNOs = liveInEmployeeNOs;
	}

	public Date getDormitoryDate() {
		return dormitoryDate;
	}

	public void setDormitoryDate(Date dormitoryDate) {
		this.dormitoryDate = dormitoryDate;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getRoomNO() {
		return roomNO;
	}

	public void setRoomNO(int roomNO) {
		this.roomNO = roomNO;
	}

	public int getSubRoomNO() {
		return subRoomNO;
	}

	public void setSubRoomNO(int subRoomNO) {
		this.subRoomNO = subRoomNO;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public float getExpense() {
		return expense;
	}

	public void setExpense(float expense) {
		this.expense = expense;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
