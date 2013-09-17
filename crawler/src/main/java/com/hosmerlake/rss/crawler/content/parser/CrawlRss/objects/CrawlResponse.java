/**
 * 
 */
package com.hosmerlake.rss.crawler.content.parser.CrawlRss.objects;

import java.io.Serializable;

import com.hosmerlake.rss.crawler.model.RssContent.RssContentRoot;

/**
 * @author BFOX1
 *
 */
public class CrawlResponse implements Serializable {

	private static final long serialVersionUID = 8500497422746772600L;

	/**
	 * 
	 */
	public CrawlResponse() {
	}

	public static CrawlResponse buildResponse(RssContentRoot rssContent) {
		CrawlResponse resp = new CrawlResponse();
		
		return resp;
	}

}
