package com.khushnish.mywallet.model;

public class CardModel {

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

	public void setCardCVVNumber(int cardCvvNumber) {
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
}