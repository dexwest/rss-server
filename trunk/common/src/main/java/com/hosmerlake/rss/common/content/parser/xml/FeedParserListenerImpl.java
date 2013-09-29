/**
 * 
 */
package com.hosmerlake.rss.common.content.parser.xml;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.feedparser.DefaultFeedParserListener;
import org.apache.commons.feedparser.FeedParserException;
import org.apache.commons.feedparser.FeedParserState;
import org.apache.commons.feedparser.FeedVersion;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author bfox1
 *
 */
public class FeedParserListenerImpl extends DefaultFeedParserListener {
	private static final Log logger = LogFactory.getLog(FeedParserListenerImpl.class);
	private List<RssChannel> channels;
	private List<RssItem> items;

	public FeedParserListenerImpl() {
	}
	public Object getDom() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
    public void onFeedVersion( FeedVersion version ) throws FeedParserException {}
	@Override
    public void init() throws FeedParserException {
		logger.info("init feed parser");
		
		channels = new ArrayList<RssChannel>();
		items = new ArrayList<RssItem>();
	}

	@Override
	public void onChannel(FeedParserState state, String title, String link, String description) throws FeedParserException {
		logger.info("on channel title:  " + title + " link: " + link + " description: " + description);
		channels.add(new RssChannel(title, link, description));
	}	
	@Override
	public void onItem(FeedParserState state, String title, String link, String description, String permalink) throws FeedParserException {
		logger.info("on item title:  " + title + " link: " + link + " description: " + description);
		
		items.add(new RssItem(state, title, link, description, permalink));
	}
	@Override
    public void finished() throws FeedParserException {
		logger.info("finished with feed parse channels:  " + String.valueOf(channels.size()) + " items:" + String.valueOf(items.size()));
	}

}
