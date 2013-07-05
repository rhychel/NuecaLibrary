package net.nueca.imonggo.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.nueca.imonggo.enums.Modules;
import net.nueca.nuecalibrary.R;
import android.content.Context;
import android.util.Base64;

/**
 * 
 * ImonggoTools used to build urls, api authentications, date and time formats.
 * 
 * @author rhymart
 *
 */
public class ImonggoTools {
	
	/**
	 * 
	 * Build Base64 authentication key for the Authorization request property.
	 * 
	 * @param apiToken
	 * @return
	 */
	public static String buildAPIAuthentication(String apiToken) {
		String base = Base64.encodeToString((apiToken+":x").getBytes(), Base64.DEFAULT).trim();
		return base;
	}
	
	/**
	 * 
	 * Build URL for getting Account URL on Imonggo.
	 * 
	 * @param context
	 * @param accountId
	 * @return
	 */
	public static String buildAPIUrlImonggo(Context context, String accountId) {
		return String.format(context.getString(R.string.API_URL_IMONGGO), accountId);
	}
	
	/**
	 * 
	 * Build URL for getting Account URL on IRetail Cloud.
	 * 
	 * @param context
	 * @param accountId
	 * @return
	 */
	public static String buildAPIUrlIRetailCloud(Context context, String accountId) {
		return String.format(context.getString(R.string.API_URL_IRETAILCLOUD), accountId);
	}
	
	/**
	 * 
	 * Build URL for getting API Token.
	 * 
	 * @param context
	 * @param accountUrl
	 * @param module
	 * @param email
	 * @param password
	 * @return
	 */
	public static String buildAPITokenUrl(Context context, String accountUrl, Modules module, String email, String password) {
		return String.format(context.getString(R.string.API_TOKEN_URL), accountUrl, Configurations.API_MODULES.get(module), email, password);
	}
	
	/**
	 * 
	 * Build URL for a specific module.
	 * 
	 * @param context
	 * @param apiToken
	 * @param accountUrlNoProtocol
	 * @param module
	 * @param params
	 * @param isSecured
	 * @return
	 */
	public static String buildAPIModuleURL(Context context, String apiToken, String accountUrlNoProtocol, Modules module, String params, boolean isSecured) {
		if(isSecured)
			return String.format(context.getString(R.string.API_MODULE_URL_SECURED), apiToken, accountUrlNoProtocol, Configurations.API_MODULES.get(module)+params);
		return String.format(context.getString(R.string.API_MODULE_URL), apiToken, accountUrlNoProtocol, Configurations.API_MODULES.get(module)+params);
	}
	
	/**
	 * 
	 * Build for product image url.
	 * 
	 * @param context
	 * @param apiToken
	 * @param accountUrlNoProtocol
	 * @param productId
	 * @param isLarge
	 * @param isSecured
	 * @return
	 */
	public static String buildProductImageUrl(Context context, String apiToken, String accountUrlNoProtocol, String productId, boolean isLarge, boolean isSecured) {
		if(isSecured) {
			if(isLarge)
				return String.format(context.getString(R.string.PRODUCT_LARGEIMAGE_URL_SECURED), apiToken, accountUrlNoProtocol, productId);
			return String.format(context.getString(R.string.PRODUCT_IMAGE_URL_SECURED), apiToken, accountUrlNoProtocol, productId);
		}
		else {
			if(isLarge)
				return String.format(context.getString(R.string.PRODUCT_LARGEIMAGE_URL), apiToken, accountUrlNoProtocol, productId);
			return String.format(context.getString(R.string.PRODUCT_IMAGE_URL), apiToken, accountUrlNoProtocol, productId);
		}
	}
	
	/**
	 * 
	 * Get current date time formatted as yyyy-MM-dd HH:mm:ss splitted by space.
	 * 
	 * e.g.: 2013-06-14 11:18:20
	 * 
	 * @return string array where index 0 is the date, and index 1 is the time.
	 */
	public static String[] getCurrentDateTimeInvoice() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDateTime= new Date();
		String[] invoiceDate = dateFormat.format(currentDateTime).split(" ");
		return invoiceDate;
	}
	
	/**
	 * 
	 * Get current date time formatted as MMMMM dd, yyyy/h:mm a splitted by slash( <b>/</b> ).
	 * 
	 * e.g. June 14, 2013/11:25 AM
	 * 
	 * @return string array where index 0 is the date, and index 1 is the time.
	 */
	public static String getCurrentDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMM dd, yyyy/h:mm a");
		Date currentDateTime= new Date();
		String[] invoiceDate = dateFormat.format(currentDateTime).split("/");
		String datetime = invoiceDate[0]+" at "+invoiceDate[1];
		return datetime;
	}
	
	/**
	 * 
	 * Convert the string date time formatted as yyyy-MM-ddTHH:mm:ssZ (where T and Z are characters, not pattern) to date
	 * and return as a string formatted as defined on the second parameter.
	 * 
	 * e.g. Input: "2013-05-05T00:00:00Z" - Output: (format to 'As of May, 2013')
	 * @param dateTime
	 * @param formatForDisplay
	 * @return
	 */
	public static String convertToDate(String dateTime, String formatForDisplay) {
		String []parseDateTime = dateTime.split("T");
		String finalDateTime = parseDateTime[0]+" "+parseDateTime[1].replace("Z", "");
	    SimpleDateFormat convertStringToDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    SimpleDateFormat forDisplaying = new SimpleDateFormat(formatForDisplay);
	    try {
			Date theDate = convertStringToDate.parse(finalDateTime);
			String result = forDisplaying.format(theDate);
			return result;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
