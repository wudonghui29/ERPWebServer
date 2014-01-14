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
import com.xinyuan.Query.QueryLimitsHelper;
import com.xinyuan.Util.ApnsHelper;
import com.xinyuan.Util.ApprovalHelper;
import com.xinyuan.Util.JsonHelper;
import com.xinyuan.Util.OrderNOGenerator;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ConfigJSON;
import com.xinyuan.model.BaseModel;
import com.xinyuan.model.BaseOrder;
import com.xinyuan.model.IApp1;
import com.xinyuan.model.User.User;

public class SuperAction extends ActionBase {
	
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() { return Action.NONE; }
	
	@Override
	protected SuperDAO getDao() { return new SuperDAOIMP(); }
	
	public String read() throws Exception {
		List<List<String>> outterSorts = requestMessage.getSORTS();
		List<List<String>> outterFields = requestMessage.getFIELDS();
		List<List<String>> outterLimits = requestMessage.getLIMITS();
		List<Map<String, String>> outterJoins = requestMessage.getJOINS();
		List<Map<String, Map<String, String>>> outterCriterials = requestMessage.getCRITERIAS();
		
		List<Object> results = new ArrayList<Object>();
		if (outterJoins != null && outterJoins.size() != 0) {
			
			List<Object> joinedResults = dao.readJoined(models, objectKeys, outterFields, outterCriterials, outterJoins, outterSorts, outterLimits);
			results.add(joinedResults);
			
			if (QueryLimitsHelper.isJoinedNeedLimits(outterLimits)) {
				if (responseMessage.numbers == null) responseMessage.numbers = new ArrayList<String>();
				responseMessage.numbers.add(dao.getJoinedTotalRows());
			}
			
		} else {
		
			for (int i = 0; i < models.size(); i++) {
				
				Object model = models.get(i);
				Set<String> keys = objectKeys.get(i);
				List<String> sorts = outterSorts == null ? null : outterSorts.get(i);
				List<String> limits= outterLimits == null ? null : outterLimits.get(i);
				List<String> fields = outterFields == null ? null : outterFields.get(i);
				Map<String, Map<String,String>> criterias = outterCriterials == null ? null : outterCriterials.get(i);
				
				results.add(dao.read(model, keys, fields, criterias, sorts, limits));
				
				if (QueryLimitsHelper.isNeedLimit(limits)) {
					if (responseMessage.numbers == null) responseMessage.numbers = new ArrayList<String>();
					responseMessage.numbers.add(dao.getTotalRows(model, keys, fields, criterias));
				}
			}
			
		}
		
		responseMessage.objects = results;
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		
		return Action.NONE;
	}
	
	
	
	public String create() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid create multi-
		
		List<Map<String,String>> results = new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < models.size(); i++) {
			Object persistence = models.get(i);
			if (persistence instanceof BaseModel) {
				OrderNOGenerator.setOrderBasicCreateDetail((BaseModel)persistence);
			}
			
			Integer identifier = (Integer) dao.create(persistence);
			Map result = new HashMap();
			result.put(ConfigJSON.IDENTIFIER, identifier);
			
			if (persistence instanceof BaseOrder) {
				result.put(ConfigJSON.ORDERNO, ((BaseOrder)persistence).getOrderNO());
				if (persistence instanceof IApp1) {
					ApprovalHelper.addPendingApprove(((IApp1)persistence).getForwardUser(), (BaseOrder)persistence);
				}
			}
			results.add(result);
		}
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE ;
		responseMessage.objects = results;
		
		return Action.NONE;
	}
	
	
	
	public String modify() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid modified multi-
		
		List<Map<String, String>> identityList = requestMessage.getIDENTITYS();	
		
		for (int i = 0; i < models.size(); i++) {
			Object model = models.get(i);
			
			Object persistence = getPersistenceByUniqueKeyValue(identityList.get(i), model.getClass());
			
			Set<String> keys = objectKeys.get(i);
			CollectionHelper.removeStartWithElement(keys, ConfigConstants.APP_PREFIX);
			ModelIntrospector.copyVoToPo(model, persistence, keys);
			
			dao.modify(persistence);
			
			if (persistence instanceof BaseOrder) {
				ApprovalHelper.handlePendingApprovals(requestMessage, i, (BaseOrder)persistence);
			}
		}
		// push APNS notifications, notify somebody to know that some order has been modified
		ApnsHelper.sendAPNS(requestMessage, responseMessage);
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		return Action.NONE;
	}
	

	public String apply() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid apply multi-
		
		String signinedUser = ((User)SessionManager.get(ConfigConstants.SIGNIN_USER)).getUsername();
		
		List<String> forwardsList = requestMessage.getAPNS_FORWARDS();
		List<Map<String, String>> identityList = requestMessage.getIDENTITYS();	
		
		for (int i = 0; i < models.size(); i++) {
			
			Object model = models.get(i);
			
			Object persistence = getPersistenceByUniqueKeyValue(identityList.get(i), model.getClass());
			
			if (persistence instanceof IApp1) {
				
				((IApp1)persistence).setForwardUser(forwardsList.get(i));
				
				String appKey = JsonHelper.getParameter(requestMessage, ConfigJSON.APPLEVEL);
				if (appKey.startsWith(ConfigConstants.APP_PREFIX) && ModelIntrospector.getProperty(persistence, appKey) == null) {
					
					ModelIntrospector.setProperty(persistence, appKey, signinedUser);
					
					// update the Order Table
					dao.modify(persistence);
				}
			}
			
			if (persistence instanceof BaseOrder) {
				ApprovalHelper.handlePendingApprovals(requestMessage, i, (BaseOrder)persistence);
			}
		}
		// push APNS notifications
		ApnsHelper.sendAPNS(requestMessage, responseMessage);
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		return Action.NONE;
	}
	
	
	
	public String delete() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid delete multi-
		
		List<Map<String, String>> identityList = requestMessage.getIDENTITYS();	
		
		for (int i = 0; i < models.size(); i++) {
					
			Object model = models.get(i);
			
			Class<?> clazz = model.getClass();
			
			Object persistence = getPersistenceByUniqueKeyValue(identityList.get(i), clazz);
			
			dao.delete(persistence);
			
			// check if delete successfully
			if (getPersistenceByUniqueKeyValue(identityList.get(i), clazz) != null) throw new Exception();
			
			if (persistence instanceof IApp1 && persistence instanceof BaseOrder) {
				ApprovalHelper.addPendingApprove(((IApp1)persistence).getForwardUser(), (BaseOrder)persistence);
			}
		}
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		
		return Action.NONE;
	}
	
	
	
	private <E extends Object> E getPersistenceByUniqueKeyValue(Map<String, String> keyValues, Class<E> clazz) throws Exception {
		String identityJSON = JsonHelper.getGson().toJson(keyValues);
		E identityVo = JsonHelper.getGson().fromJson(identityJSON, clazz);
		E persistence = dao.readUnique(identityVo, keyValues.keySet());
		return persistence;
	}
	
}
