package com.xinyuan.action.command.HumanResource;

import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.xinyuan.Util.ApporvalsHelper;
import com.xinyuan.action.ActionBase;
import com.xinyuan.action.command.category.HumanResourceCommandDelete;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.ApprovalDAOIMP;
import com.xinyuan.dao.impl.UserDAOIMP;
import com.xinyuan.message.MessagesKeys;
import com.xinyuan.model.Approval.Approvals;
import com.xinyuan.model.HumanResource.Employee;
import com.xinyuan.model.User.User;

public class EmployeeCommandDelete extends HumanResourceCommandDelete {

	@Override
	protected void handlePersistence(SuperDAO dao, Object model,
			Set<String> modelkeys, Object persistence) throws Exception {
		
		super.handlePersistence(dao, model, modelkeys, persistence);
		
		// Check have approvals left , then Delete the user and Approvals
		String employeeNO = ((Employee)persistence).getEmployeeNO();
		
		UserDAOIMP userDAOIMP = new UserDAOIMP();
		ApprovalDAOIMP approvalDAOIMP = new ApprovalDAOIMP();
		User user = userDAOIMP.getUser(employeeNO);
		Approvals approvals = approvalDAOIMP.getApprovals(employeeNO);
		
		if (ApporvalsHelper.isUserHavePendingApprovals(approvals)) {
			((ActionBase)ActionContext.getContext().getActionInvocation().getAction()).getResponseMessage().descriptions = MessagesKeys.DES + MessagesKeys.Connector + MessagesKeys.HR.EMPLOYEE_HAVING_APPROVALS;
			throw new Exception();
		} else {
			userDAOIMP.deleteObject(user);
			approvalDAOIMP.deleteObject(approvals);
		}
		
	}

	
}
