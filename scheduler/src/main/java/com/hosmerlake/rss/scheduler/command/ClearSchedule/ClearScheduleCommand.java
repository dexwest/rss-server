/**
 * 
 */
package com.hosmerlake.rss.scheduler.command.ClearSchedule;

import java.util.List;

import org.apache.commons.chain2.impl.ContextBase;
import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.command.BaseCommand;
import com.hosmerlake.rss.common.command.Command;

/**
 * @author BFOX1
 *
 */
@Component("clear-schedule-command")
public class ClearScheduleCommand extends BaseCommand implements ClearSchedule {

	/**
	 * 
	 */
	public ClearScheduleCommand() {
	}

	@Override
	public List<Command> getDependants() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean run(ContextBase context) {
		return true;
	}

}
