/**
 * 
 */
package com.hosmerlake.rss.crawler.controller.content;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.controller.DefaultParameters;

/**
 * @author bfox1
 *
 */
@Component("crawl-rss-content-parameters")
public class CrawlRssContentParameters extends DefaultParameters {
	
	public void init(HttpServletRequest request) {
		this.constructParams(request);
	}
	
	public CrawlRssContentParameters() {
		super();
	}
	public URL getUrl() throws MalformedURLException {
		String url = this.get("url");
		return new URL(url);
	}
}
