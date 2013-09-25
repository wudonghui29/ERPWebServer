package com.xinyuan.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.xinyuan.message.ConfigConstants;

public class JsonHelper {

	public static Map<String, Object> translateElementToMap(JsonElement element) {
		String objectString = JsonHelper.getGson().toJson(element);
		Map<String, Object> map = JsonHelper.getGson().fromJson(objectString, Map.class);
		return map;
	}
	
	public static List<String> translateJsonArrayToList(JsonArray jsonArray) {
		List<String> list = new ArrayList<String>();
		int len = jsonArray.size();
		for (int i = 0; i < len; i++) {
			list.add(jsonArray.get(i).getAsString());
		}
		return list;
	}
	
	public static Gson getGson(){
		return new GsonBuilder().setDateFormat(ConfigConstants.STRING_TO_DATE_FORMAT).create();
	}
	
}
