package net.nueca.http;

import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

public interface HttpRequestProperties {
	public void requestGetAddProperty(HttpURLConnection conn);
	public void requestPostAddProperty(HttpURLConnection conn);
	public void requestPutAddProperty(HttpURLConnection conn);
	public void requestDeleteAddProperty(HttpURLConnection conn);
}
