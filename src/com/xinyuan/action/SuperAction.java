package com.xinyuan.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.global.SessionManager;
import com.google.gson.JsonArray;
import com.modules.introspector.IntrospectHelper;
import com.modules.introspector.ModelIntrospector;
import com.opensymphony.xwork2.Action;
import com.xinyuan.dao.BaseDAO;
import com.xinyuan.dao.ModelDAO;
import com.xinyuan.dao.impl.BaseDAOIMP;
import com.xinyuan.message.ConstantsConfig;
import com.xinyuan.model.OrderModel;
import com.xinyuan.model.User.User;
import com.xinyuan.util.ApnsHelper;
import com.xinyuan.util.ApprovalHelper;
import com.xinyuan.util.JsonHelper;
import com.xinyuan.util.ModelHelper;

public class SuperAction extends ActionModelBase {
	
	@Override
	public String execute() {
		return Action.NONE;
	}
	
	@Override
	protected BaseDAO getDao() {
		return new BaseDAOIMP();
	}

	
	
	
	public String read() throws Exception {
		
		List<List<Object>> results = new ArrayList<List<Object>>();
		
		List<List<String>> outterFields = JsonHelper.getListFromJson(allJsonObject, ConstantsConfig.FIELDS, false);
		List<List<String>> outterSorts = JsonHelper.getListFromJson(allJsonObject, ConstantsConfig.SORTS, false);
		List<Map<String, Map>> outterCriterials = JsonHelper.getListFromJson(allJsonObject, ConstantsConfig.CRITERIAS, true);
		
		
		List<Map<String, String>> outterJoins = JsonHelper.getListFromJson(allJsonObject, ConstantsConfig.JOINS, true);
		if (outterJoins != null && outterJoins.size() != 0) {
			
			List<Object> result = dao.readJoined(models, objectKeys, outterFields, outterCriterials, outterJoins, outterSorts);
			
			results.add(result);
			
		} else {
		
			for (int i = 0; i < models.size(); i++) {
				
				Object model = models.get(i);
				
				Set<String> keys = objectKeys.get(i);
				
				List<String> fields = outterFields == null ? null : outterFields.get(i);
				
				List<String> sorts = outterSorts == null ? null : outterSorts.get(i);
				
				Map<String, Map> criterias = outterCriterials == null ? null : outterCriterials.get(i);
				
				List<Object> result = dao.read(model, keys, fields, criterias, sorts);
				
				results.add(result);
			}
			
		}
		
		
		message.object = results;
		message.status = ConstantsConfig.STATUS_SUCCESS;
		
		return Action.NONE;
	}
	
	
	
	public String create() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid create multi-
		
		List<Map<String,String>> results = new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < models.size(); i++) {
			
			OrderModel model = (OrderModel)models.get(i);
			
			ModelHelper.setOrderBasicCreateDetail(model);
			Integer identifier = (Integer) dao.create(model);
			
			Map result = new HashMap();
			result.put(ConstantsConfig.IDENTIFIER, identifier);
			result.put(ConstantsConfig.ORDERNO, model.getOrderNO());
			
			results.add(result);
		}
		
		
		message.status = ConstantsConfig.STATUS_SUCCESS ;
		message.object = results;
		
		return Action.NONE;
	}
	
	
	
	public String modify() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid modified multi-
		
		Map<String, Object> allJsonMap = JsonHelper.translateElementToMap(allJsonObject);
		List identityList = (List)allJsonMap.get(ConstantsConfig.IDENTITYS);
		
		for (int i = 0; i < models.size(); i++) {
			
			OrderModel model = (OrderModel)models.get(i);
			
			Set<String> keys = objectKeys.get(i);
			
			String identityJSON = JsonHelper.getGson().toJson(identityList.get(i));
			OrderModel persistenceWithID = JsonHelper.getGson().fromJson(identityJSON, model.getClass());		// po
			OrderModel persistence = ((ModelDAO)dao).read(persistenceWithID);		// get all values of this po
			
			ModelIntrospector.copyVoToPo(model, persistence, keys);
			
			dao.modify(persistence);
		}
		
		message.status = ConstantsConfig.STATUS_SUCCESS;
		return Action.NONE;
	}
	
	
	
	public String delete() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid delete multi-
		
		Map<String, Object> allJsonMap = JsonHelper.translateElementToMap(allJsonObject);
		List identityList = (List)allJsonMap.get(ConstantsConfig.IDENTITYS);
		
		for (int i = 0; i < models.size(); i++) {
					
			OrderModel model = (OrderModel)models.get(i);
				
			String identityJSON = JsonHelper.getGson().toJson(identityList.get(i));
			OrderModel persistenceWithID = JsonHelper.getGson().fromJson(identityJSON, model.getClass());		// po
			OrderModel persistence = ((ModelDAO)dao).read(persistenceWithID);		// get all values of this po
			
			dao.delete(persistence);
			
			// check if delete successfully
			if (((ModelDAO)dao).read(persistenceWithID) == null) {
				message.status = ConstantsConfig.STATUS_SUCCESS;
			}
		}
		
		
		return Action.NONE;
	}
	
	

	public String apply() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid apply multi-
		
		String approveUsername = ((User)SessionManager.get(ConstantsConfig.SIGNIN_USER)).getUsername();
		
		JsonArray forwardsList = allJsonObject.getAsJsonArray(ConstantsConfig.APNS_FORWARDS);
		JsonArray forwardContents = allJsonObject.getAsJsonArray(ConstantsConfig.APNS_CONTENTS);
		
		for (int i = 0; i < models.size(); i++) {
			
			OrderModel model = (OrderModel)models.get(i);
			
			OrderModel persistence = ((ModelDAO)dao).read(model);		// get all values
			
//			if (!model.getForwardUser().equals(approveUsername)) DLog.log("not same , ask for leave???"); // TODO:
			boolean isAllApproved = ModelHelper.approve(persistence, approveUsername);  // TODO: Handle Exception
			
			String forwardUsername = forwardsList.get(i).getAsString();
			persistence.setForwardUser(forwardUsername);
			dao.modify(persistence);
			
			String orderNO = persistence.getOrderNO();
			String orderType = IntrospectHelper.getShortClassName(persistence);
			ApprovalHelper.addPendingApprove(forwardUsername, orderNO, orderType);
			ApprovalHelper.deletePendingApprove(approveUsername, orderNO, orderType);
			
			// specified to notify who
			String[] apnsTokens = ApprovalHelper.getAPNSToken(forwardUsername).split(",");
			Map<String, Object> apnsMap = JsonHelper.translateElementToMap(forwardContents.get(i));
			ApnsHelper.push(apnsMap, apnsTokens);
			
		}
		
		message.status = ConstantsConfig.STATUS_SUCCESS;
		return Action.NONE;
	}

	
}
