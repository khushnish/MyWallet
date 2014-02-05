package com.khushnish.mywallet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;

import com.khushnish.mywallet.fragment.LoginFragment;
import com.khushnish.mywallet.fragment.NewUserLoginFrgment;

public class MainActivity extends ActionBarActivity  {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final FragmentManager fragmentManager = getSupportFragmentManager();
		final FragmentTransaction transaction = fragmentManager.beginTransaction();
		
		final SharedPreferences preferences = getSharedPreferences(getString(R.string.pref_name), Context.MODE_PRIVATE);
		if ( TextUtils.isEmpty(preferences.getString(getString(R.string.pref_wallet_password), "")) ) {
			transaction.add(R.id.activity_main_frame, new NewUserLoginFrgment(), 
					NewUserLoginFrgment.class.getSimpleName());	
		} else {
			transaction.add(R.id.activity_main_frame, new LoginFragment(), 
					LoginFragment.class.getSimpleName());
		}
		
		transaction.commit();
	}
	
	public void showBackButton() {
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	public void hideBackButton() {
		getSupportActionBar().setHomeButtonEnabled(false);
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
	}
}
