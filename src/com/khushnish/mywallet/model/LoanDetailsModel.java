package com.khushnish.mywallet.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LoanDetailsModel implements Parcelable {

	private long id = 0;
	private String name = "";
	private String loanAccountNumer = "";
	private String bankName = "";
	private String branchName = "";
	private String loanDate = "";
	private String sanctionDate = "";
	private String emi = "";
	private String rateOfInterest = "";
	private String tenure = "";
	private String loanAmount = "";
	private String distributedAmount = "";
	private String outstandingAmount = "";
	private String others = "";

	public LoanDetailsModel() {
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

	public String getLoanAccountNumer() {
		return loanAccountNumer;
	}

	public void setLoanAccountNumer(String loanAccountNumer) {
		this.loanAccountNumer = loanAccountNumer;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public String getSanctionDate() {
		return sanctionDate;
	}

	public void setSanctionDate(String sanctionDate) {
		this.sanctionDate = sanctionDate;
	}

	public String getEmi() {
		return emi;
	}

	public void setEmi(String emi) {
		this.emi = emi;
	}

	public String getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(String rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public String getTenure() {
		return tenure;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getDistributedAmount() {
		return distributedAmount;
	}

	public void setDistributedAmount(String distributedAmount) {
		this.distributedAmount = distributedAmount;
	}

	public String getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(String outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	protected LoanDetailsModel(Parcel in) {
		id = in.readLong();
		name = in.readString();
		loanAccountNumer = in.readString();
		bankName = in.readString();
		branchName = in.readString();
		loanDate = in.readString();
		sanctionDate = in.readString();
		emi = in.readString();
		rateOfInterest = in.readString();
		tenure = in.readString();
		loanAmount = in.readString();
		distributedAmount = in.readString();
		outstandingAmount = in.readString();
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
		dest.writeString(loanAccountNumer);
		dest.writeString(bankName);
		dest.writeString(branchName);
		dest.writeString(loanDate);
		dest.writeString(sanctionDate);
		dest.writeString(emi);
		dest.writeString(rateOfInterest);
		dest.writeString(tenure);
		dest.writeString(loanAmount);
		dest.writeString(distributedAmount);
		dest.writeString(outstandingAmount);
		dest.writeString(others);
	}

	public static final Parcelable.Creator<LoanDetailsModel> CREATOR = new Parcelable.Creator<LoanDetailsModel>() {
		@Override
		public LoanDetailsModel createFromParcel(Parcel in) {
			return new LoanDetailsModel(in);
		}

		@Override
		public LoanDetailsModel[] newArray(int size) {
			return new LoanDetailsModel[size];
		}
	};
}
