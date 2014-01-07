package com.khushnish.mywallet.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PassportDetailsModel implements Parcelable {

	private long id = 0;
	private String name = "";
	private String passportNumber = "";
	private String type = "";
	private String countryCode = "";
	private String nationality = "";
	private String gender = "";
	private String dateOfBirth = "";
	private String placeOfBirth = "";
	private String placeOfIssue = "";
	private String validFrom = "";
	private String validTill = "";
	private String fatherName = "";
	private String motherName = "";
	private String address = "";
	private String passportFileNumber = "";
	private String passportFrontImage = "";
	private String passportBackImage = "";
	private String others = "";

	public PassportDetailsModel() {
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

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getPlaceOfIssue() {
		return placeOfIssue;
	}

	public void setPlaceOfIssue(String placeOfIssue) {
		this.placeOfIssue = placeOfIssue;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidTill() {
		return validTill;
	}

	public void setValidTill(String validTill) {
		this.validTill = validTill;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassportFileNumber() {
		return passportFileNumber;
	}

	public void setPassportFileNumber(String passportFileNumber) {
		this.passportFileNumber = passportFileNumber;
	}

	public String getPassportFrontImage() {
		return passportFrontImage;
	}

	public void setPassportFrontImage(String passportFrontImage) {
		this.passportFrontImage = passportFrontImage;
	}

	public String getPassportBackImage() {
		return passportBackImage;
	}

	public void setPassportBackImage(String passportBackImage) {
		this.passportBackImage = passportBackImage;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	protected PassportDetailsModel(Parcel in) {
		id = in.readLong();
		name = in.readString();
		passportNumber = in.readString();
		type = in.readString();
		countryCode = in.readString();
		nationality = in.readString();
		gender = in.readString();
		dateOfBirth = in.readString();
		placeOfBirth = in.readString();
		placeOfIssue = in.readString();
		validFrom = in.readString();
		validTill = in.readString();
		fatherName = in.readString();
		motherName = in.readString();
		address = in.readString();
		passportFileNumber = in.readString();
		passportFrontImage = in.readString();
		passportBackImage = in.readString();
		others = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeString(name);
		dest.writeString(passportNumber);
		dest.writeString(type);
		dest.writeString(countryCode);
		dest.writeString(nationality);
		dest.writeString(gender);
		dest.writeString(dateOfBirth);
		dest.writeString(placeOfBirth);
		dest.writeString(placeOfIssue);
		dest.writeString(validFrom);
		dest.writeString(validTill);
		dest.writeString(fatherName);
		dest.writeString(motherName);
		dest.writeString(address);
		dest.writeString(passportFileNumber);
		dest.writeString(passportFrontImage);
		dest.writeString(passportBackImage);
		dest.writeString(others);
	}

	public static final Parcelable.Creator<PassportDetailsModel> CREATOR = new Parcelable.Creator<PassportDetailsModel>() {
		@Override
		public PassportDetailsModel createFromParcel(Parcel in) {
			return new PassportDetailsModel(in);
		}

		@Override
		public PassportDetailsModel[] newArray(int size) {
			return new PassportDetailsModel[size];
		}
	};
}
