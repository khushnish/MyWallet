package com.khushnish.mywallet.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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
import android.widget.EditText;

import com.khushnish.mywallet.MyWallet;
import com.khushnish.mywallet.R;
import com.khushnish.mywallet.database.DatabaseHelper;
import com.khushnish.mywallet.model.CardModel;
import com.khushnish.mywallet.utils.CheckBoxRoboto;
import com.khushnish.mywallet.utils.EditTextRoboto;

public class AddCardDetailsFragment extends Fragment {
	
	private CheckBoxRoboto chkCardCvvPassword;
	private CheckBoxRoboto chkCardAtmPinNumber;
	private CheckBoxRoboto chkCardTransactionPassword;
	private CheckBoxRoboto chkCardMobilePinNumber;
	
	private EditTextRoboto edtCardCvvNumber;
	private EditTextRoboto edtCardAtmPinNumber;
	private EditTextRoboto edtCardTransactionPassword;
	private EditTextRoboto edtCardMobilePinNumber;
	
	private boolean isEdit = false;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_add_carddetails, null);
		
		initializeComponents(view);
		return view;
	}
	
	private void initializeComponents(View view) {
		setHasOptionsMenu(true);
		
		chkCardCvvPassword = (CheckBoxRoboto) view.findViewById(R.id.fragment_add_details_chk_cardcvvnumber);
		chkCardAtmPinNumber = (CheckBoxRoboto) view.findViewById(R.id.fragment_add_details_chk_cardatmpinmumber);
		chkCardTransactionPassword = (CheckBoxRoboto) view.findViewById(R.id.fragment_add_details_chk_transactionpassword);
		chkCardMobilePinNumber = (CheckBoxRoboto) view.findViewById(R.id.fragment_add_details_chk_cardmobilepinumber);
		
		edtCardCvvNumber = (EditTextRoboto) view.findViewById(R.id.fragment_add_details_edt_cardcvvnumber);
		edtCardAtmPinNumber = (EditTextRoboto) view.findViewById(R.id.fragment_add_details_edt_cardatmpinmumber);
		edtCardTransactionPassword = (EditTextRoboto) view.findViewById(R.id.fragment_add_details_edt_cardtransactionpassword);
		edtCardMobilePinNumber = (EditTextRoboto) view.findViewById(R.id.fragment_add_details_edt_cardmobilepinumber);
		
		implementCheckListener(chkCardCvvPassword, edtCardCvvNumber);
		implementCheckListener(chkCardAtmPinNumber, edtCardAtmPinNumber);
		implementCheckListener(chkCardTransactionPassword, edtCardTransactionPassword);
		implementCheckListener(chkCardMobilePinNumber, edtCardMobilePinNumber);
		
		isEdit = getArguments().getBoolean("isEdit", false);
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
			saveCardDetails();
			getFragmentManager().popBackStackImmediate();
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	private void saveCardDetails() {
		final EditText edtBankName = (EditText) getView().findViewById(R.id.fragment_add_details_edt_bankname);
		final EditText edtBankAccountNUmber = (EditText) getView().findViewById(R.id.fragment_add_details_edt_bankaccountnumber);
		final EditText edtBankCustomerId = (EditText) getView().findViewById(R.id.fragment_add_details_edt_bankcustomerid);
		final EditText edtBankCardNumber = (EditText) getView().findViewById(R.id.fragment_add_details_edt_cardnumber);
		final EditText edtBankCardHolderName = (EditText) getView().findViewById(R.id.fragment_add_details_edt_cardholdername);
		final EditText edtBankCardCvvNumber = (EditText) getView().findViewById(R.id.fragment_add_details_edt_cardcvvnumber);
		final EditText edtBankCardAtmPinNumber = (EditText) getView().findViewById(R.id.fragment_add_details_edt_cardatmpinmumber);
		final EditText edtBankCardTransactionPassword = (EditText) getView().findViewById(R.id.fragment_add_details_edt_cardtransactionpassword);
		final EditText edtBankCardMobilePinNumber = (EditText) getView().findViewById(R.id.fragment_add_details_edt_cardmobilepinumber);
		
		final CardModel cardModel = new CardModel();
		cardModel.setBankName(edtBankName.getText().toString());
		cardModel.setBankAccountNumber(edtBankAccountNUmber.getText().toString());
		cardModel.setBankCustomerId(edtBankCustomerId.getText().toString());
		cardModel.setCardNumber(edtBankCardNumber.getText().toString());
		cardModel.setCardHolderName(edtBankCardHolderName.getText().toString());
		
		if ( !TextUtils.isEmpty(edtBankCardCvvNumber.getText().toString()) ) {
			cardModel.setCardCvvNumber(Integer.parseInt(edtBankCardCvvNumber.getText().toString()));
		}
		
		if ( !TextUtils.isEmpty(edtBankCardAtmPinNumber.getText().toString()) ) {
			cardModel.setCardAtmPinNumber(Integer.parseInt(edtBankCardAtmPinNumber.getText().toString()));
		}
		
		cardModel.setCardTransactionPassword(edtBankCardTransactionPassword.getText().toString());
		
		if ( !TextUtils.isEmpty(edtBankCardMobilePinNumber.getText().toString()) ) {
			cardModel.setBankCardMobilePinNumber(Integer.parseInt(edtBankCardMobilePinNumber.getText().toString()));
		}
		
		final DatabaseHelper databaseHelper = ((MyWallet)getActivity().getApplication()).getDatabaseHelper();
		databaseHelper.insertOrUpdateCardDetails(cardModel, isEdit);
	}
}
