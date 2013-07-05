package net.nueca.http;

import net.nueca.exceptions.RequestException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestResult {
	
	private JSONObject jsonResult = null;
	
	public RequestResult() { }
	
	public RequestResult(String result) {
		try {
			setJsonResult(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private JSONObject getJsonResult() {
		return jsonResult;
	}

	private void setJsonResult(String result) throws JSONException {
		this.jsonResult = new JSONObject(result);
	}
	
	/**
	 * 
	 * Returns the result data from the request.
	 * 
	 * @return
	 * @throws RequestException
	 * @throws JSONException
	 */
	public String getData() throws RequestException, JSONException {
		if(jsonResult.getBoolean("request_success"))
			return jsonResult.getString("data");
		else
			throw new RequestException("There is an error on your request. ["+jsonResult.getString("data")+"]");
	}
	
}
