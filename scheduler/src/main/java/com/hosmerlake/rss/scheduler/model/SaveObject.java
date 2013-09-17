/**
 * 
 */
package com.hosmerlake.rss.scheduler.model;

import java.io.ByteArrayInputStream;

/**
 * @author BFOX1
 *
 */
public interface SaveObject {
	void saveObj(ByteArrayInputStream obj, String objUid) throws ModelException; 
}
