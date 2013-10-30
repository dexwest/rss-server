/**
 * 
 */
package com.hosmerlake.rss.crawler;

import org.junit.Ignore;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;

import com.hosmerlake.rss.common.CommonBaseSpringTest;

/**
 * @author BFOX1
 *
 */
@Ignore
@ContextConfiguration(classes = {CrawlerBaseSpringTest.SpringConfig.class})
public class CrawlerBaseSpringTest extends CommonBaseSpringTest {

	@Configuration
	@Import( CommonBaseSpringTest.SpringConfig.class )
	@PropertySource({"classpath:/crawler.properties","classpath:/env-config.properties"} )
	@ImportResource( { "classpath*:/application-context.xml" } )
	public static class SpringConfig {
    }
}
