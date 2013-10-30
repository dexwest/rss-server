/**
 * 
 */
package com.hosmerlake.rss.common.content.parser.xml;

import java.util.ArrayList;

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
	private FeedDom dom;
	public FeedParserListenerImpl() {
	}
	public Object getDom() {
		return dom;
	}
	@Override
    public void onFeedVersion( FeedVersion version ) throws FeedParserException {}
	@Override
    public void init() throws FeedParserException {
		logger.info("init feed parser");
		
		dom = new FeedDom(new ArrayList<RssChannel>(), new ArrayList<RssItem>());
	}

	@Override
	public void onChannel(FeedParserState state, String title, String link, String description) throws FeedParserException {
		logger.info("on channel title:  " + title + " link: " + link + " description: " + description);
		dom.getChannels().add(new RssChannel(title, link, description));
	}	
	@Override
	public void onItem(FeedParserState state, String title, String link, String description, String permalink) throws FeedParserException {
		logger.info("on item title:  " + title + " link: " + link + " description: " + description);
		
		dom.getItems().add(new RssItem(state, title, link, description, permalink));
	}
	@Override
    public void finished() throws FeedParserException {
		logger.info("finished with feed parse channels:  " + String.valueOf(dom.getChannels().size()) + " items:" + String.valueOf(dom.getItems().size()));
	}

}
