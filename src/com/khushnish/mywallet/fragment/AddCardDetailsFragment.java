package com.khushnish.mywallet.fragment;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.khushnish.mywallet.MyWallet;
import com.khushnish.mywallet.R;
import com.khushnish.mywallet.database.DatabaseHelper;
import com.khushnish.mywallet.model.CardModel;
import com.khushnish.mywallet.utils.ButtonRoboto;
import com.khushnish.mywallet.utils.CheckBoxRoboto;
import com.khushnish.mywallet.utils.EditTextRoboto;

public class AddCardDetailsFragment extends Fragment {
	
	private CheckBoxRoboto chkCardCvvPassword;
	private CheckBoxRoboto chkCardAtmPinNumber;
	private CheckBoxRoboto chkCardTransactionPassword;
	private CheckBoxRoboto chkCardMobilePinNumber;
	
	private EditTextRoboto edtBankName;
	private EditTextRoboto edtBankAccountNumber;
	private EditTextRoboto edtBankCustomerId;
	private EditTextRoboto edtBankCardNumber;
	private EditTextRoboto edtBankCardHolderName;
	private EditTextRoboto edtBankCardCvvNumber;
	private EditTextRoboto edtBankCardAtmPinNumber;
	private EditTextRoboto edtBankCardTransactionPassword;
	private EditTextRoboto edtBankCardMobilePinNumber;
	private EditTextRoboto edtCardOther;
	
	private RadioGroup rgCardType;
	private RadioGroup rgCardUserType;
	
	private Spinner spinnerValidFromMonth;
	private Spinner spinnerValidFromYear;
	private Spinner spinnerValidTillMonth;
	private Spinner spinnerValidTillYear;
	
	private ImageView imgFrontImage;
	private ImageView imgBackImage;
	
	private File fileFrontImage;
	private File fileBackImage;
	
	private boolean isEdit = false;
	
	private String[] months;
	private String[] validFromYears;
	private String[] validTillYears;
	
	private CardModel cardModel;
	
