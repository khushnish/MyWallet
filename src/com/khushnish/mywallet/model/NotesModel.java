package com.khushnish.mywallet.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NotesModel implements Parcelable {

	private long id = 0;
	private String name = "";
	private String description = "";

	public NotesModel() {
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

	protected NotesModel(Parcel in) {
		id = in.readLong();
		name = in.readString();
		description = in.readString();
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