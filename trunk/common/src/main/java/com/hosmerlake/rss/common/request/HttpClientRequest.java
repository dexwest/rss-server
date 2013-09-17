/**
 * 
 */
package com.hosmerlake.rss.common.request;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.springframework.stereotype.Component;

import com.hosmerlake.rss.common.content.DefaultContentRequest;
import com.hosmerlake.rss.common.content.parser.ContentParser;
import com.hosmerlake.rss.common.content.response.HttpClientResponseHandler;
import com.hosmerlake.rss.common.exception.ContentRequestException;

/**
 * @author BFOX1
 * 
 */
@Component("http-client-request")
public class HttpClientRequest {
	private static final Log logger = LogFactory.getLog(HttpClientRequest.class);

	private HttpClient httpClient;

	@Resource(name="http-client-request-config")
	HttpClientRequestConfig config;
	
	
	@PostConstruct
	public void init(){
		
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
	public Object doRequest(DefaultContentRequest<?> request, ContentParser parser) throws ContentRequestException {
		Object response;

		try {
			HttpRequestBase method = makeGetMethod(request);
			HttpClientResponseHandler handler = new HttpClientResponseHandler(request, parser);
		    response = httpClient.execute(method, handler);
		} catch (ClientProtocolException cpe) {
			throw new ContentRequestException("invalid http protocol",cpe);
		} catch (IOException ioe) {
			throw new ContentRequestException("failed to request content", ioe);
		}

		return response;
	}

	private HttpGet makeGetMethod(DefaultContentRequest<?> request) throws ContentRequestException {
		if (request != null) {
			String builtUrl = request.getService().getUrl();
			if (StringUtils.isNotEmpty(builtUrl)) {
				if (request.getOutgoingParameters() != null) {
					return new HttpGet(builtUrl + "?" + request.getOutgoingParameters());
				} else {
					return new HttpGet(builtUrl);
				}
			}
		}
		return null;
	}

	public HttpClient getHttpClient() {
		return httpClient;
	}

	@Resource(name="default-http-client")
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

}
