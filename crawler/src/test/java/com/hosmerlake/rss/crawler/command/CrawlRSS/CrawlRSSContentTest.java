/**
 * 
 */
package com.hosmerlake.rss.crawler.command.CrawlRSS;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.hosmerlake.rss.common.content.parser.json.DefaultJsonContentParser;
import com.hosmerlake.rss.common.content.parser.rss.RssParser;
import com.hosmerlake.rss.common.request.HttpClientRequest;
import com.hosmerlake.rss.crawler.CrawlerBaseSpringTest;
import com.hosmerlake.rss.crawler.content.parser.CrawlRss.RSSContentRequest;
import com.hosmerlake.rss.crawler.controller.content.CrawlRssContentParameters;
import com.hosmerlake.rss.crawler.service.crawl.CrawlRSSService;

/**
 * @author BFOX1
 *
 */
@ContextConfiguration(classes = {CrawlRSSContentTest.SpringConfig.class})
public class CrawlRSSContentTest extends AbstractJUnit4SpringContextTests {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}
	
	@Configuration
	@Import( CrawlerBaseSpringTest.SpringConfig.class )
	public static class SpringConfig {

		@Bean(name="crawl-rss-content")
		public RSSContentBuilder crawlRssContent() {
			return new RSSContentBuilder();
		}
		
		@Bean(name = "rss-request")
		public RSSContentRequest crawlerRssRequest() {
			RSSContentRequest request = new RSSContentRequest();
    		
    		return request;
		}
		@Bean(name = "http-client-request")
		public HttpClientRequest httpClient() {
			return new HttpClientRequest();
		}
		@Bean(name = "default-json-content-parser")
		public DefaultJsonContentParser jsonParser() {
			DefaultJsonContentParser parser = new DefaultJsonContentParser();
			
			return parser;
		}
		@Bean(name = "rss-parser")
		public RssParser rssParser() {
			RssParser parser = new RssParser();
			
			return parser;
		}
		@Bean(name="crawl-rss-service")
		public CrawlRSSService rssService() {
			return new CrawlRSSService();
		}
		
		@Bean(name="crawl-rss-content-parameters")
		public CrawlRssContentParameters rssParams() {
			CrawlRssContentParameters params = new CrawlRssContentParameters();
			Map<String, String> paramTable = new HashMap<String, String>();
			
			paramTable.put("url", "http://localhost/mockdata");
			params.setParameters(paramTable );
			return params;
		}
	}

	@Test
	public void testSpring() {
		assertNotNull(crawlRss);
	}

}
