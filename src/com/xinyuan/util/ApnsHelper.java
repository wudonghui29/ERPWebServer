package com.xinyuan.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.devices.exceptions.InvalidDeviceTokenFormatException;
import javapns.notification.Payload;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.ResponsePacket;
import javapns.notification.transmission.PushQueue;

import com.modules.util.DLog;


// Reference : http://demo.netfoucs.com/truenaruto/article/details/9165011

public class ApnsHelper {
	
	private static final boolean APNS_IN_PRODUCTION = false;
	
	private static final String APNS_ALERT = "Alert";
	private static final String APNS_Sound = "Sound";
	private static final String APNS_Badge = "Badge";
	
	private static final String DEFAULT = "default";
	
	private static final String CERTIFICATEPASSWORD = "12345";
	private static final String CERTIFICATEPATH = "/Users/Isaacs/Downloads/work/xinyuan_develop/apnsDevelop.p12";
	
	
	/**
	 * 
	 * @param map		Push contents , e.g. {"Alert":"","Badge":"","Sound",""}
	 * @param apnsToken Push device token
	 * @throws Exception
	 */
	
	public static void push(Map<String, Object>map , String[] apnsTokens) throws Exception {
		
		// FILETER THE PLACEHOLDER
		String[] devices = new String[apnsTokens.length];
		for (int i = 0; i < apnsTokens.length; i++) {
			devices[i] = apnsTokens[i].replaceAll(" ", "");
		}
		
		// GET THE APN MESSAGE OUT
		String message = (String) map.get(APNS_ALERT);
		String badgeString = (String) map.get(APNS_Badge);
		int badge = badgeString != null && !badgeString.isEmpty() ? Integer.valueOf(badgeString) : -1;
		String sound = (String) map.get(APNS_Sound);
		sound = sound == null || sound.isEmpty() ? DEFAULT : sound;
		
		
		/* Build a blank payload to customize */ 
		PushNotificationPayload payload = PushNotificationPayload.complex();
		payload.addAlert(message);
		payload.addBadge(badge);
		payload.addSound(sound);
		for (Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			if (key.equals(APNS_ALERT) || key.equals(APNS_Badge) || key.equals(APNS_Sound)) continue;
			payload.addCustomDictionary(key, (String) entry.getValue());
		}
		
		ApnsHelper.sendUseThread(payload, CERTIFICATEPATH, CERTIFICATEPASSWORD, APNS_IN_PRODUCTION, devices);
		
	}
	
	
	public static void sendUseThread(final Payload payload, final Object keystore, final String password, final boolean production, final String[] devices) {
		
		// start a new thread to send notification
		new Thread(new Runnable() {
			@Override
			public void run() {
				ApnsHelper.send(payload, keystore, password, production, devices);
			}
		}).start();
		
	}
	
	
	public static void send(Payload payload, Object keystore, String password, boolean production, String[] devices) {
		try {
			
			List<PushedNotification> notifications = Push.payload(payload, keystore, password, production, devices);
	        
			
			for (PushedNotification notification : notifications) {
	            if (notification.isSuccessful()) {
	                    /* Apple accepted the notification and should deliver it */  
	                    System.out.println("Push notification sent successfully to: " + notification.getDevice().getToken());
	                    /* Still need to query the Feedback Service regularly */  
	            } else {
	                    String invalidToken = notification.getDevice().getToken();
	                    /* Add code here to remove invalidToken from your database */  
	
	                    /* Find out more about what the problem was */  
	                    Exception theProblem = notification.getException();
	                    theProblem.printStackTrace();
	
	                    /* If the problem was an error-response packet returned by Apple, get it */  
	                    ResponsePacket theErrorResponse = notification.getResponse();
	                    if (theErrorResponse != null) {
	                            System.out.println(theErrorResponse.getMessage());
	                    }
	            }
	            
			}
			
			
		
		} catch (KeystoreException e) {
			/* A critical problem occurred while trying to use your keystore */  
			e.printStackTrace();
			
		} catch (CommunicationException e) {
			/* A critical communication error occurred while trying to contact Apple servers */  
			e.printStackTrace();
		}
	}
	
}



