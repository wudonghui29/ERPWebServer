package com.xinyuan.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Global.SessionManager;
import com.modules.Introspector.IntrospectHelper;
import com.modules.Util.CollectionHelper;
import com.xinyuan.dao.impl.HibernateDAO;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.RequestMessage;
import com.xinyuan.model.BaseOrder;
import com.xinyuan.model.Approval.Approvals;
import com.xinyuan.model.User.User;

public class ApprovalHelper {
	
	
	public static void handlePendingApprovals(RequestMessage requestMessage , int index, BaseOrder persistence ) {
		String signinedUser = ((User)SessionManager.get(ConfigConstants.SIGNIN_USER)).getUsername();
		
		List<String> forwardsList = requestMessage.getAPNS_FORWARDS();
		if (forwardsList == null || forwardsList.size() < index) return;
		
		String forwardUsername = forwardsList.get(index);
		if (forwardUsername == null) return;
		
		ApprovalHelper.addPendingApprove(forwardUsername, persistence);
		ApprovalHelper.deletePendingApprove(signinedUser, persistence);
	}
	
	
	public static void addPendingApprove(String userName, BaseOrder order) {
		if (userName == null || userName.isEmpty()) return;
		
		String department = IntrospectHelper.getParentPackageName(order);
		String orderType = IntrospectHelper.getShortClassName(order);
		String orderNO = order.getOrderNO();
		
		
		HibernateDAO hibernateDAO = new HibernateDAO();
		Approvals pendingApproval = (Approvals)hibernateDAO.getObject(Approvals.class, userName);
		
		String oldPendingApprovalsJSON = pendingApproval.getPendingApprovals();
		Map<String, Map<String, List<String>>> pendingApprovalsMap = JsonHelper.getGson().fromJson(oldPendingApprovalsJSON, Map.class);
		
		
		Map<String, List<String>> departmentMap = pendingApprovalsMap.get(department);
		if (departmentMap == null) {
			departmentMap = new HashMap<String, List<String>>();
			pendingApprovalsMap.put(department, departmentMap);
		}
		List<String> orderList = departmentMap.get(orderType);
		if (orderList == null) {
			orderList = new ArrayList<String>();
			departmentMap.put(orderType, orderList);
		}
		
		
		// --------- do add
		if(!CollectionHelper.isContains(orderList, orderNO)) orderList.add(orderNO);
		
		
		String newPendingApprovalsJSON = JsonHelper.getGson().toJson(pendingApprovalsMap);
		pendingApproval.setPendingApprovals(newPendingApprovalsJSON);
		hibernateDAO.updateObject(pendingApproval);
	}
	
	public static void deletePendingApprove(String userName, BaseOrder order) {
		if (userName == null || userName.isEmpty()) return;
		
		String department = IntrospectHelper.getParentPackageName(order);
		String orderType = IntrospectHelper.getShortClassName(order);
		String orderNO = order.getOrderNO();
		
		
		HibernateDAO hibernateDAO = new HibernateDAO();
		Approvals pendingApproval = (Approvals)hibernateDAO.getObject(Approvals.class, userName);
		
		String oldPendingApprovalsJSON = pendingApproval.getPendingApprovals();
		Map<String, Map<String, List<String>>> pendingApprovalsMap = JsonHelper.getGson().fromJson(oldPendingApprovalsJSON, Map.class);
		
		
		Map<String, List<String>> departmentMap = pendingApprovalsMap.get(department);
		if (departmentMap == null) {
			return;
		}
		List<String> orderList = departmentMap.get(orderType);
		if (orderList == null) {
			return;
		}
		
		
		// --------- do delete
		CollectionHelper.removeElement(orderList, orderNO);
		
		
		
		String newPendingApprovalsJSON = JsonHelper.getGson().toJson(pendingApprovalsMap);
		pendingApproval.setPendingApprovals(newPendingApprovalsJSON);
		hibernateDAO.updateObject(pendingApproval);
	}
	
}
