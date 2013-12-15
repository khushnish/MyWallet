package com.khushnish.mywallet.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SocialModel implements Parcelable {

	private long id = 0;
	private String name = "";
	private String emailAddress = "";
	private String password = "";

	public SocialModel() {
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	protected SocialModel(Parcel in) {
		id = in.readLong();
		name = in.readString();
		emailAddress = in.readString();
		password = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeString(name);
		dest.writeString(emailAddress);
		dest.writeString(password);
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
