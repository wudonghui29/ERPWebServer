package com.xinyuan.model;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class Level_One_Approve extends BaseOrderModel {

	private String levelApp_1 ;		// employeeNO

	public String getLevelApp_1() {
		return levelApp_1;
	}

	public void setLevelApp_1(String levelApp_1) {
		this.levelApp_1 = levelApp_1;
	}

}
