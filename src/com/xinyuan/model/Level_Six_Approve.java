package com.xinyuan.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Level_Six_Approve extends Level_Five_Approve {

	private String levelApp_6;

	public String getLevelApp_6() {
		return levelApp_6;
	}

	public void setLevelApp_6(String levelApp_6) {
		this.levelApp_6 = levelApp_6;
	}

}
