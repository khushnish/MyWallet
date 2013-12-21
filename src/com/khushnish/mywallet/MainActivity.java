package com.khushnish.mywallet;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.khushnish.mywallet.fragment.ListDetailsFragment;

public class MainActivity extends ActionBarActivity  {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final FragmentManager fragmentManager = getSupportFragmentManager();
		final FragmentTransaction transaction = fragmentManager.beginTransaction();
		
		transaction.add(R.id.activity_main_frame, new ListDetailsFragment(), 
				ListDetailsFragment.class.getSimpleName());
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
