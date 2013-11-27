package com.khushnish.mywallet;

import android.app.Application;

import com.khushnish.mywallet.database.DatabaseHelper;

public class MyWallet extends Application {
	
	private DatabaseHelper databaseHelper;
	
	@Override
	public void onCreate() {
		super.onCreate();
		databaseHelper = new DatabaseHelper(getApplicationContext(), getString(R.string.db_name), null, 1);
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
		databaseHelper.close();
	}
	
	public DatabaseHelper getDatabaseHelper() {
		return databaseHelper;
	}
}