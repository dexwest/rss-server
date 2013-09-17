/**
 * 
 */
package com.hosmerlake.rss.scheduler.model;

import java.io.Serializable;

/**
 * @author bfox1
 *
 */
public class LoadSchedulerResponse implements Serializable {
	private static final long serialVersionUID = 66995584571578385L;

	private String Result;
	private String serverDateTime;
	
	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}

	public String getServerDateTime() {
		return serverDateTime;
	}

	public void setServerDateTime(String serverDateTime) {
		this.serverDateTime = serverDateTime;
	}
}
