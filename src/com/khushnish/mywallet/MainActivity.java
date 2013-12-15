package com.khushnish.mywallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.khushnish.mywallet.fragment.AddCardDetailsFragment;
import com.khushnish.mywallet.fragment.ListDetailsFragment;

public class MainActivity extends ActionBarActivity  {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final FragmentManager fragmentManager = getSupportFragmentManager();
		final FragmentTransaction transaction = fragmentManager.beginTransaction();
		
		transaction.add(R.id.activity_main_frame, new ListDetailsFragment(), "ListDetails");
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
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		Log.e("Activity", "Activity : onActivityResult() called : requestCode : " + requestCode
				+", resultCode : " + resultCode);
		
		if ( requestCode == 200 ) {
			Fragment fragment = getSupportFragmentManager().
					findFragmentByTag(AddCardDetailsFragment.class.getSimpleName());
			fragment.onActivityResult(requestCode, resultCode, data);
		}
	}
}
