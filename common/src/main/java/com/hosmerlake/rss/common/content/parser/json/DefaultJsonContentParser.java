package com.hosmerlake.rss.common.content.parser.json;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.content.ParseException;
import com.hosmerlake.rss.common.content.parser.ContentParser;

@Component("default-json-content-parser")
public class DefaultJsonContentParser implements ContentParser {
	private static final Log logger = LogFactory.getLog(DefaultJsonContentParser.class);

    private String resultClassName = null;

    @Value("${object.filename.pattern}")
    private String objectFilenamePattern;

    @Override
	public Object parse(InputStream data, String contentEncoding) throws ParseException {
        Reader readerData;
        if (StringUtils.isNotEmpty(contentEncoding))
        {
          try
          {
            readerData = new InputStreamReader(data, contentEncoding);
          }
          catch (UnsupportedEncodingException e)
          {
            if (logger.isWarnEnabled())
            {
              logger.warn("Invalid content encoding specified: "+contentEncoding+". The system default will be used.");
            }
            readerData = new InputStreamReader(data);
          }

        }
        else
        {
          if (logger.isWarnEnabled())
          {
            logger.warn("No content encoding specified. The system default will be used.");
          }
          readerData = new InputStreamReader(data);
        }
        
        return parseInternal(readerData);
	}
    
    @SuppressWarnings({"unchecked", "rawtypes"})
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
    public String getObjectName(String objUid) {
    	return String.format(objectFilenamePattern, objUid);
    }

    public String getResultClassName() {
        return resultClassName;
    }

    public void setResultClassName(String resultClassName) {
        this.resultClassName = resultClassName;
    }
}


