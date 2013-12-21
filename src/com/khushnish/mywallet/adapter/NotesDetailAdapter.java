package com.khushnish.mywallet.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.khushnish.mywallet.R;
import com.khushnish.mywallet.model.NotesModel;

public class NotesDetailAdapter extends ArrayAdapter<NotesModel> {
	
	private Context context;
	private ArrayList<NotesModel> notesModels;
	
	public NotesDetailAdapter(Context context, int resource,
			int textViewResourceId, ArrayList<NotesModel> notesModels) {
		super(context, resource, textViewResourceId, notesModels);
		this.context = context;
		this.notesModels = notesModels;
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
		
		holder.txtTitle.setText(notesModels.get(position).getName());
		
		return convertView;
	}
	
	static class ViewHolder {
		public TextView txtTitle;
	}
}