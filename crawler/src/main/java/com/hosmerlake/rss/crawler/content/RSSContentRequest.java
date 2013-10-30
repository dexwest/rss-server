/**
 * 
 */
package com.hosmerlake.rss.crawler.content;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.content.DefaultContentRequest;
import com.hosmerlake.rss.common.exception.ContentRequestException;
import com.hosmerlake.rss.common.model.RssContent.RssContentRoot;
import com.hosmerlake.rss.common.service.RemoteService;

/**
 * High level interface to handle all RSS content, parse and return result.  The
 * content will be determined by the remote service return type through content type.
 * Parser type is specific to this request.
 * @author BFOX1
 *
 */
@Component("rss-request")
public class RSSContentRequest extends DefaultContentRequest<RssContentRoot> {
	
	@Override
	public RssContentRoot processRequest() throws ContentRequestException {
		super.executeRequest();
		return (RssContentRoot) getResult();
	}
	
	@Resource(name="crawl-rss-service")
	public void setService(RemoteService service)
	{
		super.setService(service);
	}
}
