package com.hosmerlake.rss.common.content;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hosmerlake.rss.common.content.parser.ContentParser;
import com.hosmerlake.rss.common.controller.DefaultParameters;
import com.hosmerlake.rss.common.exception.ContentRequestException;
import com.hosmerlake.rss.common.request.HttpClientRequest;
import com.hosmerlake.rss.common.service.RemoteService;

public abstract class DefaultContentRequest<E> implements ContentRequest<E> {
	  private static final Log logger = LogFactory.getLog(DefaultContentRequest.class);
	
	@Resource(name = "http-client-request")
	private HttpClientRequest httpClient;

	private RemoteService service;
	private DefaultParameters outgoingParameters;
	private Object result;	
	
	private boolean canceled = false;

	public abstract E processRequest() throws ContentRequestException;
	
	protected void executeRequest(ContentParser parser) throws ContentRequestException {
	    try
	    {
	      setResult(executeRequestInternal(parser));
	    }
	    // ContentRequestException is not an extension of RuntimeException so catch it separately
	    catch(ContentRequestException cre)
	    {
	      setResult(null);
	      throw cre;
	    }
	    // We want to make sure result is null if any kind of exception is thrown 
	    catch(RuntimeException re)
	    {
	      setResult(null);
	      throw re;
	    }
	    finally
	    {
	    }
	}

	
	/**
	 * Execute the current request and return the result. Note that if a parser
	 * is defined on the request, the parser will be run before the result is
	 * set, and a parsed object will be returned.
	 * @param parser 
	 */
	protected Object executeRequestInternal(ContentParser parser) throws ContentRequestException {
		Object result = null;

		// Don't execute request if it's invalid, canceled, or already executed
		if (validateInput()) {
			result = getHttpClient().doRequest(this, parser);
			logResponseToConsole(result);
		}
		return result;
	}

	/**
	 * Check to ensure that the request and httpClient are set. Additionally,
	 * check to make sure that the request is not cancelled.
	 * 
	 * @return boolean true is request is valid
	 */
	public boolean validateInput() {

		if (getHttpClient() == null) {
			logger.error("httpClient must be set to execute request");
			return false;
		}

		if (canceled) {
			logger.debug("request is marked as cancelled.");
			return false;
		}

		return true;
	}
	private void logResponseToConsole(Object result) {
		// TODO Auto-generated method stub
		
	}

	public RemoteService getService() {
		return service;
	}

	public void setService(RemoteService service) {
		this.service = service;
	}

	public DefaultParameters getOutgoingParameters() {
		return outgoingParameters;
	}

	public void setOutgoingParameters(DefaultParameters outgoingParameters) {
		this.outgoingParameters = outgoingParameters;
	}


	public Object getResult() {
		return result;
	}


	public void setResult(Object result) {
		this.result = result;
	}

	public HttpClientRequest getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(HttpClientRequest httpClient) {
		this.httpClient = httpClient;
	}

}
