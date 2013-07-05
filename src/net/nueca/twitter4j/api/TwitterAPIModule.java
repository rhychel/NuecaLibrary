package net.nueca.twitter4j.api;

import java.io.File;

import net.nueca.imonggo.tools.Configurations;
import net.nueca.twitter4j.objects.TwitterConfigurations;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.util.Log;

public class TwitterAPIModule {
	
	public TwitterAPIModule(){
		Constants.twitter = new TwitterFactory().getInstance();
		Constants.twitter.setOAuthConsumer(TwitterConfigurations.CONSUMER_KEY, TwitterConfigurations.CONSUMER_SECRET);
	}
	
	/**
	 * Remove Token, Secret from preferences
	 */
	public void disconnectTwitter() {
		SharedPreferences.Editor editor = Constants.sharedPrefsTwitter.edit();
		editor.remove(TwitterConfigurations.PREF_ACCESS_TOKEN);
		editor.remove(TwitterConfigurations.PREF_ACCESS_TOKEN_SECRET);
		editor.commit();
	}
	
	/**
	 * check if the account is authorized
	 * @return
	 */
	public boolean isConnected() {
		return Constants.sharedPrefsTwitter.getString(TwitterConfigurations.PREF_ACCESS_TOKEN, null) != null;
	}
	
	/**
	 * Handle OAuth Callback
	 */
	public boolean handleCallback(Uri uri) {
		String verifier = uri.getQueryParameter(TwitterConfigurations.IEXTRA_OAUTH_VERIFIER);
		Log.e("handleCallback", "OK"+verifier+" URI = "+uri.toString());
		try {
        	if(Constants.twitter == null)
        		Log.e("twitter", "is null");
        	if(Constants.reqToken == null)
        		Log.e("requestToken", "is null");
            AccessToken accessToken = Constants.twitter.getOAuthAccessToken(Constants.reqToken, verifier); 
            Editor e = Constants.sharedPrefsTwitter.edit();
            e.putString(TwitterConfigurations.PREF_ACCESS_TOKEN, accessToken.getToken()); 
            e.putString(TwitterConfigurations.PREF_ACCESS_TOKEN_SECRET, accessToken.getTokenSecret()); 
            e.commit();
            return true;
        } catch (Exception e) { 
        	e.printStackTrace();
        	return false;
		}
	}
	
	/**
	 * 
	 * Create the twitter access token from the credentials we got previously.
	 * 
	 * @param oauthAccessToken
	 * @param oauthAccessTokenSecret
	 * @param twitterSharedPref
	 */
	public void connectedTwitter() {
		String oauthAccessToken = Constants.sharedPrefsTwitter.getString(TwitterConfigurations.PREF_ACCESS_TOKEN, null);
		String oauthAccessTokenSecret = Constants.sharedPrefsTwitter.getString(TwitterConfigurations.PREF_ACCESS_TOKEN_SECRET, null);
		
		// Create the twitter access token from the credentials we got previously
        AccessToken at = new AccessToken(oauthAccessToken, oauthAccessTokenSecret);

        Constants.twitter.setOAuthAccessToken(at);
	}
	
	/**
	 * 
	 * If app is not yet connected to twitter.
	 * 
	 * @param activity
	 * @param twitter
	 * @param requestToken
	 */
	public void askTwitterOAuth(Activity activity) {
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setOAuthConsumerKey(TwitterConfigurations.CONSUMER_KEY);
		configurationBuilder.setOAuthConsumerSecret(TwitterConfigurations.CONSUMER_SECRET);
		Configuration configuration = configurationBuilder.build();
		Constants.twitter = new TwitterFactory(configuration).getInstance();
		
		try {
			Constants.reqToken = Constants.twitter.getOAuthRequestToken(TwitterConfigurations.CALLBACK_URL);
			activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.reqToken.getAuthenticationURL())));
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * To upload a picture with some piece of text.
	 * 
	 * @param file The file which we want to share with our tweet
	 * @param message Message to display with picture
	 * @param twitter Instance of authorized Twitter class
	 * @throws Exception exception if any
	 */

	public void uploadPic(File file, String message) throws Exception  {
	    try{
	        StatusUpdate status = new StatusUpdate(message);
	        if(file == null)
	        	Log.e("FILE", "is null");
	        status.setMedia(file);
	        if(Constants.twitter == null)
	        	Log.e("TWITTER", "is null");
	        Constants.twitter.updateStatus(status);
	    } catch(TwitterException e) {
	    	e.printStackTrace();
	        Log.d("TAG", "Pic Upload error ");
	    }
	}
}
