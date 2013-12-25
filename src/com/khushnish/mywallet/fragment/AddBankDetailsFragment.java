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
import com.khushnish.mywallet.model.BankDetailsModel;
import com.khushnish.mywallet.utils.CheckBoxRoboto;
import com.khushnish.mywallet.utils.EditTextRoboto;

public class AddBankDetailsFragment extends Fragment {
	
	private CheckBoxRoboto chkLoginPassword;
	private CheckBoxRoboto chkTransactionPassword;
	private CheckBoxRoboto chkMobilePinNumber;
	
	private EditTextRoboto edtName;
	private EditTextRoboto edtBankName;
	private EditTextRoboto edtProfileName;
	private EditTextRoboto edtBankAccountNumber;
	private EditTextRoboto edtBalance;
	private EditTextRoboto edtBankCustomerId;
	private EditTextRoboto edtLoginUserName;
	private EditTextRoboto edtLoginPassword;
	private EditTextRoboto edtTransactionPassword;
	private EditTextRoboto edtMobilePinNumber;
	private EditTextRoboto edtOthers;
	
	private boolean isEdit = false;
	private BankDetailsModel bankDetailsModel;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_add_bankdetails, null);
		
		initializeComponents(view);
		return view;
	}
	
	private void initializeComponents(View view) {
		setHasOptionsMenu(true);
		setRetainInstance(true);
		
		edtName = (EditTextRoboto) view.findViewById(R.id.fragment_add_bankdetails_edt_name);
		edtBankName = (EditTextRoboto) view.findViewById(R.id.fragment_add_bankdetails_edt_bankname);
		edtProfileName = (EditTextRoboto) view.findViewById(R.id.fragment_add_bankdetails_edt_profilename);
		edtBankAccountNumber = (EditTextRoboto) view.findViewById(R.id.fragment_add_bankdetails_edt_bankaccountnumber);
		edtBalance = (EditTextRoboto) view.findViewById(R.id.fragment_add_bankdetails_edt_balance);
		edtBankCustomerId = (EditTextRoboto) view.findViewById(R.id.fragment_add_bankdetails_edt_bankcustomerid);
		edtLoginUserName = (EditTextRoboto) view.findViewById(R.id.fragment_add_bankdetails_edt_loginusername);
		edtLoginPassword = (EditTextRoboto) view.findViewById(R.id.fragment_add_bankdetails_edt_loginpassword);
		edtTransactionPassword = (EditTextRoboto) view.findViewById(R.id.fragment_add_bankdetails_edt_transactionpassword);
		edtMobilePinNumber = (EditTextRoboto) view.findViewById(R.id.fragment_add_bankdetails_edt_mobilepinumber);
		edtOthers = (EditTextRoboto) view.findViewById(R.id.fragment_add_bankdetails_edt_other);
		
		chkLoginPassword = (CheckBoxRoboto) view.findViewById(R.id.fragment_add_bankdetails_chk_loginpassword);
		chkTransactionPassword = (CheckBoxRoboto) view.findViewById(R.id.fragment_add_bankdetails_chk_showtransactionpassword);
		chkMobilePinNumber = (CheckBoxRoboto) view.findViewById(R.id.fragment_add_bankdetails_chk_mobilepinumber);

		implementCheckListener(chkLoginPassword, edtLoginPassword);
		implementCheckListener(chkTransactionPassword, edtTransactionPassword);
		implementCheckListener(chkMobilePinNumber, edtMobilePinNumber);
		
		final Bundle bundle = getArguments();
		
		if ( bundle!= null && bundle.containsKey("isEdit") ) {
			isEdit = bundle.getBoolean("isEdit", false);
			bankDetailsModel = bundle.getParcelable("bankDetailsModel");
			
			setValues(view);
		} else {
			bankDetailsModel = new BankDetailsModel();
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
			if( saveCardDetails() ) {
				getFragmentManager().popBackStackImmediate();
			}
		} else if (item.getItemId() == android.R.id.home) {
			getFragmentManager().popBackStack();
	        return true;
	    }
		
		return super.onOptionsItemSelected(item);
	}
	
	private void setValues(View view) {
		
		edtName.setText(bankDetailsModel.getName());
		edtBankName.setText(bankDetailsModel.getBankName());
		edtProfileName.setText(bankDetailsModel.getProfileName());
		edtBankAccountNumber.setText(bankDetailsModel.getBankAccountNumber());
		edtBalance.setText(bankDetailsModel.getBalance());
		edtBankCustomerId.setText(bankDetailsModel.getBankCustomerId());
		edtLoginUserName.setText(bankDetailsModel.getLoginUserName());
		edtLoginPassword.setText(bankDetailsModel.getLoginPassword());
		edtTransactionPassword.setText(bankDetailsModel.getTransactionPassword());
		edtMobilePinNumber.setText(bankDetailsModel.getMobilePinNumber());
		edtOthers.setText(bankDetailsModel.getOthers());
	}
	
	private boolean saveCardDetails() {
		
		if ( edtName.getText().toString().trim().equalsIgnoreCase("") ) {
			edtName.setError(getString(R.string.error_enter_name));
			edtName.requestFocus();
			return false;
		}
		
		bankDetailsModel.setName(edtName.getText().toString());
		bankDetailsModel.setBankName(edtBankName.getText().toString());
		bankDetailsModel.setProfileName(edtProfileName.getText().toString());
		bankDetailsModel.setBankAccountNumber(edtBankAccountNumber.getText().toString());
		bankDetailsModel.setBalance(edtBalance.getText().toString());
		bankDetailsModel.setBankCustomerId(edtBankCustomerId.getText().toString());
		bankDetailsModel.setLoginUserName(edtLoginUserName.getText().toString());
		bankDetailsModel.setLoginPassword(edtLoginPassword.getText().toString());
		bankDetailsModel.setTransactionPassword(edtTransactionPassword.getText().toString());
		bankDetailsModel.setMobilePinNumber(edtMobilePinNumber.getText().toString());
		bankDetailsModel.setOthers(edtOthers.getText().toString());
		
		final DatabaseHelper databaseHelper = ((MyWallet)getActivity().getApplication()).getDatabaseHelper();
		databaseHelper.insertOrUpdateBankDetails(bankDetailsModel, isEdit);
		
		return true;
	}
}