package net.nueca.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

public class Tools {

	/**
	 * Check if <b>executeOnExecutor</b> compatible.
	 * 
	 * @return true if the function is compatible, otherwise false then call <b>execute</b> function
	 */
	public static boolean isExecuteOnExecutorCompatible() {
		if(Build.VERSION.SDK_INT >=  Build.VERSION_CODES.HONEYCOMB)
			return true;
		return false;
	}
	
	/**
	 * 
	 * Formatted Amount
	 * 
	 * @param amount
	 * @return
	 */
	@SuppressLint("NewApi")
	public static String formattedAmount(double amount, boolean withText) {
		DecimalFormat formatter = new DecimalFormat();
		formatter.setGroupingSize(3);
		formatter.setGroupingUsed(true);
		
		DecimalFormatSymbols decimalSymbol = new DecimalFormatSymbols();
		decimalSymbol.setDecimalSeparator('.');
		decimalSymbol.setGroupingSeparator(',');
		// decimalSymbol.setCurrencySymbol(Settings.settings.get(Settings.FORMAT_UNIT).getValue());
		((DecimalFormat) formatter).setDecimalFormatSymbols(decimalSymbol);
		
		formatter.setMinimumFractionDigits(2);
		formatter.setMinimumIntegerDigits(1);
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH)
			formatter.setRoundingMode(RoundingMode.HALF_DOWN); // NOT COMPATIBLE WITH 4.0 below
		
		return (withText) ? formatter.format(amount) : String.format("P %s", formatter.format(amount));
	}
	
	/**
	 * 
	 * Save a bitmap on the sd card as <i>jpg</i>.
	 * 
	 * @param imageBitmap
	 * @param filename
	 * @param directory
	 */
	public static void saveImage(Bitmap imageBitmap, String filename, String directory) {
		String root = Environment.getExternalStorageDirectory().toString();
		File myDir = new File(root + "/" + directory);    
		myDir.mkdirs();
		/*
		Random generator = new Random();
		int n = 10000;
		n = generator.nextInt(n);
		*/
		String fname = filename +".jpg";
		File file = new File (myDir, fname);
		if (file.exists ())
			file.delete(); 
		try {
			FileOutputStream out = new FileOutputStream(file);
			imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Get the absolute path of the saved image.
	 * 
	 * @param filename
	 * @param directory
	 * @return
	 */
	public static String savedImageDirectory(String filename, String directory) {
		String root = Environment.getExternalStorageDirectory().toString();
		File myDir = new File(root + "/" + directory);
		return myDir.getAbsolutePath()+"/"+filename+".jpg";
	}
	
	/**
	 * 
	 * Extract image from an ImageView to Bitmap.
	 * 
	 * @param imageView
	 * @return
	 */
	public static Bitmap getBitmapFromImageview(ImageView imageView) {
		imageView.buildDrawingCache();
		return imageView.getDrawingCache();
	}
	
	/**
	 * This method converts dp unit to equivalent pixels, depending on device density. 
	 * 
	 * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
	 * @param context Context to get resources and device specific display metrics
	 * @return A float value to represent px equivalent to dp depending on device density
	 */
	public static float convertDpToPixel(float dp, Context context){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    float px = dp * (metrics.densityDpi / 160f);
	    return px;
	}

	/**
	 * This method converts device specific pixels to density independent pixels.
	 * 
	 * @param px A value in px (pixels) unit. Which we need to convert into db
	 * @param context Context to get resources and device specific display metrics
	 * @return A float value to represent dp equivalent to px value
	 */
	public static float convertPixelsToDp(float px, Context context){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    float dp = px / (metrics.densityDpi / 160f);
	    return dp;
	}
	
	public static void call(Activity activity, String contact_number) {
	    try {
	        Intent callIntent = new Intent(Intent.ACTION_CALL);
	        callIntent.setData(Uri.parse("tel:"+contact_number));
	        activity.startActivity(callIntent);
	    } catch (ActivityNotFoundException activityException) {
	         Log.e("helloandroid dialing example", "Call failed", activityException);
	    }
	}
	
	public static void email(Activity activity, String email) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
		intent.putExtra(Intent.EXTRA_SUBJECT, "");
		intent.putExtra(Intent.EXTRA_TEXT, "");
		intent.setType("message/rfc822");
		Intent mailer = Intent.createChooser(intent, "Send mail...");
		activity.startActivity(mailer);
	}
}
