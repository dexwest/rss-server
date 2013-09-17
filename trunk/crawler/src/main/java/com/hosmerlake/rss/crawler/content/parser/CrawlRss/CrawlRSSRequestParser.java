/**
 * 
 */
package com.hosmerlake.rss.crawler.content.parser.CrawlRss;

import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.content.ParseException;
import com.hosmerlake.rss.common.content.parser.ContentParser;
import com.hosmerlake.rss.common.content.parser.json.DefaultJsonContentParser;
import com.hosmerlake.rss.common.content.parser.xml.DefaultXMLContentParser;

/**
 * @author bfox1
 *
 */
@Component
public class CrawlRSSRequestParser implements ContentParser {
	  private static final Log logger = LogFactory.getLog(CrawlRSSRequestParser.class);

	@Resource(name="default-json-content-parser")
	DefaultJsonContentParser jsonParser;
	
	@Resource(name="default-xml-content-parser")
	DefaultXMLContentParser xmlParser;
	
	/* (non-Javadoc)
	 * @see com.hosmerlake.rss.common.content.parser.ContentParser#parse(java.io.InputStream, java.lang.String)
	 */
	@Override
	public Object parse(InputStream respIS, String contentType) throws ParseException {
		if(StringUtils.equals("application/xml", contentType))
			return xmlParser.parse(respIS, contentType);
		else if(StringUtils.equals("application/json", contentType))
			return jsonParser.parse(respIS, contentType);
		else
			logger.warn("cannot parse inputstream do not recognize content type:  " + contentType);
			
		return null;
	}

}
