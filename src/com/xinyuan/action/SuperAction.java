package com.xinyuan.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.modules.introspector.ModelIntrospector;
import com.opensymphony.xwork2.Action;
import com.xinyuan.dao.BaseDAO;
import com.xinyuan.dao.ModelDAO;
import com.xinyuan.dao.impl.BaseDAOIMP;
import com.xinyuan.message.ConstantsConfig;
import com.xinyuan.model.BaseOrderModel;
import com.xinyuan.model.User;
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
		
		List<List<String>> options = JsonHelper.getListFromJson(allJsonObject, ConstantsConfig.FIELDS, false);
		List<Map<String, String>> criterials = JsonHelper.getListFromJson(allJsonObject, ConstantsConfig.CRITERIAS, true);
		
		for (int i = 0; i < models.size(); i++) {
			
			Object model = models.get(i);
			
			JsonElement object = objects.get(i);
			
			Map<String, Object> map = JsonHelper.translateElementToMap(object);
			
			Set<String> keys = map.keySet();
			
			List<String> fields = options == null ? null : options.get(i);
			
			Map<String, String> criteria = criterials == null ? null : criterials.get(i);
			
			List<Object> result = dao.read(model, keys, fields, criteria);
			
			results.add(result);
		}
		
		
		message.object = results;
		message.status = ConstantsConfig.STATUS_SUCCESS;
		
		return Action.NONE;
	}
	
	
	
	public String create() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid create multi-
		
		List<Map<String,String>> results = new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < models.size(); i++) {
			
			BaseOrderModel model = (BaseOrderModel)models.get(i);
			
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
			
			BaseOrderModel model = (BaseOrderModel)models.get(i);
			
			JsonElement object = objects.get(i);
			
			Map<String, Object> map = JsonHelper.translateElementToMap(object);
			
			Set<String> keys = map.keySet();
			
			String identityJSON = JsonHelper.getGson().toJson(identityList.get(i));
			
			BaseOrderModel persistence = JsonHelper.getGson().fromJson(identityJSON, model.getClass());
			
			persistence = ((ModelDAO)dao).read(persistence);		// get all values of this po
			
			ModelIntrospector.copyVoToPo(model, persistence, keys);
			
			dao.modify(persistence);
		}
		
		message.status = ConstantsConfig.STATUS_SUCCESS;
		return Action.NONE;
	}
	
	
	
	public String delete() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid delete multi-
		for (int i = 0; i < models.size(); i++) {
					
			BaseOrderModel model = (BaseOrderModel)models.get(i);
				
			BaseOrderModel persistence = ((ModelDAO)dao).read(model);		// get all values
				
			dao.delete(persistence);
			
			// check if delete successfully
			if (((ModelDAO)dao).read(model) == null) {
				message.status = ConstantsConfig.STATUS_SUCCESS;
			}
		}
		
		
		return Action.NONE;
	}
	
	

	public String apply() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid apply multi-
		
		String approveUsername = ((User)UserAction.sessionGet(ConstantsConfig.SIGNIN_USER)).getUsername();
		
		JsonArray forwardsList = allJsonObject.getAsJsonArray(ConstantsConfig.APNS_FORWARDS);
		JsonArray forwardContents = allJsonObject.getAsJsonArray(ConstantsConfig.APNS_CONTENTS);
		
		for (int i = 0; i < models.size(); i++) {
			
			BaseOrderModel model = (BaseOrderModel)models.get(i);
			
			BaseOrderModel persistence = ((ModelDAO)dao).read(model);		// get all values
			
//			if (!model.getForwardUser().equals(approveUsername)) DLog.log("not same , ask for leave???"); // TODO:
			boolean isAllApproved = ModelHelper.approve(persistence, approveUsername);  // TODO: Handle Exception
			
			String forwardUsername = forwardsList.get(i).getAsString();
			persistence.setForwardUser(forwardUsername);
			dao.modify(persistence);
			
			String orderNO = persistence.getOrderNO();
			String modelType = ModelHelper.getModelType(persistence);
			
			ApprovalHelper.addPendingApprove(forwardUsername, orderNO, modelType);
			ApprovalHelper.deletePendingApprove(approveUsername, orderNO, modelType);
			
			// specified to notify who
			String[] apnsTokens = ApprovalHelper.getAPNSToken(forwardUsername).split(",");
			Map<String, Object> apnsMap = JsonHelper.translateElementToMap(forwardContents.get(i));
			ApnsHelper.push(apnsMap, apnsTokens);
			
		}
		
		message.status = ConstantsConfig.STATUS_SUCCESS;
		return Action.NONE;
	}

	
}
