package com.xinyuan.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.xinyuan.dao.impl.HibernateDAO;
import com.xinyuan.model.Setting.Approvals;

public class ApprovalHelper {
	
	private static String ORDER_DIVIDER = ",";
	private static String ORDER_MODEL_CONNECTOR = ".";
	
	// in BaseAction Apply() method
	public static void addPendingApprove(String forwardUserName, String orderNO, String modelType) {
		HibernateDAO hibernateDAO = new HibernateDAO();
		Approvals pendingApproval = (Approvals)hibernateDAO.getObject(Approvals.class, forwardUserName);
		
		String pendingApprovals = pendingApproval.getPendingApprovals();
		
		
		
		// orderNO + mode type
		String orderIdentifier = modelType + ORDER_MODEL_CONNECTOR + orderNO;
		
		pendingApprovals = pendingApprovals == null || pendingApprovals.isEmpty() ? orderIdentifier : pendingApprovals + ORDER_DIVIDER + orderIdentifier;
		pendingApproval.setPendingApprovals(pendingApprovals);
		
		hibernateDAO.updateObject(pendingApproval);
	}
	
	// in BaseAction Apply() method
	public static void deletePendingApprove(String approveUserName, String orderNO, String modelType) {
		HibernateDAO hibernateDAO = new HibernateDAO();
		Approvals pendingApproval = (Approvals)hibernateDAO.getObject(Approvals.class, approveUserName);
		
		String pendingApprovals = pendingApproval.getPendingApprovals() ;
		
		if (pendingApprovals == null) return;
		
		// list
		String[] pendingList = pendingApprovals.split(ORDER_DIVIDER);
		List<String> list = new ArrayList<String>(Arrays.asList(pendingList));
		
		// orderNO + mode type
		String orderTypeNO = modelType + ORDER_MODEL_CONNECTOR + orderNO;
		
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
		boolean isLagerThanOne = apnsToken.split(ORDER_DIVIDER).length > 1 ;
		
		pendingApproval.setApnsToken(isLagerThanOne ? apnsTokens.replaceAll(ORDER_DIVIDER + apnsToken, "") : apnsToken.replaceAll(apnsToken, ""));
		hibernateDAO.updateObject(pendingApproval);
	}

}
