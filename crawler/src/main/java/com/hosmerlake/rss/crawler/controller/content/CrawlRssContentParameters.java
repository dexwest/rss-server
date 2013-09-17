/**
 * 
 */
package com.hosmerlake.rss.crawler.controller.content;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.controller.DefaultParameters;

/**
 * @author bfox1
 *
 */
@Component("crawl-rss-content-parameters")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "request")
public class CrawlRssContentParameters extends DefaultParameters {
	
	@Resource
	protected HttpServletRequest request;

	@PostConstruct
	public void init() {
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
