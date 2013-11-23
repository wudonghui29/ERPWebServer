package com.xinyuan.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.Global.SessionManager;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ConfigFormat;
import com.xinyuan.model.BaseModel;
import com.xinyuan.model.BaseOrder;
import com.xinyuan.model.User.User;

public class OrderNOGenerator {
	
	// in BaseAction Create() method
	public static void setOrderBasicCreateDetail(BaseModel model) {
		Date date = new Date();
		model.setCreateDate(date);
		User user = (User)SessionManager.get(ConfigConstants.SIGNIN_USER);
		model.setCreateUser(user.getUsername());
		
		if (model instanceof BaseOrder) {
			// Generate the orderNO
			String modelClassName = model.getClass().getName();
			String orederPrefix = ConfigFormat.orderNOPrefixMap.get(modelClassName);
			String previousOrderNO = ConfigFormat.previousOrderNOMap.get(modelClassName);
			
			String format = ConfigFormat.secondOrderTypeMap.containsKey(orederPrefix) ? ConfigFormat.DATESTRING_WITH_SECOND_FORMAT : ConfigFormat.DATESTRING_WITHOUT_SECOND_FORMAT;
			
			SimpleDateFormat sdf = new SimpleDateFormat(format);  
			String dateString = sdf.format(date);
			String orderNO = orederPrefix + dateString;
			if (previousOrderNO != null && previousOrderNO.contains(orderNO)) {
				orderNO = generateOrderNO(orederPrefix, sdf, date, previousOrderNO);
			}
			
			((BaseOrder)model).setOrderNO(orderNO);		// TODO: check the database if already have this no.
			ConfigFormat.previousOrderNOMap.put(modelClassName, orderNO);
		}
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
