/**
 * 
 */
package com.hosmerlake.rss.scheduler.command.ResestSchedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.chain2.impl.ContextBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.command.BaseCommand;
import com.hosmerlake.rss.common.command.Command;
import com.hosmerlake.rss.scheduler.command.ClearSchedule.ClearSchedule;
import com.hosmerlake.rss.scheduler.command.LoadSchedule.LoadSchedule;

/**
 * @author BFOX1
 * 
 */
@Component("reset-schedule-command")
public class ResetScheduleCommand extends BaseCommand implements ResetSchedule {

	@Autowired
	private List<ClearSchedule> clearCommands;
	@Autowired
	private List<LoadSchedule> loadCommands;

	@PostConstruct
	public void init() {
		Collections.sort(clearCommands, AnnotationAwareOrderComparator.INSTANCE);
		Collections.sort(loadCommands, AnnotationAwareOrderComparator.INSTANCE);
	}

	public ResetScheduleCommand() {
	}

	@Override
	public List<Command> getDependants() {
		List<Command> dependants = new ArrayList<Command>();
		
		//dependants.addAll((Collection<? extends Command>) clearCommands);
		//dependants.addAll((Collection<? extends Command>) loadCommands);
		
		return dependants;
	}

	@Override
	public boolean run(ContextBase context) {
		return true;
	}
}
