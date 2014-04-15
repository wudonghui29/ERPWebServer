package com.xinyuan.Util;

import com.modules.Util.MD5Helper;
import com.xinyuan.model.User.User;

public class AppCryptoHelper {

	public static String encode(String string) {
//		return string;
		
		return MD5Helper.encode2hex(string);
	}
	
	public static boolean isUserImpacted(User model, User persistence) {
		
//		return model.getPassword().equals(persistence.getPassword());
		
//		String modelPassword = model.getPassword();
//		String persistencePassword = persistence.getPassword();
		return MD5Helper.validate(model.getPassword(), persistence.getPassword());
	}
}
