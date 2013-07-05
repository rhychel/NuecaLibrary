package net.nueca.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
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

/**
 * 
 * This is a class for request json values from a server.
 * 
 * @author rhymart
 *
 */

public class JSONRequestor extends AsyncTask<Void, String, String> {
	
	private static final String TAG = JSONRequestor.class.getSimpleName();
	
	private OnHttpRequestor ojo = null;
	private HttpsRequestProperties hsrp = null;
	private HttpRequestProperties hrp = null;
	private int module;
	
	private Context context;
	private String requestURL;
	private JSONObject jsonObj;
	private boolean isSecured = false;
	
	private boolean success = false;
	private int returnCodes;
	private RequestMethod requestMethod;
	
	public JSONRequestor() { }
	
	public JSONRequestor(Context context) {
		setContext(context);
	}
	
	public JSONRequestor(Context context, RequestMethod requestMethod) {
		setContext(context);
		setRequestMethod(requestMethod);
	}
	
	public JSONRequestor(Context context, String url, RequestMethod requestMethod) {
		setContext(context);
		setRequestMethod(requestMethod);
		setRequestURL(url);
	}
	
	public JSONRequestor(Context context, String url, boolean isSecured, RequestMethod requestMethod) {
		setContext(context);
		setRequestMethod(requestMethod);
		setSecured(isSecured);
		setRequestURL(url);
	}
	
	public void setRequestMethod(RequestMethod requestMethod) {
		this.requestMethod = requestMethod;
	}
	
	public RequestMethod getRequestMethod() {
		return requestMethod;
	}
	
	public OnHttpRequestor getOnHttpRequestor() {
		return ojo;
	}

