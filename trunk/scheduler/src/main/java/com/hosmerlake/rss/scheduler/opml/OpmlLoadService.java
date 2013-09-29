/**
 * 
 */
package com.hosmerlake.rss.scheduler.opml;

import java.io.ByteArrayInputStream;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.content.ParseException;
import com.hosmerlake.rss.common.content.parser.json.DefaultJsonContentParser;
import com.hosmerlake.rss.scheduler.model.DefaultObjectStore;
import com.hosmerlake.rss.scheduler.model.LoadObject;
import com.hosmerlake.rss.scheduler.model.ModelException;
import com.hosmerlake.rss.scheduler.model.SaveObject;

/**
 * @author bfox1
 * Service to abstract loading of 
 */

@Component("opml-load-service")
public class OpmlLoadService implements LoadObject, SaveObject {

	@Resource(name="default-json-content-parser")
	private DefaultJsonContentParser parser;
	
	@Resource(name="default-object-store")
	private DefaultObjectStore objStore;

    @Value("${opml.root.object.classname}")
    private String opmlRootClassName;
    
	@PostConstruct
	public void init() {
		getParser().setResultClassName(opmlRootClassName);
	}
	@Override
	public Object loadObj(String objUid) throws ModelException, ParseException {
		return getParser().parse(objStore.loadObj(objStore.getObjectFileName(objUid, DefaultObjectStore.OBJ_TYPE.JSON)), null, null);
	}
	@Override
	public void saveObj(ByteArrayInputStream obj, String objUid) throws ModelException {
		objStore.saveObj(obj, objStore.getObjectFileName(objUid, DefaultObjectStore.OBJ_TYPE.JSON));
	}

	public DefaultJsonContentParser getParser() {
		return parser;
	}

	public void setParser(DefaultJsonContentParser parser) {
		this.parser = parser;
	}
}
