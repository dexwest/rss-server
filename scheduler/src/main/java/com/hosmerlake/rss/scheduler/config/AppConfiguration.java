package com.hosmerlake.rss.scheduler.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hosmerlake.rss.common.interceptor.MetricLoggerInterceptor;


@Configuration
@ComponentScan( basePackages = {"com.hosmerlake.rss.scheduler"} )
@Import(SchedulerConfiguration.class)
@ImportResource( { "classpath*:/application-context.xml","classpath*:/http-client-context.xml" } )
public class AppConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MetricLoggerInterceptor());
    }
}