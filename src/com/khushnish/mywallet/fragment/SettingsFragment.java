package com.khushnish.mywallet.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.khushnish.mywallet.MainActivity;
import com.khushnish.mywallet.R;

public class SettingsFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_settings, null);
		
		setHasOptionsMenu(true);
		((MainActivity) getActivity()).showBackButton();
		
		return view;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if (item.getItemId() == android.R.id.home) {
	        getFragmentManager().popBackStack();
	        return true;
	    }
		return super.onOptionsItemSelected(item);
	}
}
