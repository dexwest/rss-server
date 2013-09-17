/**
 * 
 */
package com.hosmerlake.rss.common.content.parser;

import java.io.InputStream;

import com.hosmerlake.rss.common.content.ParseException;

/**
 * @author BFOX1
 *
 */
public interface ContentParser {
	Object parse(InputStream responseStream, String contentType) throws ParseException; 
}
