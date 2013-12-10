package com.khushnish.mywallet.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import com.khushnish.mywallet.R;

public class AddCardDetailsFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_add_carddetails, null);
		
		final RadioButton rbvisa = (RadioButton) view.findViewById(R.id.fragment_add_carddetails_rb_visa);
		final Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/Roboto-Regular.ttf");
		rbvisa.setTypeface(tf);
		
		setHasOptionsMenu(true);
		
		return view;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu_savecarddetails, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if ( item.getItemId() == R.id.menu_save_details ) {
			saveCardDetails();
			getFragmentManager().popBackStackImmediate();
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	private void saveCardDetails() {
		final EditText edtBankName = (EditText) getView().findViewById(R.id.fragment_add_details_edt_bankname);
	}
}
