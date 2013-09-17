package com.hosmerlake.rss.scheduler.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;


@Configuration
@EnableAsync
@EnableScheduling
@PropertySource({"classpath:/objectStore.properties","classpath:/scheduler.properties"} )
public class SchedulerConfiguration implements SchedulingConfigurer {

	@Override
	public void configureTasks(ScheduledTaskRegistrar registrar) {
		// TODO Auto-generated method stub
		
	}
}