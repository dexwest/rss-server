/**
 * 
 */
package com.hosmerlake.rss.crawler.command.CrawlRSS;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hosmerlake.rss.crawler.CrawlerBaseSpringTest;

/**
 * @author BFOX1
 *
 */
public class CrawlRSSCommandTest extends CrawlerBaseSpringTest<CrawlRSSCommandTest> {

	@Autowired
	private CrawlRSSContent command;

	@Test
	public void test() {
		assertNotNull(command);
	}

}
