package com.khushnish.mywallet.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BankDetailsModel implements Parcelable {

	private long id = 0;
	private String name = "";
	private String bankName = "";
	private String profileName = "";
	private String bankAccountNumber = "";
	private String balance = "";
	private String bankCustomerId = "";
	private String loginUserName = "";
	private String loginPassword = "";
	private String transactionPassword = "";
	private String mobilePinNumber = "";
	private String others = "";

	public BankDetailsModel() {
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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankCustomerId() {
		return bankCustomerId;
	}

	public void setBankCustomerId(String bankCustomerId) {
		this.bankCustomerId = bankCustomerId;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getLoginUserName() {
		return loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getTransactionPassword() {
		return transactionPassword;
	}

	public void setTransactionPassword(String transactionPassword) {
		this.transactionPassword = transactionPassword;
	}

	public String getMobilePinNumber() {
		return mobilePinNumber;
	}

	public void setMobilePinNumber(String mobilePinNumber) {
		this.mobilePinNumber = mobilePinNumber;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	protected BankDetailsModel(Parcel in) {
		id = in.readLong();
		name = in.readString();
		bankName = in.readString();
		profileName = in.readString();
		bankAccountNumber = in.readString();
		balance = in.readString();
		bankCustomerId = in.readString();
		loginUserName = in.readString();
		loginPassword = in.readString();
		transactionPassword = in.readString();
		mobilePinNumber = in.readString();
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
		dest.writeString(bankName);
		dest.writeString(profileName);
		dest.writeString(bankAccountNumber);
		dest.writeString(balance);
		dest.writeString(bankCustomerId);
		dest.writeString(loginUserName);
		dest.writeString(loginPassword);
		dest.writeString(transactionPassword);
		dest.writeString(mobilePinNumber);
		dest.writeString(others);
	}

	public static final Parcelable.Creator<BankDetailsModel> CREATOR = new Parcelable.Creator<BankDetailsModel>() {
		@Override
		public BankDetailsModel createFromParcel(Parcel in) {
			return new BankDetailsModel(in);
		}

		@Override
		public BankDetailsModel[] newArray(int size) {
			return new BankDetailsModel[size];
		}
	};
}
