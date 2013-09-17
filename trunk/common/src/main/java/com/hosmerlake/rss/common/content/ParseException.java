/**
 * 
 */
package com.hosmerlake.rss.common.content;

/**
 * @author BFOX1
 *
 */
public class ParseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6081705592349328827L;
	private String expectedClassName;
	/**
	 * 
	 */
	public ParseException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public ParseException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ParseException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParseException(String message, String resultClassName, Exception e) {
		super(message, e);
		setExpectedClassName(resultClassName);
	}

	public String getExpectedClassName() {
		return expectedClassName;
	}

	public void setExpectedClassName(String expectedClassName) {
		this.expectedClassName = expectedClassName;
	}

}
