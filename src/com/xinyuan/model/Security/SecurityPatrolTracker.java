package com.xinyuan.model.Security;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.BaseOrder;

@Entity
@Table
public class SecurityPatrolTracker extends BaseOrder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Time startTime;		//开始巡逻时间
	private Time endTime;		//结束巡逻时间
	
	
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	

}
