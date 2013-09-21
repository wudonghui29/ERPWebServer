package com.xinyuan.util;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class JsonHelper {

	public static Map<String, Object> translateElementToMap(JsonElement element) {
		String objectString = new Gson().toJson(element);
		Map<String, Object> map = new Gson().fromJson(objectString, Map.class);
		return map;
	}
}
