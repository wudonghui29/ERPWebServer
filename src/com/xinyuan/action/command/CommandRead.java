package com.xinyuan.action.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xinyuan.Query.QueryCriteriasHelper;
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
		
		
		List<List<Object>> outterJoinModels = null;
		List<List<Set<String>>> outterJoinModelKeys = null;
		List<List<List<String>>> outterJoinOutterSorts = null;
		List<List<List<String>>> outterJoinOutterLimits = null;
		List<List<List<String>>> outterJoinOutterFields = null;
		List<List<Map<String, String>>> outterJoinOutterJoins = null;
		List<List<Map<String, Map<String, String>>>> outterJoinOutterCriterials = null;
		
		// Have Joins . get the need joins models  --- Begin --------------------------------
		if (outterJoins != null && outterJoins.size() != 0) {
			
			if (outterJoinModels == null) outterJoinModels = new ArrayList<List<Object>>();
			if (outterJoinModelKeys == null) outterJoinModelKeys = new ArrayList<List<Set<String>>>();
			if (outterJoinOutterSorts == null) outterJoinOutterSorts = new ArrayList<List<List<String>>>();
			if (outterJoinOutterLimits == null) outterJoinOutterLimits = new ArrayList<List<List<String>>>();
			if (outterJoinOutterFields == null) outterJoinOutterFields = new ArrayList<List<List<String>>>();
			if (outterJoinOutterJoins == null) outterJoinOutterJoins = new ArrayList<List<Map<String,String>>>();
			if (outterJoinOutterCriterials == null) outterJoinOutterCriterials = new ArrayList<List<Map<String,Map<String,String>>>>();
			
			// reset endJoinIndex
			int endJoinIndex = 0;
			
			for (int i = outterJoins.size() - 1; i >= 0 ; i--) {
				Map<String, String> joinMap = outterJoins.get(i);
				if (joinMap.size() != 0) {
					if (endJoinIndex == 0) endJoinIndex = i;		// get the join endIndex
				} else  if (joinMap.size() == 0) {
					if (endJoinIndex != 0) {						// get the join beginIndex
						// have one range
						List<Object> joinModels = new ArrayList<Object>();
						List<Set<String>> joinModelKeys = new ArrayList<Set<String>>();
						List<List<String>> joinOutterSorts = new ArrayList<List<String>>();
						List<List<String>> joinOutterLimits = new ArrayList<List<String>>();
						List<List<String>> joinOutterFields = new ArrayList<List<String>>();
						List<Map<String, String>> joinOutterJoins = new ArrayList<Map<String,String>>();
						List<Map<String, Map<String, String>>> joinOutterCriterials = new ArrayList<Map<String,Map<String,String>>>();
						// add them
						for (int j = i; j <= endJoinIndex; j++) {
							Object model = models.get(j);
							Set<String> keys = modelsKeys.get(j);
							List<String> sorts = outterSorts == null ? null : outterSorts.get(j);
							List<String> limits= outterLimits == null ? null : outterLimits.get(j);
							List<String> fields = outterFields == null ? null : outterFields.get(j);
							Map<String, String> joins = outterJoins == null ? null : outterJoins.get(j);
							Map<String, Map<String,String>> criterias = outterCriterials == null ? null : outterCriterials.get(j);
							
							joinModels.add(model);
							joinModelKeys.add(keys);
							if (sorts != null) joinOutterSorts.add(sorts);
							if (limits != null) joinOutterLimits.add(limits);
							if (fields != null) joinOutterFields.add(fields);
							if (joins != null) joinOutterJoins.add(joins);
							if (criterias != null) joinOutterCriterials.add(criterias);
						}
						
						// remove them
						for (int j = endJoinIndex; j >= i ; j--) {
							models.remove(j);
							modelsKeys.remove(j);
							if (outterSorts != null) outterSorts.remove(j);
							if (outterLimits != null) outterLimits.remove(j);
							if (outterFields != null) outterFields.remove(j);
							if (outterJoins != null) outterJoins.remove(j);
							if (outterCriterials != null) outterCriterials.remove(j);
						}
						
						// add them to list for query
						outterJoinModels.add(0, joinModels);
						outterJoinModelKeys.add(0, joinModelKeys);
						outterJoinOutterSorts.add(0, joinOutterSorts);
						outterJoinOutterLimits.add(0, joinOutterLimits);
						outterJoinOutterFields.add(0, joinOutterFields);
						outterJoinOutterJoins.add(0, joinOutterJoins);
						outterJoinOutterCriterials.add(0, joinOutterCriterials);
						
						// reset endJoinIndex
						endJoinIndex = 0;
					}
				}
			}
			
			// get the join result
			for(int i = 0; i < outterJoinModels.size(); i++) {
				List<Object> joinModels = outterJoinModels.get(i);
				List<Set<String>> joinModelKeys = outterJoinModelKeys.get(i);
				List<List<String>> joinOutterSorts = outterJoinOutterSorts.get(i);
				List<List<String>> joinOutterLimits = outterJoinOutterLimits.get(i);
				List<List<String>> joinOutterFields = outterJoinOutterFields.get(i);
				List<Map<String, String>> joinOutterJoins = outterJoinOutterJoins.get(i);
				List<Map<String, Map<String, String>>> joinOutterCriterials = outterJoinOutterCriterials.get(i);
				
				// get the join result
				List<Object> joinedResults = dao.readJoined(joinModels, joinModelKeys, joinOutterFields, joinOutterCriterials, joinOutterJoins, joinOutterSorts, joinOutterLimits);
				results.add(joinedResults);
				// get the number
				if (QueryLimitsHelper.isJoinedNeedLimits(joinOutterLimits)) {
					if (responseMessage.numbers == null) responseMessage.numbers = new ArrayList<String>();
					responseMessage.numbers.add(dao.getJoinedTotalRows());
				}
			}
		}
		// Have Joins . get the need joins models  --- End  --------------------------------
		
		
		
		
		
		// Have No Joins .   --- Begin  --------------------------------
		List<Map<String, String>> outterPreconditions = requestMessage.getPRECONDITIONS();
		for (int i = 0; i < models.size(); i++) {
			Object model = models.get(i);
			Set<String> keys = modelsKeys.get(i);
			List<String> sorts = outterSorts == null ? null : outterSorts.get(i);
			List<String> limits= outterLimits == null ? null : outterLimits.get(i);
			List<String> fields = outterFields == null ? null : outterFields.get(i);
			Map<String, Map<String,String>> criterias = outterCriterials == null ? null : outterCriterials.get(i);
			
			Map<String, String> precondition = outterPreconditions == null ? null : outterPreconditions.get(i);
			if (precondition != null) QueryCriteriasHelper.readAssemblePreconditions(results, model, keys, precondition);
			
			// get the read result
			List<Object> readResults = dao.read(model, keys, fields, criterias, sorts, limits);
			results.add(readResults);
			// get the number
			if (QueryLimitsHelper.isNeedLimit(limits)) {
				if (responseMessage.numbers == null) responseMessage.numbers = new ArrayList<String>();
				responseMessage.numbers.add(dao.getTotalRows(model, keys, fields, criterias));
			}
		}
		// Have No Joins .   --- End  --------------------------------
			
		
		
		
		responseMessage.results = results;
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
	}

}
