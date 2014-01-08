package com.khushnish.mywallet.database;

public class DBConstants {

	public static final String ID = "ID";

	public static final String TBL_CARDDETAILS = "CardDetails";
	public static final String COL_CARDDETAILS_CARDTYPE = "CardType";
	public static final String COL_CARDDETAILS_OTHERCARDNAME = "OtherCardName";
	public static final String COL_CARDDETAILS_CARDUSERTYPE = "CardUserType";
	public static final String COL_CARDDETAILS_BANKNAME = "BankName";
	public static final String COL_CARDDETAILS_NAME = "Name";
	public static final String COL_CARDDETAILS_BANKACCOUNTNUMBER = "BankAccountNumber";
	public static final String COL_CARDDETAILS_BANKCUSTOMERID = "BankCustomerId";
	public static final String COL_CARDDETAILS_CARDNUMBER = "CardNumber";
	public static final String COL_CARDDETAILS_CARDHOLDERNAME = "CardHolderName";
	public static final String COL_CARDDETAILS_CARDCVVNUMBER = "CardCVVNumber";
	public static final String COL_CARDDETAILS_CARDATMPINNUMBER = "CardATMPinNumber";
	public static final String COL_CARDDETAILS_BANKMOBILEPINNUMBER = "BankMobilePinNumber";
	public static final String COL_CARDDETAILS_CARDTRANSACTIONPASSWORD = "CardTransactionPassword";
	public static final String COL_CARDDETAILS_VALIDFROMMONTH = "ValidFromMonth";
	public static final String COL_CARDDETAILS_VALIDFROMYEAR = "ValidFromYear";
	public static final String COL_CARDDETAILS_VALIDTILLMONTH = "ValidTillMonth";
	public static final String COL_CARDDETAILS_VALIDTILLYEAR = "ValidTillYear";
	public static final String COL_CARDDETAILS_IMAGEFRONT = "ImageFront";
	public static final String COL_CARDDETAILS_IMAGEBACK = "ImageBack";
	
	public static final String TBL_BANKDETAILS = "BankDetails";
	public static final String COL_BANKDETAILS_NAME = "Name";
	public static final String COL_BANKDETAILS_BANKNAME = "BankName";
	public static final String COL_BANKDETAILS_PROFILENAME = "ProfileName";
	public static final String COL_BANKDETAILS_BANKACCOUNTNUMBER = "BankAccountNumber";
	public static final String COL_BANKDETAILS_BALANCE = "Balance";
	public static final String COL_BANKDETAILS_BANKCUSTOMERID = "BankCustomerId";
	public static final String COL_BANKDETAILS_LOGINUSERNAME = "LoginUserName";
	public static final String COL_BANKDETAILS_LOGINPASSWORD = "LoginPassword";
	public static final String COL_BANKDETAILS_TRANSACTIONPASSWORD = "TransactionPassword";
	public static final String COL_BANKDETAILS_MOBILEPINNUMBER = "MobilePinNumber";
	public static final String COL_BANKDETAILS_OTHERS = "Others";
	
	public static final String TBL_LOANDETAILS = "LoanDetails";
	public static final String COL_LOANDETAILS_NAME = "Name";
	public static final String COL_LOANDETAILS_LOANACCOUNTNUMER = "LoanAccountNumer";
	public static final String COL_LOANDETAILS_BANKNAME = "BankName";
	public static final String COL_LOANDETAILS_BRANCHNAME = "BranchName";
	public static final String COL_LOANDETAILS_LOANDATE = "LoanDate";
	public static final String COL_LOANDETAILS_SANCTIONDATE = "SanctionDate";
	public static final String COL_LOANDETAILS_EMI = "EMI";
	public static final String COL_LOANDETAILS_RATEOFINTEREST = "RateOfInterest";
	public static final String COL_LOANDETAILS_TENURE = "Tenure";
	public static final String COL_LOANDETAILS_LOANAMOUNT = "LoanAmount";
	public static final String COL_LOANDETAILS_DISTRIBUTEDAMOUNT = "DistributedAmount";
	public static final String COL_LOANDETAILS_OUTSTANDINGAMOUNT = "OutstandingAmount";
	public static final String COL_LOANDETAILS_OTHER = "Other";
	
