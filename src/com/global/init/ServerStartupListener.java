package com.global.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.modules.util.DLog;

public class ServerStartupListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		DLog.log("--------- context initialized ---------");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		DLog.log("--------- context destroyed ---------");
	}

}
