package com.xinyuan.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	private boolean exception;	// Exception or not
	
	public boolean isException() {
		return exception;
	}
	public void setException(boolean exception) {
		this.exception = exception;
	}
}
