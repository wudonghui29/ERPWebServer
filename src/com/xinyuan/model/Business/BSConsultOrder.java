package com.xinyuan.model.Business;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.BaseOrder;

/**
 * 
 * 洽谈记录单
 * 
 *
 */

@Table
@Entity
public class BSConsultOrder extends BaseOrder {

	private static final long serialVersionUID = 1L;
	
	private String number ;		// 客户编号
	
	private String consult;		// 洽谈人
	
	private String project;		// 工程名称
	private String projectAddr;	// 工程地址

}
