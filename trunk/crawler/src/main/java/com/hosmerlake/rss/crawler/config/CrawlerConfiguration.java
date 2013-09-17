package com.hosmerlake.rss.crawler.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hosmerlake.rss.common.interceptor.MetricLoggerInterceptor;

@Configuration
@PropertySource({"classpath:/crawler.properties"} )
@ComponentScan( basePackages = {"com.hosmerlake.rss.crawler","com.hosmerlake.rss.common"} )
@ImportResource( { "classpath*:/application-context.xml","classpath*:/http-client-context.xml" } )
public class CrawlerConfiguration extends WebMvcConfigurerAdapter implements EnvironmentAware  {

	private Environment environment;

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MetricLoggerInterceptor());
    }

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;		
	}
}