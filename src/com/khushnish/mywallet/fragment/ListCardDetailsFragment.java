package com.khushnish.mywallet.fragment;

import com.khushnish.mywallet.MainActivity;
import com.khushnish.mywallet.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class ListCardDetailsFragment extends Fragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("Fragment", "onCreate() called");
		setRetainInstance(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.e("Fragment", "onCreateView() called");
		final View view = inflater.inflate(R.layout.fragment_list_carddetails, null);
		
		initializeComponents();
		return view;
	}
	
	private void initializeComponents() {
		((MainActivity) getActivity()).showBackButton();
		setHasOptionsMenu(true);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu_addcarddetails, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if ( item.getItemId() == R.id.menu_addcarddetails_add_details ) {
			final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
			final FragmentTransaction transaction = fragmentManager.beginTransaction();
			transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out,R.anim.left_in, R.anim.right_out);
			
			transaction.replace(R.id.activity_main_frame, new AddCardDetailsFragment(), "AddCardDetails");
			transaction.addToBackStack("AddCardDetails");
			transaction.commit();
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.e("Fragment", "onActivityCreated() called");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.e("Fragment", "onResume() called");
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.e("Fragment", "onPause() called");
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.e("Fragment", "onDestroyView() called");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e("Fragment", "onDestroy() called");
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.e("Fragment", "onAttach() called");
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		Log.e("Fragment", "onDetach() called");
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Log.e("Fragment", "onStart() called");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		Log.e("Fragment", "onStop() called");
	}
}