package com.hosmerlake.rss.crawler.service.crawl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.HashMap;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.hosmerlake.rss.common.content.parser.Http.HttpContentTypeParser;
import com.hosmerlake.rss.common.content.parser.html.HtmlContentParser;
import com.hosmerlake.rss.common.content.parser.json.DefaultJsonContentParser;
import com.hosmerlake.rss.common.content.parser.rss.RssParser;
import com.hosmerlake.rss.common.exception.ContentRequestException;
import com.hosmerlake.rss.common.model.RssContent.RssContentRoot;
import com.hosmerlake.rss.common.request.HttpClientRequest;
import com.hosmerlake.rss.crawler.CrawlerBaseSpringTest;
import com.hosmerlake.rss.crawler.content.RSSContentRequest;
import com.hosmerlake.rss.crawler.controller.content.CrawlRssContentParameters;

/**
 * @author BFOX1
 * 
 */
@ContextConfiguration(classes = {RSSContentRequestTest.SpringConfig.class})
public class RSSContentRequestTest extends AbstractJUnit4SpringContextTests {

	@Resource(name = "rss-request")
	private RSSContentRequest request;

	@Configuration
	@Import( CrawlerBaseSpringTest.SpringConfig.class )
	public static class SpringConfig {
		
		@Bean(name = "rss-request")
		public RSSContentRequest crawlerRssRequest() {
			RSSContentRequest request = new RSSContentRequest();
    		
    		return request;
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
		@Bean(name="html-parser")
		public HtmlContentParser htmlParser() {
			return new HtmlContentParser();
		}
		
		@Bean(name = "http-client-request")
		public HttpClientRequest httpClient() {
			return new HttpClientRequest();
		}
		
		@Bean(name="crawl-rss-service")
		public CrawlRSSService rssService() {
			return new CrawlRSSService();
		}
		@Bean(name="http-content-type-parser")
		public HttpContentTypeParser typeParser()
		{
			return new HttpContentTypeParser();
		}
		
		@Bean(name="crawl-rss-content-parameters")
		public CrawlRssContentParameters rssParams() {
			return new CrawlRssContentParameters();
		}
    }

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() {
	}
	@Test
	public void testSpring() {
		assertNotNull(request);
	}
	@Test
	@DirtiesContext
	public void testGetClient() {
		assertNotNull(request.getHttpClient());
	}
	@Test
	@DirtiesContext
	public void testRequest() {
		try {
			request.getService().getParams().constructParams(new HashMap<String, String>(){{put("url","http://www.feedforall.com/sample.xml");}});
			RssContentRoot content = request.processRequest();
			assertNotNull(content);
			assertNotNull(content.getDom());
		} catch (ContentRequestException e) {
			e.printStackTrace();
			fail();
		}
	}

}
