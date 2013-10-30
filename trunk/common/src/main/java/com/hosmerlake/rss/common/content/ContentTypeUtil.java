/**
 * 
 */
package com.hosmerlake.rss.common.content;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;

/**
 * @author bfox1
 * 
 */
public class ContentTypeUtil {

	public static String[] xmlTypes;
	public static String[] jsonTypes;

	static {
		xmlTypes = new String[] {"Application/xml","text/xml"};
		jsonTypes = new String[] {"application/json"};
	}

	public static String isXML(Header[] headers) {
		return parseHeaders(headers, xmlTypes);
	}

	public static String isJson(Header[] headers) {
		return parseHeaders(headers, jsonTypes);
	}

	static private String parseHeaders(Header[] headers, String[] contentTypes) {
		if(headers == null || contentTypes == null)
			return "";
		
		for (Header header : headers) {
			if (header != null && StringUtils.equalsIgnoreCase("content-type", header.getName()) && contentTypes != null) {
				for (String type : contentTypes) {
					if (StringUtils.equalsIgnoreCase(header.getValue(), type)) {
						return header.getValue();
					}
				}
			}
		}
		return "";
	}
}
