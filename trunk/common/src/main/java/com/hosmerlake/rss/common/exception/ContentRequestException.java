/**
 * 
 */
package com.hosmerlake.rss.common.exception;

/**
 * @author BFOX1
 *
 */
public class ContentRequestException extends Exception {
	private static final long serialVersionUID = -9018723141682653413L;
	private String StatusCode;
	/**
	 * 
	 */
	public ContentRequestException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public ContentRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ContentRequestException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ContentRequestException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public String getStatusCode() {
		return StatusCode;
	}

	public void setStatusCode(String statusCode) {
		StatusCode = statusCode;
	}

}
