package com.khushnish.mywallet.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CardModel implements Parcelable {

	private long id = 0;
	// Visa, Master, Mastro, Other
	private String cardType = "";
	private String otherCardName = "";
	// Personal, Business
	private String cardUserType = "";
	private String name = "";
	private String bankName = "";
	private String bankAccountNumber = "";
	private String bankCustomerId = "";
	private String cardNumber = "";
	private String cardHolderName = "";
	private String cardCvvNumber = "";
	private String cardAtmPinNumber = "";
	private String cardTransactionPassword = "";
	private String validFromMonth = "";
	private String validFromYear = "";
	private String validTillMonth = "";
	private String validTillYear = "";
	private String imageFront = "";
	private String imageBack = "";
	private String bankCardMobilePinNumber = "";

	public CardModel() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getOtherCardName() {
		return otherCardName;
	}

	public void setOtherCardName(String otherCardName) {
		this.otherCardName = otherCardName;
	}

	public String getCardUserType() {
		return cardUserType;
	}

	public void setCardUserType(String cardUserType) {
		this.cardUserType = cardUserType;
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

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardCvvNumber() {
		return cardCvvNumber;
	}

	public void setCardCvvNumber(String cardCvvNumber) {
		this.cardCvvNumber = cardCvvNumber;
	}

	public String getCardAtmPinNumber() {
		return cardAtmPinNumber;
	}

	public void setCardAtmPinNumber(String cardAtmPinNumber) {
		this.cardAtmPinNumber = cardAtmPinNumber;
	}

	public String getCardTransactionPassword() {
		return cardTransactionPassword;
	}

	public void setCardTransactionPassword(String cardTransactionPassword) {
		this.cardTransactionPassword = cardTransactionPassword;
	}

	public String getValidFromMonth() {
		return validFromMonth;
	}

	public void setValidFromMonth(String validFromMonth) {
		this.validFromMonth = validFromMonth;
	}

	public String getValidFromYear() {
		return validFromYear;
	}

	public void setValidFromYear(String validFromYear) {
		this.validFromYear = validFromYear;
	}

	public String getValidTillMonth() {
		return validTillMonth;
	}

	public void setValidTillMonth(String validTillMonth) {
		this.validTillMonth = validTillMonth;
	}

	public String getValidTillYear() {
		return validTillYear;
	}

	public void setValidTillYear(String validTillYear) {
		this.validTillYear = validTillYear;
	}

	public String getImageFront() {
		return imageFront;
	}

	public void setImageFront(String imageFront) {
		this.imageFront = imageFront;
	}

	public String getImageBack() {
		return imageBack;
	}

	public void setImageBack(String imageBack) {
		this.imageBack = imageBack;
	}

	public String getBankCardMobilePinNumber() {
		return bankCardMobilePinNumber;
	}

	public void setBankCardMobilePinNumber(String bankCardMobilePinNumber) {
		this.bankCardMobilePinNumber = bankCardMobilePinNumber;
	}

	protected CardModel(Parcel in) {
		id = in.readLong();
		cardType = in.readString();
		otherCardName = in.readString();
		cardUserType = in.readString();
		name = in.readString();
		bankName = in.readString();
		bankAccountNumber = in.readString();
		bankCustomerId = in.readString();
		cardNumber = in.readString();
		cardHolderName = in.readString();
		cardCvvNumber = in.readString();
		cardAtmPinNumber = in.readString();
		cardTransactionPassword = in.readString();
		validFromMonth = in.readString();
		validFromYear = in.readString();
		validTillMonth = in.readString();
		validTillYear = in.readString();
		imageFront = in.readString();
		imageBack = in.readString();
		bankCardMobilePinNumber = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeString(cardType);
		dest.writeString(otherCardName);
		dest.writeString(cardUserType);
		dest.writeString(name);
		dest.writeString(bankName);
		dest.writeString(bankAccountNumber);
		dest.writeString(bankCustomerId);
		dest.writeString(cardNumber);
		dest.writeString(cardHolderName);
		dest.writeString(cardCvvNumber);
		dest.writeString(cardAtmPinNumber);
		dest.writeString(cardTransactionPassword);
		dest.writeString(validFromMonth);
		dest.writeString(validFromYear);
		dest.writeString(validTillMonth);
		dest.writeString(validTillYear);
		dest.writeString(imageFront);
		dest.writeString(imageBack);
		dest.writeString(bankCardMobilePinNumber);
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
