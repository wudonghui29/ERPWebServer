package com.xinyuan.model.Security;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.xinyuan.model.OrderApp3;

/**
 * 巡查日志
 *
 */

@Entity
@Table
public class SecurityPatrolOrder extends OrderApp3 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Date protralDate;	// 日期
	
	private String exComment; 	// 异常反应
	
	private Set<SecurityPatrolBill> bills;

	public Date getProtralDate() {
		return protralDate;
	}

	public void setProtralDate(Date protralDate) {
		this.protralDate = protralDate;
	}

	public String getExComment() {
		return exComment;
	}

	public void setExComment(String exComment) {
		this.exComment = exComment;
	}

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="SecurityPatrolOrder_id")
	public Set<SecurityPatrolBill> getBills() {
		return bills;
	}

	public void setBills(Set<SecurityPatrolBill> bills) {
		this.bills = bills;
	}

}
