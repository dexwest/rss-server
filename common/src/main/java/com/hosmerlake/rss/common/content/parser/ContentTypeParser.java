/**
 * 
 */
package com.hosmerlake.rss.common.content.parser;

import org.apache.http.Header;

/**
 * @author BFOX1
 *
 */
public interface ContentTypeParser {
	StreamParser getStreamParser(Header[] headers, String source); 
}
