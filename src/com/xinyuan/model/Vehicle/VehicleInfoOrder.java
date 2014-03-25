package com.xinyuan.model.Vehicle;


import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.BaseOrder;

@Entity
@Table
public class VehicleInfoOrder extends BaseOrder {
	private static final long serialVersionUID = 1L;
	
	private String nextMaintain;
	private Date insurExpira;
	private Date checkExpira;
	private String vehicleNo;
	private String vehicleType;
	private String fileNo;
	private String vehicleAlias;
	private String engineNo;
	private Date boughtDate;
	private Date paperDate;
	public String getNextMaintain() {
		return nextMaintain;
	}
	public void setNextMaintain(String nextMaintain) {
		this.nextMaintain = nextMaintain;
	}
	public Date getInsurExpira() {
		return insurExpira;
	}
	public void setInsurExpira(Date insurExpira) {
		this.insurExpira = insurExpira;
	}
	public Date getCheckExpira() {
		return checkExpira;
	}
	public void setCheckExpira(Date checkExpira) {
		this.checkExpira = checkExpira;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String getVehicleAlias() {
		return vehicleAlias;
	}
	public void setVehicleAlias(String vehicleAlias) {
		this.vehicleAlias = vehicleAlias;
	}
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	public Date getBoughtDate() {
		return boughtDate;
	}
	public void setBoughtDate(Date boughtDate) {
		this.boughtDate = boughtDate;
	}
	public Date getPaperDate() {
		return paperDate;
	}
	public void setPaperDate(Date paperDate) {
		this.paperDate = paperDate;
	}
	
	
}
