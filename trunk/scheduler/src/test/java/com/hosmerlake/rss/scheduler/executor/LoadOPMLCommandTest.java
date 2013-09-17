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

import com.hosmerlake.rss.scheduler.SchedulerBaseSpringTest;
import com.hosmerlake.rss.scheduler.command.LoadSchedule.LoadOPMLCommand;

/**
 * @author BFOX1
 *
 */
public class LoadOPMLCommandTest extends SchedulerBaseSpringTest<LoadOPMLCommandTest> {

	private static final String INPUT_OBJ_NAME = "opmlLoadServiceTestInput";
	@Autowired
	private LoadOPMLCommand command;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
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
