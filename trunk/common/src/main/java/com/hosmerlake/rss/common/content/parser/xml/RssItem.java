package com.hosmerlake.rss.common.content.parser.xml;

import org.apache.commons.feedparser.FeedParserState;

public class RssItem {

	private FeedParserState state;
	private String title;
	private String link;
	private String description;
	private String permalink;

	public RssItem(FeedParserState state, String title, String link, String description, String permalink) {
		this.state = state;
		this.title = title;
		this.link = link;
		this.description = description;
		this.permalink = permalink;
	}
}
