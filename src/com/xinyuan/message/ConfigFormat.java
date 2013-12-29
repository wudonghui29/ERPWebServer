package com.xinyuan.message;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigFormat {

	public static final String STRING_TO_DATE_FORMAT			 	=	"yyyy-MM-dd HH:mm:ss";
	public static final String DATESTRING_WITH_SECOND_FORMAT	 	= 	"yyyyMMddHHmmss";
	public static final String DATESTRING_WITHOUT_SECOND_FORMAT 	= 	"yyyyMMddHHmm";
	
	
	
	// Order NO Prefix
	public static Properties serialProperties = null;
	public static final Map<String, String> previousOrderNOMap 	= new HashMap<String, String>();
	public static final Map<String, String> secondOrderTypeMap 	= new HashMap<String, String>();
	
	static {
		secondOrderTypeMap.put("FKD", ConfigConstants.EMPTY_STRING);
	}
	
}
