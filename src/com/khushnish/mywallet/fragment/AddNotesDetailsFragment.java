package com.khushnish.mywallet.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.khushnish.mywallet.MyWallet;
import com.khushnish.mywallet.R;
import com.khushnish.mywallet.database.DatabaseHelper;
import com.khushnish.mywallet.model.NotesModel;
import com.khushnish.mywallet.utils.EditTextRoboto;

public class AddNotesDetailsFragment extends Fragment {
	
	private EditTextRoboto edtName;
	private EditTextRoboto edtDescription;
	
	private NotesModel notesModel;
	private boolean isEdit = false;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_add_notesdetails, null);
		
		initializeComponents(view);
		return view;
	}
	
	private void initializeComponents(View view) {
		setHasOptionsMenu(true);
		setRetainInstance(true);
		
		edtName = (EditTextRoboto) view.findViewById(R.id.fragment_add_notesdetails_edt_name);
		edtDescription = (EditTextRoboto) view.findViewById(R.id.fragment_add_notesdetails_edt_description);
		
		final Bundle bundle = getArguments();
		
		if ( bundle!= null && bundle.containsKey("isEdit") ) {
			isEdit = bundle.getBoolean("isEdit", false);
			notesModel = bundle.getParcelable("notesModel");
			
			setValues(view);
		} else {
			notesModel = new NotesModel();
		}
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu_savecarddetails, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if ( item.getItemId() == R.id.menu_save_details ) {
			if ( saveCardDetails() ) {
				getFragmentManager().popBackStackImmediate();	
			}
		} else if (item.getItemId() == android.R.id.home) {
			getFragmentManager().popBackStack();
	        return true;
	    }
		
		return super.onOptionsItemSelected(item);
	}
	
	private void setValues(View view) {
		
		edtName.setText(notesModel.getName());
		edtDescription.setText(notesModel.getDescription());
	}
	
	private boolean saveCardDetails() {
		
		if ( edtName.getText().toString().trim().equalsIgnoreCase("") ) {
			edtName.setError(getString(R.string.error_enter_name));
			edtName.requestFocus();
			return false;
		}
		
		notesModel.setName(edtName.getText().toString());
		notesModel.setDescription(edtDescription.getText().toString());
		
		final DatabaseHelper databaseHelper = ((MyWallet)getActivity().getApplication()).getDatabaseHelper();
		databaseHelper.insertOrUpdateNotesDetails(notesModel, isEdit);
		
		return true;
	}
}