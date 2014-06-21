package com.xinyuan.Util;

import com.modules.Encryptor.MD5Encryptor;
import com.modules.Encryptor.RSAEncryptor;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.model.User.User;

public class AppCryptoHelper {
	
	// convenient properties
    public static RSAEncryptor sharedInstance = null;
    public static RSAEncryptor getSharedInstance() throws Exception {
    	if (sharedInstance == null) {
			sharedInstance =   new RSAEncryptor(ConfigConstants.RSA_PUBLIC_KEY_Path, ConfigConstants.RSA_PRIVATE_KEY_Path);
		}
    	return sharedInstance ;
    }
    
    
	public static String encodeWithRSA(String string) throws Exception {
		return getSharedInstance().encryptWithBase64(string);
	}
	public static String decodeWithRSA(String string) throws Exception {
		return getSharedInstance().decryptWithBase64(string);
	}
	

	
	
	public static String encodeWithMD5(String string) {
		return MD5Encryptor.encode2hex(string);
	}
	
	public static boolean isUserImpacted(User model, User persistence) {
		
		String modelPassword = model.getPassword();
		try {
			modelPassword = decodeWithRSA(modelPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String persistencePassword = persistence.getPassword();
		
		
		return MD5Encryptor.validate(modelPassword, persistencePassword);
	}
	
	
	
}
