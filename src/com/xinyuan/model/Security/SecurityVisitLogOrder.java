package com.xinyuan.model.Security;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.LevelApp_4;

/**
 *
 *  警卫 日志
 *
 */

@Entity
@Table
public class SecurityVisitLogOrder extends LevelApp_4 {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date date ;		// 日期时间范围
	
	private String annotation;		// 特别记事


	@Column(unique=true)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(columnDefinition="TEXT")
	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	
}
