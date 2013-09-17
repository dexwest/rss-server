package com.hosmerlake.rss.scheduler.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hosmerlake.rss.scheduler.command.ResestSchedule.ResetScheduleCommand;
import com.hosmerlake.rss.scheduler.model.LoadSchedulerResponse;

/**
 * Handles requests for the application home page.
 */
@Controller
public class StartController {
	
	private static final Logger logger = LoggerFactory.getLogger(StartController.class);
	
	public StartController() {
		logger.info("constructor");
	}
	
	@Resource(name="reset-schedule-command")
	ResetScheduleCommand resetSchedule;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/start", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<LoadSchedulerResponse> get(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
		logger.info("Start RSS crawl scheduler. locale %1", request.getLocale());
		
		//LoadSchedulerResponse startRssResponse = resetSchedule.execute(request);
		
		return new ResponseEntity<LoadSchedulerResponse>(null, new HttpHeaders(), HttpStatus.OK);
    }
}
