package com.xinyuan.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.Global.SessionManager;
import com.modules.Introspector.ModelIntrospector;
import com.modules.Util.CollectionHelper;
import com.opensymphony.xwork2.Action;
import com.xinyuan.Util.ApnsHelper;
import com.xinyuan.Util.ApprovalHelper;
import com.xinyuan.Util.JsonHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.WarehouseDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ConfigJSON;
import com.xinyuan.model.App1;
import com.xinyuan.model.BaseBill;
import com.xinyuan.model.BillApp1;
import com.xinyuan.model.User.User;
import com.xinyuan.model.Warehouse.WHLendOutBill;
import com.xinyuan.model.Warehouse.WHLendOutOrder;
import com.xinyuan.model.Warehouse.WHScrapOrder;

public class WarehouseAction extends SuperAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected SuperDAO getDao() {
		return new WarehouseDAOIMP();
	}
	
	public String createBill() throws Exception{
		
	if (models.size() != 1) return Action.NONE;		
		
		List<Map<String,String>> results = new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < models.size(); i++) {
			WHLendOutBill model = (WHLendOutBill)models.get(i);
			Integer identifier = (Integer) dao.create(model);
			
			Map result = new HashMap();
			result.put(ConfigJSON.IDENTIFIER, identifier);
			result.put(ConfigJSON.ORDERNO, model.getBillNO());
			results.add(result);
		}
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE ;
		responseMessage.objects = results;
		
		return Action.NONE;
	}
	
	public String applyBill() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid apply multi-
		
		String signinedUser = ((User)SessionManager.get(ConfigConstants.SIGNIN_USER)).getUsername();
		
		List<String> forwardsList = requestMessage.getAPNS_FORWARDS();
		List<Map<String, String>> identityList = requestMessage.getIDENTITYS();	
		
		for (int i = 0; i < models.size(); i++) {
			
			WHLendOutBill model = (WHLendOutBill)models.get(i);
			
			Class<WHLendOutBill> clazz = (Class<WHLendOutBill>)model.getClass();
			WHLendOutBill persistence = getPersistenceByIdentity(identityList.get(i), clazz);
			
			String forwardUsername = forwardsList.get(i);
			persistence.setForwardNO(forwardUsername);

			if (persistence instanceof WHLendOutBill) {
			
				Set<String> keys = objectKeys.get(i);
				CollectionHelper.removeNotStartWithElement(keys, ConfigConstants.APP_PREFIX);
				if (keys.size() != 1) continue;		// only one 
				String app = (String) keys.toArray()[0];
				String appValue = (String) ModelIntrospector.getProperty(model, app);
				if (!appValue.equals(signinedUser)){
					responseMessage.descriptions = "Not_The_Same_User";
					throw new Exception();
				}
				ModelIntrospector.setProperty(persistence, app, signinedUser);
			}
			// update the Order Table
			dao.modify(persistence);
			
			ApprovalHelper.handlePendingApprovals(requestMessage, i, (WHLendOutOrder)((WarehouseDAOIMP)dao).getObject(WHLendOutOrder.class, "orderNO", persistence.getBillNO()));
		}
		// push APNS notifications
		ApnsHelper.sendAPNS(requestMessage, responseMessage);
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		return Action.NONE;
	}
	
	private <E extends Object> E getPersistenceByIdentity(Map<String, String> keyValues, Class<E> clazz) throws Exception {
		String identityJSON = JsonHelper.getGson().toJson(keyValues);
		E identityVo = JsonHelper.getGson().fromJson(identityJSON, clazz);
		E persistence = dao.readUnique(identityVo, keyValues.keySet());
		return persistence;
	}
	
}
