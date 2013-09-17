/**
 * 
 */
package com.hosmerlake.rss.scheduler.command.ResestSchedule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.chain2.impl.ContextBase;
import org.junit.Before;
import org.junit.Test;

import com.hosmerlake.rss.common.command.Command;
import com.hosmerlake.rss.common.command.CommandRunner;
import com.hosmerlake.rss.scheduler.SchedulerBaseSpringTest;
import com.hosmerlake.rss.scheduler.command.ClearSchedule.ClearScheduleCommand;
import com.hosmerlake.rss.scheduler.command.LoadSchedule.LoadOPMLCommand;
import com.hosmerlake.rss.scheduler.command.LoadSchedule.LoadScheduleCommand;

/**
 * @author BFOX1
 *
 */
public class ResetScheduleCommandTest extends SchedulerBaseSpringTest<ResetScheduleCommandTest> {


	@Resource(name="reset-schedule-command")
	private ResetScheduleCommand cmd;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(cmd);
		
		List<Command> dependants = cmd.getDependants();
		assertEquals(3, dependants.size());
		assertEquals(ClearScheduleCommand.class, dependants.get(0).getClass());
		assertEquals(LoadOPMLCommand.class, dependants.get(1).getClass());
		assertEquals(LoadScheduleCommand.class, dependants.get(2).getClass());
		ContextBase context = new ContextBase();
		context.put(LoadOPMLCommand.CONTEXT_USER_OPML_ID, "opmlLoadServiceTestInput");
		assertTrue(CommandRunner.run(cmd, context));
	}
}
