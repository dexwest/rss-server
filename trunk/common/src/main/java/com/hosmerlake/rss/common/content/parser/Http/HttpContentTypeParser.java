/**
 * 
 */
package com.hosmerlake.rss.common.content.parser.Http;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.content.ContentTypeUtil;
import com.hosmerlake.rss.common.content.parser.ContentTypeParser;
import com.hosmerlake.rss.common.content.parser.StreamParser;
import com.hosmerlake.rss.common.content.parser.html.HtmlContentParser;
import com.hosmerlake.rss.common.content.parser.json.DefaultJsonContentParser;
import com.hosmerlake.rss.common.content.parser.xml.DefaultXMLContentParser;

/**
 * @author BFOX1
 *
 */
@Component("http-content-type-parser")
public class HttpContentTypeParser implements ContentTypeParser {
	private static final Log logger = LogFactory.getLog(HttpContentTypeParser.class);

	@Resource(name = "default-json-content-parser")
	DefaultJsonContentParser jsonParser;

	@Resource(name = "rss-parser")
	DefaultXMLContentParser xmlParser;

	@Resource(name= "html-parser")
	HtmlContentParser htmlParser;

	/* (non-Javadoc)
	 * @see com.hosmerlake.rss.common.content.parser.ContentParser#parse(org.apache.http.Header[])
	 */
	@Override
	public StreamParser getStreamParser(Header[] headers, String source) {
		if (StringUtils.isNotBlank(ContentTypeUtil.isXML(headers)))
			return xmlParser;
		else if (StringUtils.isNotBlank(ContentTypeUtil.isJson(headers)))
			return jsonParser;
		logger.warn("cannot parse inputstream do not recognize content type:  " + StringUtils.defaultString(headers.toString(), "no headers")+" for url: " + source);
		return htmlParser;
	}
}
