package com.xinyuan.model.Security;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp4;

/**
 *
 *  警卫 日志
 *
 */

@Entity
@Table
public class SecurityVisitLogOrder extends OrderApp4 {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date logDate ;		// 日期
	
	private String annotation;		// 特别记事
	

	@Column(unique=true)
	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	@Column(columnDefinition="TEXT")
	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	
}
