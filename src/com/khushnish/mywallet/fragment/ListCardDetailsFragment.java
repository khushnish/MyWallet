package com.khushnish.mywallet.fragment;

import com.khushnish.mywallet.MainActivity;
import com.khushnish.mywallet.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class ListCardDetailsFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_list_carddetails, null);
		((MainActivity) getActivity()).showBackButton();
		
		setHasOptionsMenu(true);
		return view;
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
}