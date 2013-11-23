package com.xinyuan.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class LevelApp_1 extends BaseOrder {

	private String levelApp_1 ;		// username , employeeNO

	public String getLevelApp_1() {
		return levelApp_1;
	}

	public void setLevelApp_1(String levelApp_1) {
		this.levelApp_1 = levelApp_1;
	}

}
