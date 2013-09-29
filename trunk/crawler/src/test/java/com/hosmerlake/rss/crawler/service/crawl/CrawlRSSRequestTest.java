package com.hosmerlake.rss.crawler.service.crawl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.hosmerlake.rss.common.exception.ContentRequestException;
import com.hosmerlake.rss.common.service.RemoteService;
import com.hosmerlake.rss.crawler.CrawlerBaseSpringTest;
import com.hosmerlake.rss.crawler.content.parser.CrawlRss.CrawlRSSRequest;

/**
 * @author BFOX1
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class CrawlRSSRequestTest extends CrawlerBaseSpringTest<CrawlRSSRequestTest> {

	@Resource(name = "rss-request")
	private CrawlRSSRequest request;


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() {
		assertNotNull(request);
		request.getParser().setContentUrl("http://localhost:8080/mock");
		assertNotNull(request.getHttpClient());
		assertNotNull(request.getHttpClient().getConfig());
	}

}
