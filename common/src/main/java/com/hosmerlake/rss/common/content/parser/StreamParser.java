package com.hosmerlake.rss.common.content.parser;

import java.io.InputStream;

import com.hosmerlake.rss.common.exception.ParseException;

public interface StreamParser {
	Object parse(InputStream responseStream, String source) throws ParseException; 

}
