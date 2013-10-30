/**
 * 
 */
package com.hosmerlake.rss.common.model.RssContent;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.content.parser.xml.FeedDom;

/**
 * @author BFOX1
 *
 */
@Component("rss-content-root")
public class RssContentRoot implements Serializable {
	private static final long serialVersionUID = 741104813016798099L;
	private FeedDom dom;
	private boolean parsed;
	private String url;
	/**
	 * @param url 
	 * 
	 */
	public RssContentRoot(String url) {
		this.url = url;
	}
	public boolean isParsed() {
		return parsed;
	}
	public void setParsed(boolean parsed) {
		this.parsed = parsed;
	}
	public FeedDom getDom() {
		return dom;
	}
	public void setDom(FeedDom dom) {
		this.dom = dom;
	}
}
