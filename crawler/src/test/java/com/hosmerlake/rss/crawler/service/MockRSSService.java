/**
 * 
 */
package com.hosmerlake.rss.crawler.service;

import com.hosmerlake.rss.common.controller.DefaultParameters;
import com.hosmerlake.rss.common.exception.ContentRequestException;
import com.hosmerlake.rss.common.service.RemoteService;

/**
 * @author bfox1
 *
 */
public class MockRSSService extends RemoteService {

	/* (non-Javadoc)
	 * @see com.hosmerlake.rss.common.service.RemoteService#getUrl()
	 */
	@Override
	public String getUrl() throws ContentRequestException {
		return "localhost:8080/mockservice";
	}

	@Override
	public DefaultParameters getParams() {
		return new DefaultParameters("{localhost:8080/mockservice}");
	}
}
