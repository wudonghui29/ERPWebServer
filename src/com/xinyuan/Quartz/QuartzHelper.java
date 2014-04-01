package com.xinyuan.Quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzHelper {

	static SchedulerFactory schedulerFactory = null;
	
	public static void startEraseJobs() {
		if (schedulerFactory == null) {
			schedulerFactory = new StdSchedulerFactory();
		}
		
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			
			JobDetail job = newJob(EraseJob.class)
				    .withIdentity("EraseJob", "EraseJobGroup")
				    .build();
			

//			CronTrigger trigger = newTrigger()
//				    .withIdentity("EraseTrigger", "EraseTriggerGroup")
//				    .withSchedule(cronSchedule("0 30 6 ? * MON-SAT"))
//				    .withSchedule(cronSchedule("0 32 15 ? * MON-SAT"))		// TEST
//				    .build();
			
			Trigger trigger = newTrigger()
				    .withIdentity("EraseTrigger", "EraseTriggerGroup")
				    .startNow()
				    .build();
			
			
			scheduler.scheduleJob(job, trigger);  
			scheduler.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
