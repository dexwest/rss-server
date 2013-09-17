/**
 * 
 */
package com.hosmerlake.rss.crawler;

import org.junit.Ignore;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;

import com.hosmerlake.rss.common.CommonBaseSpringTest;

/**
 * @author BFOX1
 *
 */
@Ignore
@ContextConfiguration(classes = {CrawlerBaseSpringTest.SpringConfig.class})
public class CrawlerBaseSpringTest<E> extends CommonBaseSpringTest<E> {

	@Configuration
	@Import( CommonBaseSpringTest.SpringConfig.class )
	@ImportResource( { "classpath*:/application-context.xml" } )
	@ComponentScan( basePackages = {"com.hosmerlake.rss.crawler"} )
	public static class SpringConfig {
    }
}
