package com.khushnish.mywallet.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.khushnish.mywallet.R;

public class AddCardDetailsFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_add_carddetails, null);
		
		RadioButton rbvisa = (RadioButton) view.findViewById(R.id.fragment_add_carddetails_rb_visa);
		final Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/Roboto-Regular.ttf");
		rbvisa.setTypeface(tf);
		return view;
	}
}