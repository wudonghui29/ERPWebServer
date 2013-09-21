package com.xinyuan.util;

import java.util.Map;
import java.util.Map.Entry;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

import com.xinyuan.message.ConfigConstants;

public class ApnsPushNotificatioin {

	public static void push(Map<String, Object>map , String apnsToken) throws Exception {
		PayLoad payLoad = new PayLoad();
		if (map.containsKey(ConfigConstants.APNS_ALERT)) payLoad.addAlert((String) map.get(ConfigConstants.APNS_ALERT));
		if (map.containsKey(ConfigConstants.APNS_Badge)) payLoad.addBadge(Integer.valueOf((String) map.get(ConfigConstants.APNS_Badge)));
		if (map.containsKey(ConfigConstants.APNS_Sound)) payLoad.addSound((String) map.get(ConfigConstants.APNS_Sound));
		
		for (Entry<String, Object> entry : map.entrySet()) {
//		    System.out.println(entry.getKey() + "/" + entry.getValue());
			String key = entry.getKey();
			if (key.equals(ConfigConstants.APNS_ALERT) || key.equals(ConfigConstants.APNS_Badge) || key.equals(ConfigConstants.APNS_Sound)) continue;
			payLoad.addCustomDictionary(key, (String) entry.getValue());
		}

		// initial push notification manager
		PushNotificationManager pushManager = PushNotificationManager.getInstance();
		pushManager.addDevice("iPhone", apnsToken);

		// Connect to APNS
		String certificatePath = "/Users/Isaacs/Downloads/work/xinyuan_develop/apnsDevelop.p12";
		String certificatePassword = "12345";
		pushManager.initializeConnection(ConfigConstants.APNS_URL, ConfigConstants.APNS_PORT, certificatePath, certificatePassword, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);

		// Send Push
		Device client = pushManager.getDevice("iPhone");
		pushManager.sendNotification(client, payLoad);
		pushManager.stopConnection();
		pushManager.removeDevice("iPhone");
	}
}
