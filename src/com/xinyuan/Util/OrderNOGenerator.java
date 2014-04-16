package com.xinyuan.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.Global.SessionManager;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ConfigFormat;
import com.xinyuan.model.BaseModel;
import com.xinyuan.model.BaseOrder;
import com.xinyuan.model.User.User;

public class OrderNOGenerator {
	
	// For Generate Order Number
	private static final Map<String, String> previousOrderNOMap 	= new HashMap<String, String>();
	private static final Map<String, String> secondOrderTypeMap 	= new HashMap<String, String>();
	static {
		secondOrderTypeMap.put("FKD", ConfigConstants.EMPTY_STRING);
	}
	
	
	
	
	
	
	// in BaseAction Create() method
	public static void setOrderBasicCreateDetail(BaseModel model) {
		Date date = new Date();
		model.setCreateDate(date);
		User user = (User)SessionManager.get(ConfigConstants.SIGNIN_USER);
		model.setCreateUser(user.getUsername());
		
		if (model instanceof BaseOrder) {
			String orderNO = getNewOrderNO(model, date);
			((BaseOrder)model).setOrderNO(orderNO);		// TODO: check the database if already have this no.
		}
	}
	
	private static String getNewOrderNO(BaseModel model, Date date) {
		// Generate the orderNO
		String modelClassName = model.getClass().getName();
		String orederPrefix = ConfigConstants.serialNumberProperties.getProperty(modelClassName);
		String previousOrderNO = previousOrderNOMap.get(modelClassName);
		
		String format = secondOrderTypeMap.containsKey(orederPrefix) ? ConfigFormat.DATESTRING_WITH_SECOND_FORMAT : ConfigFormat.DATESTRING_WITHOUT_SECOND_FORMAT;
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);  
		String dateString = sdf.format(date);
		String orderNO = orederPrefix + dateString;
		// If have 
		if (previousOrderNO != null && previousOrderNO.contains(orderNO)) {
			orderNO = generateOrderNO(orederPrefix, sdf, date, previousOrderNO);
		}
		previousOrderNOMap.put(modelClassName, orderNO);
		
		return orderNO;
	}
	
	private static String generateOrderNO(String orederPrefix, SimpleDateFormat sdf, Date date, String previousOrderNO) {
		String previousOrderDigit = previousOrderNO.replaceAll("\\D+","");
//		int previousDigitCount = previousOrderDigit.length();
//		int formatCount = FormatConfig.DATESTRING_WITH_SECOND_FORMAT.length();
//		boolean isPreviousFormatWithSecond = previousDigitCount >= formatCount;
		
		// digit add 1 to avoid same in second
		sdf.applyPattern(ConfigFormat.DATESTRING_WITH_SECOND_FORMAT);
		String dateString = sdf.format(date);
		
		// the second + 1
		if (dateString.equals(previousOrderDigit)) {
			String lastStr = dateString.substring(dateString.length() - 1);
			int i = Integer.valueOf(lastStr);
			dateString = dateString.substring(0, dateString.length() - 1) + (i+1) ;
		}
		
		String newOrderNO = orederPrefix + dateString;
		return newOrderNO;
	}

}
