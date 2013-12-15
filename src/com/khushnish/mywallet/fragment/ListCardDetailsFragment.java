package com.khushnish.mywallet.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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
import com.khushnish.mywallet.adapter.CardListDetailAdapter;
import com.khushnish.mywallet.database.DatabaseHelper;
import com.khushnish.mywallet.model.CardModel;

public class ListCardDetailsFragment extends Fragment {
	
	private CardListDetailAdapter cardListDetailAdapter;
	private ArrayList<CardModel> cardModels;
	private DatabaseHelper databaseHelper;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("Fragment", "onCreate() called");
		setRetainInstance(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.e("Fragment", "onCreateView() called");
		final View view = inflater.inflate(R.layout.fragment_list_carddetails, null);
		
		initializeComponents(view);
		return view;
	}
	
	private void initializeComponents(View view) {
		((MainActivity) getActivity()).showBackButton();
		setHasOptionsMenu(true);
		
		cardModels = new ArrayList<CardModel>();
		final ListView listCardDetails = (ListView) view.findViewById(R.id.fragment_list_carddetails_list);
		cardListDetailAdapter = new CardListDetailAdapter(getActivity(), R.layout.row_fragment_list_details,
				R.id.row_fragment_list_details_title, cardModels);
		listCardDetails.setAdapter(cardListDetailAdapter);
		
		listCardDetails.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long id) {
				final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
				final FragmentTransaction transaction = fragmentManager.beginTransaction();
				transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out,R.anim.left_in, R.anim.right_out);
				
				final AddCardDetailsFragment addCardDetailsFragment = new AddCardDetailsFragment();
				final Bundle bundle = new Bundle();
				
				bundle.putBoolean("isEdit", true);
				bundle.putParcelable("cardModel", cardModels.get(position));
				addCardDetailsFragment.setArguments(bundle);
				
				transaction.add(R.id.activity_main_frame, addCardDetailsFragment, AddCardDetailsFragment.class.getSimpleName());
				transaction.addToBackStack(AddCardDetailsFragment.class.getSimpleName());
				transaction.hide(ListCardDetailsFragment.this);
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
			
			transaction.add(R.id.activity_main_frame, new AddCardDetailsFragment(), AddCardDetailsFragment.class.getSimpleName());
			transaction.addToBackStack(AddCardDetailsFragment.class.getSimpleName());
			transaction.hide(this);
			transaction.commit();
		} else if (item.getItemId() == android.R.id.home) {
	        getFragmentManager().popBackStack();
	        return true;
	    }
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.e("Fragment", "onActivityCreated() called");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.e("Fragment", "onResume() called");
		setListAdapter();
	}
	
	private void setListAdapter() {
		cardModels.clear();
		cardModels.addAll(databaseHelper.getAllCardDetails());
		Log.e("Database", "Card Size : " + cardModels.size());
		cardListDetailAdapter.notifyDataSetChanged();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.e("Fragment", "onPause() called");
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.e("Fragment", "onDestroyView() called");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e("Fragment", "onDestroy() called");
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.e("Fragment", "onAttach() called");
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		Log.e("Fragment", "onDetach() called");
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Log.e("Fragment", "onStart() called");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		Log.e("Fragment", "onStop() called");
	}
	
	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		Log.e("Fragment", "onHiddenChanged() called " + hidden);
		
		if ( !hidden ) {
			setListAdapter();
		}
	}
}
