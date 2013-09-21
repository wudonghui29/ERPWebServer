package com.global.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.modules.util.DLog;

public class ServerStartupListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		DLog.log("--------- contextInitialized");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		DLog.log("--------- contextDestroyed");
	}

}
