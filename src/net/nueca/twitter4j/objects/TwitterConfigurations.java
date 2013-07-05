package net.nueca.twitter4j.objects;

public class TwitterConfigurations {
	/** Name to store the users access token */
    public static final String PREF_ACCESS_TOKEN = "oauth_token";
    /** Name to store the users access token secret */
    public static final String PREF_ACCESS_TOKEN_SECRET = "oauth_token_secret";
    /** Consumer Key generated when you registered your app at https://dev.twitter.com/apps/ */
    public static String CONSUMER_KEY = "<your consumer key>";
    /** Consumer Secret generated when you registered your app at https://dev.twitter.com/apps/  */
    public static String CONSUMER_SECRET = "<your consumer secret>"; // XXX Encode in your app
    /** The url that Twitter will redirect to after a user log's in - this will be picked up by your app manifest and redirected into this activity */
    public static String CALLBACK_URL = "oauth://t4j<appname>";
    
    public static String TWITTER_PREFERENCES = "<your shared preference file name>";

	public static final String IEXTRA_AUTH_URL = "auth_url";
	public static final String IEXTRA_OAUTH_VERIFIER = "oauth_verifier";
	public static final String IEXTRA_OAUTH_TOKEN = "oauth_token";
}
