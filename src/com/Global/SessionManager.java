package com.Global;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class SessionManager {
	
	
	public static void put(String key , Object value) {
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute(key, value);
		
//		String sessionID = session.getId();
//		System.out.println("put : " + key + " session id -> " + sessionID);
		
		
//		ActionContext.getContext().getSession().put(key, value);
//		Map<String, Object> servletSession = ServletActionContext.getContext().getSession();
//		Map<String, Object> actionSessioin = ActionContext.getContext().getSession();
//		if (servletSession == actionSessioin)  DLog.log("YES");
		
	}
	
	public static void remove(String key) {
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute(key);
		
//		String sessionID = session.getId();
//		System.out.println("remove : " + key + " session id -> " + sessionID);
		
		
//		ActionContext.getContext().getSession().remove(key);
	}
	
	public static Object get(String key) {
		HttpSession session = ServletActionContext.getRequest().getSession();
		
//		String sessionID = session.getId();
//		System.out.println("get : " + key + " session id -> " + sessionID);
		
		return session.getAttribute(key);
		
		
//		return ActionContext.getContext().getSession().get(key);
	}

}
