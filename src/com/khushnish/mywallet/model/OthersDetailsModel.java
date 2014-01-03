package com.khushnish.mywallet.model;

import android.os.Parcel;
import android.os.Parcelable;

public class OthersDetailsModel implements Parcelable {

	private long id = 0;
	private String name = "";
	private String description = "";
	private String image1 = "";
	private String image2 = "";

	public OthersDetailsModel() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	protected OthersDetailsModel(Parcel in) {
		id = in.readLong();
		name = in.readString();
		description = in.readString();
		image1 = in.readString();
		image2 = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeString(name);
		dest.writeString(description);
		dest.writeString(image1);
		dest.writeString(image2);
	}

	public static final Parcelable.Creator<CardModel> CREATOR = new Parcelable.Creator<CardModel>() {
		@Override
		public CardModel createFromParcel(Parcel in) {
			return new CardModel(in);
		}

		@Override
		public CardModel[] newArray(int size) {
			return new CardModel[size];
		}
	};
}