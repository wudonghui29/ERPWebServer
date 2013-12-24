package com.xinyuan.model.Finance;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.xinyuan.model.App4;

@Entity
@Table
public class FinanceCHOrder extends App4 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String employeeNO;

	// _O for Old , _N for New.
	
	private float baseSalary;		// 本薪
//	private float skill
	
	
	
}
