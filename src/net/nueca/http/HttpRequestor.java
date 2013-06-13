package net.nueca.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import net.nueca.imonggo.enums.Modules;
import net.nueca.imonggo.interfaces.OnHttpRequestor;
import net.nueca.nuecalibrary.R;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class HttpRequestor extends AsyncTask<Void, String, String> {

	private static final String TAG = HttpRequestor.class.getSimpleName();
	
	private RequestMethod requestMethod;
	private String requestUrl = "";
	private Context context;
	private HttpsRequestProperties hsrp = null;
	private HttpRequestProperties hrp = null;
	private OnHttpRequestor ohr;
	private Modules module;
	
	private boolean success = false;
	private boolean isSecured = false;
	private int returnCodes;
	
	private String parameters;
	
	public HttpRequestor() { }
	
	public HttpRequestor(Context context) {
		setContext(context);
	}
	
	public HttpRequestor(Context context, RequestMethod requestMethod) {
		setContext(context);
		setRequestMethod(requestMethod);
	}
	
	public HttpRequestor(Context context, String url, RequestMethod requestMethod) {
		setContext(context);
		setRequestUrl(url);
		setRequestMethod(requestMethod);
	}
	
	public HttpRequestor(Context context, String url, boolean isSecured, RequestMethod requestMethod) {
		setContext(context);
		setRequestMethod(requestMethod);
		setIsSecured(isSecured);
		setRequestUrl(url);
	}
	
	public HttpRequestor(Context context, String url, boolean isSecured, RequestMethod requestMethod, Modules module) {
		setContext(context);
		setRequestMethod(requestMethod);
		setIsSecured(isSecured);
		setRequestUrl(url);
		setModule(module);
	}
	
	public void setIsSecured(boolean isSecured) {
		this.isSecured = isSecured;
	}
	
	public boolean isSecured() {
		return isSecured;
	}
	
	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public RequestMethod getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(RequestMethod requestMethod) {
		this.requestMethod = requestMethod;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getReturnCodes() {
		return returnCodes;
	}

	public void setReturnCodes(int returnCodes) {
		this.returnCodes = returnCodes;
	}

	public String getParameters() {
		return parameters;
	}

	/**
	 * With format of <b>&[key]=[value]</b>
	 * @param parameters
	 */
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public Modules getModule() {
		return module;
	}

	public void setModule(Modules module) {
		this.module = module;
	}

	private HttpURLConnection requestGET() {
		HttpURLConnection conn = null;
		try {
			URL url = new URL(getRequestUrl());
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(15000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("GET");
			
			if(getHttpRequestProperties() != null)
				getHttpRequestProperties().requestGetAddProperty(conn);
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	private HttpsURLConnection requestSecuredGET() {
		HttpsURLConnection conn = null;
		try {
			URL url = new URL(getRequestUrl());
			conn = (HttpsURLConnection) url.openConnection();
			conn.setReadTimeout(15000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			
			if(getHttpsRequestProperties() != null)
				getHttpsRequestProperties().requestGetAddProperty(conn);
			
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	private HttpURLConnection requestPOST() {
		HttpURLConnection conn = null;
		try {
			URL url = new URL(getRequestUrl());
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(15000);
			conn.setConnectTimeout(15000);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", String.valueOf(getParameters().getBytes().length));
			
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	private HttpsURLConnection requestSecuredPOST() {
		HttpsURLConnection conn = null;
		try {
			URL url = new URL(getRequestUrl());
			conn = (HttpsURLConnection) url.openConnection();
			conn.setReadTimeout(15000);
			conn.setConnectTimeout(15000);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", String.valueOf(getParameters().getBytes().length));
			
			if(getHttpsRequestProperties() != null)
				getHttpsRequestProperties().requestPostAddProperty(conn);
			
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public OnHttpRequestor getOnHttpRequestor() {
		return ohr;
	}

	public void setOnHttpRequestor(OnHttpRequestor ohr) {
		this.ohr = ohr;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		try {
			getOnHttpRequestor().preExecuteOperation(getModule());
		} catch(NullPointerException e) {
			Log.e(TAG, "ohr is not set");
		}
	}
	
	@Override
	protected String doInBackground(Void... params) {
		String resultStr = "";
		InputStream is = null;
		HttpURLConnection conn = null;
		HttpsURLConnection conns = null;
		try {
			switch(getRequestMethod()) {
				case GET: {
					if(isSecured()) {
						conns = requestSecuredGET();
						conns.connect();
					}
					else {
						conn = requestGET();
						conn.connect();
					}
				} break;
				case POST: {
					if(isSecured()) {
						conns = requestSecuredPOST();
						conns.connect();
					}
					else {
						conn = requestPOST();
						conn.connect();
					}
					OutputStream os = conn.getOutputStream();
					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
					writer.write(getParameters());
					writer.close();
					os.close();
				} break;
				case DELETE: {
					
				} break;
				case PUT: {
					
				} break;
			}
			
			if(isSecured())
				setReturnCodes(conns.getResponseCode());
			else
				setReturnCodes(conn.getResponseCode());
			
			switch(getRequestMethod()) {
				case GET:
				case POST: {
					if(returnCodes == HttpStatus.SC_OK || HttpStatus.SC_CREATED == returnCodes){
						if(isSecured())
							is = conns.getInputStream();
						else
							is = conn.getInputStream();
						resultStr = readIt(is).toString();
						setSuccess(true);
					} else {
						setSuccess(false);
						JSONObject jsonResponse = new JSONObject();
						jsonResponse.put("request_success", false);
						jsonResponse.put("reason", "Return code => "+returnCodes);
						return jsonResponse.toString();
					}

				} break;
			}
		} catch(SocketTimeoutException e) {
			setSuccess(false);
			JSONObject jsonResponse = new JSONObject();
			try {
				jsonResponse.put("request_success", false);
				jsonResponse.put("reason", getContext().getString(R.string.connection_timedout));
			} catch(JSONException eJson) {
				eJson.printStackTrace();
			}
			return jsonResponse.toString();
		} catch (IOException e) {
			setSuccess(false);
			e.printStackTrace();
		} catch (JSONException e) {
			setSuccess(false);
			e.printStackTrace();
		} catch (Exception e) {
			setSuccess(false);
			e.printStackTrace();
		} finally {
			if(isSecured())
				conns.disconnect();
			else
				conn.disconnect();
			if(is != null)
				try {
					is.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
		}
		
		JSONObject jsonResponse = new JSONObject();
		try {
			if(isSuccess()) {
				jsonResponse.put("request_success", true);
				jsonResponse.put("data", resultStr);
			}
			else {
				jsonResponse.put("request_success", false);
				jsonResponse.put("data", "ERROR");
			}
		} catch(JSONException eJson) {
			eJson.printStackTrace();
		}
		
		return jsonResponse.toString();
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if(isSuccess()) {
			if(getOnHttpRequestor() != null)
				getOnHttpRequestor().postExecuteOperation(getModule(), result, true);
		}
		else {
			if(getOnHttpRequestor() != null)
				getOnHttpRequestor().postExecuteOperation(getModule(), result, false);
		}
	}
	
	public StringBuilder readIt(InputStream stream) {
		String line = "";
	    StringBuilder total = new StringBuilder();
	 
	    BufferedReader rd = new BufferedReader(new InputStreamReader(stream));
	    try {
		    while ((line = rd.readLine()) != null) { 
		        total.append(line); 
		    }
	    } catch(Exception e) {
			e.printStackTrace();
	    }
	    return total;
	}

	public HttpsRequestProperties getHttpsRequestProperties() {
		return hsrp;
	}

	public void setHttpsRequestProperties(HttpsRequestProperties hsrp) {
		this.hsrp = hsrp;
	}

	public HttpRequestProperties getHttpRequestProperties() {
		return hrp;
	}

	public void setHttpRequestProperties(HttpRequestProperties hrp) {
		this.hrp = hrp;
	}
	
}
