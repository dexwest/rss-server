/**
 * 
 */
package com.hosmerlake.rss.crawler.service.crawl;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.hosmerlake.rss.crawler.CrawlerBaseSpringTest;
import com.hosmerlake.rss.crawler.content.parser.CrawlRss.CrawlRSSRequest;

/**
 * @author BFOX1
 *
 */
public class CrawlRSSRequestTest extends CrawlerBaseSpringTest<CrawlRSSRequestTest> {

	@Resource(name="rss-request")
	private CrawlRSSRequest request;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(request);
	}

}
