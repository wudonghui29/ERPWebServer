package com.xinyuan.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xinyuan.message.ConfigFormat;
import com.xinyuan.message.RequestMessage;

public class JsonHelper {
	
	public static Gson getGson(){
		return new GsonBuilder().setDateFormat(ConfigFormat.STRING_TO_DATE_FORMAT).create();
	}

	/**
	 * Convert JsonElement to Java Map
	 * @param element
	 * @return
	 */
	public static Map<String, Object> translateElementToMap(JsonElement element) {	// this element must be a jsonobject , not a jsonarray
		String objectString = JsonHelper.getGson().toJson(element);
		Map<String, Object> map = JsonHelper.getGson().fromJson(objectString, Map.class);
		return map;
	}
	
	/**
	 * Convert JsonElement to Java List, the element should be string
	 * @param jsonArray
	 * @return
	 */
	public static List<String> translateJsonArrayToList(JsonArray jsonArray) {
		List<String> list = new ArrayList<String>();
		int len = jsonArray.size();
		for (int i = 0; i < len; i++) {
			list.add(jsonArray.get(i).getAsString());
		}
		return list;
	}
	
	
	/**
	 * 		for FIELDS : E = List<String>
	 * @param jsonObject
	 * @param key
	 * @param isContentMap
	 * @return
	 */
	public static <E extends Object> List<E> getListFromJson(JsonObject jsonObject, String key, boolean isContentMap) {
		
		List<E> results = new ArrayList<E>();
		JsonArray outterArray = (JsonArray) jsonObject.get(key);	
		if (outterArray == null) return null;
		
		for (int i = 0; i < outterArray.size(); i++) {
			JsonElement jsonElement = outterArray.get(i);
			E content = (E) (isContentMap ? JsonHelper.translateElementToMap((JsonObject)jsonElement) : JsonHelper.translateJsonArrayToList((JsonArray)jsonElement));
			results.add(content);
		}
		
		return (List<E>) results;
	}
	
	
	/**
	 * 
	 * 
	 {
    	"OBJECTS": 
    	[ { } ]
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
