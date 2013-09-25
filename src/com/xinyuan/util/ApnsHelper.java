package com.xinyuan.util;

import java.util.Map;
import java.util.Map.Entry;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

import com.xinyuan.message.ConfigConstants;

public class ApnsHelper {
	
	private static final int APNS_PORT = 2195;
	private static final String APNS_URL = "gateway.sandbox.push.apple.com";
	
	
	private static final String APNS_ALERT = "Alert";
	private static final String APNS_Sound = "Sound";
	private static final String APNS_Badge = "Badge";
	
	
	private static final String CERTIFICATEPASSWORD = "12345";
	private static final String CERTIFICATEPATH = "/Users/Isaacs/Downloads/work/xinyuan_develop/apnsDevelop.p12";
	
	
	private static final String DEVICE = "iphone";

	
	/**
	 * 
	 * @param map		Push contents , e.g. {"Alert":"","Badge":"","Sound",""}
	 * @param apnsToken Push device token
	 * @throws Exception
	 */
	public static void push(Map<String, Object>map , String apnsToken) throws Exception {
		PayLoad payLoad = new PayLoad();
		if (map.containsKey(APNS_ALERT)) payLoad.addAlert((String) map.get(APNS_ALERT));
		if (map.containsKey(APNS_Badge)) payLoad.addBadge(Integer.valueOf((String) map.get(APNS_Badge)));
		if (map.containsKey(APNS_Sound)) payLoad.addSound((String) map.get(APNS_Sound));
		
		for (Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			if (key.equals(APNS_ALERT) || key.equals(APNS_Badge) || key.equals(APNS_Sound)) continue;
			payLoad.addCustomDictionary(key, (String) entry.getValue());
		}

		// initial push notification manager
		PushNotificationManager pushManager = PushNotificationManager.getInstance();
		pushManager.addDevice(DEVICE, apnsToken);

		// Connect to APNS
		pushManager.initializeConnection(APNS_URL, APNS_PORT, CERTIFICATEPATH, CERTIFICATEPASSWORD, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);

		// Send Push
		Device client = pushManager.getDevice(DEVICE);
		pushManager.sendNotification(client, payLoad);
		pushManager.stopConnection();
		pushManager.removeDevice(DEVICE);
	}
}
