package com.hosmerlake.rss.common.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultParameters implements ControllerParameters {
	private Map<String, String> parameters;
	private List<String> parameterNames;

	public DefaultParameters() {
	    this.parameters = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
	    this.parameterNames = new ArrayList<String>();
	    this.parameterNames.addAll(Arrays.asList(new String[] { "scheme", "hostname", "path", "url", "referrer", "queryString", "userAgent", "method", "requestBody", "port" }));
	}

	/**
	 * Helper method to take an HttpServletRequest and initialize the parameters
	 * object
	 * 
	 * @param request
	 */
	public void constructParams(HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		this.putAll(compressMapToOneValuePerKey(parameterMap));

		if (!this.contains("scheme"))
			parameters.put("scheme", StringUtils.defaultString(request.getScheme(), "http"));
		if (!this.contains("hostname"))
			parameters.put("hostname", request.getServerName());
		if (!this.contains("path"))
			parameters.put("path", request.getPathTranslated());
		if (!this.contains("url"))
			parameters.put("url", request.getRequestURL().toString());
		if (!this.contains("referer"))
			parameters.put("referer", request.getHeader("referer"));
		if (!this.contains("queryString"))
			parameters.put("queryString", request.getQueryString());
		if (!this.contains("userAgent"))
			parameters.put("userAgent", request.getHeader("user-agent"));
		if (!this.contains("port"))
			parameters.put("port", String.valueOf(request.getServerPort()));
		if (!this.contains("method"))
			parameters.put("method", request.getMethod());

		if (this.get("method").equals("POST")) {
			// parameters.put("body", readRequestBody(request));
		}
	}

	@Override
	public boolean contains(String key) {
		return parameters.containsKey(key);
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
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

}
