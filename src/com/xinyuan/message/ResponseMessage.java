package com.xinyuan.message;

import java.util.List;



public class ResponseMessage {
	
	public String status = ConfigConstants.STATUS_NEGATIVE; // 0 failed, 1 success. Important only 0 and 1 value , cause in client end use if (data.status) to judge. .
	public String action = ConfigConstants.EMPTY_STRING;
	public String exception = null;
	public String denyStatus = null;
	public String descriptions = null;
	
	public Object results = null;
	public List<String> numbers = null;
	
	public List<String> models = null;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public Object getResults() {
		return results;
	}
	public void setResults(Object results) {
		this.results = results;
	}
	public List<String> getModels() {
		return models;
	}
	public void setModels(List<String> models) {
		this.models = models;
	}
	public List<String> getNumbers() {
		return numbers;
	}
	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}
	public String getDenyStatus() {
		return denyStatus;
	}
	public void setDenyStatus(String denyStatus) {
		this.denyStatus = denyStatus;
	}

}
