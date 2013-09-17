/**
 * 
 */
package com.hosmerlake.rss.scheduler.command.LoadSchedule;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.chain2.impl.ContextBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.command.BaseCommand;
import com.hosmerlake.rss.common.command.Command;
import com.hosmerlake.rss.scheduler.model.LoadSchedulerResponse;

/**
 * @author BFOX1
 *
 */
@Component("load-schedule-command")
public class LoadScheduleCommand extends BaseCommand implements LoadSchedule {

	private static final Logger logger = LoggerFactory.getLogger(LoadScheduleCommand.class);

	public static final String CONTEXT_OPML = "opml_obj";
	
	@Resource(name = "rssScheduler")
	private ThreadPoolTaskScheduler scheduler;

	private String result;
	
	@Override 
	public int getOrder() {
		return LoadSchedule.Order.TWO.ordinal();
	}

	@Override
	public boolean run(ContextBase context) {
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, request.getLocale());
//		String formattedDate = dateFormat.format(date);
//		LoadSchedulerResponse loadSchedulerResp = new LoadSchedulerResponse();
//		loadSchedulerResp.setServerDateTime(formattedDate);
//		
//		
//		getScheduler().execute(new ClearSchedulerTask());
//		getScheduler().execute(new ProcessOPMLTask(opml));
//		loadSchedulerResp.setResult("success");
//		return loadSchedulerResp;
		return true;
	}
	public LoadSchedulerResponse execute(HttpServletRequest request)
	{
		return null;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public ThreadPoolTaskScheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(ThreadPoolTaskScheduler scheduler) {
		this.scheduler = scheduler;
	}

	@Override
	public List<Command> getDependants() {
		return null;
	}


}
