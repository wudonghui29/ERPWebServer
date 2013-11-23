package com.xinyuan.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class LevelAPP_5 extends LevelApp_4 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String levelApp_5;

	public String getLevelApp_5() {
		return levelApp_5;
	}

	public void setLevelApp_5(String levelApp_5) {
		this.levelApp_5 = levelApp_5;
	}

}
