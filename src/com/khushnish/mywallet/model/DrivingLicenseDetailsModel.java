package com.khushnish.mywallet.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DrivingLicenseDetailsModel implements Parcelable {

	private long id = 0;
	private String name = "";
	private String drivingLicenseNumber = "";
	private String address = "";
	private String issuedOn = "";
	private String dateOfBirth = "";
	private String telephoneNumber = "";
	private String licenceFor = "";
	private String validFrom = "";
	private String validTill = "";
	private String frontImage = "";
	private String others = "";

	public DrivingLicenseDetailsModel() {
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

	public String getDrivingLicenseNumber() {
		return drivingLicenseNumber;
	}

	public void setDrivingLicenseNumber(String drivingLicenseNumber) {
		this.drivingLicenseNumber = drivingLicenseNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIssuedOn() {
		return issuedOn;
	}

	public void setIssuedOn(String issuedOn) {
		this.issuedOn = issuedOn;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getLicenceFor() {
		return licenceFor;
	}

	public void setLicenceFor(String licenceFor) {
		this.licenceFor = licenceFor;
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

	public String getFrontImage() {
		return frontImage;
	}

	public void setFrontImage(String frontImage) {
		this.frontImage = frontImage;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	protected DrivingLicenseDetailsModel(Parcel in) {
		id = in.readLong();
		name = in.readString();
		drivingLicenseNumber = in.readString();
		address = in.readString();
		issuedOn = in.readString();
		dateOfBirth = in.readString();
		telephoneNumber = in.readString();
		licenceFor = in.readString();
		validFrom = in.readString();
		validTill = in.readString();
		frontImage = in.readString();
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
		dest.writeString(drivingLicenseNumber);
		dest.writeString(address);
		dest.writeString(issuedOn);
		dest.writeString(dateOfBirth);
		dest.writeString(telephoneNumber);
		dest.writeString(licenceFor);
		dest.writeString(validFrom);
		dest.writeString(validTill);
		dest.writeString(frontImage);
		dest.writeString(others);
	}

	public static final Parcelable.Creator<DrivingLicenseDetailsModel> CREATOR = new Parcelable.Creator<DrivingLicenseDetailsModel>() {
		@Override
		public DrivingLicenseDetailsModel createFromParcel(Parcel in) {
			return new DrivingLicenseDetailsModel(in);
		}

		@Override
		public DrivingLicenseDetailsModel[] newArray(int size) {
			return new DrivingLicenseDetailsModel[size];
		}
	};
}
