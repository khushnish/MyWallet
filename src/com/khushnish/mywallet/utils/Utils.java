package com.khushnish.mywallet.utils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;

import com.khushnish.mywallet.R;

public class Utils {
	
	public static String key = "testwallet";
	
	public static void displayPrompt(String msg, String positiveMsg, String negativeMsg, 
			boolean isNegative, final FragmentActivity context, final boolean isFinish) {

		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
		alertDialog.setTitle(context.getString(R.string.app_name));
		alertDialog.setCancelable(false);
		alertDialog.setMessage(msg);
		alertDialog.setPositiveButton(positiveMsg, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				if (isFinish) {
					context.getSupportFragmentManager().popBackStack();
				}
			}
		});
		
		if ( isNegative ) {
			alertDialog.setNegativeButton(negativeMsg, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					if (isFinish) {
						context.getSupportFragmentManager().popBackStack();
					}
				}
			});
		}
		alertDialog.create().show();
	}
}