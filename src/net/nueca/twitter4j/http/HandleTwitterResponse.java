package net.nueca.twitter4j.http;

import net.nueca.twitter4j.objects.TwitterConfigurations;
import twitter4j.Twitter;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.AsyncTask;

public class HandleTwitterResponse extends AsyncTask<Void, Void, Boolean> {

	public interface HandledResponse {
		public void onHandlingSuccess();
		public void onHandlingFailed();
	}
	
	private HandledResponse hr;
	
	private Intent intent;
	private Twitter twitter;
	private SharedPreferences prefTwitter;
	private RequestToken requiredToken;
	
	public HandleTwitterResponse(SharedPreferences prefTwitter, Intent intent) {
		this.intent = intent;
		this.prefTwitter = prefTwitter;
	}
	
	public void setRequiredToken(RequestToken requiredToken) {
		this.requiredToken = requiredToken;
	}
	
	public void setHandledResponse(HandledResponse hr) {
		this.hr = hr;
	}
	
	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}
	
	private void dealWithTwitterResponse(Intent intent) throws Exception {
		Uri uri = intent.getData();
		if (uri != null && uri.toString().startsWith(TwitterConfigurations.CALLBACK_URL)) { // If the user has just logged in
			String oauthVerifier = uri.getQueryParameter("oauth_verifier");
			authoriseNewUser(oauthVerifier);
		}
	}
	
	private void authoriseNewUser(String oauthVerifier) throws Exception {
	    AccessToken at = twitter.getOAuthAccessToken(requiredToken, oauthVerifier);
	    twitter.setOAuthAccessToken(at);
	
	    saveAccessToken(at);
    }
	
	private void saveAccessToken(AccessToken at) {
        String token = at.getToken();
        String secret = at.getTokenSecret();
        Editor editor = prefTwitter.edit();
        editor.putString(TwitterConfigurations.PREF_ACCESS_TOKEN, token);
        editor.putString(TwitterConfigurations.PREF_ACCESS_TOKEN_SECRET, secret);
        editor.commit();
	}
	
	@Override
	protected Boolean doInBackground(Void... params) {
		try {
			dealWithTwitterResponse(intent);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		if(result)
			hr.onHandlingSuccess();
		else
			hr.onHandlingFailed();
	}

}
