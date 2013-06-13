package net.nueca.http;

import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

public interface HttpsRequestProperties {
	public void requestGetAddProperty(HttpsURLConnection conn);
	public void requestPostAddProperty(HttpsURLConnection conn);
	public void requestPutAddProperty(HttpsURLConnection conn);
	public void requestDeleteAddProperty(HttpsURLConnection conn);
}
