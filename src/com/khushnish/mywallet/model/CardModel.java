package com.khushnish.mywallet.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CardModel implements Parcelable {

	private long id = 0;
	// Visa, Master, Mastro, Other
	private int cardType = 0;
	private String otherCardName = "";
	// Personal, Business
	private int cardUserType = 0;
	private String bankName = "";
	private String bankAccountNumber = "";
	private String bankCustomerId = "";
	private String cardNumber = "";
	private String cardHolderName = "";
	private int cardCvvNumber = 0;
	private int cardAtmPinNumber = 0;
	private String cardTransactionPassword = "";
	private int validFromMonth = 0;
	private int validFromYear = 0;
	private int validTillMonth = 0;
	private int validTillYear = 0;
	private String imageFront = "";
	private String imageBack = "";
	private int bankCardMobilePinNumber = 0;
	
	public CardModel() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public String getOtherCardName() {
		return otherCardName;
	}

	public void setOtherCardName(String otherCardName) {
		this.otherCardName = otherCardName;
	}

	public int getCardUserType() {
		return cardUserType;
	}

	public void setCardUserType(int cardUserType) {
		this.cardUserType = cardUserType;
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

	public int getCardCvvNumber() {
		return cardCvvNumber;
	}

	public void setCardCvvNumber(int cardCvvNumber) {
		this.cardCvvNumber = cardCvvNumber;
	}

	public int getCardAtmPinNumber() {
		return cardAtmPinNumber;
	}

	public void setCardAtmPinNumber(int cardAtmPinNumber) {
		this.cardAtmPinNumber = cardAtmPinNumber;
	}

	public String getCardTransactionPassword() {
		return cardTransactionPassword;
	}

	public void setCardTransactionPassword(String cardTransactionPassword) {
		this.cardTransactionPassword = cardTransactionPassword;
	}

	public int getValidFromMonth() {
		return validFromMonth;
	}

	public void setValidFromMonth(int validFromMonth) {
		this.validFromMonth = validFromMonth;
	}

	public int getValidFromYear() {
		return validFromYear;
	}

	public void setValidFromYear(int validFromYear) {
		this.validFromYear = validFromYear;
	}

	public int getValidTillMonth() {
		return validTillMonth;
	}

	public void setValidTillMonth(int validTillMonth) {
		this.validTillMonth = validTillMonth;
	}

	public int getValidTillYear() {
		return validTillYear;
	}

	public void setValidTillYear(int validTillYear) {
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

	public int getBankCardMobilePinNumber() {
		return bankCardMobilePinNumber;
	}

	public void setBankCardMobilePinNumber(int bankCardMobilePinNumber) {
		this.bankCardMobilePinNumber = bankCardMobilePinNumber;
	}

	protected CardModel(Parcel in) {
		id = in.readLong();
		cardType = in.readInt();
		otherCardName = in.readString();
		cardUserType = in.readInt();
		bankName = in.readString();
		bankAccountNumber = in.readString();
		bankCustomerId = in.readString();
		cardNumber = in.readString();
		cardHolderName = in.readString();
		cardCvvNumber = in.readInt();
		cardAtmPinNumber = in.readInt();
		cardTransactionPassword = in.readString();
		validFromMonth = in.readInt();
		validFromYear = in.readInt();
		validTillMonth = in.readInt();
		validTillYear = in.readInt();
		imageFront = in.readString();
		imageBack = in.readString();
		bankCardMobilePinNumber = in.readInt();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeInt(cardType);
		dest.writeString(otherCardName);
		dest.writeInt(cardUserType);
		dest.writeString(bankName);
		dest.writeString(bankAccountNumber);
		dest.writeString(bankCustomerId);
		dest.writeString(cardNumber);
		dest.writeString(cardHolderName);
		dest.writeInt(cardCvvNumber);
		dest.writeInt(cardAtmPinNumber);
		dest.writeString(cardTransactionPassword);
		dest.writeInt(validFromMonth);
		dest.writeInt(validFromYear);
		dest.writeInt(validTillMonth);
		dest.writeInt(validTillYear);
		dest.writeString(imageFront);
		dest.writeString(imageBack);
		dest.writeInt(bankCardMobilePinNumber);
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
