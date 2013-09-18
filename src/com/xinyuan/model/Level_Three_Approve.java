package com.xinyuan.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Level_Three_Approve extends Level_Two_Approve {

	private String levelApp_3 ;

	public String getLevelApp_3() {
		return levelApp_3;
	}

	public void setLevelApp_3(String levelApp_3) {
		this.levelApp_3 = levelApp_3;
	}

}
