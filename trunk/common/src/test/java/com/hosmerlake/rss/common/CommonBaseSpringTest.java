/**
 * 
 */
package com.hosmerlake.rss.common;

import org.junit.Ignore;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author BFOX1
 *
 */
@Ignore
@ContextConfiguration(classes = {CommonBaseSpringTest.SpringConfig.class})
public class CommonBaseSpringTest<E> extends AbstractJUnit4SpringContextTests {

	@Configuration
	@ComponentScan( basePackages = {"com.hosmerlake.rss.common"} )
	@ImportResource( { "classpath*:/http-client-context.xml" } )
	public static class SpringConfig {
    }
}
