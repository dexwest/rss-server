package com.hosmerlake.rss.common.content.parser.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.hosmerlake.rss.common.content.ParseException;

public abstract class DefaultParseRSS {
	private XMLEventReader reader;
	private List<DefaultParseRSS> children;
	private Object result;

	abstract boolean parseNode() throws XMLStreamException;
	
	public DefaultParseRSS(XMLEventReader xmlReader) {
		reader = xmlReader;
		children = new ArrayList<DefaultParseRSS>();
	}

	public XMLEventReader getReader() {
		return reader;
	}

	public void setReader(XMLEventReader reader) {
		this.reader = reader;
	}

	public List<DefaultParseRSS> getChildren() {
		return children;
	}

	public void setChildren(List<DefaultParseRSS> children) {
		this.children = children;
	}

	public Object getResult() throws ParseException {
		return result;
	}

	protected void setResult(Object result) {
		this.result = result;
	}
}
