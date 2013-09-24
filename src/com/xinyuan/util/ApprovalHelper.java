package com.xinyuan.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.xinyuan.dao.impl.HibernateDAO;
import com.xinyuan.model.UserApproval;

public class ApprovalHelper {
	
	private static String ORDER_DIVIDER = ",";
	private static String ORDER_MODEL_CONNECTOR = "_";
	
	// in BaseAction Apply() method
	public static void addPendingApprove(String forwardUserName, String orderNO, String modelType) {
		HibernateDAO hibernateDAO = new HibernateDAO();
		UserApproval pendingApproval = (UserApproval)hibernateDAO.getObject(UserApproval.class, forwardUserName);
		
		String pendingApprovals = pendingApproval.getPendingApprovals();
		
		// orderNO + mode type
		String orderIdentifier = orderNO + ORDER_MODEL_CONNECTOR + modelType;
		
		pendingApprovals = pendingApprovals == null || pendingApprovals.isEmpty() ? orderIdentifier : pendingApprovals + ORDER_DIVIDER + orderIdentifier;
		pendingApproval.setPendingApprovals(pendingApprovals);
		
		hibernateDAO.updateObject(pendingApproval);
	}
	
	// in BaseAction Apply() method
	public static void deletePendingApprove(String approveUserName, String orderNO, String modelType) {
		HibernateDAO hibernateDAO = new HibernateDAO();
		UserApproval pendingApproval = (UserApproval)hibernateDAO.getObject(UserApproval.class, approveUserName);
		
		String pendingApprovals = pendingApproval.getPendingApprovals() ;
		String[] pendingList = pendingApprovals.split(ORDER_DIVIDER);
		List<String> list = new ArrayList<String>(Arrays.asList(pendingList));
		
		// orderNO + mode type
		String orderTypeNO = orderNO + ORDER_MODEL_CONNECTOR + modelType;
		
		list.removeAll(Arrays.asList(orderTypeNO));
		String result = "";
		for (int i = 0; i < list.size(); i++) {
			String orderString = list.get(i);
			if (i != 0) result += ORDER_DIVIDER;
			result += orderString ;
		}
		pendingApproval.setPendingApprovals(result);
		
		hibernateDAO.updateObject(pendingApproval);
	}

}
