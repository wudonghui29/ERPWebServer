package com.modules.Util;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class PrintHelper {

	public static void printServeletRequestHeader() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Enumeration<String> enumeration = request.getHeaderNames();
		while (enumeration.hasMoreElements()) {
			String name = (String) enumeration.nextElement();
			String value = request.getHeader(name);
			System.out.println(name + " : " + value);
		}
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		String sessionID = session.getId();
		System.out.println("\n session id -> " + sessionID);
		
		
//		Enumeration<String> enumeration = request.getParameterNames();
//		while (enumeration.hasMoreElements()) {
//			String name = (String) enumeration.nextElement();
//			String value = request.getParameter(name);
//			System.out.println(name + " - " + value);
//		}
	}
	
	
	public static void printMapKeysValues(Map map) {
	    Iterator iterator = map.entrySet().iterator();
	    while (iterator.hasNext()) {
	        Map.Entry pairs = (Map.Entry)iterator.next();
	        String key = (String) pairs.getKey(); 
	        Object value = pairs.getValue();
	        System.out.println(key + " = " + value);
	        iterator.remove(); // avoids a ConcurrentModificationException
	    }
	}
	
}
