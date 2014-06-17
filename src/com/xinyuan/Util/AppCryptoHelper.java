package com.xinyuan.Util;

import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;

import sun.misc.BASE64Decoder;

import com.modules.Util.MD5Helper;
import com.modules.Util.RSAEncrypt;
import com.xinyuan.model.User.User;

public class AppCryptoHelper {

	public static String encode(String string) {
//		return string;
		
		return MD5Helper.encode2hex(string);
	}
	
	public static boolean isUserImpacted(User model, User persistence) {
		
//		return model.getPassword().equals(persistence.getPassword());
		
//		String modelPassword = model.getPassword();
//		modelPassword = modelPassword.replace(" ", "+");
//		try {
//			byte[] buffer = modelPassword.getBytes() ;// new BASE64Decoder().decodeBuffer(modelPassword);
//			RSAEncrypt rsaEncrypt = AppCryptoHelper.getRsaEncrypt();
//			byte[] plainText = rsaEncrypt.decrypt(rsaEncrypt.getPrivateKey(), buffer);
//			String decryptPassword = new String(plainText);
//			
//			System.out.println();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		String persistencePassword = persistence.getPassword();
		return MD5Helper.validate(model.getPassword(), persistence.getPassword());
	}
	
	
	
	private static RSAEncrypt uniqueInstance = null;  
	public static RSAEncrypt getRsaEncrypt() {
		if (uniqueInstance == null) {  
	           uniqueInstance = new RSAEncrypt(); 
	           
	         //加载公钥  
	           try {  
	        	   uniqueInstance.loadPublicKey(RSAEncrypt.DEFAULT_PUBLIC_KEY);  
	               System.out.println("加载公钥成功");  
	           } catch (Exception e) {  
	               System.err.println(e.getMessage());  
	               System.err.println("加载公钥失败");  
	           }  
	     
	           //加载私钥  
	           try {  
	        	   uniqueInstance.loadPrivateKey(RSAEncrypt.DEFAULT_PRIVATE_KEY);  
	               System.out.println("加载私钥成功");  
	           } catch (Exception e) {  
	               System.err.println(e.getMessage());  
	               System.err.println("加载私钥失败");  
	           } 
	       }  
	       return uniqueInstance;  
	}
}
