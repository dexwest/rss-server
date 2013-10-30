package com.hosmerlake.rss.common.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class DefaultParameters implements ControllerParameters {
	private Map<String, String> parameters;
	private List<String> parameterNames;


	public DefaultParameters() {
		this.parameters = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
		this.parameterNames = new ArrayList<String>();
		this.parameterNames.addAll(Arrays.asList(new String[] { "scheme", "hostname", "path", "url", "referrer", "queryString", "userAgent", "method", "requestBody", "port" }));
	}

	public DefaultParameters(String jsonParams) {

	}

	/**
	 * Helper method to take an HttpServletRequest and initialize the parameters
	 * object
	 * 
	 * @param request
	 */
	public void constructParams(HttpServletRequest request) {
		Map<String, String[]> parameterMap = new HashMap<String, String[]>(request.getParameterMap());
		this.putAll(compressMapToOneValuePerKey(parameterMap));

		assignParamIfEmpty("scheme", request.getScheme(), "http");
		assignParamIfEmpty("hostname", request.getServerName(), "http");
		assignParamIfEmpty("path", request.getPathTranslated(), "http");
		assignParamIfEmpty("url", request.getRequestURL().toString(), "");
		assignParamIfEmpty("referer", request.getHeader("referer"), "");
		assignParamIfEmpty("queryString", request.getQueryString(), "");
		assignParamIfEmpty("userAgent", request.getHeader("user-agent"), "");
		assignParamIfEmpty("port", String.valueOf(request.getServerPort()), "80");
		assignParamIfEmpty("method", request.getMethod(), "");
	}
	/***
	 * Helper method to initialize Default params based on a json string of values
	 * 
	 * @param json
	 */
	public void constructParams(Map<String, String> input) {
		for(Entry<String, String> entry : input.entrySet())
			assignParamIfEmpty(entry.getKey(), entry.getValue(), null);
	}


	@Override
	public boolean contains(String key) {
		return parameters.containsKey(key);
	}

	@Override
	public String get(String key) {
		return this.parameters.get(key);
	}

	@Override
	public String get(String key, String defaultValue) {
		return StringUtils.defaultString(parameters.get(key), defaultValue);
	}

	@Override
	public Map<String, String> getAll() {
		return parameters;
	}

	@Override
	public void putAll(Map<String, String> params) {
		parameters.putAll(params);
	}

	@Override
	public String[] getPropertyNames() {
		return parameterNames.toArray(new String[parameterNames.size()]);
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	/**
	 * Static helper used to compress a map containing multiple values into a
	 * single <String,String> map. First one wins.
	 * 
	 * @param parameters
	 *            the map to flatten Map<String,String[]>
	 * @return a flattened map<String,String>
	 */
	public static Map<String, String> compressMapToOneValuePerKey(final Map<String, String[]> parameters) {
		if (parameters == null || parameters.entrySet() == null) {
			return null;
		}

		final Map<String, String> compressedParams = new HashMap<String, String>();
		for (Map.Entry<String, String[]> param : parameters.entrySet()) {
			String[] values = param.getValue();
			if (values != null && values.length > 0 && values[0] != null && !StringUtils.isEmpty(values[0])) {
				compressedParams.put(param.getKey(), values[0]);
			}
		}
		return compressedParams;
	} 

	private void assignParamIfEmpty(String paramName, String paramValue, String defaultValue) {
		if (!this.contains(paramName))
			parameters.put(paramName, StringUtils.defaultString(paramValue, defaultValue));
	}

}
