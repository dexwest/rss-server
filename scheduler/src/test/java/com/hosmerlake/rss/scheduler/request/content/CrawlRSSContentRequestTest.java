package com.hosmerlake.rss.scheduler.request.content;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hosmerlake.rss.common.exception.ContentRequestException;
import com.hosmerlake.rss.scheduler.SchedulerBaseSpringTest;

/**
 * @author BFOX1
 *
 */
public class CrawlRSSContentRequestTest extends SchedulerBaseSpringTest {

	@Autowired
	private CrawlerContentRequest request;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(request);
		assertNotNull(request.getService());
		
		try {
			assertNotNull(request.getService().getUrl());
			assertNotNull(request.processRequest());
		} catch (ContentRequestException e) {
			e.printStackTrace();
			fail();
		}
	}
}
