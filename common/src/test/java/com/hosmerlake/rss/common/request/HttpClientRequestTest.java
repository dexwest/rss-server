/**
 * 
 */
package com.hosmerlake.rss.common.request;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.hosmerlake.rss.common.CommonBaseSpringTest;

/**
 * @author BFOX1
 *
 */
@ContextConfiguration(classes = {HttpClientRequestTest.SpringConfig.class})
public class HttpClientRequestTest extends AbstractJUnit4SpringContextTests {

	
	@Resource(name = "http-client-request")
	private HttpClientRequest httpClient;

	@Configuration
	@Import( CommonBaseSpringTest.SpringConfig.class )
	public static class SpringConfig {
		@Bean(name="http-client-request")
		public HttpClientRequest httpRequest() {
			HttpClientRequest request = new HttpClientRequest();
			return request;
		}
    }
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSpring() {
		assertNotNull(httpClient);
		assertNotNull(httpClient.getConfig());
	}
}
