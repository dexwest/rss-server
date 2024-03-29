package com.hosmerlake.rss.crawler.controller;

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

import com.hosmerlake.rss.common.model.RssContent.RssContentRoot;
import com.hosmerlake.rss.crawler.builder.RSSContentBuilder;
import com.hosmerlake.rss.crawler.controller.content.CrawlRssContentParameters;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/rss", method = RequestMethod.GET)
public class RssController {
	
	private static final Logger logger = LoggerFactory.getLogger(RssController.class);
	
	@Resource(name="rss-content-builder")
	private RSSContentBuilder builder;
	
	@Resource
	CrawlRssContentParameters params;


	public RssController() {
		logger.info("constructor");
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
    @ResponseBody
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    public ResponseEntity<RssContentRoot> get(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	params.init(request);
		logger.info("Start RSS crawl content. locale %1", request.getLocale());
		return new ResponseEntity<RssContentRoot>(builder.getContent(), new HttpHeaders(), HttpStatus.OK);
    }
}
