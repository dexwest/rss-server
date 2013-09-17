/**
 * 
 */
package com.hosmerlake.rss.scheduler;

import org.springframework.context.annotation.ComponentScan;
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
@ContextConfiguration(classes = {SchedulerBaseSpringTest.SpringConfig.class})
public class SchedulerBaseSpringTest<E> extends CommonBaseSpringTest<E> {

	@Configuration
	@ComponentScan( basePackages = {"com.hosmerlake.rss.common","com.hosmerlake.rss.scheduler"} )
	@Import( CommonBaseSpringTest.SpringConfig.class )
	@ImportResource( { "classpath*:/application-context.xml" } )
	@PropertySource({"classpath:/objectStore.properties","classpath:/scheduler.properties"} )
	public static class SpringConfig {
    }
}
