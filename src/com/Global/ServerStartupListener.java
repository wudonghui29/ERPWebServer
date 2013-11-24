package com.Global;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.struts2.ServletActionContext;

import com.modules.Util.DLog;
import com.modules.Util.Test;
import com.xinyuan.message.ConfigConstants;

public class ServerStartupListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		DLog.log("--------- context initialized ---------");
		
		ConfigConstants.initializeContextVariables(sce.getServletContext());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		DLog.log("--------- context destroyed ---------");
	}

}
