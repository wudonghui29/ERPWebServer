package com.Global;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.modules.Util.DLog;
import com.xinyuan.Util.QueueHelper;

public class ServletSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		DLog.log("--------- sessionCreated : " + se.getSession().getId());
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		DLog.log("--------- sessionDestroyed : " + se.getSession().getId());
		
		QueueHelper.writers.remove(se.getSession().getId());
		
	}

}
