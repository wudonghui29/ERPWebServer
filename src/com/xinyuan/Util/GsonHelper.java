package com.xinyuan.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xinyuan.message.ConfigFormat;

public class GsonHelper {
	
	public static Gson getGson(){
		return new GsonBuilder().setDateFormat(ConfigFormat.STRING_TO_DATE_FORMAT).create();
	}

	public static Map<String, Object> translateJsonStringToMap(String string) {
		return GsonHelper.translateElementToMap((JsonObject)(new JsonParser()).parse(string));
	}
	
	/**
	 * Convert JsonElement to Java Map
	 * @param element
	 * @return
	 */
	public static Map<String, Object> translateElementToMap(JsonElement element) {	// this element must be a jsonobject , not a jsonarray
		String objectString = GsonHelper.getGson().toJson(element);
		Map<String, Object> map = GsonHelper.getGson().fromJson(objectString, Map.class);
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
			E content = (E) (isContentMap ? GsonHelper.translateElementToMap((JsonObject)jsonElement) : GsonHelper.translateJsonArrayToList((JsonArray)jsonElement));
			results.add(content);
		}
		
		return (List<E>) results;
	}
	
}