	public static final String TBL_DRIVERLICENSEDETAILS = "DriverLicenseDetails";
	public static final String COL_DRIVERLICENSEDETAILS_NAME = "Name";
	public static final String COL_DRIVERLICENSEDETAILS_DRIVINGLICENSENUMBER = "DrivingLicenseNumber";
	public static final String COL_DRIVERLICENSEDETAILS_ADDRESS = "Address";
	public static final String COL_DRIVERLICENSEDETAILS_ISSUEDON = "IssuedOn";
	public static final String COL_DRIVERLICENSEDETAILS_DATEOFBIRTH = "DateOfBirth";
	public static final String COL_DRIVERLICENSEDETAILS_TELEPHONENUMBER = "TelephoneNumber";
	public static final String COL_DRIVERLICENSEDETAILS_LICENCEFOR = "LicenceFor";
	public static final String COL_DRIVERLICENSEDETAILS_VALIDFROM = "ValidFrom";
	public static final String COL_DRIVERLICENSEDETAILS_VALIDTILL = "ValidTill";
	public static final String COL_DRIVERLICENSEDETAILS_FRONTIMAGE = "FrontImage";
	public static final String COL_DRIVERLICENSEDETAILS_OTHER = "Other";
	
	public static final String TBL_PASSPORTDETAILS = "PassportDetails";
	public static final String COL_PASSPORTDETAILS_NAME = "Name";
	public static final String COL_PASSPORTDETAILS_PASSPORTNUMBER = "PassportNumber";
	public static final String COL_PASSPORTDETAILS_TYPE = "Type";
	public static final String COL_PASSPORTDETAILS_COUNTRYCODE = "CountryCode";
	public static final String COL_PASSPORTDETAILS_NATIONALITY = "Nationality";
	public static final String COL_PASSPORTDETAILS_GENDER = "Gender";
	public static final String COL_PASSPORTDETAILS_DATEOFBIRTH = "DateOfBirth";
	public static final String COL_PASSPORTDETAILS_PLACEOFBIRTH = "PlaceOfBirth";
	public static final String COL_PASSPORTDETAILS_PLACEOFISSUE = "PlaceOfIssue";
	public static final String COL_PASSPORTDETAILS_VALIDFROM = "ValidFrom";
	public static final String COL_PASSPORTDETAILS_VALIDTILL = "ValidTill";
	public static final String COL_PASSPORTDETAILS_FATHERNAME = "FatherName";
	public static final String COL_PASSPORTDETAILS_MOTHERNAME = "MotherName";
	public static final String COL_PASSPORTDETAILS_ADDRESS = "Address";
	public static final String COL_PASSPORTDETAILS_PASSPORTFILENUMBER = "PassportFileNumber";
	public static final String COL_PASSPORTDETAILS_PASSPORTFRONTIMAGE = "PassportFrontImage";
	public static final String COL_PASSPORTDETAILS_PASSPORTBACKIMAGE = "PassportBackImage";
	public static final String COL_PASSPORTDETAILS_OTHER = "Other";

	public static final String TBL_SOCIALDETAILS = "SocialDetails";
	public static final String COL_SOCIALDETAILS_NAME = "Name";
	public static final String COL_SOCIALDETAILS_EMAILADDRESS = "EmailAddress";
	public static final String COL_SOCIALDETAILS_PASSWORD = "Password";

	public static final String TBL_NOTESDETAILS = "NotesDetails";
	public static final String COL_NOTESDETAILS_NAME = "Name";
	public static final String COL_NOTESDETAILS_DESCRIPTION = "Description";
	
	public static final String TBL_OTHERDETAILS = "OtherDetails";
	public static final String COL_OTHERDETAILS_NAME = "Name";
	public static final String COL_OTHERDETAILS_DESCRIPTION = "Description";
	public static final String COL_OTHERDETAILS_IMAGE1 = "Image1";
	public static final String COL_OTHERDETAILS_IMAGE2 = "Image2";
}