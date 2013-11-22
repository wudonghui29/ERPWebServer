package com.xinyuan.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.Global.SessionManager;
import com.google.gson.JsonArray;
import com.modules.Introspector.IntrospectHelper;
import com.modules.Introspector.ModelIntrospector;
import com.opensymphony.xwork2.Action;
import com.xinyuan.Config.ConfigConstants;
import com.xinyuan.Config.ConfigJSON;
import com.xinyuan.Query.QueryLimitsHelper;
import com.xinyuan.Util.ApnsHelper;
import com.xinyuan.Util.ApprovalHelper;
import com.xinyuan.Util.JsonHelper;
import com.xinyuan.Util.OrderNOGenerator;
import com.xinyuan.dao.ModelDAO;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.model.BaseModel;
import com.xinyuan.model.OrderModel;
import com.xinyuan.model.User.User;

public class SuperAction extends ActionModelBase {
	
	@Override
	public String execute() {
		return Action.NONE;
	}
	
	@Override
	protected SuperDAO getDao() {
		return new SuperDAOIMP();
	}
	
	
	public String read() throws Exception {
		List<List<String>> outterSorts = requestMessage.getSORTS();
		List<List<String>> outterFields = requestMessage.getFIELDS();
		List<List<String>> outterLimits = requestMessage.getLIMITS();
		List<Map<String, String>> outterJoins = requestMessage.getJOINS();
		List<Map<String, Map>> outterCriterials = requestMessage.getCRITERIAS();
		
		List<Object> results = new ArrayList<Object>();
		if (outterJoins != null && outterJoins.size() != 0) {
			
			results = dao.readJoined(models, objectKeys, outterFields, outterCriterials, outterJoins, outterSorts, outterLimits);
			
			if (QueryLimitsHelper.isJoinedNeedLimits(outterLimits)) {
				if (message.numbers == null) message.numbers = new ArrayList<String>();
				message.numbers.add(dao.getJoinedTotalRows());
			}
			
		} else {
		
			for (int i = 0; i < models.size(); i++) {
				
				Object model = models.get(i);
				Set<String> keys = objectKeys.get(i);
				List<String> sorts = outterSorts == null ? null : outterSorts.get(i);
				List<String> limits= outterLimits == null ? null : outterLimits.get(i);
				List<String> fields = outterFields == null ? null : outterFields.get(i);
				Map<String, Map> criterias = outterCriterials == null ? null : outterCriterials.get(i);
				
				results.add(dao.read(model, keys, fields, criterias, sorts, limits));
				
				if (QueryLimitsHelper.isNeedLimit(limits)) {
					if (message.numbers == null) message.numbers = new ArrayList<String>();
					message.numbers.add(dao.getTotalRows(model, keys, fields, criterias));
				}
			}
			
		}
		
		message.objects = results;
		message.status = ConfigConstants.STATUS_SUCCESS;
		
		return Action.NONE;
	}
	
	
	
	public String create() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid create multi-
		
		List<Map<String,String>> results = new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < models.size(); i++) {
			
			BaseModel model = (BaseModel)models.get(i);
			
			OrderNOGenerator.setOrderBasicCreateDetail(model);
			
			Integer identifier = (Integer) dao.create(model);
			
			Map result = new HashMap();
			
			result.put(ConfigJSON.IDENTIFIER, identifier);
			
			result.put(ConfigJSON.ORDERNO, model.getOrderNO());
			
			results.add(result);
		}
		
		message.status = ConfigConstants.STATUS_SUCCESS ;
		message.objects = results;
		
		return Action.NONE;
	}
	
	
	
	public String modify() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid modified multi-
		
		Map<String, Object> allJsonMap = JsonHelper.translateElementToMap(allJsonObject);
		List identityList = (List)allJsonMap.get(ConfigJSON.IDENTITYS);		// key - value
		
		for (int i = 0; i < models.size(); i++) {
			
			BaseModel model = (BaseModel)models.get(i);
			
			Set<String> keys = objectKeys.get(i);
			
			String identityJSON = JsonHelper.getGson().toJson(identityList.get(i));
			BaseModel persistenceWithID = JsonHelper.getGson().fromJson(identityJSON, model.getClass());		// po
			BaseModel persistence = ((ModelDAO)dao).readUnique(persistenceWithID);		// get all values of this po
			
			ModelIntrospector.copyVoToPo(model, persistence, keys);
			
			dao.modify(persistence);
		}
		// push APNS notifications
		ApnsHelper.sendAPNS(allJsonObject, message);
		
		message.status = ConfigConstants.STATUS_SUCCESS;
		return Action.NONE;
	}
	
	
	
	public String delete() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid delete multi-
		
		Map<String, Object> allJsonMap = JsonHelper.translateElementToMap(allJsonObject);
		List identityList = (List)allJsonMap.get(ConfigJSON.IDENTITYS);			// key -value
		
		for (int i = 0; i < models.size(); i++) {
					
			BaseModel model = (BaseModel)models.get(i);
				
			String identityJSON = JsonHelper.getGson().toJson(identityList.get(i));
			BaseModel persistenceWithID = JsonHelper.getGson().fromJson(identityJSON, model.getClass());		// po
			BaseModel persistence = ((ModelDAO)dao).readUnique(persistenceWithID);		// get all values of this po
			
			dao.delete(persistence);
			
			// check if delete successfully
			if (((ModelDAO)dao).readUnique(persistenceWithID) == null) {
				message.status = ConfigConstants.STATUS_SUCCESS;
			}
		}
		
		return Action.NONE;
	}
	
	

	public String apply() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid apply multi-
		
		String signinedUser = ((User)SessionManager.get(ConfigConstants.SIGNIN_USER)).getUsername();
		
		JsonArray forwardsList = allJsonObject.getAsJsonArray(ConfigJSON.APNS_FORWARDS);
		
		for (int i = 0; i < models.size(); i++) {
			
			OrderModel model = (OrderModel)models.get(i);
			
			OrderModel persistence = ((ModelDAO)dao).readUnique(model);		// get all values
			
//			boolean isAllApproved = ModelHelper.approve(persistence, signinedUser);  // TODO: Handle Exception
			
			// update the Order Table
			String forwardUsername = forwardsList.get(i).getAsString();
			persistence.setForwardUser(forwardUsername);
			dao.modify(persistence);
			
			// update the Approval Table
			String orderNO = persistence.getOrderNO();
			String orderType = IntrospectHelper.getShortClassName(persistence);
			ApprovalHelper.addPendingApprove(forwardUsername, orderNO, orderType);
			ApprovalHelper.deletePendingApprove(signinedUser, orderNO, orderType);
			
		}
		
		// push APNS notifications
		ApnsHelper.sendAPNS(allJsonObject, message);
		
		message.status = ConfigConstants.STATUS_SUCCESS;
		return Action.NONE;
	}
	
	
}
