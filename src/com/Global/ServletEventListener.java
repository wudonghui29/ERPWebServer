package com.Global;

import j2se.modules.Helper.DLog;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.xinyuan.message.ConfigConstants;

public class ServletEventListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		DLog.log("--------- contextInitialized ---------");
		
		ConfigConstants.initializeContextVariables(sce.getServletContext());
		ConfigConstants.initializeSystemProperties();
		
		DLog.log("--------------- Hibernate initialize Begin ----------------\n");
		HibernateInitializer.initialize();
		DLog.log("--------------- Hibernate initialize End 	 ----------------\n");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		DLog.log("--------- contextDestroyed ---------");
	}

}
