package com.xinyuan.model.Warehouse;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.xinyuan.model.LevelApp_3;

@Entity
@Table
public class WHLendOutOrder extends LevelApp_3 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date lendDate;			//借出日期
	private Date planReturnDate; 	//预计还入日期
	private Date returnDate;		//归还日期
	
	
	private String staffCategory;	//人员类别
	private String staffNO;			//借出人员
	
	private String annotation;		// 附注
	
	private Set<WHLendOutBill> bills;		// 借出列表		//  OneToMany , and the relationship is unidirectional.

	public Date getLendDate() {
		return lendDate;
	}

	public void setLendDate(Date lendDate) {
		this.lendDate = lendDate;
	}

	public Date getPlanReturnDate() {
		return planReturnDate;
	}

	public void setPlanReturnDate(Date planReturnDate) {
		this.planReturnDate = planReturnDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getStaffCategory() {
		return staffCategory;
	}

	public void setStaffCategory(String staffCategory) {
		this.staffCategory = staffCategory;
	}

	public String getStaffNO() {
		return staffNO;
	}

	public void setStaffNO(String staffNO) {
		this.staffNO = staffNO;
	}

	@Column(columnDefinition="TEXT")
	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)	// LAZY : http://stackoverflow.com/a/2192256/1749293
	@JoinColumn(name="WHLendOutOrder_id")						// This will add a column "WHLendOutOrder_id" in WHLendOutBill table, if not written, DB will create a middle-join table .
	public Set<WHLendOutBill> getBills() {
		return bills;
	}

	public void setBills(Set<WHLendOutBill> bills) {
		this.bills = bills;
	}
	
}
