/**
 * 
 */
package com.hosmerlake.rss.crawler.content.parser.CrawlRss;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.content.DefaultContentRequest;
import com.hosmerlake.rss.common.exception.ContentRequestException;
import com.hosmerlake.rss.common.request.HttpClientConfig;
import com.hosmerlake.rss.common.service.RemoteService;
import com.hosmerlake.rss.crawler.model.RssContent.RssContentRoot;

/**
 * @author BFOX1
 *
 */
@Component("rss-request")
public class CrawlRSSRequest extends DefaultContentRequest<RssContentRoot> {

	@Resource(name="rss-request-parser")
	private
	CrawlRSSRequestParser parser;
	
	@PostConstruct
	public void init() throws ContentRequestException {
		getParser().setContentUrl(getService().getUrl());
	}
	
	@Override
	public RssContentRoot processRequest() throws ContentRequestException {
		super.executeRequest(getParser());
		return (RssContentRoot) getResult();
	}
	
	@Resource(name="crawl-rss-service")
	public void setService(RemoteService service)
	{
		super.setService(service);
	}
	
	@Resource(name="crawler-http-client-config")
	public void setHttpConfig(HttpClientConfig config) {
		super.getHttpClient().setConfig(config);
	}

	public CrawlRSSRequestParser getParser() {
		return parser;
	}

	public void setParser(CrawlRSSRequestParser parser) {
		this.parser = parser;
	}
}
