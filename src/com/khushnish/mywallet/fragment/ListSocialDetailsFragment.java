package com.khushnish.mywallet.fragment;

import java.util.ArrayList;

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
import com.khushnish.mywallet.adapter.SocialDetailAdapter;
import com.khushnish.mywallet.database.DatabaseHelper;
import com.khushnish.mywallet.model.SocialModel;

public class ListSocialDetailsFragment extends Fragment {
	
	private SocialDetailAdapter socialListDetailAdapter;
	private ArrayList<SocialModel> socialModels;
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
		
		socialModels = new ArrayList<SocialModel>();
		final ListView listCardDetails = (ListView) view.findViewById(R.id.fragment_list_carddetails_list);
		socialListDetailAdapter = new SocialDetailAdapter(getActivity(), R.layout.row_fragment_list_details,
				R.id.row_fragment_list_details_title, socialModels);
		listCardDetails.setAdapter(socialListDetailAdapter);
		
		listCardDetails.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long id) {
				final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
				final FragmentTransaction transaction = fragmentManager.beginTransaction();
				transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out, 
						R.anim.left_in, R.anim.right_out);
				
				final AddSocialDetailsFragment socialCardDetailsFragment = new AddSocialDetailsFragment();
				final Bundle bundle = new Bundle();
				
				bundle.putBoolean("isEdit", true);
				bundle.putParcelable("socialModel", socialModels.get(position));
				socialCardDetailsFragment.setArguments(bundle);
				
				transaction.add(R.id.activity_main_frame, socialCardDetailsFragment, 
						AddCardDetailsFragment.class.getSimpleName());
				transaction.addToBackStack(AddSocialDetailsFragment.class.getSimpleName());
				transaction.hide(ListSocialDetailsFragment.this);
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
			transaction.setCustomAnimations(R.anim.right_in, 
					R.anim.left_out,R.anim.left_in, R.anim.right_out);
			transaction.add(R.id.activity_main_frame, new AddSocialDetailsFragment(),
					AddSocialDetailsFragment.class.getSimpleName());
			transaction.addToBackStack(AddSocialDetailsFragment.class.getSimpleName());
			transaction.hide(this);
			transaction.commit();
		} else if (item.getItemId() == android.R.id.home) {
	        getFragmentManager().popBackStack();
	        return true;
	    }
		return super.onOptionsItemSelected(item);
	}
	
	private void setListAdapter() {
		socialModels.clear();
		socialModels.addAll(databaseHelper.getAllSocialDetails());
		Log.e("Database", "Social Size : " + socialModels.size());
		socialListDetailAdapter.notifyDataSetChanged();
	}
	
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		Log.e("Fragment", "onHiddenChanged() called " + hidden);
		
		if ( !hidden ) {
			setListAdapter();
		}
	}
}
