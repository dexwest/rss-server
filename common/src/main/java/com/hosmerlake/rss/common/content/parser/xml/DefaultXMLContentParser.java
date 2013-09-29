/**
 * 
 */
package com.hosmerlake.rss.common.content.parser.xml;

import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;

import com.hosmerlake.rss.common.content.ParseException;
import com.hosmerlake.rss.common.content.parser.ContentParser;

/**
 * @author bfox1
 * 
 */
public abstract class DefaultXMLContentParser implements ContentParser {
	private static final Log logger = LogFactory.getLog(DefaultXMLContentParser.class);

	protected abstract Object parserInternal(InputStream is, Header[] headers, String url);
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hosmerlake.rss.common.content.parser.ContentParser#parse(java.io.
	 * InputStream, java.lang.String)
	 */
	@Override
	public Object parse(InputStream is, Header[] headers, String url) throws ParseException {
		Object dom;
		try {
			dom = parserInternal(is, headers, url);
		} catch (Exception e) {
			logger.warn("General exception occured while creating reader", e);
			throw new ParseException("failed to create XML stream reader", e);
		} finally {
		}
		return dom;
	}
}
