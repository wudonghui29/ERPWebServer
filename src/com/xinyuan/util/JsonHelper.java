package com.xinyuan.util;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.xinyuan.message.ConfigConstants;

public class JsonHelper {

	public static Map<String, Object> translateElementToMap(JsonElement element) {
		String objectString = JsonHelper.getGson().toJson(element);
		Map<String, Object> map = JsonHelper.getGson().fromJson(objectString, Map.class);
		return map;
	}
	
	public static Gson getGson(){
		return new GsonBuilder().setDateFormat(ConfigConstants.STRING_TO_DATE_FORMAT).create();
	}
}
