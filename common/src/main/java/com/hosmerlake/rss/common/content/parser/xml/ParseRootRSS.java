/**
 * 
 */
package com.hosmerlake.rss.common.content.parser.xml;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hosmerlake.rss.common.content.ParseException;

/**
 * @author bfox1
 * 
 */
public class ParseRootRSS extends DefaultParseRSS {
	private static final Log logger = LogFactory.getLog(ParseRootRSS.class);

	private static final String RSS_NODE = "rss";

	public ParseRootRSS(XMLEventReader xmlReader) {
		super(xmlReader);
	}

	public Object getResult() throws ParseException {
		try {
			parseNode();
			return getResult();
		} catch (XMLStreamException xse) {
			logger.warn("Failed to get root object", xse);
			throw new ParseException("Failed to get root object", xse);
		}
	}

	@Override
	boolean parseNode() throws XMLStreamException {

		while (getReader().hasNext()) {
			XMLEvent event = getReader().nextEvent();

			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				if (!StringUtils.equals(startElement.getName().getLocalPart(),RSS_NODE)) {
					logger.warn("expected <rss> but got:  " + startElement.getName().getLocalPart());
					break;
				}
			}
		}
		return false;
	}

}
