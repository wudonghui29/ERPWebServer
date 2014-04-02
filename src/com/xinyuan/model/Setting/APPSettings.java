package com.xinyuan.model.Setting;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class APPSettings implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Transient
	public static String COLUMN_TYPE = "type";
	
	private int id;
	
	private String type;
	
	private String settings = "{}";		// default , can be array "[]", or something else

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(unique=true)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(columnDefinition="TEXT")
	public String getSettings() {
		return settings;
	}

	public void setSettings(String settings) {
		this.settings = settings;
	}

}
