/**
 * 
 */
package com.hosmerlake.rss.common.content.response;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.CharEncoding;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import com.hosmerlake.rss.common.content.DefaultContentRequest;
import com.hosmerlake.rss.common.content.parser.ContentTypeParser;
import com.hosmerlake.rss.common.exception.ContentRequestException;

/**
 * @author BFOX1
 * 
 */
public class HttpClientResponseHandler implements ResponseHandler<Object> {
	private static final Log logger = LogFactory.getLog(HttpClientResponseHandler.class);

	private static final String DEFAULT_RESPONSE_ENCODING = CharEncoding.UTF_8;

	private DefaultContentRequest<?> contentRequest = null;
	
	private String defaultResponseEncoding = DEFAULT_RESPONSE_ENCODING;

	private ContentTypeParser parser;

	/**
	 */
	public HttpClientResponseHandler(DefaultContentRequest<?> request, ContentTypeParser parser) {
		if (request == null || parser == null) {
			throw new RuntimeException("Null content request is not allowed in Response Handler constructor");
		}
		this.parser = parser;
		this.contentRequest = request;
	}

	/**
	 * Return Apache HTTP client response after upon executing a request.
	 * 
	 * @param response
	 *            The response which resulted from an executed HTTP request.
	 * @return The parsed response or the raw response in String form if no
	 *         parser is set on the current object.
	 */
	public Object handleResponse(HttpResponse response) throws IOException {
	    Object returnValue = null;

	    try
	    {
	      // Check for non-200 response
	      handleError(response);
	      
	      if (parser != null)
	      {
	        InputStream responseStream = null;
	        
	        try
	        {
	          // Parse and close the input stream
	          responseStream = response.getEntity().getContent();
	          String url = contentRequest.getService().getUrl();
	          returnValue = parser.getStreamParser(response.getHeaders("content-type"),url).parse(responseStream, url); 
	        }
	        catch(Exception e)
	        {
	          throw new ContentRequestException("Error parsing response: "+e.getMessage(), e);
	        }
	        finally
	        {
	          ensureClosedStream(responseStream);
	        }
	      }
	  
	      else
	      {
	        if (logger.isTraceEnabled())
	        {
	          logger.trace("No parser defined; returning the String.");
	        }
	        
	        // This will get and close the stream
	        returnValue = EntityUtils.toString(response.getEntity(), this.getDefaultResponseEncoding());
	      }
	    } catch (ContentRequestException cre) {
	    	logger.warn("Second catch of error see logs above for originating error", cre);
		}
	    finally
	    {
	      /*
	       * Set the response entity to null so that the http connection is released and when the http 
	       * client tries to consume the entity, the underlying stream won't complain that it's already at EOF.
	       */
	      if (response != null)
	      {
	        response.setEntity(null);          
	      }
	    }
	    
	    return returnValue;
	}

	/**
	 * Close the input stream if it hasn't already been closed.
	 * 
	 * @param stream
	 *            the stream to close
	 */
	private void ensureClosedStream(InputStream stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				// The stream is already closed
			}
		}
	}

	/**
	 * Throw exception if the response did not return an OK status code and then
	 * close the response stream.
	 * 
	 * @param response
	 * @throws IOException
	 * @throws ContentRequestException 
	 */
	private void handleError(HttpResponse response) throws IOException, ContentRequestException {
		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			try {
				ContentRequestException e = new ContentRequestException("Http request failed with response code " + response.getStatusLine());
				e.setStatusCode(String.valueOf(response.getStatusLine().getStatusCode()));
				throw e;
			} finally {
				ensureClosedStream(response.getEntity().getContent());
			}
		}
	}

	public String getDefaultResponseEncoding() {
		return defaultResponseEncoding;
	}

	public void setDefaultResponseEncoding(String defaultResponseEncoding) {
		this.defaultResponseEncoding = defaultResponseEncoding;
	}

	public ContentTypeParser getParser() {
		return parser;
	}

	public void setParser(ContentTypeParser parser) {
		this.parser = parser;
	}
}
