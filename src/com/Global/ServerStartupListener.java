package com.Global;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.modules.Util.DLog;

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
