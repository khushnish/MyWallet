package com.khushnish.mywallet;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.khushnish.mywallet.fragment.AddDetailsFragment;
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		final FragmentManager fragmentManager = getSupportFragmentManager();
		final FragmentTransaction transaction = fragmentManager.beginTransaction();
		
		if ( item.getItemId() == R.id.action_add_details ) {
			transaction.add(R.id.activity_main_frame, new AddDetailsFragment(), "AddDetails");
			transaction.addToBackStack("AddDetails");
			transaction.commit();
		} else if ( item.getItemId() == R.id.action_notes ) {
			
		} else if ( item.getItemId() == R.id.action_settings ) {
			
		}
		return super.onOptionsItemSelected(item);
	}
}