	public void setOnHttpRequestor(OnHttpRequestor ojo) {
		this.ojo = ojo;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public String getRequestURL() {
		return requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
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
	
	public HttpsRequestProperties getHttpsRequestProperties() {
		return hsrp;
	}
	
	public void setHttpsRequestProperties(HttpsRequestProperties irp) {
		this.hsrp = irp;
	}

	public HttpRequestProperties getHttpRequestProperties() {
		return hrp;
	}
	
	public void setHttpRequestProperties(HttpRequestProperties irp) {
		this.hrp = irp;
	}

	public int getModule() {
		return module;
	}

	public void setModule(int module) {
		this.module = module;
	}

	protected HttpsURLConnection requestSecuredGET(){
		HttpsURLConnection conn = null;
		try {
			Log.e("getUrl", getRequestURL()+"");
			URL url = new URL(getRequestURL());
			conn = (HttpsURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			
			if(getHttpsRequestProperties() != null)
				getHttpsRequestProperties().requestGetAddProperty(conn);
			/*
			if(getAuthorized()){
				conn.setRequestProperty("Authorization", "Basic "+Configurations.API_AUTHENTICATION);
			}
			*/
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	protected HttpURLConnection requestGET(){
		HttpURLConnection conn = null;
		try {
			Log.e("getUrl Unsecure", getRequestURL()+"");
			URL url = new URL(getRequestURL());
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			
			if(getHttpRequestProperties() != null) {
				getHttpRequestProperties().requestGetAddProperty(conn);
			}
			// conn.setRequestProperty("Authorization", "Basic "+Configurations.API_AUTHENTICATION);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	protected HttpsURLConnection requestSecuredPOST(){
		
		HttpsURLConnection conn = null;
		try {
			URL url = new URL(getRequestURL());
			conn = (HttpsURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setFixedLengthStreamingMode(getJsonObj().toString().getBytes().length);
			conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			
			if(getHttpsRequestProperties() != null)
				getHttpsRequestProperties().requestPostAddProperty(conn);
			/*
			if(getAuthorized()){
				conn.setRequestProperty("Authorization", "Basic "+Configurations.API_AUTHENTICATION);
			}
			*/
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	protected HttpURLConnection requestPOST(){
		
		HttpURLConnection conn = null;
		try {
			URL url = new URL(getRequestURL());
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setFixedLengthStreamingMode(getJsonObj().toString().getBytes().length);
			conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			
			if(getHttpRequestProperties() != null)
				getHttpRequestProperties().requestPostAddProperty(conn);
			/*
			if(getAuthorized()){
				conn.setRequestProperty("Authorization", "Basic "+Configurations.API_AUTHENTICATION);
			}
			*/
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	protected HttpsURLConnection requestSecuredPUT(){
		HttpsURLConnection conn = null;
		try {
			Log.e("getUrl", getRequestURL()+"");
			URL url = new URL(getRequestURL());
			conn = (HttpsURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("PUT");
			conn.setDoInput(true);
			
			if(getHttpsRequestProperties() != null)
				getHttpsRequestProperties().requestPutAddProperty(conn);
			/*
			if(getAuthorized()){
				conn.setRequestProperty("Authorization", "Basic "+Configurations.API_AUTHENTICATION);
			}
			*/
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	protected HttpsURLConnection requestSecuredDELETE(){
		HttpsURLConnection conn = null;
		try {
			Log.e("getUrl", getRequestURL()+"");
			URL url = new URL(getRequestURL());
			conn = (HttpsURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("DELETE");
			conn.setDoInput(true);
			
			if(getHttpsRequestProperties() != null)
				getHttpsRequestProperties().requestDeleteAddProperty(conn);
			/*
			if(getAuthorized()){
				conn.setRequestProperty("Authorization", "Basic "+Configurations.API_AUTHENTICATION);
			}
			*/
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		try {
			ojo.preExecuteOperation(getModule());
		} catch(NullPointerException e) {
			Log.e(TAG, "ojo is not set");
		}
	}
	
	@Override
	protected String doInBackground(Void... arg) {
		
		Thread.currentThread().setName(getRequestURL());

		InputStream is = null;
		HttpURLConnection conn = null;
		HttpsURLConnection conns = null;
		String jsonString = "";
		
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
					OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
					writer.write(getJsonObj().toString());
					//clean up
					writer.flush();
					writer.close();
				} break;
				case PUT: {
					// TODO
				} break;
				case DELETE: {
					// TODO
				} break;
			}
			
			if(isSecured())
				setReturnCodes(conns.getResponseCode());
			else
				setReturnCodes(conn.getResponseCode());
			
			switch(getRequestMethod()) {
				case GET:
				case POST: {
					Log.e("getRequestMethod", "GET/POST");
					if(returnCodes == HttpStatus.SC_OK || HttpStatus.SC_CREATED == returnCodes){
						if(isSecured())
							is = conns.getInputStream();
						else
							is = conn.getInputStream();

						Log.e("getRequestMethod", "reading...");
						jsonString = readIt(is).toString();
						setSuccess(true);
					} else {
						Log.e("getRequestMethod", "failing...");
						setSuccess(false);
						JSONObject jsonResponse = new JSONObject();
						jsonResponse.put("request_success", false);
						jsonResponse.put("reason", "Return code=>"+returnCodes);
						return jsonResponse.toString();
					}
				} break;
				case DELETE:
				case PUT: {
					
				} break;
			}
			
		} catch(SocketTimeoutException e) {
			setSuccess(false);
			Log.e("SocketTimeoutException", "Here");
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
			Log.e("IOException", "Here");
			e.printStackTrace();
		} catch(JSONException e) {
			setSuccess(false);
			Log.e("JSONException", "Here");
			e.printStackTrace();
		} catch(Exception e) {
			setSuccess(false);
			e.printStackTrace();
			Log.e("Exception", "Here");
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
				jsonResponse.put("data", jsonString);
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
			if(ojo != null)
				ojo.postExecuteOperation(getModule(), result, true);
		}
		else {
			if(ojo != null)
				ojo.postExecuteOperation(getModule(), result, false);
		}
	}
	
	// Reads an InputStream and converts it to a String.
	public StringBuilder readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
		String line = "";
	    StringBuilder total = new StringBuilder();
	    
	    // Wrap a BufferedReader around the InputStream
	    BufferedReader rd = new BufferedReader(new InputStreamReader(stream), 8*1024);

	    // Read response until the end
	    while ((line = rd.readLine()) != null) { 
	        total.append(line); 
	    }
	    
	    // Return full string
	    return total;
	}

	public boolean isSecured() {
		return isSecured;
	}

	public void setSecured(boolean isSecured) {
		this.isSecured = isSecured;
	}
}
