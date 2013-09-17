/**
 * 
 */
package com.hosmerlake.rss.common.command;

import org.springframework.core.Ordered;

/**
 * @author BFOX1
 *
 */
public abstract class BaseCommand implements Ordered,Command  {

	/* (non-Javadoc)
	 * @see org.springframework.core.Ordered#getOrder()
	 */
	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}
}
