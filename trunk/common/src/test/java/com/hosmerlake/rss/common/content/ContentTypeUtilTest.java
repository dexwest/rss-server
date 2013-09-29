package com.hosmerlake.rss.common.content;

import static org.junit.Assert.*;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.junit.Before;
import org.junit.Test;

public class ContentTypeUtilTest {

	private Header[] xmltestHeaders;
	private Header[] jsontestHeaders;

	@Before
	public void setUp() throws Exception {
		xmltestHeaders = new Header[] {new BasicHeader("content-type","text/xml")};
		jsontestHeaders = new Header[] {new BasicHeader("content-type","application/json")};
	}

	@Test
	public void test() {
		assertEquals(ContentTypeUtil.isJson(xmltestHeaders), "");
		assertEquals(ContentTypeUtil.isXML(jsontestHeaders), "");

		assertEquals(ContentTypeUtil.isXML(xmltestHeaders), "text/xml");
		assertEquals(ContentTypeUtil.isJson(jsontestHeaders), "application/json");
	}

}
