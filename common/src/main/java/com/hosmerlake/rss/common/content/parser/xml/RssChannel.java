package com.hosmerlake.rss.common.content.parser.xml;

import java.util.List;

public class RssChannel {
	private String title;
	private String link;
	private String description;

	private List<RssItem> items;

	public RssChannel(String title, String link, String description) {
		this.title = title;
		this.link = link;
		this.description = description;
	}


}
