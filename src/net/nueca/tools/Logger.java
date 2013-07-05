package net.nueca.tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;

public class Logger {
	
	private static ProgressDialog pd;
	
	/**
	 * 
	 * Popup dialog generic.
	 * 
	 * @param activity
	 * @param title
	 * @param message
	 * @param positiveText
	 * @param positive
	 * @param negativeText
	 * @param negative
	 */
	public static void popupDialog(Activity activity, String title, String message, String positiveText, DialogInterface.OnClickListener positive, 
			String negativeText, DialogInterface.OnClickListener negative) {
		AlertDialog.Builder popupDialog = new AlertDialog.Builder(activity);
		popupDialog.setTitle(title);
		popupDialog.setMessage(message);
		popupDialog.setCancelable(false);
		if(positive != null)
			popupDialog.setPositiveButton(positiveText, positive);
		if(negative != null)
			popupDialog.setNegativeButton(negativeText, negative);
		popupDialog.show();
	}
	
	/**
	 * 
	 * Popup error dialog.
	 * 
	 * @param activity
	 * @param title
	 * @param message
	 * @param closingText
	 * @param closing
	 */
	public static void popupErrorDialog(Activity activity, String title, String message, String closingText, DialogInterface.OnClickListener closing) {
		AlertDialog.Builder errorDialog = new AlertDialog.Builder(activity);
		errorDialog.setTitle(title);
		errorDialog.setMessage(message);
		errorDialog.setCancelable(false);
		if(closing != null)
			errorDialog.setNegativeButton(closingText, closing);
		errorDialog.show();
	}

	public static void progressDialog(final Activity activity, String message) {
		pd = new ProgressDialog(activity);
		pd.setMessage(message);
		pd.setCancelable(false);
		pd.setCanceledOnTouchOutside(false);
		pd.show();
	}
	
	public static void closeProgressDialog() {
		pd.dismiss();
	}
	
	public static void shouldEmail(final Activity activity, String title, String message, final String email) {
		AlertDialog.Builder alert = new AlertDialog.Builder(activity);
		alert.setTitle(title);
		alert.setMessage(message);
		alert.setCancelable(false);
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) { }
		});
		alert.setPositiveButton("Send an email", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Tools.email(activity, email);
			}
		});
		alert.show();
	}
	
	/**
	 * 
	 * Requires <uses-permission android:name="android.permission.CALL_PHONE"/> on the Manifest.
	 * 
	 * @param activity
	 * @param title
	 * @param message
	 * @param number
	 */
	public static void shouldCall(final Activity activity, String title, String message, final String number) {
		AlertDialog.Builder alert = new AlertDialog.Builder(activity);
		alert.setTitle(title);
		alert.setMessage(message);
		alert.setCancelable(false);
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) { }
		});
		alert.setPositiveButton("Call", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Tools.call(activity, number);
			}
		});
		alert.show();
	}
}
