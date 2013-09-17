package com.hosmerlake.rss.scheduler.service;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.exception.ContentRequestException;
import com.hosmerlake.rss.common.service.RemoteService;
/**
 * @author BFOX1
 *
 */
@Component("crawl-rss-service")
public class CrawlRssService extends RemoteService {
	@Value("${crawler.http.fqdn}")
	String fqdn;
	
	@Value("${crawler.rss.content.path}")
	String path;
	
	@Override
	public String getUrl() throws ContentRequestException {
		try {
			return new URL("http",fqdn, path).toString();
		} catch (MalformedURLException e) {
			throw new ContentRequestException("url_fqdn:"+StringUtils.defaultString(fqdn, "no fqdn")+" path:"+StringUtils.defaultString(path, "no path"));
		}
	}
}
