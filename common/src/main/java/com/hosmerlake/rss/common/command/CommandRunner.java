/**
 * 
 */
package com.hosmerlake.rss.common.command;

import org.apache.commons.chain2.impl.ContextBase;


/**
 * @author BFOX1
 *
 */
public class CommandRunner {

	static public boolean run(Command cmd, ContextBase context) {
		if(cmd == null)
			return false;
		if(cmd.getDependants() != null && !cmd.getDependants().isEmpty())
		{
			for(Command depCmd : cmd.getDependants())
			{
				if(!CommandRunner.run(depCmd, context))
					return false;
			}
		}	
		return cmd.run(context);
	}
}
