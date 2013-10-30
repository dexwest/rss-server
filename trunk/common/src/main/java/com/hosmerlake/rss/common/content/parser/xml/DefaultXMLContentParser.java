/**
 * 
 */
package com.hosmerlake.rss.common.content.parser.xml;

import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hosmerlake.rss.common.content.parser.StreamParser;
import com.hosmerlake.rss.common.exception.ParseException;

/**
 * @author bfox1
 * 
 */
public abstract class DefaultXMLContentParser implements StreamParser {
	private static final Log logger = LogFactory.getLog(DefaultXMLContentParser.class);

	protected abstract Object parserInternal(InputStream is, String source);
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hosmerlake.rss.common.content.parser.ContentParser#parse(java.io.
	 * InputStream, java.lang.String)
	 */
	@Override
	public Object parse(InputStream is, String source) throws ParseException {
		Object dom;
		try {
			dom = parserInternal(is, source);
		} catch (Exception e) {
			logger.warn("General exception occured while creating reader", e);
			throw new ParseException("failed to create XML stream reader", e);
		} finally {
		}
		return dom;
	}
}
