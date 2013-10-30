/**
 * 
 */
package com.hosmerlake.rss.common.content.parser.xml;

import java.io.Serializable;
import java.util.List;

/**
 * @author BFOX1
 *
 */
public class FeedDom implements Serializable {
	private static final long serialVersionUID = 5441623422021300762L;
	private List<RssChannel> channels;
	private List<RssItem> items;

	/**
	 * 
	 */
	public FeedDom() {
		// TODO Auto-generated constructor stub
	}

	public FeedDom(List<RssChannel> channels, List<RssItem> items) {
		this.setChannels(channels);
		// TODO Auto-generated constructor stub
		this.setItems(items);
	}

	public List<RssChannel> getChannels() {
		return channels;
	}

	public void setChannels(List<RssChannel> channels) {
		this.channels = channels;
	}

	public List<RssItem> getItems() {
		return items;
	}

	public void setItems(List<RssItem> items) {
		this.items = items;
	}

}
