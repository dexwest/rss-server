/**
 * 
 */
package com.hosmerlake.rss.common.service;

import com.hosmerlake.rss.common.controller.DefaultParameters;
import com.hosmerlake.rss.common.exception.ContentRequestException;

/**
 * @author BFOX1
 *
 */
public abstract class RemoteService {
	abstract public String getUrl() throws ContentRequestException;
	abstract public DefaultParameters getParams();
}
