package com.hosmerlake.rss.scheduler.connection;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;

/***
 * 
 * @author bfox1
 *
 */
public class RssConnection implements Serializable {
	private static final long serialVersionUID = 8298881502561109375L;

	private String hashCode;
	private Date modDate;
	private String etag;
	private URL url;
	public String getHashCode() {
		return hashCode;
	}
	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}
	public Date getModDate() {
		return modDate;
	}
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}
	public String getEtag() {
		return etag;
	}
	public void setEtag(String etag) {
		this.etag = etag;
	}
	public URL getUrl() {
		return url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}
}
