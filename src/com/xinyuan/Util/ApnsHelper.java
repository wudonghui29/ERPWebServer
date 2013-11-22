package com.xinyuan.Util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.notification.Payload;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.ResponsePacket;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionContext;
import com.xinyuan.Config.ConfigConstants;
import com.xinyuan.Config.ConfigJSON;
import com.xinyuan.Config.ResponseMessage;
import com.xinyuan.action.ActionBase;


// Reference : http://demo.netfoucs.com/truenaruto/article/details/9165011

public class ApnsHelper {
	
	private static final String APNS_Sound_DEFAULT = "default";
	
	
	public static boolean sendAPNS(JsonObject allJsonObject, ResponseMessage message) {
		try {
			message.apnsStatus = ConfigConstants.STATUS_SUCCESS;
			
			// push APNS notifications
			ApnsHelper.inform(allJsonObject);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			message.apnsStatus = ConfigConstants.STATUS_FAILED;
			message.description = ConfigConstants.MESSAGE.PushAPNSFailed;
		}
		
		return message.apnsStatus.equalsIgnoreCase(ConfigConstants.STATUS_SUCCESS) ;
	}
	
	/**
	 * @param allJsonObject		{ "APNS_FORWARDS" : [] , "APNS_CONTENTS" : [] }
	 * @throws Exception
	 */
	private static void inform(JsonObject allJsonObject) throws Exception {
		JsonArray forwardsList = allJsonObject.getAsJsonArray(ConfigJSON.APNS_FORWARDS);
		JsonArray forwardContents = allJsonObject.getAsJsonArray(ConfigJSON.APNS_CONTENTS);
		
		int forwardsCount = forwardsList != null ? forwardsList.size() : 0 ;
		
		for (int index = 0; index < forwardsCount; index++) {
			
			String forwardUsername = forwardsList.get(index).getAsString();
			String[] apnsTokens = ApprovalHelper.getAPNSToken(forwardUsername).split(ConfigConstants.CONTENT_DIVIDER);
			Map<String, Object> apnsMap = JsonHelper.translateElementToMap(forwardContents.get(index));
			
			push(apnsMap, apnsTokens);
		}
	}
	
	
	/**
	 * 
	 * @param map		Push contents , e.g. {"Alert":"","Badge":"","Sound",""}
	 * @param apnsToken Push device token
	 * @throws Exception
	 */
	
	public static void push(Map<String, Object>map , String[] apnsTokens) throws Exception {
		String APNS_Alert = ConfigJSON.APNS_Alert;
		String APNS_Badge = ConfigJSON.APNS_Badge;
		String APNS_Sound = ConfigJSON.APNS_Sound;
		
		// FILETER THE PLACEHOLDER
		String[] devices = new String[apnsTokens.length];
		for (int i = 0; i < apnsTokens.length; i++) {
			devices[i] = apnsTokens[i].replaceAll(" ", "");
		}
		
		// GET THE APN MESSAGE OUT
		String message = (String) map.get(APNS_Alert);
		String badgeString = (String) map.get(APNS_Badge);
		int badge = badgeString != null && !badgeString.isEmpty() ? Integer.valueOf(badgeString) : -1;
		String sound = (String) map.get(APNS_Sound);
		sound = sound == null || sound.isEmpty() ? APNS_Sound_DEFAULT : sound;
		
		
		/* Build a blank payload to customize */ 
		PushNotificationPayload payload = PushNotificationPayload.complex();
		payload.addAlert(message);
		payload.addBadge(badge);
		payload.addSound(sound);
		for (Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			if (key.equals(APNS_Alert) || key.equals(APNS_Badge) || key.equals(APNS_Sound)) continue;
			payload.addCustomDictionary(key, (String) entry.getValue());
		}
		
		sendWithOutThread(payload, ConfigConstants.APNS_CERTIFICATE_PATH, ConfigConstants.APNS_CERTIFICATE_PASSWORD, ConfigConstants.APNS_IN_PRODUCTION, devices);
		
	}
	
	private static void sendWithOutThread(final Payload payload, final Object keystore, final String password, final boolean production, final String[] devices) throws Exception {
		send(payload, keystore, password, production, devices);
	}
	
	// But: http://stackoverflow.com/questions/533783/why-spawning-threads-in-java-ee-container-is-discouraged
	private static void sendWithThread(final Payload payload, final Object keystore, final String password, final boolean production, final String[] devices) {
		// start a new thread to send notification  
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					send(payload, keystore, password, production, devices);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private static void send(Payload payload, Object keystore, String password, boolean production, String[] devices) throws Exception {
		List<PushedNotification> notifications = null;
		
		try {
			
			notifications = Push.payload(payload, keystore, password, production, devices);
		
		} catch (KeystoreException e) {
			/* A critical problem occurred while trying to use your keystore */  
			e.printStackTrace();
			throw e;
		} catch (CommunicationException e) {
			/* A critical communication error occurred while trying to contact Apple servers */  
			e.printStackTrace();
			throw e;
		}
			
		/* App Message */
		ActionBase action = (ActionBase)ActionContext.getContext().getActionInvocation().getAction();
		ResponseMessage message = action.getMessage();
		
		for (PushedNotification notification : notifications) {
            if (notification.isSuccessful()) {
                    /* Apple accepted the notification and should deliver it */  
                    System.out.println("Push notification sent successfully to: " + notification.getDevice().getToken());
                    /* Still need to query the Feedback Service regularly */  
                    
            } else {
            		message.apnsStatus = ConfigConstants.STATUS_FAILED;
            		
                    String invalidToken = notification.getDevice().getToken();
                    /* Add code here to remove invalidToken from your database */  

                    /* Find out more about what the problem was */  
                    Exception theProblem = notification.getException();
                    theProblem.printStackTrace();

                    /* If the problem was an error-response packet returned by Apple, get it */  
                    ResponsePacket theErrorResponse = notification.getResponse();
                    if (theErrorResponse != null)  System.out.println(theErrorResponse.getMessage());
            }
            
		}
			
			
	}
	
}



