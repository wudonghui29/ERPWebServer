package com.xinyuan.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Level_Four_Approve extends Level_Three_Approve {

	private String levelApp_4 ;

	public String getLevelApp_4() {
		return levelApp_4;
	}

	public void setLevelApp_4(String levelApp_4) {
		this.levelApp_4 = levelApp_4;
	}

}
