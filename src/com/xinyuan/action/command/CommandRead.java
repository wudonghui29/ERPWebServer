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
