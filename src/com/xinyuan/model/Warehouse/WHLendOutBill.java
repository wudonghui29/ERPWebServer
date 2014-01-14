package com.xinyuan.model.Warehouse;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.BillApp2;

@Entity
@Table
public class WHLendOutBill extends BillApp2 {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String billNO;     //Bill单号
	
	private float returnAmount; //还入数量
	private Date returnDate; //还入日期
	
	private String billCreateUser; //制单人
	private String returnImageName;//还入图片名
	
	
	
	
	public String getBillNO() {
		return billNO;
	}
	public void setBillNO(String billNO) {
		this.billNO = billNO;
	}
	public String getReturnImageName() {
		return returnImageName;
	}
	public void setReturnImageName(String returnImageName) {
		this.returnImageName = returnImageName;
	}
	public float getReturnAmount() {
		return returnAmount;
	}
	public void setReturnAmount(float returnAmount) {
		this.returnAmount = returnAmount;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getBillCreateUser() {
		return billCreateUser;
	}
	public void setBillCreateUser(String billCreateUser) {
		this.billCreateUser = billCreateUser;
	}
	
	
	
	
	
	
}
