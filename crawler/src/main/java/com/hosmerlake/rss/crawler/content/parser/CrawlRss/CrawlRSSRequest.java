/**
 * 
 */
package com.hosmerlake.rss.crawler.content.parser.CrawlRss;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.ScopedProxyMode;

import com.hosmerlake.rss.common.content.DefaultContentRequest;
import com.hosmerlake.rss.common.exception.ContentRequestException;
import com.hosmerlake.rss.common.service.RemoteService;
import com.hosmerlake.rss.crawler.controller.content.CrawlRssContentParameters;
import com.hosmerlake.rss.crawler.model.RssContent.RssContentRoot;

/**
 * @author BFOX1
 *
 */
@Component("rss-request")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value="request")
public class CrawlRSSRequest extends DefaultContentRequest<RssContentRoot> {

	@Autowired
	CrawlRSSRequestParser parser;
	
	@Override
	public RssContentRoot processRequest() throws ContentRequestException {
		super.executeRequest(parser);
		return (RssContentRoot) getResult();
	}
	
	@Resource(name="crawl-rss-service")
	public void setService(RemoteService service)
	{
		super.setService(service);
	}
}
