package com.xinyuan.model.Security;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp1;


/**
 *  拜访单/访客单
 *
 */

@Entity
@Table
public class SecurityVisitorOrder extends OrderApp1 {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String visitor;		//访客
	private String vehicleNO;	//车号
	private int	personNUM;		//人数
	private String visitEmployeeNO;	//拜访
	private String reason;		//事由
	private String doneButtonEmployeeNO;	//已接见
	private String passIn_name;	//入厂放行保安
	private Date passIn_time;	//入厂放行时间
	private String passOut_name;	//出厂放行保安
	private Date passOut_time;	//出厂放行时间
	
	
	public String getVisitor() {
		return visitor;
	}
	public void setVisitor(String visitor) {
		this.visitor = visitor;
	}
	public String getVehicleNO() {
		return vehicleNO;
	}
	public void setVehicleNO(String vehicleNO) {
		this.vehicleNO = vehicleNO;
	}
	public int getPersonNUM() {
		return personNUM;
	}
	public void setPersonNUM(int personNUM) {
		this.personNUM = personNUM;
	}
	public String getVisitEmployeeNO() {
		return visitEmployeeNO;
	}
	public void setVisitEmployeeNO(String visitEmployeeNO) {
		this.visitEmployeeNO = visitEmployeeNO;
	}
	
	@Column(columnDefinition="TEXT")
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getDoneButtonEmployeeNO() {
		return doneButtonEmployeeNO;
	}
	public void setDoneButtonEmployeeNO(String doneButtonEmployeeNO) {
		this.doneButtonEmployeeNO = doneButtonEmployeeNO;
	}
	public String getPassIn_name() {
		return passIn_name;
	}
	public void setPassIn_name(String passIn_name) {
		this.passIn_name = passIn_name;
	}
	public Date getPassIn_time() {
		return passIn_time;
	}
	public void setPassIn_time(Date passIn_time) {
		this.passIn_time = passIn_time;
	}
	public String getPassOut_name() {
		return passOut_name;
	}
	public void setPassOut_name(String passOut_name) {
		this.passOut_name = passOut_name;
	}
	public Date getPassOut_time() {
		return passOut_time;
	}
	public void setPassOut_time(Date passOut_time) {
		this.passOut_time = passOut_time;
	}
	
}
