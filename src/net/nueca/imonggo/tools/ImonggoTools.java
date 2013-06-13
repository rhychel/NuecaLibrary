package net.nueca.imonggo.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.nueca.imonggo.enums.Modules;
import net.nueca.nuecalibrary.R;
import android.content.Context;
import android.util.Base64;

public class ImonggoTools {
	
	public static String buildAPIAuthentication(String apiToken) {
		String base = Base64.encodeToString((apiToken+":x").getBytes(), Base64.DEFAULT).trim();
		return base;
	}
	
	public static String buildAPIUrlImonggo(Context context, String accountId) {
		return String.format(context.getString(R.string.API_URL_IMONGGO), accountId);
	}
	
	public static String buildAPIUrlIRetailCloud(Context context, String accountId) {
		return String.format(context.getString(R.string.API_URL_IRETAILCLOUD), accountId);
	}
	
	public static String buildAPITokenUrl(Context context, String accountUrl, Modules module, String email, String password) {
		return String.format(context.getString(R.string.API_TOKEN_URL), accountUrl, Configurations.API_MODULES.get(module), email, password);
	}
	
	public static String buildAPIModuleURL(Context context, String apiToken, String accountUrlNoProtocol, Modules module, String params, boolean isSecured) {
		if(isSecured)
			return String.format(context.getString(R.string.API_MODULE_URL_SECURED), apiToken, accountUrlNoProtocol, Configurations.API_MODULES.get(module)+params);
		return String.format(context.getString(R.string.API_MODULE_URL), apiToken, accountUrlNoProtocol, Configurations.API_MODULES.get(module)+params);
	}
	
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
	
	public static String[] getCurrentDateTimeInvoice() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDateTime= new Date();
		String[] invoiceDate = dateFormat.format(currentDateTime).split(" ");
		return invoiceDate;
	}
	
	public static String getCurrentDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMM dd, yyyy/h:mm a");
		Date currentDateTime= new Date();
		String[] invoiceDate = dateFormat.format(currentDateTime).split("/");
		String datetime = invoiceDate[0]+" at "+invoiceDate[1];
		return datetime;
	}
}
