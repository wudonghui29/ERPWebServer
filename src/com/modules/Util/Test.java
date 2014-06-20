package com.modules.Util;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.modules.Helper.MD5Helper;
import com.modules.Helper.RSAEncryptor;
import com.xinyuan.Util.GsonHelper;
import com.xinyuan.interceptor.PermissionInterceptor;

public class Test extends HashSet {

	public class Worker  {
		private String name;  
		  
		private java.util.Date dob;  
		  
		private java.util.Date inTime;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public java.util.Date getDob() {
			return dob;
		}

		public void setDob(java.util.Date dob) {
			this.dob = dob;
		}

		public java.util.Date getInTime() {
			return inTime;
		}

		public void setInTime(java.util.Date inTime) {
			this.inTime = inTime;
		}
	}
	
	
	// Test Permission check 
	public static void main000(String[] args) {
		
		String perssionStr = "{\"Business\":{\"Client\":[\"read\",\"create\"]},\"Assistant\":{\"ASDeviceBatteryMROrder\":[\"read\",\"create\",\"modify\"]}}";

		JsonObject jsonObject = (JsonObject)(new JsonParser()).parse(perssionStr);
		Map<String, Object> permissions = GsonHelper.translateElementToMap(jsonObject);
		
		String modelsStr = ".Assistant.ASDeviceBatteryMROrder, .Business.Client";
		String[] array = modelsStr.split(",");
		List<String> models = Arrays.asList(array);
		
//		boolean allowed = PermissionInterceptor.checkPermission("Business", "read", models, permissions);
		boolean alloweda = PermissionInterceptor.checkPermission("read", models, permissions);  // for action = "super"
		
		System.out.println("");

	}
	
	// TEST APNS
	 public static void main__w(String[] args) {
//       String[] devices = {"7df340181160dcb82607885e332e770b497a754758592047646396cebc9ab913",  "9ab941ea30f5cc4db41fc0a5dbbeae2dfe6a9d0f8c3bca1b97cc5c043aff6be0"}; 
		 String[] devices = {"7df34018 1160dcb8 2607885e 332e770b 497a7547 58592047 646396ce bc9ab913",  "9ab941ea 30f5cc4d b41fc0a5 dbbeae2d fe6a9d0f 8c3bca1b 97cc5c04 3aff6be0"}; 
		 Map<String, String> map = new HashMap<String, String>();
		map.put("Alert",
				"ERP!!!!---!===sos sos sos sos sos sos=T.M.D.====");
		map.put("Badge", 3 + "");
		map.put("Sound", "");
		map.put("ATTENDTIONS:", "YOU JUST A ERP GUY , DO YOU KNOW ?");

		try {
//			ApnsHelper.pushApns(map, devices);	// Private Mthods Now
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	 public static void main_MD5(String[] args) throws IOException {

		 String source = "今天99";
		 
		 System.out.println("加密后为	(bytes): " + MD5Helper.encode2bytes(source));
		 System.out.println("加密后为	  (hex): " + MD5Helper.encode2hex(source));
		 System.out.println("BASE64 ENCODE : " + (new BASE64Encoder()).encode(MD5Helper.encode2bytes(source)));
		 System.out.println("BASE64 DECODE : " + (new BASE64Decoder()).decodeBuffer((new BASE64Encoder()).encode(MD5Helper.encode2bytes(source))));
		 
        String str = MD5Helper.encode2hex("今天99");  
        System.out.println("是否匹配:" + MD5Helper.validate("今天99" , str));  
	 }
	 
	 
	 public static void main(String[] args) throws Exception {

		 String base46String =  
		 "GCqbwShexn94UVkgdQznFh6fTMaWtjv2PQiRlEeGZDVOh2+OrpPEYK8YMOgtGmYt" + "\r" +
		 "ErW8k8BnMibV6K6qcFlGtzBHi5/FqtbiFuWqXEqH/wCkJrdMSl1ojcgneqE8GK8c" + "\r" +
		 "X662CpHb91M2lXPmDdrXRbmHNC6JsINzjhI2Udq3JyA=";
		 
		 RSAEncryptor rsaEncryptor = new RSAEncryptor("/Users/Isaacs/Desktop/ERP_RSAA/rsa_public_key.pem", "/Users/Isaacs/Desktop/ERP_RSAA/pkcs8_private_key.pem");
		 
		 String test = "Test123.";
		 
//		 AzOd4faUUWcUaA12nB4rWXRAbZu1Z1xA4PUxVDGcMRUVAt0fGZSlowB0rwk5TqtGLcJs7fuSt1CQ
//		 mjHIF90aELb7kfy71ScQT9Uk6H7XsEzjyoq+n4Uu8ASDeygd5FplApxfNKjkRNtT4A6eM/Yc6nTC
//		 4UiNrrfB+pnOlEBJ3Vc=
		 
		 String testEn64 = rsaEncryptor.encryptWithBase64(test);
		 String testDe64 = rsaEncryptor.decryptWithBase64(testEn64);
		 
		 String result = rsaEncryptor.decryptWithBase64(base46String);
		 System.out.println(result);
		 
		 System.out.println();
	}
	 
}
