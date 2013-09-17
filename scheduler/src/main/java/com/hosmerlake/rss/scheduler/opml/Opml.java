/**
 * 
 */
package com.hosmerlake.rss.scheduler.opml;

import java.io.Serializable;
import java.util.List;

import com.hosmerlake.rss.scheduler.rss.RssItems;

/**
 * @author bfox1
 *
 */
public class Opml implements Serializable {

	private static final long serialVersionUID = 3648298229497659367L;
	
	private List<RssItems> rssItems;

	public List<RssItems> getRssItems() {
		return rssItems;
	}

	public void setRssItems(List<RssItems> rssItems) {
		this.rssItems = rssItems;
	}
}
