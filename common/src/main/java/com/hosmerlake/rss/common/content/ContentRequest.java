/**
 * 
 */
package com.hosmerlake.rss.common.content;

import com.hosmerlake.rss.common.exception.ContentRequestException;

/**
 * @author BFOX1
 *
 */
public interface ContentRequest<E> {
	abstract E processRequest() throws ContentRequestException;
}
