/**
 * 
 */
package com.hosmerlake.rss.crawler.builder;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.content.Content;
import com.hosmerlake.rss.common.model.RssContent.RssContentRoot;
import com.hosmerlake.rss.crawler.content.RSSContentRequest;

/**
 * @author BFOX1
 *
 */
@Component("rss-content-builder")
public class RSSContentBuilder implements Content<RssContentRoot> {
	private static final Log logger = LogFactory.getLog(RSSContentBuilder.class);

	@Resource(name="rss-request")
	private RSSContentRequest request;
	
	@Override
	public RssContentRoot getContent() {
		try {
			RssContentRoot result = request.processRequest();
			return result;
		} catch(Exception e) {
			logger.warn("Exception", e);
			return new RssContentRoot("no url");
		}
	}
}
