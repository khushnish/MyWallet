package com.khushnish.mywallet.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.khushnish.mywallet.MyWallet;
import com.khushnish.mywallet.R;
import com.khushnish.mywallet.database.DatabaseHelper;
import com.khushnish.mywallet.utils.EditTextRoboto;
import com.khushnish.mywallet.utils.Utils;

public class NewUserLoginFrgment extends Fragment {
	
	private EditTextRoboto edtPassword;
	private EditTextRoboto edtConfirmPassword;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_newuserlogin, null);
		initializeComponents(view);
		return view;
	}
	
	private void initializeComponents( View view ) {
		final Button btnRegister = (Button) view.findViewById(R.id.fragment_newuserlogin_btn_register);
		edtPassword = (EditTextRoboto) view.findViewById(R.id.fragment_newuserlogin_edt_newpassword4);
		edtConfirmPassword = (EditTextRoboto) view.findViewById(R.id.fragment_newuserlogin_edt_confirmpassword);
		
		btnRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if ( TextUtils.isEmpty(edtPassword.getText().toString()) ) {
					edtPassword.requestFocus();
					edtPassword.setError(getString(R.string.password_required));
				} else if ( TextUtils.isEmpty(edtConfirmPassword.getText().toString()) ) {
					edtConfirmPassword.requestFocus();
					edtConfirmPassword.setError(getString(R.string.password_required));
				} else if ( !edtPassword.getText().toString().equals(edtConfirmPassword.getText().toString())) {
					Utils.displayPrompt(getString(R.string.password_not_matched),
							getString(android.R.string.ok), "", false, getActivity(), false);
				} else {
					final DatabaseHelper databaseHelper = ((MyWallet)getActivity().getApplication()).getDatabaseHelper();
					final SharedPreferences.Editor editor = getActivity().getSharedPreferences(
							getString(R.string.pref_name), Context.MODE_PRIVATE).edit();
					final String password = edtPassword.getText().toString();
					editor.putString(getString(R.string.pref_wallet_password), databaseHelper.getEncryptedString(password));
					editor.commit();
				}
			}
		});
	}
}