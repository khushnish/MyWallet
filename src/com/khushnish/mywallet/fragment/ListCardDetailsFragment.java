package com.khushnish.mywallet.fragment;

import com.khushnish.mywallet.MainActivity;
import com.khushnish.mywallet.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListCardDetailsFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_list_carddetails, null);
		((MainActivity) getActivity()).showBackButton();
		return view;
	}
}