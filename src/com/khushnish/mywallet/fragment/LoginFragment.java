package com.khushnish.mywallet.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class LoginFragment extends Fragment {
	
	private EditTextRoboto edtPassword;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_login, null);
		initializeComponents(view);
		return view;
	}
	
	private void initializeComponents( View view ) {
		final Button btnLogin = (Button) view.findViewById(R.id.fragment_login_btn_login);
		edtPassword = (EditTextRoboto) view.findViewById(R.id.fragment_login_edt_password);
		
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if ( TextUtils.isEmpty(edtPassword.getText().toString()) ) {
					edtPassword.requestFocus();
					edtPassword.setError(getString(R.string.password_required));
				} else {
					final DatabaseHelper databaseHelper = ((MyWallet) getActivity().getApplication()).getDatabaseHelper();
					final SharedPreferences preferences = getActivity().getSharedPreferences(
							getString(R.string.pref_name), Context.MODE_PRIVATE);
					final String password = edtPassword.getText().toString();
					if ( password.equals(databaseHelper.getDencryptedString(
							preferences.getString(getString(R.string.pref_wallet_password), ""))) ) {
						final FragmentManager fragmentManager = getFragmentManager();
						final FragmentTransaction transaction = fragmentManager.beginTransaction();
						transaction.replace(R.id.activity_main_frame, new ListDetailsFragment(), 
								ListDetailsFragment.class.getSimpleName());	
						transaction.commit();
						
						fragmentManager.popBackStack();
					} else {
						Utils.displayPrompt(getString(R.string.password_not_valid),
								getString(android.R.string.ok), "", false, getActivity(), false);
					}
				}
			}
		});
	}
}