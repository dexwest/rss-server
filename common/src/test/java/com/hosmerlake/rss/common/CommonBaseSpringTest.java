/**
 * 
 */
package com.hosmerlake.rss.common;

import org.junit.Ignore;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author BFOX1
 *
 */
@Ignore
@ContextConfiguration(classes = {CommonBaseSpringTest.SpringConfig.class})
public class CommonBaseSpringTest  {

	@Configuration
	@ImportResource( { "classpath*:/http-client-context.xml","classpath*:/application-context.xml" } )
	public static class SpringConfig {
    }
}
