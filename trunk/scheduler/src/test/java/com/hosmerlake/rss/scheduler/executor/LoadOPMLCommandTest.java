/**
 * 
 */
package com.hosmerlake.rss.scheduler.executor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.chain2.impl.ContextBase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.hosmerlake.rss.scheduler.SchedulerBaseSpringTest;
import com.hosmerlake.rss.scheduler.command.LoadSchedule.LoadOPMLCommand;

/**
 * @author BFOX1
 *
 */
@ContextConfiguration(classes = {LoadOPMLCommandTest.SpringConfig.class})
public class LoadOPMLCommandTest extends AbstractJUnit4SpringContextTests {

	private static final String INPUT_OBJ_NAME = "opmlLoadServiceTestInput";
	@Autowired
	private LoadOPMLCommand command;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}
	@Configuration
	@Import( SchedulerBaseSpringTest.SpringConfig.class )
	public static class SpringConfig {
    }

	@Test
	public void test() {
		assertNotNull(command);
		ContextBase context = new ContextBase();
		context.put(LoadOPMLCommand.CONTEXT_USER_OPML_ID, INPUT_OBJ_NAME);
		command.run(context);
		assertTrue("success".equals(command.getStatus()));
	}
}
