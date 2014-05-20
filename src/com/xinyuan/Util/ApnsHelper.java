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

import com.xinyuan.dao.UserDAO;
import com.xinyuan.dao.impl.UserDAOIMP;
import com.xinyuan.message.ConfigConstants;


// Reference : http://demo.netfoucs.com/truenaruto/article/details/9165011

public class ApnsHelper {
	
	public static final String APNS_Alert = "alert";
	public static final String APNS_Badge = "badge";
	public static final String APNS_Sound = "sound";
	private static final String APNS_Sound_DEFAULT = "default";


	public static boolean inform(List<String> forwardList, List<Map<String, Object>> forwardContents) throws Exception {
		
		int forwardsCount = forwardList != null ? forwardList.size() : 0 ;
		boolean isAllSuccess = false;
		UserDAO userDAO = new UserDAOIMP();
		for (int index = 0; index < forwardsCount; index++) {
			String forwardUsername = forwardList.get(index);
			
			String tokenString = userDAO.getUserApnsToken(forwardUsername);
			if (tokenString == null) {
				isAllSuccess = false;
				continue;
			}
			
			String[] apnsTokens =  tokenString.split(ConfigConstants.CONTENT_DIVIDER);
			Map<String, Object> apnsMap = forwardContents.get(index);
			int result = pushApns(apnsMap, apnsTokens);
			isAllSuccess = result == apnsTokens.length;
		}
		return isAllSuccess;
	}
	
	
	/**
	 * 
	 * @param map		Push contents , e.g. {"alert":"Hello","badge":1,"sound","default"}
	 * @param apnsToken Push devices tokens
	 * @return the successful count
	 * @throws Exception
	 */
	
	private static int pushApns(Map<String, Object>map , String[] apnsTokens) throws Exception {
		// FILETER THE PLACEHOLDER
		String[] devices = new String[apnsTokens.length];
		for (int i = 0; i < apnsTokens.length; i++) {
			devices[i] = apnsTokens[i].replaceAll(" ", "");
		}
		
		// GET THE APN MESSAGE OUT
		String message = (String)map.get(APNS_Alert);
		Integer badge = (Integer)map.get(APNS_Badge);
		String sound = (String)map.get(APNS_Sound);
		sound = sound == null || sound.isEmpty() ? APNS_Sound_DEFAULT : sound;
		
		
		/* Build a blank payload  */ 
		PushNotificationPayload payload = PushNotificationPayload.complex();
		if (message != null) payload.addAlert(message);
		if (badge != null) payload.addBadge(badge);
		if (sound != null) payload.addSound(sound);
		
		// set the customize contents
		for (Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			if (key.equals(APNS_Alert) || key.equals(APNS_Badge) || key.equals(APNS_Sound)) continue;
			payload.addCustomDictionary(key, (String) entry.getValue());		// the custom contents
		}
		
		return sendWithOutThread(payload, ConfigConstants.Apns_Certificate_Path, ConfigConstants.APNS_CERTIFICATE_PASSWORD, ConfigConstants.APNS_IN_PRODUCTION, devices);
		
	}
	
	private static int sendWithOutThread(final Payload payload, final Object keystore, final String password, final boolean production, final String[] devices) throws Exception {
		return send(payload, keystore, password, production, devices);
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
	
	private static int send(Payload payload, Object keystore, String password, boolean production, String[] devices) throws Exception {
		List<PushedNotification> notifications = null;
		int result = 0;
		
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
			
		for (PushedNotification notification : notifications) {
            if (notification.isSuccessful()) {
                    /* Apple accepted the notification and should deliver it */  
                    System.out.println("Push notification sent successfully to: " + notification.getDevice().getToken());
                    /* Still need to query the Feedback Service regularly */ 
                    
                    result ++;
                    
            } else {
            		// TODO: Some notifications failed . 
            	
                    String invalidToken = notification.getDevice().getToken();
                    /* Add code here to remove invalidToken from your database */  

                    System.out.println("Push notification sent failed to: " + notification.getDevice().getToken());
                    
                    /* Find out more about what the problem was */  
                    Exception theProblem = notification.getException();
                    theProblem.printStackTrace();

                    /* If the problem was an error-response packet returned by Apple, get it */  
                    ResponsePacket theErrorResponse = notification.getResponse();
                    if (theErrorResponse != null)  System.out.println(theErrorResponse.getMessage());
            }
            
		}
			
		return result;	
	}
	
}



