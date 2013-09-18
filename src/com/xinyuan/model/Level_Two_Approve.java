package com.xinyuan.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Level_Two_Approve extends Level_One_Approve {

	private String levelApp_2 ;

	public String getLevelApp_2() {
		return levelApp_2;
	}

	public void setLevelApp_2(String levelApp_2) {
		this.levelApp_2 = levelApp_2;
	}
	
}
