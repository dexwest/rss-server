/**
 * 
 */
package com.hosmerlake.rss.common.content.parser.html;

import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.content.parser.StreamParser;
import com.hosmerlake.rss.common.exception.ParseException;

/**
 * @author BFOX1
 *
 */
@Component("html-parser")
public class HtmlContentParser implements StreamParser {

	@Override
	public Object parse(InputStream responseStream, String source) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

}
