package net.nueca.twitter4j.http;

import java.io.File;

import twitter4j.Twitter;
import twitter4j.auth.RequestToken;

import net.nueca.twitter4j.api.Constants;
import net.nueca.twitter4j.api.TwitterAPIModule;
import android.app.Activity;
import android.os.AsyncTask;

public class TwitterOperations extends AsyncTask<Void, Void, Boolean> {

	public enum Type {
		LOGIN,
		POST_TWEET, 
		POST_TWEET_NOIMAGE
	}
	
	public interface TwitterResult {
		public void onTwitterPreRequest(Type type);
		public void onTwitterSuccess(Type type);
		public void onTwitterFailed();
	}
	
	private TwitterResult tr;
	private Type type;
	private Activity activity;
	private String tweetContent;
	private File imageAttach;
	private TwitterAPIModule twitterAPI;
	private Twitter twitter;
	private RequestToken reqToken;
	
	public TwitterOperations(Activity activity, Type type) {
		this.type = type;
		this.activity = activity;
	}
	
	public void setTwitterResult(TwitterResult tr) {
		this.tr = tr;
	}
	
	public void setTweetContent(String twc) {
		this.tweetContent = twc;
	}
	
	public void setImageAttach(File imgAttach) {
		this.imageAttach = imgAttach;
	}
	
	public TwitterAPIModule getTwitterAPI() {
		return twitterAPI;
	}
	
	// ADDED
	public void setTwitterAPI(TwitterAPIModule twitterAPI) {
		this.twitterAPI = twitterAPI;
	}

	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}
	

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		tr.onTwitterPreRequest(type);
	}
	
	@Override
	protected Boolean doInBackground(Void... params) {
		try {
			switch(type) {
				case LOGIN: {
					Constants.twitterAPI.askTwitterOAuth(activity);
				} break;
				case POST_TWEET: {
					Constants.twitterAPI.uploadPic(imageAttach, tweetContent);
				} break;
				case POST_TWEET_NOIMAGE: {
					Constants.twitter.updateStatus(tweetContent);
				} break;
			}
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
			tr.onTwitterSuccess(type);
		else
			tr.onTwitterFailed();
	}

}
