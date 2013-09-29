/**
 * 
 */
package com.hosmerlake.rss.crawler.model.RssContent;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * @author BFOX1
 *
 */
@Component("rss-content-root")
public class RssContentRoot implements Serializable {
	private static final long serialVersionUID = 741104813016798099L;

	private boolean parsed;
	/**
	 * 
	 */
	public RssContentRoot() {
		// TODO Auto-generated constructor stub
	}
	public boolean isParsed() {
		return parsed;
	}
	public void setParsed(boolean parsed) {
		this.parsed = parsed;
	}
}
