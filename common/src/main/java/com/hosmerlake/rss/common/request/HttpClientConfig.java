/**
 * 
 */
package com.hosmerlake.rss.common.request;

import java.util.List;
import java.util.Map;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;

/**
 * @author bfox1
 *
 */
public class HttpClientConfig {
	private boolean proxyEnabled;
	private String proxyHost;
	private int proxyPort;
	private List<String> hostsToExcludeFromProxy;
    private Map<AuthScope, Credentials> credentialsMap = null;
    private boolean credentialsEnabled = false;
    
    public String getProxyHost() {
		return proxyHost;
	}
	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}
	public int getProxyPort() {
		return proxyPort;
	}
	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}
	public List<String> getHostsToExcludeFromProxy() {
		return hostsToExcludeFromProxy;
	}
	public void setHostsToExcludeFromProxy(List<String> hostsToExcludeFromProxy) {
		this.hostsToExcludeFromProxy = hostsToExcludeFromProxy;
	}
	public Map<AuthScope, Credentials> getCredentialsMap() {
		return credentialsMap;
	}
	public void setCredentialsMap(Map<AuthScope, Credentials> credentialsMap) {
		this.credentialsMap = credentialsMap;
	}
	public boolean isCredentialsEnabled() {
		return credentialsEnabled;
	}
	public void setCredentialsEnabled(boolean credentialsEnabled) {
		this.credentialsEnabled = credentialsEnabled;
	}

	public boolean isProxyEnabled() {
		return proxyEnabled;
	}

	public void setProxyEnabled(boolean proxyEnabled) {
		this.proxyEnabled = proxyEnabled;
	}

}
