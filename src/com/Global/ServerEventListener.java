package com.Global;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.modules.Util.DLog;
import com.xinyuan.message.ConfigConstants;

public class ServerEventListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		DLog.log("--------- context initialized ---------");
		
		ConfigConstants.initializeContextVariables(sce.getServletContext());
		
		DLog.log("--------------- Hibernate initialize Begin ----------------\n");
		HibernateInitializer.initialize();
		DLog.log("--------------- Hibernate initialize End 	 ----------------\n");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		DLog.log("--------- context destroyed ---------");
	}

}
