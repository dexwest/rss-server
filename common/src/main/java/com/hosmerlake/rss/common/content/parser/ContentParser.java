/**
 * 
 */
package com.hosmerlake.rss.common.content.parser;

import java.io.InputStream;

import org.apache.http.Header;

import com.hosmerlake.rss.common.content.ParseException;

/**
 * @author BFOX1
 *
 */
public interface ContentParser {
	Object parse(InputStream responseStream, Header[] headers, String url) throws ParseException; 
}
