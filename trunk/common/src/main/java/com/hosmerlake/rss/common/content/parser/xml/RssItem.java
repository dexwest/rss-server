package com.hosmerlake.rss.common.content.parser.xml;

import java.io.Serializable;

import org.apache.commons.feedparser.FeedParserState;

public class RssItem implements Serializable {
	private static final long serialVersionUID = 9157169378709977476L;
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

	public FeedParserState getState() {
		return state;
	}

	public void setState(FeedParserState state) {
		this.state = state;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}
}
