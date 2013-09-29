package com.hosmerlake.rss.common.content.parser.json;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.content.ContentTypeUtil;
import com.hosmerlake.rss.common.content.ParseException;
import com.hosmerlake.rss.common.content.parser.ContentParser;

@Component("default-json-content-parser")
public class DefaultJsonContentParser implements ContentParser {
	private static final Log logger = LogFactory.getLog(DefaultJsonContentParser.class);

	private String resultClassName = null;

	@Override
	public Object parse(InputStream data, Header[] headers, String url) throws ParseException {
		Reader readerData;
		String type = ContentTypeUtil.isJson(headers);
		if (StringUtils.isBlank(type)) {
			return null;
		}
		try {
			readerData = new InputStreamReader(data, type);
		} catch (UnsupportedEncodingException e) {
			if (logger.isWarnEnabled()) {
				logger.warn("Invalid content encoding specified: " + type + ". The system default will be used.");
			}
			readerData = new InputStreamReader(data);
		}
		return parseInternal(readerData);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object parseInternal(Reader readerData) throws ParseException {
		Object ret = null;

		ObjectMapper mapper = new ObjectMapper();

		try {

			Class resultClass = ClassUtils.getClass(resultClassName);

			if (resultClass != null) {
				ret = mapper.readValue(readerData, resultClass);
			} else {
				ret = mapper.readValue(readerData, Object.class);
			}

		} catch (Exception e) {
			throw new ParseException("Unable to parse json content.", resultClassName, e);
		}

		return ret;
	}
	public String getResultClassName() {
		return resultClassName;
	}

	public void setResultClassName(String resultClassName) {
		this.resultClassName = resultClassName;
	}
}
