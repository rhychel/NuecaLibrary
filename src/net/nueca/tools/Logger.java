package net.nueca.tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Logger {
	
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
	
	public static void popupErrorDialog(Activity activity, String title, String message, String closingText, DialogInterface.OnClickListener closing) {
		AlertDialog.Builder errorDialog = new AlertDialog.Builder(activity);
		errorDialog.setTitle(title);
		errorDialog.setMessage(message);
		errorDialog.setCancelable(false);
		if(closing != null)
			errorDialog.setNegativeButton(closingText, closing);
		errorDialog.show();
	}

}
