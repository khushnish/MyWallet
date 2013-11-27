package com.khushnish.mywallet.fragment;

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
import com.khushnish.mywallet.R;
import com.khushnish.mywallet.adapter.ListDetailAdapter;

public class ListDetailsFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_list_details, null);
		initializeComponents(view);
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		((MainActivity) getActivity()).hideBackButton();
	}
	
	private void initializeComponents(View view) {
		
		setHasOptionsMenu(true);
		
		final ListView list = (ListView) view.findViewById(R.id.fragment_list_details_list);
		final String[] titles = getResources().getStringArray(R.array.titles);
		list.setAdapter(new ListDetailAdapter(getActivity(), titles));
		
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long id) {
				final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
				final FragmentTransaction transaction = fragmentManager.beginTransaction();
				transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out,R.anim.left_in, R.anim.right_out);
				
				if ( position == 0 ) {
					transaction.replace(R.id.activity_main_frame, new ListCardDetailsFragment(), "ListCardDetails");
					transaction.addToBackStack("ListCardDetails");
				} else if ( position == 1 ) {
					
				}
				transaction.commit();
			}
		});
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.main, menu);		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		final FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.setCustomAnimations(R.anim.bottom_in, R.anim.top_out,R.anim.top_in, R.anim.bottom_out);
		
		if ( item.getItemId() == R.id.action_settings ) {
			
			transaction.replace(R.id.activity_main_frame, new SettingsFragment(), "Settings");
			transaction.addToBackStack("Settings");
			transaction.commit();
		}
		return super.onOptionsItemSelected(item);
	}
}