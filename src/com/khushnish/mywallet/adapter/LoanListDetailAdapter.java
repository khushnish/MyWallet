package com.khushnish.mywallet.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.khushnish.mywallet.R;
import com.khushnish.mywallet.model.LoanDetailsModel;

public class LoanListDetailAdapter extends ArrayAdapter<LoanDetailsModel> {
	
	private Context context;
	private ArrayList<LoanDetailsModel> loanDetailsModels;
	
	public LoanListDetailAdapter(Context context, int resource,
			int textViewResourceId, ArrayList<LoanDetailsModel> loanDetailsModels) {
		super(context, resource, textViewResourceId, loanDetailsModels);
		this.context = context;
		this.loanDetailsModels = loanDetailsModels;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if ( convertView == null ) {
			final LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    convertView = inflater.inflate(R.layout.row_fragment_list_details, null);
		    holder = new ViewHolder();
		    
		    holder.txtTitle = (TextView) convertView.findViewById(R.id.row_fragment_list_details_title);
		    
		    convertView.setTag(holder);
		} else {
		    holder = (ViewHolder) convertView.getTag();
		}
		
		holder.txtTitle.setText(loanDetailsModels.get(position).getName());
		
		return convertView;
	}
	
	static class ViewHolder {
		public TextView txtTitle;
	}
}
