/**
 * 
 */
package com.hosmerlake.rss.crawler.service.crawl;

import java.net.MalformedURLException;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.exception.ContentRequestException;
import com.hosmerlake.rss.common.service.RemoteService;
import com.hosmerlake.rss.crawler.controller.content.CrawlRssContentParameters;

/**
 * @author bfox1
 *
 */
@Component("crawl-rss-service")
public class CrawlRSSService extends RemoteService {
	private static final Log logger = LogFactory.getLog(CrawlRSSService.class);

	@Resource(name="crawl-rss-content-parameters")
	private CrawlRssContentParameters params;

	
	public CrawlRSSService() {
	}

	/* (non-Javadoc)
	 * @see com.hosmerlake.rss.common.service.RemoteService#getUrl()
	 */
	@Override
	public String getUrl() throws ContentRequestException {
		try {
			return params.getUrl().toString();
		} catch (MalformedURLException mue) {
			logger.warn("failed to build URL for rss request service", mue);
			throw new ContentRequestException("request failed for class:  " + this.getClass().getName(), mue);
		}
	}

}
