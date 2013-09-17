/**
 * 
 */
package com.hosmerlake.rss.common.content.parser.xml;

import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.content.ParseException;
import com.hosmerlake.rss.common.content.parser.ContentParser;

/**
 * @author bfox1
 *
 */
@Component("default-xml-content-parser")
public class DefaultXMLContentParser implements ContentParser {

	/* (non-Javadoc)
	 * @see com.hosmerlake.rss.common.content.parser.ContentParser#parse(java.io.InputStream, java.lang.String)
	 */
	@Override
	public Object parse(InputStream responseStream, String defaultResponseEncoding) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

}
