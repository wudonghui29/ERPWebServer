package com.xinyuan.model.Warehouse;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp4;

@Entity
@Table
public class WHPickingDetailsOrder extends OrderApp4 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date pickingDate;   //日期
	private Set<WHPickingDetailsBill> WHPickingDetailsBills;
	
		
	public Date getPickingDate() {
		return pickingDate;
	}
	public void setPickingDate(Date pickingDate) {
		this.pickingDate = pickingDate;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="WHPickingDetailsOrder_id")	
	public Set<WHPickingDetailsBill> getWHPickingDetailsBills() {
		return WHPickingDetailsBills;
	}
	public void setWHPickingDetailsBills(
			Set<WHPickingDetailsBill> wHPickingDetailsBills) {
		WHPickingDetailsBills = wHPickingDetailsBills;
	}
	
	

}
