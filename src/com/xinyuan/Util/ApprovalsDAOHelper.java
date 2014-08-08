package com.xinyuan.Util;

import j2se.modules.Helper.CollectionHelper;
import j2se.modules.Introspector.IntrospectHelper;
import j2se.modules.Introspector.ObjectIntrospector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Global.SessionManager;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.MessagesException;
import com.xinyuan.message.MessagesKeys;
import com.xinyuan.model.BaseOrder;
import com.xinyuan.model.IApp;
import com.xinyuan.model.Approval.Approvals;
import com.xinyuan.model.User.User;


interface Callable {
	public void invoke(String username, BaseOrder order);
}

public class ApprovalsDAOHelper {
	
	// add and delete pending approvals
	public static void handlePendingApprovals(SuperDAO dao, String appKey, String forwardUsername, BaseOrder persistence) throws Exception {
		if (forwardUsername == null) return;
		String signinedUser = ((User)SessionManager.get(ConfigConstants.SIGNIN_USER)).getUsername();
		
		ApprovalsDAOHelper.deletePendingApprove(signinedUser, persistence);
		
		if (! AppModelsHelper.isFinalApproval(appKey, persistence)) {
		    ApprovalsDAOHelper.addPendingApprove(forwardUsername, persistence);
		}
		
		
		// modify persistence, set the sign in user to the app-level attribute 
        if (persistence instanceof IApp) {
            ((IApp) persistence).setForwardUser(forwardUsername);
            
            if (appKey != null) {
                if (appKey.startsWith(ConfigConstants.APPROVAL_PREFIX)) {
                    
                    // forbid cross approve
                    String preAppKey = AppModelsHelper.getPreviousApprovalKey(appKey);
                    String preAppUser = (String) ObjectIntrospector.getProperty(persistence, preAppKey);
                    
                    if (! (preAppUser == null || preAppUser.isEmpty()) ) {
                        
                        String appUser = (String) ObjectIntrospector.getProperty(persistence, appKey);
                        
                        if (appUser == null || appUser.isEmpty()) {
                            ObjectIntrospector.setProperty(persistence, appKey, signinedUser);
                        }
                        
                    } else {
                        
                        throw new MessagesException(MessagesKeys.KEYS_PRE + "cannot." + appKey + MessagesKeys.CONNECTOR + MessagesKeys.KEYS_PRE + "because.without." + preAppKey );
                        
                    }
                    
                }

            }
            dao.modify(persistence);
		}
		
	}
	
	public static void addPendingApprove(String userName, BaseOrder order) {
		String department = IntrospectHelper.getParentPackageName(order);
		String orderType = IntrospectHelper.getShortClassName(order);
		String orderNO = order.getOrderNO();
		
		addPendingApprove(userName, department, orderType, orderNO);
		
		addUnReadApprovalsForCreateUser(order);
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
		if(!orderList.contains(orderNO)) orderList.add(orderNO);
		
		
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
	private static void deletePendingApprove(String userName,  String department, String orderType, String orderNO) {
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
	
	
	
	
	private static void addUnReadApprovalsForCreateUser(BaseOrder order) {
		String department = IntrospectHelper.getParentPackageName(order);
		String orderType = IntrospectHelper.getShortClassName(order);
		String orderNO = order.getOrderNO();
		
		String createUser = order.getCreateUser();
		SuperDAOIMP superDAO = new SuperDAOIMP();
		Approvals pendingApproval = (Approvals)superDAO.getObject(Approvals.class, createUser);
		
		if (pendingApproval == null) {
			// just user , such as the administrator , not the employee
			return;
		}
		
		String unReadApprovalsJSON = pendingApproval.getUnReadApprovals();
		
		@SuppressWarnings("unchecked")
		Map<String, Map<String, List<String>>> oldUnReadApprovalsMap = GsonHelper.getGson().fromJson(unReadApprovalsJSON, Map.class);
		
		
		Map<String, List<String>> departmentMap = oldUnReadApprovalsMap.get(department);
		if (departmentMap == null) {
			departmentMap = new HashMap<String, List<String>>();
			oldUnReadApprovalsMap.put(department, departmentMap);
		}
		List<String> orderList = departmentMap.get(orderType);
		if (orderList == null) {
			orderList = new ArrayList<String>();
			departmentMap.put(orderType, orderList);
		}
		
		
		// --------- do add
		if(!orderList.contains(orderNO)) orderList.add(orderNO);
		
		
		String newUnReadApprovalsJSON = GsonHelper.getGson().toJson(oldUnReadApprovalsMap);
		pendingApproval.setUnReadApprovals(newUnReadApprovalsJSON);
		superDAO.updateObject(pendingApproval);
	}
	
	public static void deleteUnReadApprovalsForCreateUser(BaseOrder order) {
		String createUser = order.getCreateUser();
		String signinedUser = ((User)SessionManager.get(ConfigConstants.SIGNIN_USER)).getUsername();
		if (!createUser.equals(signinedUser)) return;
		
		String department = IntrospectHelper.getParentPackageName(order);
		String orderType = IntrospectHelper.getShortClassName(order);
		String orderNO = order.getOrderNO();
		
		
		deleteUnReadApprovalsForCreateUser(department, orderType, orderNO, createUser);
	}
	
	private static void deleteUnReadApprovalsForCreateUser(String department, String orderType, String orderNO, String createUser) {
		SuperDAOIMP superDAO = new SuperDAOIMP();
		Approvals pendingApproval = (Approvals)superDAO.getObject(Approvals.class, createUser);
		
		if (pendingApproval == null) {
			// just user , such as the administrator , not the employee
			return;
		}
		
		String unReadApprovalsJSON = pendingApproval.getUnReadApprovals();
		
		@SuppressWarnings("unchecked")
		Map<String, Map<String, List<String>>> oldUnReadApprovalsMap = GsonHelper.getGson().fromJson(unReadApprovalsJSON, Map.class);
		
		Map<String, List<String>> departmentMap = oldUnReadApprovalsMap.get(department);
		if (departmentMap == null) {
			return;
		}
		List<String> orderList = departmentMap.get(orderType);
		if (orderList == null) {
			return;
		}
		
		
		// --------- do delete
		CollectionHelper.removeElement(orderList, orderNO);
		
		String newUnReadApprovalsJSON = GsonHelper.getGson().toJson(oldUnReadApprovalsMap);
		pendingApproval.setUnReadApprovals(newUnReadApprovalsJSON);
		superDAO.updateObject(pendingApproval);
	}
}
