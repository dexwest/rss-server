/**
 * 
 */
package com.hosmerlake.rss.scheduler.model;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author BFOX1
 *
 */
@Component("default-object-store")
public class DefaultObjectStore implements LoadObject, SaveObject {

	public static enum OBJ_TYPE {JSON, XML};
	
	@Value("${object.store.load-Path}")
	private String objStorePath;
		
	/* (non-Javadoc)
	 * @see com.hosmerlake.rss.scheduler.model.LoadObject#loadObj()
	 */
	@Override
	public InputStream loadObj(String objUid) throws ModelException {
		try {
			File obj = openObjFile(objUid);
			return new FileInputStream(obj);
		} catch (FileNotFoundException e) {
//			throw new ModelException("File not found:  " + objStorePath + " obj UID: " + objUid, e);
		}
		return null;
	}
	@Override
	public void saveObj(ByteArrayInputStream saveFileOS, String objUid) throws ModelException {
		try {
			IOUtils.copy(saveFileOS, new FileOutputStream(openObjFile(objUid)));
		} catch (IOException e) {
			throw new ModelException("save object failed  " + objStorePath + " obj UID: " + objUid, e);
		}
	}
	public String getObjStorePath() {
		return objStorePath;
	}

	public void setObjStorePath(String objStorePath) {
		this.objStorePath = objStorePath;
	}
	private File openObjFile(String objUid) {
		if(StringUtils.isBlank(objStorePath) || StringUtils.isBlank(objUid))
			return null;
		
		File obj = FileUtils.getFile(objStorePath, objUid);
		return obj;
	}
	public String getObjectFileName(String name, OBJ_TYPE type) {
		switch (type) {
			case JSON:
				return name + ".json";
			case XML:
				return name + ".xml";
			default:
				return name;
		}
	}
}
