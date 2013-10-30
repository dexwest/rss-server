/**
 * 
 */
package com.hosmerlake.rss.common.content.parser.rss;

import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.apache.commons.feedparser.FeedParser;
import org.apache.commons.feedparser.FeedParserException;
import org.apache.commons.feedparser.FeedParserFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.content.parser.xml.DefaultXMLContentParser;
import com.hosmerlake.rss.common.content.parser.xml.FeedDom;
import com.hosmerlake.rss.common.content.parser.xml.FeedParserListenerImpl;
import com.hosmerlake.rss.common.model.RssContent.RssContentRoot;

/**
 * @author bfox1
 *
 */
@Component("rss-parser")
public class RssParser extends DefaultXMLContentParser {
	private static final Log logger = LogFactory.getLog(RssParser.class);

	private FeedParser parser;
	private FeedParserListenerImpl listener;

	@PostConstruct
	public void init() {
		//create a new FeedParser...
		try {
			setParser(FeedParserFactory.newFeedParser());
			this.listener = new FeedParserListenerImpl();
		} catch (FeedParserException fpe) {
			logger.warn("create feed parser failed", fpe);
		}
	}
	@Override
	protected Object parserInternal(InputStream is, String url)
	{
		RssContentRoot content = new RssContentRoot(url);
		try {
			parser.parse(listener, is,url);
			content.setDom((FeedDom) listener.getDom());
		} catch (FeedParserException fpe) {
			logger.warn("parse fail see above in logs", fpe);
		}
		return content;
	}
	
	
	public FeedParser getParser() {
		return parser;
	}

	public void setParser(FeedParser parser) {
		this.parser = parser;
	}

	public FeedParserListenerImpl getListener() {
		return listener;
	}

	public void setListener(FeedParserListenerImpl listener) {
		this.listener = listener;
	}
}
