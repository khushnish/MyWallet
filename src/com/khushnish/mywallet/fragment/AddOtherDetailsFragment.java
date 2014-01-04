package com.khushnish.mywallet.fragment;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.khushnish.mywallet.MyWallet;
import com.khushnish.mywallet.R;
import com.khushnish.mywallet.cropimage.CropImage;
import com.khushnish.mywallet.database.DatabaseHelper;
import com.khushnish.mywallet.model.OthersDetailsModel;
import com.khushnish.mywallet.utils.EditTextRoboto;

public class AddOtherDetailsFragment extends Fragment {
	
	private EditTextRoboto edtName;
	private EditTextRoboto edtDescription;
	
	private ImageView imgImage1;
	private ImageView imgImage2;
	
	private File fileImage1;
	private File fileImage2;
	
	private OthersDetailsModel otherDetailsModel;
	private boolean isEdit = false;
	private boolean isImage1 = false;
	
	private final int CAMERA_REQUEST = 200;
	private final int CAMERA_CROP = 201;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_add_otherdetails, null);
		
		initializeComponents(view);
		return view;
	}
	
	private void initializeComponents(View view) {
		setHasOptionsMenu(true);
		setRetainInstance(true);
		
		edtName = (EditTextRoboto) view.findViewById(R.id.fragment_add_otherdetails_edt_name);
		edtDescription = (EditTextRoboto) view.findViewById(R.id.fragment_add_otherdetails_edt_description);
		imgImage1 = (ImageView) view.findViewById(R.id.fragment_add_otherdetails_img_image1);
		imgImage2 = (ImageView) view.findViewById(R.id.fragment_add_otherdetails_img_image2);
		
		
		final Bundle bundle = getArguments();
		
		if ( bundle!= null && bundle.containsKey("isEdit") ) {
			isEdit = bundle.getBoolean("isEdit", false);
			otherDetailsModel = bundle.getParcelable("otherDetailsModel");
			
			setValues(view);
		} else {
			otherDetailsModel = new OthersDetailsModel();
		}
		
		imgImage1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				isImage1 = true;
				fileImage1 = new File(getActivity().getExternalFilesDir(null) + File.separator + ".frontimage" + System.currentTimeMillis() + ".png");
				final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImage1));
				intent.putExtra("return-data", true);
				startActivityForResult(intent, CAMERA_REQUEST);
			}
		});
		
		imgImage2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				isImage1 = false;
				fileImage2 = new File(getActivity().getExternalFilesDir(null) + File.separator + ".backimage" + System.currentTimeMillis() + ".png");
				final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImage2));
				intent.putExtra("return-data", true);
				startActivityForResult(intent, CAMERA_REQUEST);
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
			if ( saveDetails() ) {
				getFragmentManager().popBackStackImmediate();	
			}
		} else if (item.getItemId() == android.R.id.home) {
			getFragmentManager().popBackStack();
	        return true;
	    }
		
		return super.onOptionsItemSelected(item);
	}
	
	private void setValues(View view) {
		
		edtName.setText(otherDetailsModel.getName());
		edtDescription.setText(otherDetailsModel.getDescription());
		
		if ( !TextUtils.isEmpty(otherDetailsModel.getImage1()) && new File(otherDetailsModel.getImage1()).exists() ) {
			fileImage1 = new File(otherDetailsModel.getImage1());
			imgImage1.setImageURI(Uri.fromFile(fileImage1));
		}
		
		if ( !TextUtils.isEmpty(otherDetailsModel.getImage2()) && new File(otherDetailsModel.getImage2()).exists() ) {
			fileImage2 = new File(otherDetailsModel.getImage2());
			imgImage2.setImageURI(Uri.fromFile(fileImage2));
		}
	}
	
	private boolean saveDetails() {
		
		if ( edtName.getText().toString().trim().equalsIgnoreCase("") ) {
			edtName.setError(getString(R.string.error_enter_name));
			edtName.requestFocus();
			return false;
		}
		
		otherDetailsModel.setName(edtName.getText().toString());
		otherDetailsModel.setDescription(edtDescription.getText().toString());
		
		if ( fileImage1 != null && fileImage1.exists() ) {
			otherDetailsModel.setImage1(fileImage1.getAbsolutePath());
		}
		
		if ( fileImage2 != null && fileImage2.exists() ) {
			otherDetailsModel.setImage2(fileImage2.getAbsolutePath());
		}
		
		final DatabaseHelper databaseHelper = ((MyWallet)getActivity().getApplication()).getDatabaseHelper();
		databaseHelper.insertOrUpdateOthersDetails(otherDetailsModel, isEdit);
		
		return true;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		Log.e(getTag(), "onActivityResult() called : requestCode : " + requestCode
				+", resultCode : " + resultCode);
		
		if ( requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK ) {
			
//			Log.e(getTag(), "File Path : " + ((fileFrontImage == null) ? "Null" : fileFrontImage.getAbsolutePath()));
//			Log.e(getTag(), "URI : " + ((data == null) ? "Null" : data.getData().toString()));
			
			final Intent intent = new Intent(getActivity(), CropImage.class);
			if ( isImage1 ) {
				intent.putExtra(CropImage.IMAGE_PATH, fileImage1.getPath());
			} else {
				intent.putExtra(CropImage.IMAGE_PATH, fileImage2.getPath());
			}
	        intent.putExtra(CropImage.SCALE, true);

	        intent.putExtra(CropImage.ASPECT_X, 3);
	        intent.putExtra(CropImage.ASPECT_Y, 2);
			
	        startActivityForResult(intent, CAMERA_CROP);
		} else if ( requestCode == CAMERA_CROP && resultCode == Activity.RESULT_OK ) {
//			Log.e(getTag(), "File Path : " + ((fileFrontImage == null) ? "Null" : fileFrontImage.getAbsolutePath()));
			
			String path = data.getStringExtra(CropImage.IMAGE_PATH);
			
			Log.e(getTag(), "New File Path : " + path);
            if (path == null) {

                return;
            }
            if ( isImage1 ) {
            	imgImage1.setImageBitmap(null);
            	imgImage1.setImageURI(Uri.fromFile(fileImage1));
            } else {
            	imgImage2.setImageBitmap(null);
            	imgImage2.setImageURI(Uri.fromFile(fileImage2));
            }
		}
	}
}