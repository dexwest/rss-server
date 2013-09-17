/**
 * 
 */
package com.hosmerlake.rss.scheduler.rss;

import java.io.Serializable;

import com.hosmerlake.rss.scheduler.connection.RssConnection;

/**
 * @author bfox1
 *
 */
public class RssItems implements Serializable {

	private static final long serialVersionUID = 3363692502143249250L;
	
	private String schedulerCron;
	private RssConnection connection;
	
	public String getSchedulerCron() {
		return schedulerCron;
	}
	public void setSchedulerCron(String schedulerCron) {
		this.schedulerCron = schedulerCron;
	}
	public RssConnection getConnection() {
		return connection;
	}
	public void setConnection(RssConnection connection) {
		this.connection = connection;
	}
}
