package com.xinyuan.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.xinyuan.dao.impl.HibernateDAO;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.model.Approval.Approvals;

public class ApprovalHelper {
	
	public static void addPendingApprove(String forwardUserName, String orderNO, String orderType) {
		HibernateDAO hibernateDAO = new HibernateDAO();
		Approvals pendingApproval = (Approvals)hibernateDAO.getObject(Approvals.class, forwardUserName);
		
		String pendingApprovals = pendingApproval.getPendingApprovals();
		
		
		// orderIdentifier = order type + orderNO 
		String orderIdentifier = orderType + ConfigConstants.CONTENT_CONNECTOR + orderNO;				// Employee.YG001,Employee.YG002
		
		
		
		// do add 
		pendingApprovals = pendingApprovals == null || pendingApprovals.isEmpty() ? orderIdentifier : pendingApprovals + ConfigConstants.CONTENT_DIVIDER + orderIdentifier;
		pendingApproval.setPendingApprovals(pendingApprovals);
		
		hibernateDAO.updateObject(pendingApproval);
	}
	
	public static void deletePendingApprove(String approveUserName, String orderNO, String orderType) {
		HibernateDAO hibernateDAO = new HibernateDAO();
		Approvals pendingApproval = (Approvals)hibernateDAO.getObject(Approvals.class, approveUserName);
		
		String pendingApprovals = pendingApproval.getPendingApprovals() ;
		
		
		
		// orderIdentifier = order type + orderNO 
		String orderIdentifier = orderType + ConfigConstants.CONTENT_CONNECTOR + orderNO;
		
		
		
		// do delete
		// list
		String[] pendingList = pendingApprovals.split(ConfigConstants.CONTENT_DIVIDER);
		List<String> list = new ArrayList<String>(Arrays.asList(pendingList));
		
		list.removeAll(Arrays.asList(orderIdentifier));
		String result = "";
		for (int i = 0; i < list.size(); i++) {
			String orderString = list.get(i);
			if (i != 0) result += ConfigConstants.CONTENT_DIVIDER;
			result += orderString ;
		}
		pendingApproval.setPendingApprovals(result);
		
		hibernateDAO.updateObject(pendingApproval);
	}

}
