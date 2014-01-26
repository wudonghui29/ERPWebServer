package com.xinyuan.Util;

import java.util.Map;

import com.xinyuan.message.RequestMessage;

public class ParametersHelper {
	
	
	/**
	 * 
	 * 
	 {
    	"OBJECTS": [ { } ]
  	 	,"MODELS": [".User"]
  		,"PARAMETERS": {
    		"VERIFYCODE_COUNT":"5",
    		"VERIFYCODE_TYPE":"true"
  		}
	}
	 *  For the parameters value inside "PARAMETERS"
	 */
	public static String getParameter(RequestMessage requestMessage, String parameterName) {
		Map<String, String> parameters = requestMessage.getPARAMETERS();
		
		if (parameters == null || parameters.size() == 0) return null;
		
		return parameters.get(parameterName) ;
	}

}
