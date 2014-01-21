package com.khushnish.mywallet.fragment;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.khushnish.mywallet.MyWallet;
import com.khushnish.mywallet.R;
import com.khushnish.mywallet.database.DatabaseHelper;
import com.khushnish.mywallet.model.LoanDetailsModel;
import com.khushnish.mywallet.utils.EditTextRoboto;

public class AddLoanDetailsFragment extends Fragment {
	
	private EditTextRoboto edtName;
	private EditTextRoboto edtBankName;
	private EditTextRoboto edtLoanAccountNumber;
	private EditTextRoboto edtBranchName;
	private EditTextRoboto edtEmi;
	private EditTextRoboto edtRateOfInterest;
	private EditTextRoboto edtTenure;
	private EditTextRoboto edtLoanAmount;
	private EditTextRoboto edtDistributeAmount;
	private EditTextRoboto edtOutstandingAmount;
	private EditTextRoboto edtOthers;
	
	private EditTextRoboto txtLoanDate;
	private EditTextRoboto txtSanctionDate;
	
	private boolean isEdit = false;
	private LoanDetailsModel loanDetailsModel;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_add_loandetails, null);
		
		initializeComponents(view);
		return view;
	}
	
	private void initializeComponents(View view) {
		setHasOptionsMenu(true);
		setRetainInstance(true);
		
		edtName = (EditTextRoboto) view.findViewById(R.id.fragment_add_loandetails_edt_name);
		edtBankName = (EditTextRoboto) view.findViewById(R.id.fragment_add_loandetails_edt_bankname);
		edtLoanAccountNumber = (EditTextRoboto) view.findViewById(R.id.fragment_add_loandetails_edt_loanaccountnumber);
		edtBranchName = (EditTextRoboto) view.findViewById(R.id.fragment_add_loandetails_edt_branchname);
		txtLoanDate = (EditTextRoboto) view.findViewById(R.id.fragment_add_loandetails_edt_loandatepicker);
		txtSanctionDate = (EditTextRoboto) view.findViewById(R.id.fragment_add_loandetails_edt_sanctiondatepicker);
		edtEmi = (EditTextRoboto) view.findViewById(R.id.fragment_add_loandetails_edt_emi);
		edtRateOfInterest = (EditTextRoboto) view.findViewById(R.id.fragment_add_loandetails_edt_rateofinterest);
		edtTenure = (EditTextRoboto) view.findViewById(R.id.fragment_add_loandetails_edt_tenure);
		edtLoanAmount = (EditTextRoboto) view.findViewById(R.id.fragment_add_loandetails_edt_loanamount);
		edtDistributeAmount = (EditTextRoboto) view.findViewById(R.id.fragment_add_loandetails_edt_distributedamount);
		edtOutstandingAmount = (EditTextRoboto) view.findViewById(R.id.fragment_add_loandetails_edt_outstandingamount);
		edtOthers = (EditTextRoboto) view.findViewById(R.id.fragment_add_bankdetails_edt_other);
		
		txtLoanDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final DatePickerLoanDateFragment dateFragment = new DatePickerLoanDateFragment();
				dateFragment.show(getFragmentManager(), "dateLoanPicker");
			}
		});
		
		txtSanctionDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final DatePickerSactionDateFragment dateFragment = new DatePickerSactionDateFragment();
				dateFragment.show(getFragmentManager(), "dateSactionPicker");
			}
		});
		
		final Bundle bundle = getArguments();
		
		if ( bundle!= null && bundle.containsKey("isEdit") ) {
			isEdit = bundle.getBoolean("isEdit", false);
			loanDetailsModel = bundle.getParcelable("loanDetailsModel");
			
			setValues(view);
		} else {
			loanDetailsModel = new LoanDetailsModel();
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
		
		edtName.setText(loanDetailsModel.getName());
		edtBankName.setText(loanDetailsModel.getBankName());
		edtLoanAccountNumber.setText(loanDetailsModel.getLoanAccountNumer());
		edtBranchName.setText(loanDetailsModel.getBranchName());
		txtLoanDate.setText(loanDetailsModel.getLoanDate());
		txtSanctionDate.setText(loanDetailsModel.getSanctionDate());
		edtEmi.setText(loanDetailsModel.getEmi());
		edtRateOfInterest.setText(loanDetailsModel.getRateOfInterest());
		edtTenure.setText(loanDetailsModel.getTenure());
		edtLoanAmount.setText(loanDetailsModel.getLoanAmount());
		edtDistributeAmount.setText(loanDetailsModel.getDistributedAmount());
		edtOutstandingAmount.setText(loanDetailsModel.getOutstandingAmount());
		edtOthers.setText(loanDetailsModel.getOthers());
	}
	
	private boolean saveCardDetails() {
		
		if ( edtName.getText().toString().trim().equalsIgnoreCase("") ) {
			edtName.setError(getString(R.string.error_enter_name));
			edtName.requestFocus();
			return false;
		}
		
		loanDetailsModel.setName(edtName.getText().toString());
		loanDetailsModel.setBankName(edtBankName.getText().toString());
		loanDetailsModel.setLoanAccountNumer(edtLoanAccountNumber.getText().toString());
		loanDetailsModel.setBranchName(edtBranchName.getText().toString());
		loanDetailsModel.setLoanDate(txtLoanDate.getText().toString());
		loanDetailsModel.setSanctionDate(txtSanctionDate.getText().toString());
		loanDetailsModel.setEmi(edtEmi.getText().toString());
		loanDetailsModel.setRateOfInterest(edtRateOfInterest.getText().toString());
		loanDetailsModel.setTenure(edtTenure.getText().toString());
		loanDetailsModel.setLoanAmount(edtLoanAmount.getText().toString());
		loanDetailsModel.setDistributedAmount(edtDistributeAmount.getText().toString());
		loanDetailsModel.setOutstandingAmount(edtOutstandingAmount.getText().toString());
		loanDetailsModel.setOthers(edtOthers.getText().toString());
		
		final DatabaseHelper databaseHelper = ((MyWallet)getActivity().getApplication()).getDatabaseHelper();
		databaseHelper.insertOrUpdateLoanDetails(loanDetailsModel, isEdit);
		
		return true;
	}
}

class DatePickerLoanDateFragment extends DialogFragment
		implements DatePickerDialog.OnDateSetListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	
	public void onDateSet(DatePicker view, int year, int month, int day) {
		((EditTextRoboto) getActivity().findViewById(R.id.fragment_add_loandetails_edt_loandatepicker)).setText(year + "/" + (month + 1) + "/" + day);
	}
}

class DatePickerSactionDateFragment extends DialogFragment
		implements DatePickerDialog.OnDateSetListener {
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	
	public void onDateSet(DatePicker view, int year, int month, int day) {
		((EditTextRoboto) getActivity().findViewById(R.id.fragment_add_loandetails_edt_sanctiondatepicker)).setText(year + "/" + (month + 1) + "/" + day);
	}
}