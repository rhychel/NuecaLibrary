package net.nueca.twitter4j.api;

import net.nueca.nuecalibrary.R;
import net.nueca.twitter4j.api.TwitterOAuthDialog.TwitterWebViewClient.OnOAuthResponded;
import net.nueca.twitter4j.objects.TwitterConfigurations;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TwitterOAuthDialog extends DialogFragment {

	private static boolean isTwitterAuthCancelled = false;
	
	private WebView webViewOauth;
	private OnOAuthResponded oor;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isTwitterAuthCancelled = false;
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	isTwitterAuthCancelled = true;
    	Log.e("onDestroy", "CALLED");
    }
    
    public void setOnOAuthResponded(OnOAuthResponded oor) {
    	this.oor = oor;
    }
    
    public static class TwitterWebViewClient extends WebViewClient {
    	
    	public interface OnOAuthResponded {
    		public void hasAuthenticated(Uri uri);
    	}

    	private OnOAuthResponded oor;
    	
    	public void setOnOAuthResponded(OnOAuthResponded oor) {
        	this.oor = oor;
        }
    	
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //check if the login was successful and the access token returned
            //this test depend of your API
        	Log.e("URL", url);
        	
        	Uri uri = Uri.parse(url);
        	if (uri != null && uri.toString().startsWith(TwitterConfigurations.CALLBACK_URL)) {
    			
        		if(isTwitterAuthCancelled)
        			return true;
        		
        		oor.hasAuthenticated(uri);
        		return true;
            }
            return false;
        }
    }
 
    /*private void saveAccessToken(String url) {
        // extract the token if it exists
        String paths[] = url.split("access_token=");
        if (paths.length > 1) {
            ApplicationData.getInstance().setAccessToken(paths[1]);
            final SharedPreferences sharedPreferences = getActivity()
                    .getSharedPreferences(Consts.SHARED_PREFS_NAME, 0);
            final Editor edit = sharedPreferences.edit();
            edit.putString(Consts.KEY_ACCESS_TOKEN, paths[1]);
            edit.putBoolean(Consts.KEY_IS_LOGGED_OUT, false);
            edit.commit();
 
            Intent intent2 = new Intent(getActivity(), WallScreenActivity.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            return;
        }
    }
    */
 
    private class GetTwitterLoad extends AsyncTask<Void, Void, Uri> {

		@Override
		protected Uri doInBackground(Void... params) {
			ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
			configurationBuilder.setOAuthConsumerKey(TwitterConfigurations.CONSUMER_KEY);
			configurationBuilder.setOAuthConsumerSecret(TwitterConfigurations.CONSUMER_SECRET);
			Configuration configuration = configurationBuilder.build();
			Constants.twitter = new TwitterFactory(configuration).getInstance();
			
			try {
				Constants.reqToken = Constants.twitter.getOAuthRequestToken(TwitterConfigurations.CALLBACK_URL);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return Uri.parse(Constants.reqToken.getAuthenticationURL());
		}
    	
		@Override
		protected void onPostExecute(Uri result) {
			super.onPostExecute(result);
			//load the url of the oAuth login page
            webViewOauth.loadUrl(result.toString());
            //set the web client
            TwitterWebViewClient twvc = new TwitterWebViewClient();
            twvc.setOnOAuthResponded(oor);
            webViewOauth.setWebViewClient(twvc);
            //activates JavaScript (just in case)
            WebSettings webSettings = webViewOauth.getSettings();
            webSettings.setJavaScriptEnabled(true);
		}
    }
    
    @Override
    public void onViewCreated(View arg0, Bundle arg1) {
        super.onViewCreated(arg0, arg1);
        (new GetTwitterLoad()).execute();
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        //Retrieve the webview
        View v = inflater.inflate(R.layout.twitter_api, container, false);
        webViewOauth = (WebView) v.findViewById(R.id.wvTwitterOAuth);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return v;
    }
}