	private final int CAMERA_REQUEST = 200;
	private final int CAMERA_CROP = 201;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_add_carddetails, null);
		
		initializeComponents(view);
		return view;
	}
	
	private void initializeComponents(View view) {
		setHasOptionsMenu(true);
		setRetainInstance(true);
		
		chkCardCvvPassword = (CheckBoxRoboto) view.findViewById(R.id.fragment_add_details_chk_cardcvvnumber);
		chkCardAtmPinNumber = (CheckBoxRoboto) view.findViewById(R.id.fragment_add_details_chk_cardatmpinmumber);
		chkCardTransactionPassword = (CheckBoxRoboto) view.findViewById(R.id.fragment_add_details_chk_transactionpassword);
		chkCardMobilePinNumber = (CheckBoxRoboto) view.findViewById(R.id.fragment_add_details_chk_cardmobilepinumber);
		
		edtBankName = (EditTextRoboto) view.findViewById(R.id.fragment_add_details_edt_bankname);
		edtBankAccountNumber = (EditTextRoboto) view.findViewById(R.id.fragment_add_details_edt_bankaccountnumber);
		edtBankCustomerId = (EditTextRoboto) view.findViewById(R.id.fragment_add_details_edt_bankcustomerid);
		edtBankCardNumber = (EditTextRoboto) view.findViewById(R.id.fragment_add_details_edt_cardnumber);
		edtBankCardHolderName = (EditTextRoboto) view.findViewById(R.id.fragment_add_details_edt_cardholdername);
		edtBankCardCvvNumber = (EditTextRoboto) view.findViewById(R.id.fragment_add_details_edt_cardcvvnumber);
		edtBankCardAtmPinNumber = (EditTextRoboto) view.findViewById(R.id.fragment_add_details_edt_cardatmpinmumber);
		edtBankCardTransactionPassword = (EditTextRoboto) view.findViewById(R.id.fragment_add_details_edt_cardtransactionpassword);
		edtBankCardMobilePinNumber = (EditTextRoboto) view.findViewById(R.id.fragment_add_details_edt_cardmobilepinumber);
		edtCardOther = (EditTextRoboto) view.findViewById(R.id.fragment_add_carddetails_edt_othername);
		
		rgCardType = (RadioGroup) view.findViewById(R.id.fragment_add_carddetails_rg_cardtype);
		rgCardUserType = (RadioGroup) view.findViewById(R.id.fragment_add_carddetails_rg_cardusertype);
		
		spinnerValidFromMonth = (Spinner) view.findViewById(R.id.fragment_add_details_spinner_validfrommonth);
		spinnerValidFromYear = (Spinner) view.findViewById(R.id.fragment_add_details_spinner_validfromyear);
		spinnerValidTillMonth = (Spinner) view.findViewById(R.id.fragment_add_details_spinner_validthrumonth);
		spinnerValidTillYear = (Spinner) view.findViewById(R.id.fragment_add_details_spinner_validthruyear);
		
		imgFrontImage = (ImageView) view.findViewById(R.id.fragment_add_details_img_front);
		imgBackImage = (ImageView) view.findViewById(R.id.fragment_add_details_img_back);
		
		months = getActivity().getResources().getStringArray(R.array.months);
		validFromYears = getActivity().getResources().getStringArray(R.array.validfromyears);
		validTillYears = getActivity().getResources().getStringArray(R.array.validthruyears);
		
		implementCheckListener(chkCardCvvPassword, edtBankCardCvvNumber);
		implementCheckListener(chkCardAtmPinNumber, edtBankCardAtmPinNumber);
		implementCheckListener(chkCardTransactionPassword, edtBankCardTransactionPassword);
		implementCheckListener(chkCardMobilePinNumber, edtBankCardMobilePinNumber);
		
		rgCardType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				switch (checkedId) {
				
				case R.id.fragment_add_carddetails_rb_other:
					edtCardOther.setVisibility(ButtonRoboto.VISIBLE);
					break;
				default:
					edtCardOther.setVisibility(ButtonRoboto.GONE);
					break;
				}
			}
		});
		
		imgFrontImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				fileFrontImage = new File(Environment.getExternalStorageDirectory() + File.separator + "frontimage.png");
				final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileFrontImage));
				startActivityForResult(intent, CAMERA_REQUEST);
			}
		});
		
		final Bundle bundle = getArguments();
		
		if ( bundle!= null && bundle.containsKey("isEdit") ) {
			isEdit = bundle.getBoolean("isEdit", false);
			cardModel = bundle.getParcelable("cardModel");
			
			setValues(view);
		} else {
			cardModel = new CardModel();
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
			saveCardDetails();
			getFragmentManager().popBackStackImmediate();
		} else if (item.getItemId() == android.R.id.home) {
			getFragmentManager().popBackStack();
	        return true;
	    }
		
		return super.onOptionsItemSelected(item);
	}
	
	private void setValues(View view) {
		
		final int cardType = Integer.parseInt(cardModel.getCardType());
		final int cardUserType = Integer.parseInt(cardModel.getCardUserType());
		
		switch (cardType) {
		case R.id.fragment_add_carddetails_rb_visa:
			final RadioButton rbVisa = (RadioButton) view.findViewById(R.id.fragment_add_carddetails_rb_visa);
			rbVisa.setChecked(true);
			break;
		case R.id.fragment_add_carddetails_rb_master:
			final RadioButton rbMaster = (RadioButton) view.findViewById(R.id.fragment_add_carddetails_rb_master);
			rbMaster.setChecked(true);
			break;
		case R.id.fragment_add_carddetails_rb_mastero:
			final RadioButton rbMastero = (RadioButton) view.findViewById(R.id.fragment_add_carddetails_rb_mastero);
			rbMastero.setChecked(true);
			break;
		case R.id.fragment_add_carddetails_rb_other:
			final RadioButton rbOther = (RadioButton) view.findViewById(R.id.fragment_add_carddetails_rb_other);
			rbOther.setChecked(true);
			break;
		default:
			break;
		}
		
		switch (cardUserType) {
		case R.id.fragment_add_carddetails_rb_personal:
			final RadioButton rbPersonal = (RadioButton) view.findViewById(R.id.fragment_add_carddetails_rb_personal);
			rbPersonal.setChecked(true);
			break;
		case R.id.fragment_add_carddetails_rb_business:
			final RadioButton rbBusiness = (RadioButton) view.findViewById(R.id.fragment_add_carddetails_rb_business);
			rbBusiness.setChecked(true);
			break;
		default:
			break;
		}
		
		edtCardOther.setText(cardModel.getOtherCardName());
		edtBankName.setText(cardModel.getBankName());
		edtBankAccountNumber.setText(cardModel.getBankAccountNumber());
		edtBankCustomerId.setText(cardModel.getBankCustomerId());
		edtBankCardNumber.setText(cardModel.getCardNumber());
		edtBankCardHolderName.setText(cardModel.getCardHolderName());
		edtBankCardCvvNumber.setText(String.valueOf(cardModel.getCardCvvNumber()));
		edtBankCardAtmPinNumber.setText(String.valueOf(cardModel.getCardAtmPinNumber()));
		edtBankCardTransactionPassword.setText(cardModel.getCardTransactionPassword());
		edtBankCardMobilePinNumber.setText(String.valueOf(cardModel.getBankCardMobilePinNumber()));
		
		for (int i = 0; i < months.length; ++i) {
			if ( months[i].equalsIgnoreCase(String.valueOf(cardModel.getValidFromMonth())) ) {
				spinnerValidFromMonth.setSelection(i);
				break;
			}
		}
		
		for (int i = 0; i < validFromYears.length; ++i) {
			if ( validFromYears[i].equalsIgnoreCase(String.valueOf(cardModel.getValidFromYear())) ) {
				spinnerValidFromYear.setSelection(i);
				break;
			}
		}
		
		for (int i = 0; i < months.length; ++i) {
			if ( months[i].equalsIgnoreCase(String.valueOf(cardModel.getValidTillMonth())) ) {
				spinnerValidTillMonth.setSelection(i);
				break;
			}
		}
		
		for (int i = 0; i < validTillYears.length; ++i) {
			if ( validTillYears[i].equalsIgnoreCase(String.valueOf(cardModel.getValidTillYear())) ) {
				spinnerValidTillYear.setSelection(i);
				break;
			}
		}
	}
	
	private void saveCardDetails() {
		
		cardModel.setCardType(String.valueOf(rgCardType.getCheckedRadioButtonId()));
		cardModel.setOtherCardName(edtCardOther.getText().toString());
		cardModel.setCardUserType(String.valueOf(rgCardUserType.getCheckedRadioButtonId()));
		cardModel.setBankName(edtBankName.getText().toString());
		cardModel.setBankAccountNumber(edtBankAccountNumber.getText().toString());
		cardModel.setBankCustomerId(edtBankCustomerId.getText().toString());
		cardModel.setCardNumber(edtBankCardNumber.getText().toString());
		cardModel.setCardHolderName(edtBankCardHolderName.getText().toString());
		
		if ( !TextUtils.isEmpty(edtBankCardCvvNumber.getText().toString()) ) {
			cardModel.setCardCvvNumber(edtBankCardCvvNumber.getText().toString());
		}
		
		if ( !TextUtils.isEmpty(edtBankCardAtmPinNumber.getText().toString()) ) {
			cardModel.setCardAtmPinNumber(edtBankCardAtmPinNumber.getText().toString());
		}
		
		cardModel.setCardTransactionPassword(edtBankCardTransactionPassword.getText().toString());
		
		if ( !TextUtils.isEmpty(edtBankCardMobilePinNumber.getText().toString()) ) {
			cardModel.setBankCardMobilePinNumber(edtBankCardMobilePinNumber.getText().toString());
		}
		
		cardModel.setValidFromMonth(months[spinnerValidFromMonth.getSelectedItemPosition()]);
		cardModel.setValidFromYear(validFromYears[spinnerValidFromYear.getSelectedItemPosition()]);
		cardModel.setValidTillMonth(months[spinnerValidTillMonth.getSelectedItemPosition()]);
		cardModel.setValidTillYear(validTillYears[spinnerValidTillYear.getSelectedItemPosition()]);
		
		final DatabaseHelper databaseHelper = ((MyWallet)getActivity().getApplication()).getDatabaseHelper();
		databaseHelper.insertOrUpdateCardDetails(cardModel, isEdit);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		Log.e(getTag(), "onActivityResult() called : requestCode : " + requestCode
				+", resultCode : " + resultCode);
		
		Log.e(getTag(), "File Path : " + ((fileFrontImage == null) ? "Null" : fileFrontImage.getAbsolutePath()));
		Log.e(getTag(), "URI : " + ((data == null) ? "Null" : data.getData().toString()));
		
		if ( requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK ) {
			
//			Bitmap bmp = (Bitmap) data.getExtras().get("data");
			imgFrontImage.setImageURI(Uri.fromFile(fileFrontImage));
//			Intent cropIntent = new Intent("com.android.camera.action.CROP");
//	        cropIntent.setDataAndType(Uri.fromFile(new File(fileFrontImage.toString())), "image/*");
//	        cropIntent.putExtra("crop", "true");
//	        cropIntent.putExtra("aspectX", 1);
//	        cropIntent.putExtra("aspectY", 1);
//	        cropIntent.putExtra("outputX", 256);
//	        cropIntent.putExtra("outputY", 256);
//	        cropIntent.putExtra("return-data", true);
//	        startActivityForResult(cropIntent, CAMERA_CROP);
		}
	}
}
