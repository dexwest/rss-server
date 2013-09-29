/**
 * 
 */
package com.hosmerlake.rss.crawler.content.parser.CrawlRss;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hosmerlake.rss.crawler.CrawlerBaseSpringTest;
import com.hosmerlake.rss.crawler.model.RssContent.RssContentRoot;

/**
 * @author bfox1
 *
 */
public class CrawlRSSRequestParserTest extends CrawlerBaseSpringTest<CrawlRSSRequestParserTest> {

	
	@Autowired
	CrawlRSSRequestParser parser;
	private InputStream parseXMLStream;
	private InputStream parseJSONStream;
	private Header[] missingHeaders;
	private Header[] headersXML;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		parseXMLStream = new FileInputStream(new File("target/test-classes/RssRequestParserSampleRss.xml"));
		parseJSONStream = new FileInputStream(new File("target/test-classes/RssRequestParserSampleJSon.json"));
		missingHeaders = new Header[]{new BasicHeader("foo", "bar")};
		headersXML = new Header[]{new BasicHeader("content-type", "text/xml; charset=iso-8859-1")};
	}

	@Test
	public void test() {
		assertNotNull(parser);
		
		// basic param checks
		assertNull(parser.parse(null, null, null));
		InputStream parseStreamNull = null;
		assertNull(parser.parse(parseStreamNull, null, null));
		Header[] headersNull = null;
		assertNull(parser.parse(null, headersNull, null));
		assertNull(parser.parse(parseXMLStream, headersNull, null));
		assertNull(parser.parse(parseStreamNull, missingHeaders, null));
		
		// Valid params but bad data in params
		assertNull(parser.parse(parseXMLStream, missingHeaders, null));
		assertNotNull(parser.parse(parseJSONStream, headersXML, null));
		Object dom = parser.parse(parseJSONStream, headersXML, null);
		assertEquals(RssContentRoot.class, dom.getClass());
		assertEquals(((RssContentRoot)dom).isParsed(), false);

		// Valid params but good data in params
		assertNotNull(parser.parse(parseXMLStream, headersXML, null));
	}
}
