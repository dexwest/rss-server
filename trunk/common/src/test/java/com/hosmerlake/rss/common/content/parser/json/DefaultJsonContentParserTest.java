package com.hosmerlake.rss.common.content.parser.json;

import static org.junit.Assert.assertEquals;
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

@ContextConfiguration(classes = {DefaultJsonContentParserTest.SpringConfig.class})
public class DefaultJsonContentParserTest extends AbstractJUnit4SpringContextTests {
	private static final String MOCK_OBJECT_CLASS_NAME = "com.hosmerlake.rss.mockObject";

	@Resource(name = "default-json-content-parser")
	private DefaultJsonContentParser parser;

	@Before
	public void setUp() throws Exception {
	}

	@Configuration
	@Import( CommonBaseSpringTest.SpringConfig.class )
	public static class SpringConfig {

		@Bean(name="default-json-content-parser")
		public DefaultJsonContentParser jsonContentParser() {
			DefaultJsonContentParser parser = new DefaultJsonContentParser();
			parser.setResultClassName(MOCK_OBJECT_CLASS_NAME);
			return parser;
		}
    }

	@Test
	public void test() {
		assertNotNull(parser);
		assertEquals(MOCK_OBJECT_CLASS_NAME, parser.getResultClassName());
	}
}
