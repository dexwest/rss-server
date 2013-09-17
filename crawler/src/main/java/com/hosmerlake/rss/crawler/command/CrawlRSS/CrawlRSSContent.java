/**
 * 
 */
package com.hosmerlake.rss.crawler.command.CrawlRSS;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.exception.ContentRequestException;
import com.hosmerlake.rss.crawler.content.parser.CrawlRss.CrawlRSSRequest;
import com.hosmerlake.rss.crawler.content.parser.CrawlRss.objects.CrawlResponse;
import com.hosmerlake.rss.crawler.model.RssContent.RssContentRoot;

/**
 * @author BFOX1
 *
 */
@Component("crawl-rss-content")
public class CrawlRSSContent {
	private static final Log logger = LogFactory.getLog(CrawlRSSContent.class);

	@Resource(name="rss-request")
	private CrawlRSSRequest request;
	
	private CrawlResponse createResponse() throws ContentRequestException {
		
		RssContentRoot rssContent = request.processRequest();
		return CrawlResponse.buildResponse(rssContent);
	}

	public CrawlResponse getContent() {
		try {
			return createResponse();
		} catch(ContentRequestException cre) {
			logger.warn("Content request failed exception", cre);
		} catch(Exception e) {
			logger.warn("Exception", e);
		}
		return new CrawlResponse();
	}
}
