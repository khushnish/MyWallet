package com.khushnish.mywallet.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.khushnish.mywallet.MainActivity;
import com.khushnish.mywallet.MyWallet;
import com.khushnish.mywallet.R;
import com.khushnish.mywallet.adapter.BankListDetailAdapter;
import com.khushnish.mywallet.database.DatabaseHelper;
import com.khushnish.mywallet.model.BankDetailsModel;

public class ListBankDetailsFragment extends Fragment {
	
	private BankListDetailAdapter bankListDetailAdapter;
	private ArrayList<BankDetailsModel> bankDetailsModels;
	private DatabaseHelper databaseHelper;
	private ListView listCardDetails;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_list_carddetails, null);
		
		initializeComponents(view);
		return view;
	}
	
	private void initializeComponents(View view) {
		((MainActivity) getActivity()).showBackButton();
		setHasOptionsMenu(true);
		
		bankDetailsModels = new ArrayList<BankDetailsModel>();
		listCardDetails = (ListView) view.findViewById(R.id.fragment_list_carddetails_list);
		bankListDetailAdapter = new BankListDetailAdapter(getActivity(), R.layout.row_fragment_list_details,
				R.id.row_fragment_list_details_title, bankDetailsModels);
		listCardDetails.setAdapter(bankListDetailAdapter);
		
		listCardDetails.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long id) {
				listCardDetails.setEnabled(false);
				final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
				final FragmentTransaction transaction = fragmentManager.beginTransaction();
				transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out,R.anim.left_in, R.anim.right_out);
				
				final AddBankDetailsFragment addCardDetailsFragment = new AddBankDetailsFragment();
				final Bundle bundle = new Bundle();
				
				bundle.putBoolean("isEdit", true);
				bundle.putParcelable("bankDetailsModel", bankDetailsModels.get(position));
				addCardDetailsFragment.setArguments(bundle);
				
				transaction.add(R.id.activity_main_frame, addCardDetailsFragment, AddBankDetailsFragment.class.getSimpleName());
				transaction.addToBackStack(AddBankDetailsFragment.class.getSimpleName());
				transaction.hide(ListBankDetailsFragment.this);
				transaction.commit();
			}
		});
		
		databaseHelper = ((MyWallet)(getActivity().getApplicationContext())).getDatabaseHelper();
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu_addcarddetails, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if ( item.getItemId() == R.id.menu_addcarddetails_add_details ) {
			final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
			final FragmentTransaction transaction = fragmentManager.beginTransaction();
			transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out,R.anim.left_in, R.anim.right_out);
			
			transaction.add(R.id.activity_main_frame, new AddBankDetailsFragment(), AddBankDetailsFragment.class.getSimpleName());
			transaction.addToBackStack(AddBankDetailsFragment.class.getSimpleName());
			transaction.hide(this);
			transaction.commit();
		} else if (item.getItemId() == android.R.id.home) {
	        getFragmentManager().popBackStack();
	        return true;
	    }
		return super.onOptionsItemSelected(item);
	}
	
	public void onResume() {
		super.onResume();
		setListAdapter();
	}
	
	private void setListAdapter() {
		bankDetailsModels.clear();
		bankDetailsModels.addAll(databaseHelper.getAllBankDetails());
		bankListDetailAdapter.notifyDataSetChanged();
	}
	
	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if ( !hidden ) {
			listCardDetails.setEnabled(true);
			setListAdapter();
		}
	}
}