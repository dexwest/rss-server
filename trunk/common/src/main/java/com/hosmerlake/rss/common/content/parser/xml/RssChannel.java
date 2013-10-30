package com.hosmerlake.rss.common.content.parser.xml;

import java.io.Serializable;
import java.util.List;

public class RssChannel implements Serializable {
	private static final long serialVersionUID = -3033052188467388981L;
	private String title;
	private String link;
	private String description;

	private List<RssItem> items;

	public RssChannel(String title, String link, String description) {
		this.title = title;
		this.link = link;
		this.description = description;
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

	public List<RssItem> getItems() {
		return items;
	}

	public void setItems(List<RssItem> items) {
		this.items = items;
	}


}
