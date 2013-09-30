package com.xinyuan.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.org.apache.bcel.internal.generic.RET;
import com.xinyuan.message.ConstantsConfig;
import com.xinyuan.message.FormatConfig;

public class JsonHelper {
	
	public static Gson getGson(){
		return new GsonBuilder().setDateFormat(FormatConfig.STRING_TO_DATE_FORMAT).create();
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
	 * Convert JsonElement to Java List
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
	 * @param isMap
	 * @return
	 */
	public static <E extends Object> List<E> getListFromJson(JsonObject jsonObject, String key, boolean isMap) {
		
		List<E> fields = new ArrayList<E>();
		JsonArray fieldsArray = (JsonArray) jsonObject.get(key);	
		if (fieldsArray == null) return null;
		
		for (int i = 0; i < fieldsArray.size(); i++) {
			JsonElement jsonElement = fieldsArray.get(i);
			E subFields = (E) (isMap ? JsonHelper.translateElementToMap((JsonObject)jsonElement) : JsonHelper.translateJsonArrayToList((JsonArray)jsonElement));
			fields.add(subFields);
		}
		
		return (List<E>) fields;
	}
	
}
