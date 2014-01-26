package com.xinyuan.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Global.SessionManager;
import com.modules.Introspector.IntrospectHelper;
import com.modules.Introspector.ModelIntrospector;
import com.modules.Util.CollectionHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.model.BaseOrder;
import com.xinyuan.model.IApp;
import com.xinyuan.model.Approval.Approvals;
import com.xinyuan.model.User.User;


interface Callable {
	public void invoke(String username, BaseOrder order);
}

public class ApprovalHelper {
	
	public static void handlePendingApprovals(SuperDAO dao, String appKey, String forwardUsername, BaseOrder persistence ) throws Exception {
		if (forwardUsername == null) return;
		ApprovalHelper.addPendingApprove(forwardUsername, persistence);
		String signinedUser = ((User)SessionManager.get(ConfigConstants.SIGNIN_USER)).getUsername();
		
		// app
		if (persistence instanceof IApp) {
			((IApp) persistence).setForwardUser(forwardUsername);
			if (appKey != null && appKey.startsWith(ConfigConstants.APPKEY_PREFIX) && ModelIntrospector.getProperty(persistence, appKey) == null) {
				ModelIntrospector.setProperty(persistence, appKey, signinedUser);
				dao.modify(persistence);
			}
		}
		
		ApprovalHelper.deletePendingApprove(signinedUser, persistence);
	}
	
	public static void addPendingApprove(String userName, BaseOrder order) {
		String department = IntrospectHelper.getParentPackageName(order);
		String orderType = IntrospectHelper.getShortClassName(order);
		String orderNO = order.getOrderNO();
		
		addPendingApprove(userName, department, orderType, orderNO);
	}
	
	public static void deletePendingApprove(String userName, BaseOrder order) {
		String department = IntrospectHelper.getParentPackageName(order);
		String orderType = IntrospectHelper.getShortClassName(order);
		String orderNO = order.getOrderNO();
		
		deletePendingApprove(userName, department, orderType, orderNO);
	}
	
	
	
	
	
	/**
	 * 
	 * @param userName
	 * @param department
	 * @param orderType
	 * @param orderNO
	 */
	public static void addPendingApprove(String userName, String department, String orderType, String orderNO) {
		if (userName == null || userName.isEmpty()) return;
		
		SuperDAOIMP superDAO = new SuperDAOIMP();
		Approvals pendingApproval = (Approvals)superDAO.getObject(Approvals.class, userName);
		
		if (pendingApproval == null) {
			// just user , such as the administrator , not the employee
			return;
		}
		
		String oldPendingApprovalsJSON = pendingApproval.getPendingApprovals();
		@SuppressWarnings("unchecked")
		Map<String, Map<String, List<String>>> pendingApprovalsMap = GsonHelper.getGson().fromJson(oldPendingApprovalsJSON, Map.class);
		
		
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
//		if(!CollectionHelper.isContains(orderList, orderNO)) orderList.add(orderNO);
		if(orderList.contains(orderNO)) orderList.add(orderNO);
		
		
		String newPendingApprovalsJSON = GsonHelper.getGson().toJson(pendingApprovalsMap);
		pendingApproval.setPendingApprovals(newPendingApprovalsJSON);
		superDAO.updateObject(pendingApproval);
	}
	
	/**
	 * 
	 * @param userName
	 * @param department
	 * @param orderType
	 * @param orderNO
	 */
	public static void deletePendingApprove(String userName,  String department, String orderType, String orderNO) {
		if (userName == null || userName.isEmpty()) return;
		
		SuperDAOIMP superDAO = new SuperDAOIMP();
		Approvals pendingApproval = (Approvals)superDAO.getObject(Approvals.class, userName);
		
		if (pendingApproval == null) {
			// just user , such as the administrator , not the employee
			return;
		}
		
		String oldPendingApprovalsJSON = pendingApproval.getPendingApprovals();
		@SuppressWarnings("unchecked")
		Map<String, Map<String, List<String>>> pendingApprovalsMap = GsonHelper.getGson().fromJson(oldPendingApprovalsJSON, Map.class);
		
		
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
		
		
		
		String newPendingApprovalsJSON = GsonHelper.getGson().toJson(pendingApprovalsMap);
		pendingApproval.setPendingApprovals(newPendingApprovalsJSON);
		superDAO.updateObject(pendingApproval);
	}
	
}
