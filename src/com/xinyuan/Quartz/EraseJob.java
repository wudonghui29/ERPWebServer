package com.xinyuan.Quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.modules.Util.DLog;

public class EraseJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		DLog.log(new Date() + "   ++++++++++++++++++++++++++++++++++++");
		
		
	}

}
