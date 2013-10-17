package com.global;

import com.opensymphony.xwork2.ActionContext;

public class SessionManager {
	
	public static void put(String key , Object value) {
		ActionContext.getContext().getSession().put(key, value);
	}
	
	public static void remove(String key) {
		ActionContext.getContext().getSession().remove(key);
	}
	
	public static Object get(String key) {
		return ActionContext.getContext().getSession().get(key);
	}

}
