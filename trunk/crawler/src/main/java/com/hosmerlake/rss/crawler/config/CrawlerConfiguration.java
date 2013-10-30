package com.hosmerlake.rss.crawler.config;

import java.util.List;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hosmerlake.rss.common.interceptor.MetricLoggerInterceptor;

@Configuration
@EnableWebMvc
@PropertySource({"classpath:/crawler.properties","classpath:/env-config.properties"} )
@ComponentScan( basePackages = {"com.hosmerlake.rss.crawler","com.hosmerlake.rss.common"} )
@ImportResource( { "classpath*:/application-context.xml","classpath*:/http-client-context.xml" } )
public class CrawlerConfiguration extends WebMvcConfigurerAdapter implements EnvironmentAware  {

	private Environment environment;

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {		
		converters.add(xmlMessageConverter());
		super.configureMessageConverters(converters);
	}
	
	
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MetricLoggerInterceptor());
    }

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;		
	}
	
	@Bean
	public HttpMessageConverter<Object> xmlMessageConverter() {
		return new MappingJacksonHttpMessageConverter();
	}
}