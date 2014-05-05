package com.xinyuan.dao.impl;

import java.io.Serializable;

import com.xinyuan.dao.ApprovalDAO;
import com.xinyuan.model.Approval.Approvals;

public class ApprovalDAOIMP extends ModelDAOIMP implements ApprovalDAO {

	public Approvals getApprovals(Serializable id) {
		return (Approvals)super.getObject(Approvals.class, id);
	}

	
}
