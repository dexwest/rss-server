/**
 * 
 */
package com.hosmerlake.rss.crawler.content.parser.CrawlRss;

import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.content.ContentTypeUtil;
import com.hosmerlake.rss.common.content.ParseException;
import com.hosmerlake.rss.common.content.parser.ContentParser;
import com.hosmerlake.rss.common.content.parser.json.DefaultJsonContentParser;

/**
 * @author bfox1
 * 
 */
@Component("rss-request-parser")
public class CrawlRSSRequestParser implements ContentParser {
	private static final Log logger = LogFactory.getLog(CrawlRSSRequestParser.class);

	@Resource(name = "default-json-content-parser")
	DefaultJsonContentParser jsonParser;

	@Resource(name = "rss-parser")
	RssParser xmlParser;

	private String contentUrl;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hosmerlake.rss.common.content.parser.ContentParser#parse(java.io.
	 * InputStream, java.lang.String)
	 */
	@Override
	public Object parse(InputStream respIS, Header[] headers, String url) {
		if (respIS == null || headers == null)
			return null;

		try {
			if (StringUtils.isNotBlank(ContentTypeUtil.isXML(headers)))
				return xmlParser.parse(respIS, headers, url);
			else if (StringUtils.isNotBlank(ContentTypeUtil.isJson(headers)))
				return jsonParser.parse(respIS, headers, url);
			else
				logger.warn("cannot parse inputstream do not recognize content type:  " + StringUtils.defaultString(headers.toString(), "no headers")+" for url: " + url);
		} catch (ParseException pe) {
			logger.warn("parser failure see above in logs", pe);
		}

		return null;
	}
	public String getContentUrl() {
		return contentUrl;
	}
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
}
