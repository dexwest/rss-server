package com.hosmerlake.rss.crawler.content.parser.CrawlRss;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.hosmerlake.rss.common.content.parser.rss.RssParser;
import com.hosmerlake.rss.crawler.CrawlerBaseSpringTest;

@ContextConfiguration(classes = {RssParserTest.SpringConfig.class})
public class RssParserTest extends AbstractJUnit4SpringContextTests {

	@Resource(name = "rss-parser")
	private RssParser parser;

	@Before
	public void setUp() throws Exception {
	}

	
	@Configuration
	@Import( CrawlerBaseSpringTest.SpringConfig.class )
	public static class SpringConfig {
		@Bean(name="rss-parser")
		public RssParser rssParser() {
			RssParser parser = new RssParser();
			return parser;
		}
    }
	
	@Test
	public void test() {
		assertNotNull(parser);
	}

}
