package com.xinyuan.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.xinyuan.dao.impl.HibernateDAO;
import com.xinyuan.model.Setting.Approvals;

public class ApprovalHelper {
	
	private static String ORDERS_DIVIDER = ",";
	private static String ORDER_TYPE_NO_CONNECTOR = ".";    // Employee.YG001,Employee.YG002
	
	public static void addPendingApprove(String forwardUserName, String orderNO, String orderType) {
		HibernateDAO hibernateDAO = new HibernateDAO();
		Approvals pendingApproval = (Approvals)hibernateDAO.getObject(Approvals.class, forwardUserName);
		
		String pendingApprovals = pendingApproval.getPendingApprovals();
		
		
		// orderIdentifier = order type + orderNO 
		String orderIdentifier = orderType + ORDER_TYPE_NO_CONNECTOR + orderNO;
		
		
		
		// do add 
		pendingApprovals = pendingApprovals == null || pendingApprovals.isEmpty() ? orderIdentifier : pendingApprovals + ORDERS_DIVIDER + orderIdentifier;
		pendingApproval.setPendingApprovals(pendingApprovals);
		
		hibernateDAO.updateObject(pendingApproval);
	}
	
	public static void deletePendingApprove(String approveUserName, String orderNO, String orderType) {
		HibernateDAO hibernateDAO = new HibernateDAO();
		Approvals pendingApproval = (Approvals)hibernateDAO.getObject(Approvals.class, approveUserName);
		
		String pendingApprovals = pendingApproval.getPendingApprovals() ;
		
		
		
		// orderIdentifier = order type + orderNO 
		String orderIdentifier = orderType + ORDER_TYPE_NO_CONNECTOR + orderNO;
		
		
		
		// do delete
		// list
		String[] pendingList = pendingApprovals.split(ORDERS_DIVIDER);
		List<String> list = new ArrayList<String>(Arrays.asList(pendingList));
		
		list.removeAll(Arrays.asList(orderIdentifier));
		String result = "";
		for (int i = 0; i < list.size(); i++) {
			String orderString = list.get(i);
			if (i != 0) result += ORDERS_DIVIDER;
			result += orderString ;
		}
		pendingApproval.setPendingApprovals(result);
		
		hibernateDAO.updateObject(pendingApproval);
	}
	
	
	
	public static String getAPNSToken(String username) {
		HibernateDAO hibernateDAO = new HibernateDAO();
		Approvals pendingApproval = (Approvals)hibernateDAO.getObject(Approvals.class, username);
		
		return pendingApproval.getApnsToken();
	}
	
	public static void setAPNSToken(String username, String apnsToken) {
		HibernateDAO hibernateDAO = new HibernateDAO();
		Approvals pendingApproval = (Approvals)hibernateDAO.getObject(Approvals.class, username);
		
		pendingApproval.setApnsToken(apnsToken);
		hibernateDAO.updateObject(pendingApproval);
	}
	
	public static void deleteAPNSToken(String username, String apnsToken) {
		HibernateDAO hibernateDAO = new HibernateDAO();
		Approvals pendingApproval = (Approvals)hibernateDAO.getObject(Approvals.class, username);
		
		String apnsTokens = ApprovalHelper.getAPNSToken(username);
		boolean isLagerThanOne = apnsToken.split(ORDERS_DIVIDER).length > 1 ;
		
		pendingApproval.setApnsToken(isLagerThanOne ? apnsTokens.replaceAll(ORDERS_DIVIDER + apnsToken, "") : apnsToken.replaceAll(apnsToken, ""));
		hibernateDAO.updateObject(pendingApproval);
	}

}
