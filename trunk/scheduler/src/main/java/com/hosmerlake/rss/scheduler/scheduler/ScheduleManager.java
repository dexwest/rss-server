package com.hosmerlake.rss.scheduler.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

/***
 * 
 * @author BFOX1
 *
 */

public class ScheduleManager {
	@Autowired
	private ThreadPoolTaskScheduler scheduler;
	
	public boolean schedule(Runnable task, String cron)
	{
		scheduler.schedule(task, new CronTrigger(cron));
		return true;
	}
}
