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
import com.khushnish.mywallet.adapter.NotesDetailAdapter;
import com.khushnish.mywallet.database.DatabaseHelper;
import com.khushnish.mywallet.model.NotesModel;

public class ListNotesDetailsFragment extends Fragment {
	
	private NotesDetailAdapter notesListDetailAdapter;
	private ArrayList<NotesModel> notesModels;
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
		
		notesModels = new ArrayList<NotesModel>();
		final ListView listCardDetails = (ListView) view.findViewById(R.id.fragment_list_carddetails_list);
		notesListDetailAdapter = new NotesDetailAdapter(getActivity(), R.layout.row_fragment_list_details,
				R.id.row_fragment_list_details_title, notesModels);
		listCardDetails.setAdapter(notesListDetailAdapter);
		
		listCardDetails.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long id) {
				final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
				final FragmentTransaction transaction = fragmentManager.beginTransaction();
				transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out, 
						R.anim.left_in, R.anim.right_out);
				
				final AddNotesDetailsFragment notesCardDetailsFragment = new AddNotesDetailsFragment();
				final Bundle bundle = new Bundle();
				
				bundle.putBoolean("isEdit", true);
				bundle.putParcelable("notesModel", notesModels.get(position));
				notesCardDetailsFragment.setArguments(bundle);
				
				transaction.add(R.id.activity_main_frame, notesCardDetailsFragment, 
						AddNotesDetailsFragment.class.getSimpleName());
				transaction.addToBackStack(AddNotesDetailsFragment.class.getSimpleName());
				transaction.hide(ListNotesDetailsFragment.this);
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
			transaction.add(R.id.activity_main_frame, new AddNotesDetailsFragment(),
					AddNotesDetailsFragment.class.getSimpleName());
			transaction.addToBackStack(AddNotesDetailsFragment.class.getSimpleName());
			transaction.hide(this);
			transaction.commit();
		} else if (item.getItemId() == android.R.id.home) {
	        getFragmentManager().popBackStack();
	        return true;
	    }
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		setListAdapter();
	}
	
	private void setListAdapter() {
		notesModels.clear();
		notesModels.addAll(databaseHelper.getAllNotesDetails());
		Log.e("Database", "Notes Size : " + notesModels.size());
		notesListDetailAdapter.notifyDataSetChanged();
	}
	
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		
		if ( !hidden ) {
			setListAdapter();
		}
	}
}