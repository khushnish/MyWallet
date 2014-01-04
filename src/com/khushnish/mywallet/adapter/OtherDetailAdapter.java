package com.khushnish.mywallet.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.khushnish.mywallet.R;
import com.khushnish.mywallet.model.OthersDetailsModel;

public class OtherDetailAdapter extends ArrayAdapter<OthersDetailsModel> {
	
	private Context context;
	private ArrayList<OthersDetailsModel> otherDetailsModels;
	
	public OtherDetailAdapter(Context context, int resource,
			int textViewResourceId, ArrayList<OthersDetailsModel> otherDetailsModels) {
		super(context, resource, textViewResourceId, otherDetailsModels);
		this.context = context;
		this.otherDetailsModels = otherDetailsModels;
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
		
		holder.txtTitle.setText(otherDetailsModels.get(position).getName());
		
		return convertView;
	}
	
	static class ViewHolder {
		public TextView txtTitle;
	}
}