/**
 * 
 */
package com.hosmerlake.rss.scheduler.model;

import com.hosmerlake.rss.common.content.ParseException;

/**
 * @author BFOX1
 *
 */
public interface LoadObject {

	Object loadObj(String objUid) throws ModelException, ParseException; 
}
