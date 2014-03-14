package com.xinyuan.action.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xinyuan.Query.QueryLimitsHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.RequestMessage;
import com.xinyuan.message.ResponseMessage;

public class CommandRead implements Command {

	@Override
	public void execute(SuperDAO dao,ResponseMessage responseMessage, RequestMessage requestMessage, List<Object> models, List<Set<String>> modelsKeys ) throws Exception {
		List<List<String>> outterSorts = requestMessage.getSORTS();
		List<List<String>> outterFields = requestMessage.getFIELDS();
		List<List<String>> outterLimits = requestMessage.getLIMITS();
		List<Map<String, String>> outterJoins = requestMessage.getJOINS();
		List<Map<String, Map<String, String>>> outterCriterials = requestMessage.getCRITERIAS();
		
		List<Object> results = new ArrayList<Object>();
		
		/*
		// get the need joins models
		int endJoinIndex = 0;
		for (int i = outterJoins.size() - 1; i >= 0 ; i--) {
			Map<String, String> joinMap = outterJoins.get(i);
			if (joinMap.size() != 0) {
				if (endJoinIndex == 0) endJoinIndex = i;
			} else  if (joinMap.size() == 0) {
				if (endJoinIndex != 0) {
					// have one range
					List<Object> joinModels = new ArrayList<Object>();
					List<Set<String>> joinModelKeys = new ArrayList<Set<String>>();
					List<List<String>> joinOutterSorts = new ArrayList<List<String>>();
					List<List<String>> joinOutterLimits = new ArrayList<List<String>>();
					List<List<String>> joinOutterFields = new ArrayList<List<String>>();
					List<Map<String, String>> joinOutterJoins = new ArrayList<Map<String,String>>();
					List<Map<String, Map<String, String>>> joinOutterCriterials = new ArrayList<Map<String,Map<String,String>>>();
					for (int j = endJoinIndex; j >= i ; j--) {
						Object model = models.remove(j);
						Set<String> keys = modelsKeys.remove(j);
						List<String> sorts = outterSorts == null ? null : outterSorts.remove(j);
						List<String> limits= outterLimits == null ? null : outterLimits.remove(j);
						List<String> fields = outterFields == null ? null : outterFields.remove(j);
						Map<String, String> joins = outterJoins == null ? null : outterJoins.remove(j);
						Map<String, Map<String,String>> criterias = outterCriterials == null ? null : outterCriterials.remove(j);
						
						joinModels.add(model);
						joinModelKeys.add(keys);
						if (sorts != null) joinOutterSorts.add(sorts);
						if (limits != null) joinOutterLimits.add(limits);
						if (fields != null) joinOutterFields.add(fields);
						if (joins != null) joinOutterJoins.add(joins);
						if (criterias != null) joinOutterCriterials.add(criterias);
					}
					
					List<Object> joinedResults = dao.readJoined(joinModels, joinModelKeys, joinOutterFields, joinOutterCriterials, joinOutterJoins, joinOutterSorts, joinOutterLimits);
					results.add(joinedResults);
					
					if (QueryLimitsHelper.isJoinedNeedLimits(joinOutterLimits)) {
						if (responseMessage.numbers == null) responseMessage.numbers = new ArrayList<String>();
						responseMessage.numbers.add(dao.getJoinedTotalRows());
					}
					
					// reset it 
					endJoinIndex = 0;
				}
			}
		}
		*/
		
		// Have joins
		if (outterJoins != null && outterJoins.size() != 0) {
			
			List<Object> joinedResults = dao.readJoined(models, modelsKeys, outterFields, outterCriterials, outterJoins, outterSorts, outterLimits);
			results.add(joinedResults);
			
			if (QueryLimitsHelper.isJoinedNeedLimits(outterLimits)) {
				if (responseMessage.numbers == null) responseMessage.numbers = new ArrayList<String>();
				responseMessage.numbers.add(dao.getJoinedTotalRows());
			}
		
		// No joins
		} else {
		
			for (int i = 0; i < models.size(); i++) {
				
				Object model = models.get(i);
				Set<String> keys = modelsKeys.get(i);
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
		
		responseMessage.results = results;
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
	}

	

}
