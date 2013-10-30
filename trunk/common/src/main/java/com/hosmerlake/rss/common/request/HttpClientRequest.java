/**
 * 
 */
package com.hosmerlake.rss.common.request;

import java.io.IOException;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpHost;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthScope;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.conn.ProxySelectorRoutePlanner;
import org.apache.http.protocol.HttpContext;

import com.hosmerlake.rss.common.content.DefaultContentRequest;
import com.hosmerlake.rss.common.content.parser.ContentTypeParser;
import com.hosmerlake.rss.common.content.response.HttpClientResponseHandler;
import com.hosmerlake.rss.common.exception.ContentRequestException;

/**
 * @author BFOX1
 * 
 */
public class HttpClientRequest {
	private static final Log logger = LogFactory.getLog(HttpClientRequest.class);

	// Base http Client impl
	private HttpClient httpClient;
	
	@Resource(name="http-client-config")
	private HttpClientConfig config;

	@Resource(name="http-content-type-parser")
	private ContentTypeParser parser;
	
	@PostConstruct
	public void init() {
		if (config != null) {
			// Setup proxy settings
			setupHttpClientProxy(config.getProxyHost(), config.getProxyPort(), config.getHostsToExcludeFromProxy());

			// Setup authentication if it's been configured
			if (config.getCredentialsMap() != null && config.isCredentialsEnabled()) {
				if (httpClient instanceof AbstractHttpClient)
					for (AuthScope auth : config.getCredentialsMap().keySet()) {
						((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(auth, config.getCredentialsMap().get(auth));
					}
				else
					logger.error("httpClient is not instanceof AbstractHttpClient");
			}
		}
	}

	/**
	 * handle POST and GET requests. If there is raw POST data that should be
	 * attached, we use that, otherwise, we simply take the parameter object and
	 * convert them to postdata key/val pairs.
	 * 
	 * 
	 * @param request
	 *            a content request, containing the URL to call and the
	 *            parameters to pass along
	 * @throws ContentRequestException
	 * @throws IOException
	 */
	public Object doRequest(DefaultContentRequest<?> request) throws ContentRequestException {
		Object response;

		try {
			HttpRequestBase method = makeGetMethod(request);
			HttpClientResponseHandler handler = new HttpClientResponseHandler(request, parser);
			response = httpClient.execute(method, handler);
		} catch (ClientProtocolException cpe) {
			throw new ContentRequestException("invalid http protocol", cpe);
		} catch (IOException ioe) {
			throw new ContentRequestException("failed to request content", ioe);
		}

		return response;
	}

	private HttpGet makeGetMethod(DefaultContentRequest<?> request) throws ContentRequestException {
		if (request != null) {
			String builtUrl = request.getService().getUrl();
			if (StringUtils.isNotEmpty(builtUrl)) {
				if (request.getService().getParams() != null) {
					return new HttpGet(builtUrl + "?" + request.getService().getParams());
				} else {
					return new HttpGet(builtUrl);
				}
			}
		}
		return null;
	}

	/**
	 * This sets up the proxy settings based on the proxyHost, proxyPort and a
	 * list of hosts to include
	 * 
	 * @param host
	 *            the proxy host - if null, no proxy will be used
	 * @param port
	 *            the proxy port - if <= 0, no proxy will be used
	 * @param hostsToExclude
	 *            list of regexes to test against the host being requested. If
	 *            it's a match, the request will not go through the proxy,
	 *            otherwise it will. If this is null, all requests will go
	 *            through the proxy.
	 */
	private void setupHttpClientProxy(String host, int port, List<String> hostsToExclude) {
		if (StringUtils.isNotEmpty(host) && port > 0) {
			if (hostsToExclude != null) {
				// Setup list of regexes for hosts to exclude in this instance
				List<Pattern> hostsToExcludeFromProxyRegexList = new ArrayList<Pattern>();
				for (String hostExcludeRegex : hostsToExclude) {
					hostsToExcludeFromProxyRegexList.add(Pattern.compile(hostExcludeRegex, Pattern.CASE_INSENSITIVE));
				}

				SchemeRegistry registry = new SchemeRegistry();
				registry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
				registry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));

				((AbstractHttpClient) httpClient).setRoutePlanner(new ProxyRoute(registry, null, host, port, hostsToExcludeFromProxyRegexList));
			} else {
				HttpHost proxy = new HttpHost(host, port);
				httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
			}
		}
	}

	public HttpClient getHttpClient() {
		return httpClient;
	}

	@Resource(name = "default-http-client")
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public HttpClientConfig getConfig() {
		return config;
	}

	public void setConfig(HttpClientConfig config) {
		this.config = config;
	}

	static class ProxyRoute extends ProxySelectorRoutePlanner {
		List<Pattern> hostToExcludeRegexList = null;
		String proxyHostName = null;
		int proxyPort = -1;

		public ProxyRoute(SchemeRegistry schreg, ProxySelector prosel, String proxyHost, int proxyPort, List<Pattern> hostToExcludeRegexList) {
			super(schreg, prosel);
			this.hostToExcludeRegexList = hostToExcludeRegexList;
			this.proxyHostName = proxyHost;
			this.proxyPort = proxyPort;
		}

		public HttpHost determineProxy(HttpHost target, HttpRequest request, HttpContext context) throws HttpException {

			boolean useProxy = true;
			for (Pattern hostPattern : hostToExcludeRegexList) {
				// If host to exclude matches the target host, don't use the
				// proxy
				Matcher matcher = hostPattern.matcher(target.getHostName());
				if (matcher.find()) {
					useProxy = false;
					break;
				}
			}

			HttpHost proxyHost = null;
			if (useProxy) {
				proxyHost = new HttpHost(proxyHostName, proxyPort);
			}

			return proxyHost;
		}

	}

}
