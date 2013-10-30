/**
 * 
 */
package com.hosmerlake.rss.scheduler.request.content;

import java.net.URL;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.content.DefaultContentRequest;
import com.hosmerlake.rss.common.exception.ContentRequestException;
import com.hosmerlake.rss.common.service.RemoteService;
import com.hosmerlake.rss.scheduler.model.rssCrawl.CrawlerRSSContentRoot;
import com.hosmerlake.rss.scheduler.parser.CrawlRss.CrawlRssContentParser;
import com.hosmerlake.rss.scheduler.service.CrawlRssService;

/**
 * @author BFOX1
 * 
 */
@Component("crawl-rss-request")
public class CrawlerContentRequest extends DefaultContentRequest<CrawlerRSSContentRoot> {
	
	@Resource(name="crawl-rss-content-parser")
	CrawlRssContentParser parser;
	
	@Resource(name = "crawl-rss-service")
	public void setService(RemoteService service) {
		super.setService(service);
	}

	@Override
	public CrawlerRSSContentRoot processRequest() throws ContentRequestException {
		validateRequest();
		super.executeRequest();
		validateResult();
		return (CrawlerRSSContentRoot) getResult();
	}

	private void validateRequest() throws ContentRequestException {
		if (getService() != null && getService() instanceof CrawlRssService) {
			CrawlRssService service = (CrawlRssService) getService();

			try {
				if (StringUtils.isNotBlank(service.getUrl())) {
					URL url = new URL(service.getUrl());
				}
			} catch (Exception e) {
				throw new ContentRequestException("invalid URL", e);
			}
		}
	}

	private void validateResult() throws ContentRequestException {
		Object result = getResult();
		if (result instanceof CrawlerRSSContentRoot) {
			CrawlerRSSContentRoot contentResult = (CrawlerRSSContentRoot) getResult();
		}

	}
}
