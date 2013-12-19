package com.khushnish.mywallet.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.khushnish.mywallet.MyWallet;
import com.khushnish.mywallet.R;
import com.khushnish.mywallet.database.DatabaseHelper;
import com.khushnish.mywallet.model.SocialModel;
import com.khushnish.mywallet.utils.CheckBoxRoboto;
import com.khushnish.mywallet.utils.EditTextRoboto;

public class AddSocialDetailsFragment extends Fragment {
	
	private EditTextRoboto edtName;
	private EditTextRoboto edtEmailAddress;
	private EditTextRoboto edtPassword;
	
	private CheckBoxRoboto chkShowPassword;
	
	private SocialModel socialModel;
	private boolean isEdit = false;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_add_socialdetails, null);
		
		initializeComponents(view);
		return view;
	}
	
	private void initializeComponents(View view) {
		setHasOptionsMenu(true);
		setRetainInstance(true);
		
		edtName = (EditTextRoboto) view.findViewById(R.id.fragment_add_socialdetails_edt_name);
		edtEmailAddress = (EditTextRoboto) view.findViewById(R.id.fragment_add_socialdetails_edt_emailaddress);
		edtPassword = (EditTextRoboto) view.findViewById(R.id.fragment_add_socialdetails_edt_password);
		
		chkShowPassword = (CheckBoxRoboto) view.findViewById(R.id.fragment_add_socialdetails_chk_password);
		
		implementCheckListener(chkShowPassword, edtPassword);
		
		final Bundle bundle = getArguments();
		
		if ( bundle!= null && bundle.containsKey("isEdit") ) {
			isEdit = bundle.getBoolean("isEdit", false);
			socialModel = bundle.getParcelable("socialModel");
			
			setValues(view);
		} else {
			socialModel = new SocialModel();
		}
	}
	
	private void implementCheckListener( CheckBoxRoboto chkPassword, final EditTextRoboto edtPassword ) {
		chkPassword.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());					
	            } else {
	            	edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
	            }
			}
		});
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
		
		edtName.setText(socialModel.getName());
		edtEmailAddress.setText(socialModel.getEmailAddress());
		edtPassword.setText(socialModel.getPassword());
	}
	
	private boolean saveCardDetails() {
		
		if ( edtName.getText().toString().trim().equalsIgnoreCase("") ) {
			edtName.setError(getString(R.string.error_enter_name));
			return false;
		}
		
		socialModel.setName(edtName.getText().toString());
		socialModel.setEmailAddress(edtEmailAddress.getText().toString());
		socialModel.setPassword(edtPassword.getText().toString());
		
		final DatabaseHelper databaseHelper = ((MyWallet)getActivity().getApplication()).getDatabaseHelper();
		databaseHelper.insertOrUpdateSocialDetails(socialModel, isEdit);
		
		return true;
	}
}
