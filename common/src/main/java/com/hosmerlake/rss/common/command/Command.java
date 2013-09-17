/**
 * 
 */
package com.hosmerlake.rss.common.command;

import java.util.List;

import org.apache.commons.chain2.impl.ContextBase;

/**
 * @author BFOX1
 *
 */
public interface Command {
	public List<Command> getDependants();
	public boolean run(ContextBase context);
}
