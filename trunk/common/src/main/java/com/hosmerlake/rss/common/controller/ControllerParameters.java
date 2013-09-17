/**
 * 
 */
package com.hosmerlake.rss.common.controller;

import java.util.Map;

/**
 * @author bfox1
 * 
 */
public interface ControllerParameters {
	/**
	 * Returns a request parameters formatted as a string
	 * 
	 * @return String a representation of the parameters.
	 */
	public String toString();

	/**
	 * Get a single parameter value
	 * 
	 * @param key
	 *            the lookup key
	 * @return String the parameter value for the specified key
	 */
	public String get(String parameterName);

	/**
	 * Get a parameter value based on name. If parameter doesn't exist, we use
	 * the specified default value
	 * 
	 * @param parameterName
	 *            The parameter name to retrieve
	 * @param defaultValue
	 *            the default value if lookup fails
	 * @return String the parameter value
	 */
	public String get(String parameterName, String defaultValue);

	/**
	 * Return all parameters in a map form;
	 * 
	 * @return
	 */
	public Map<String, String> getAll();

	public void putAll(Map<String, String> params);

	public String[] getPropertyNames();

	boolean contains(String key);
}
