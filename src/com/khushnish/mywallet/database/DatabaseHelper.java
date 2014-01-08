package com.khushnish.mywallet.database;

import java.util.ArrayList;

import javax.crypto.SecretKey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.khushnish.mywallet.R;
import com.khushnish.mywallet.model.BankDetailsModel;
import com.khushnish.mywallet.model.CardModel;
import com.khushnish.mywallet.model.DrivingLicenseDetailsModel;
import com.khushnish.mywallet.model.LoanDetailsModel;
import com.khushnish.mywallet.model.NotesModel;
import com.khushnish.mywallet.model.OthersDetailsModel;
import com.khushnish.mywallet.model.PassportDetailsModel;
import com.khushnish.mywallet.model.SocialModel;
import com.khushnish.mywallet.utils.Crypto;
import com.khushnish.mywallet.utils.Utils;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private SQLiteDatabase database;
	private Encryptor encryptor;
	
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, context.getString(R.string.db_name), null, version);
		encryptor = PADDING_ENCRYPTOR;
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		this.database = database;
		
		if (!this.database.isReadOnly()) {
			this.database.execSQL("PRAGMA foreign_keys = ON;");
		}
		
		final String cardDetailsTable = "CREATE TABLE IF NOT EXISTS CardDetails (ID INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , CardType TEXT, OtherCardName TEXT, CardUserType TEXT, Name TEXT, BankName TEXT, BankAccountNumber TEXT, BankCustomerId TEXT, CardNumber TEXT, CardHolderName TEXT, CardCVVNumber TEXT, CardATMPinNumber TEXT, CardTransactionPassword TEXT, BankMobilePinNumber TEXT, ValidFromMonth TEXT, ValidFromYear TEXT, ValidTillMonth TEXT, ValidTillYear TEXT, ImageFront TEXT, ImageBack TEXT)";
		final String bankDetailsTable = "CREATE TABLE IF NOT EXISTS BankDetails (ID INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , Name TEXT, BankName TEXT, ProfileName TEXT, BankAccountNumber TEXT, Balance TEXT, BankCustomerId TEXT, LoginUserName TEXT, LoginPassword TEXT, TransactionPassword TEXT, MobilePinNumber TEXT, Others TEXT)";
		final String loanDetailsTable = "CREATE TABLE IF NOT EXISTS LoanDetails (ID INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , Name TEXT, LoanAccountNumer TEXT, BankName TEXT, BranchName TEXT, LoanDate TEXT, SanctionDate TEXT, EMI TEXT, RateOfInterest TEXT, Tenure TEXT, LoanAmount TEXT, DistributedAmount TEXT, OutstandingAmount TEXT, Other TEXT)";
		final String drivingLicenceDetailsTable = "CREATE TABLE IF NOT EXISTS DriverLicenseDetails (ID INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , Name TEXT, DrivingLicenseNumber TEXT, Address TEXT, IssuedOn TEXT, DateOfBirth TEXT, TelephoneNumber TEXT, LicenceFor TEXT, ValidFrom TEXT, ValidTill TEXT, FrontImage TEXT, Other TEXT)";
		final String passportDetailsTable = "CREATE TABLE IF NOT EXISTS PassportDetails (ID INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , Name TEXT, PassportNumber TEXT, Type TEXT, CountryCode TEXT, Nationality TEXT, Gender TEXT, DateOfBirth TEXT, PlaceOfBirth TEXT, PlaceOfIssue TEXT, ValidFrom TEXT, ValidTill TEXT, FatherName TEXT, MotherName TEXT, Address TEXT, PassportFileNumber TEXT, PassportFrontImage TEXT, PassportBackImage TEXT, Other TEXT)";
		final String notesDetailsTable = "CREATE TABLE IF NOT EXISTS NotesDetails (ID INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , Name TEXT, Description TEXT)";
		final String socialDetailsTable = "CREATE TABLE IF NOT EXISTS SocialDetails (ID INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , Name TEXT, EmailAddress TEXT, Password TEXT)";
		final String otherDetailsTable = "CREATE TABLE IF NOT EXISTS OtherDetails (ID INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , Name TEXT, Description TEXT, Image1 TEXT, Image2 TEXT)";
		
		this.database.execSQL(cardDetailsTable);
		this.database.execSQL(bankDetailsTable);
		this.database.execSQL(loanDetailsTable);
		this.database.execSQL(drivingLicenceDetailsTable);
		this.database.execSQL(passportDetailsTable);
		this.database.execSQL(notesDetailsTable);
		this.database.execSQL(socialDetailsTable);
		this.database.execSQL(otherDetailsTable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqliteDatabase, int oldVersion, int newVersion) {
		
	}
	
	public void open() {
		database = this.getWritableDatabase();
	}
	
	public boolean isOpen() {
		if (database == null)
			return false;
		return database.isOpen();
	}
	
	@Override
	public synchronized void close() {
		super.close();
		database.close();
	}
	
	public ArrayList<CardModel> getAllCardDetails() {
		final ArrayList<CardModel> cardModels = new ArrayList<CardModel>();
		if ( database == null ) {
			open();
		}
		
		Cursor cursor = null;
		
		try {
			cursor = database.query(DBConstants.TBL_CARDDETAILS, new String[] {"*"}, null,
					null, null, null, null);
			
			if ( cursor.getCount() > 0 ) {
				CardModel cardModel;
				for (int i = 0; i < cursor.getCount(); ++i) {
					cursor.moveToNext();
					cardModel = new CardModel();
					cardModel.setId(cursor.getLong(cursor.getColumnIndex(DBConstants.ID)));
					cardModel.setCardType(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_CARDDETAILS_CARDTYPE)), Utils.key));
					cardModel.setOtherCardName(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_CARDDETAILS_OTHERCARDNAME)), Utils.key));
					cardModel.setCardUserType(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_CARDDETAILS_CARDUSERTYPE)), Utils.key));
					cardModel.setName(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_CARDDETAILS_NAME)), Utils.key));
					cardModel.setBankName(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_CARDDETAILS_BANKNAME)), Utils.key));
					cardModel.setBankAccountNumber(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_CARDDETAILS_BANKACCOUNTNUMBER)), Utils.key));
					cardModel.setBankCustomerId(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_CARDDETAILS_BANKCUSTOMERID)), Utils.key));
					cardModel.setCardNumber(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_CARDDETAILS_CARDNUMBER)), Utils.key));
					cardModel.setCardHolderName(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_CARDDETAILS_CARDHOLDERNAME)), Utils.key));
					cardModel.setCardCvvNumber(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_CARDDETAILS_CARDCVVNUMBER)), Utils.key));
					cardModel.setCardAtmPinNumber(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_CARDDETAILS_CARDATMPINNUMBER)), Utils.key));
					cardModel.setBankCardMobilePinNumber(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_CARDDETAILS_BANKMOBILEPINNUMBER)), Utils.key));
					cardModel.setCardTransactionPassword(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_CARDDETAILS_CARDTRANSACTIONPASSWORD)), Utils.key));
					cardModel.setValidFromMonth(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_CARDDETAILS_VALIDFROMMONTH)), Utils.key));
					cardModel.setValidFromYear(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_CARDDETAILS_VALIDFROMYEAR)), Utils.key));
					cardModel.setValidTillMonth(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_CARDDETAILS_VALIDTILLMONTH)), Utils.key));
					cardModel.setValidTillYear(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_CARDDETAILS_VALIDTILLYEAR)), Utils.key));
					cardModel.setImageFront(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_CARDDETAILS_IMAGEFRONT)), Utils.key));
					cardModel.setImageBack(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_CARDDETAILS_IMAGEBACK)), Utils.key));
					cardModels.add(cardModel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( cursor != null && !cursor.isClosed() ) {
				cursor.close();
			}
		}
		cardModels.trimToSize();
		return cardModels;
	}
	
	public long insertOrUpdateCardDetails( CardModel cardModel, boolean isEdit ) {
		if ( database == null ) {
			open();
		}
		
		try {
			final ContentValues values = new ContentValues();
			values.put(DBConstants.COL_CARDDETAILS_CARDTYPE, encryptor.encrypt(cardModel.getCardType(), Utils.key));
			values.put(DBConstants.COL_CARDDETAILS_OTHERCARDNAME, encryptor.encrypt(cardModel.getOtherCardName(), Utils.key));
			values.put(DBConstants.COL_CARDDETAILS_CARDUSERTYPE, encryptor.encrypt(cardModel.getCardUserType(), Utils.key));
			values.put(DBConstants.COL_CARDDETAILS_NAME, encryptor.encrypt(cardModel.getName(), Utils.key));
			values.put(DBConstants.COL_CARDDETAILS_BANKNAME, encryptor.encrypt(cardModel.getBankName(), Utils.key));
			values.put(DBConstants.COL_CARDDETAILS_BANKACCOUNTNUMBER, encryptor.encrypt(cardModel.getBankAccountNumber(), Utils.key));
			values.put(DBConstants.COL_CARDDETAILS_BANKCUSTOMERID, encryptor.encrypt(cardModel.getBankCustomerId(), Utils.key));
			values.put(DBConstants.COL_CARDDETAILS_CARDNUMBER, encryptor.encrypt(cardModel.getCardNumber(), Utils.key));
			values.put(DBConstants.COL_CARDDETAILS_CARDHOLDERNAME, encryptor.encrypt(cardModel.getCardHolderName(), Utils.key));
			values.put(DBConstants.COL_CARDDETAILS_CARDCVVNUMBER, encryptor.encrypt(cardModel.getCardCvvNumber(), Utils.key));
			values.put(DBConstants.COL_CARDDETAILS_CARDATMPINNUMBER, encryptor.encrypt(cardModel.getCardAtmPinNumber(), Utils.key));
			values.put(DBConstants.COL_CARDDETAILS_BANKMOBILEPINNUMBER, encryptor.encrypt(cardModel.getBankCardMobilePinNumber(), Utils.key));
			values.put(DBConstants.COL_CARDDETAILS_CARDTRANSACTIONPASSWORD, encryptor.encrypt(cardModel.getCardTransactionPassword(), Utils.key));
			values.put(DBConstants.COL_CARDDETAILS_VALIDFROMMONTH, encryptor.encrypt(cardModel.getValidFromMonth(), Utils.key));
			values.put(DBConstants.COL_CARDDETAILS_VALIDFROMYEAR, encryptor.encrypt(cardModel.getValidFromYear(), Utils.key));
			values.put(DBConstants.COL_CARDDETAILS_VALIDTILLMONTH, encryptor.encrypt(cardModel.getValidTillMonth(), Utils.key));
			values.put(DBConstants.COL_CARDDETAILS_VALIDTILLYEAR, encryptor.encrypt(cardModel.getValidTillYear(), Utils.key));
			values.put(DBConstants.COL_CARDDETAILS_IMAGEFRONT, encryptor.encrypt(cardModel.getImageFront(), Utils.key));
			values.put(DBConstants.COL_CARDDETAILS_IMAGEBACK, encryptor.encrypt(cardModel.getImageBack(), Utils.key));
			
			if ( isEdit ) {
				database.update(DBConstants.TBL_CARDDETAILS, values, DBConstants.ID + "=?",
						new String[] {String.valueOf(cardModel.getId())});
				return cardModel.getId();
			} else {
				return database.insert(DBConstants.TBL_CARDDETAILS, null, values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<BankDetailsModel> getAllBankDetails() {
		final ArrayList<BankDetailsModel> bankDetailsModels = new ArrayList<BankDetailsModel>();
		if ( database == null ) {
			open();
		}
		
		Cursor cursor = null;
		
		try {
			cursor = database.query(DBConstants.TBL_BANKDETAILS, new String[] {"*"}, null,
					null, null, null, null);
			
			if ( cursor.getCount() > 0 ) {
				BankDetailsModel bankDetailsModel;
				for (int i = 0; i < cursor.getCount(); ++i) {
					cursor.moveToNext();
					bankDetailsModel = new BankDetailsModel();
					bankDetailsModel.setId(cursor.getLong(cursor.getColumnIndex(DBConstants.ID)));
					bankDetailsModel.setName(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_BANKDETAILS_NAME)), Utils.key));
					bankDetailsModel.setBankName(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_BANKDETAILS_BANKNAME)), Utils.key));
					bankDetailsModel.setProfileName(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_BANKDETAILS_PROFILENAME)), Utils.key));
					bankDetailsModel.setBankAccountNumber(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_BANKDETAILS_BANKACCOUNTNUMBER)), Utils.key));
					bankDetailsModel.setBalance(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_BANKDETAILS_BALANCE)), Utils.key));
					bankDetailsModel.setBankCustomerId(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_BANKDETAILS_BANKCUSTOMERID)), Utils.key));
					bankDetailsModel.setLoginUserName(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_BANKDETAILS_LOGINUSERNAME)), Utils.key));
					bankDetailsModel.setLoginPassword(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_BANKDETAILS_LOGINPASSWORD)), Utils.key));
					bankDetailsModel.setTransactionPassword(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_BANKDETAILS_TRANSACTIONPASSWORD)), Utils.key));
					bankDetailsModel.setMobilePinNumber(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_BANKDETAILS_MOBILEPINNUMBER)), Utils.key));
					bankDetailsModel.setOthers(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_BANKDETAILS_OTHERS)), Utils.key));
					bankDetailsModels.add(bankDetailsModel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( cursor != null && !cursor.isClosed() ) {
				cursor.close();
			}
		}
		bankDetailsModels.trimToSize();
		return bankDetailsModels;
	}
	
	public long insertOrUpdateBankDetails( BankDetailsModel bankDetailsModel, boolean isEdit ) {
		if ( database == null ) {
			open();
		}
		
		try {
			final ContentValues values = new ContentValues();
			values.put(DBConstants.COL_BANKDETAILS_NAME, encryptor.encrypt(bankDetailsModel.getName(), Utils.key));
			values.put(DBConstants.COL_BANKDETAILS_BANKNAME, encryptor.encrypt(bankDetailsModel.getBankName(), Utils.key));
			values.put(DBConstants.COL_BANKDETAILS_PROFILENAME, encryptor.encrypt(bankDetailsModel.getProfileName(), Utils.key));
			values.put(DBConstants.COL_BANKDETAILS_BANKACCOUNTNUMBER, encryptor.encrypt(bankDetailsModel.getBankAccountNumber(), Utils.key));
			values.put(DBConstants.COL_BANKDETAILS_BALANCE, encryptor.encrypt(bankDetailsModel.getBalance(), Utils.key));
			values.put(DBConstants.COL_BANKDETAILS_BANKCUSTOMERID, encryptor.encrypt(bankDetailsModel.getBankCustomerId(), Utils.key));
			values.put(DBConstants.COL_BANKDETAILS_LOGINUSERNAME, encryptor.encrypt(bankDetailsModel.getLoginUserName(), Utils.key));
			values.put(DBConstants.COL_BANKDETAILS_LOGINPASSWORD, encryptor.encrypt(bankDetailsModel.getLoginPassword(), Utils.key));
			values.put(DBConstants.COL_BANKDETAILS_TRANSACTIONPASSWORD, encryptor.encrypt(bankDetailsModel.getTransactionPassword(), Utils.key));
			values.put(DBConstants.COL_BANKDETAILS_MOBILEPINNUMBER, encryptor.encrypt(bankDetailsModel.getMobilePinNumber(), Utils.key));
			values.put(DBConstants.COL_BANKDETAILS_OTHERS, encryptor.encrypt(bankDetailsModel.getOthers(), Utils.key));
			
			if ( isEdit ) {
				database.update(DBConstants.TBL_BANKDETAILS, values, DBConstants.ID + "=?",
						new String[] {String.valueOf(bankDetailsModel.getId())});
				return bankDetailsModel.getId();
			} else {
				return database.insert(DBConstants.TBL_BANKDETAILS, null, values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<LoanDetailsModel> getAllLoanDetails() {
		final ArrayList<LoanDetailsModel> loanDetailsModels = new ArrayList<LoanDetailsModel>();
		if ( database == null ) {
			open();
		}
		
		Cursor cursor = null;
		
		try {
			cursor = database.query(DBConstants.TBL_LOANDETAILS, new String[] {"*"}, null,
					null, null, null, null);
			
			if ( cursor.getCount() > 0 ) {
				LoanDetailsModel loanDetailsModel;
				for (int i = 0; i < cursor.getCount(); ++i) {
					cursor.moveToNext();
					loanDetailsModel = new LoanDetailsModel();
					loanDetailsModel.setId(cursor.getLong(cursor.getColumnIndex(DBConstants.ID)));
					loanDetailsModel.setName(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_LOANDETAILS_NAME)), Utils.key));
					loanDetailsModel.setLoanAccountNumer(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_LOANDETAILS_LOANACCOUNTNUMER)), Utils.key));
					loanDetailsModel.setBankName(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_LOANDETAILS_BANKNAME)), Utils.key));
					loanDetailsModel.setBranchName(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_LOANDETAILS_BRANCHNAME)), Utils.key));
					loanDetailsModel.setLoanDate(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_LOANDETAILS_LOANDATE)), Utils.key));
					loanDetailsModel.setSanctionDate(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_LOANDETAILS_SANCTIONDATE)), Utils.key));
					loanDetailsModel.setEmi(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_LOANDETAILS_EMI)), Utils.key));
					loanDetailsModel.setRateOfInterest(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_LOANDETAILS_RATEOFINTEREST)), Utils.key));
					loanDetailsModel.setTenure(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_LOANDETAILS_TENURE)), Utils.key));
					loanDetailsModel.setLoanAmount(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_LOANDETAILS_LOANAMOUNT)), Utils.key));
					loanDetailsModel.setDistributedAmount(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_LOANDETAILS_DISTRIBUTEDAMOUNT)), Utils.key));
					loanDetailsModel.setOutstandingAmount(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_LOANDETAILS_OUTSTANDINGAMOUNT)), Utils.key));
					loanDetailsModel.setOthers(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_LOANDETAILS_OTHER)), Utils.key));
					loanDetailsModels.add(loanDetailsModel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( cursor != null && !cursor.isClosed() ) {
				cursor.close();
			}
		}
		loanDetailsModels.trimToSize();
		return loanDetailsModels;
	}
	
	public long insertOrUpdateLoanDetails( LoanDetailsModel loanDetailsModel, boolean isEdit ) {
		if ( database == null ) {
			open();
		}
		
		try {
			final ContentValues values = new ContentValues();
			values.put(DBConstants.COL_LOANDETAILS_NAME, encryptor.encrypt(loanDetailsModel.getName(), Utils.key));
			values.put(DBConstants.COL_LOANDETAILS_LOANACCOUNTNUMER, encryptor.encrypt(loanDetailsModel.getLoanAccountNumer(), Utils.key));
			values.put(DBConstants.COL_LOANDETAILS_BANKNAME, encryptor.encrypt(loanDetailsModel.getBankName(), Utils.key));
			values.put(DBConstants.COL_LOANDETAILS_BRANCHNAME, encryptor.encrypt(loanDetailsModel.getBranchName(), Utils.key));
			values.put(DBConstants.COL_LOANDETAILS_LOANDATE, encryptor.encrypt(loanDetailsModel.getLoanDate(), Utils.key));
			values.put(DBConstants.COL_LOANDETAILS_SANCTIONDATE, encryptor.encrypt(loanDetailsModel.getSanctionDate(), Utils.key));
			values.put(DBConstants.COL_LOANDETAILS_EMI, encryptor.encrypt(loanDetailsModel.getEmi(), Utils.key));
			values.put(DBConstants.COL_LOANDETAILS_RATEOFINTEREST, encryptor.encrypt(loanDetailsModel.getRateOfInterest(), Utils.key));
			values.put(DBConstants.COL_LOANDETAILS_TENURE, encryptor.encrypt(loanDetailsModel.getTenure(), Utils.key));
			values.put(DBConstants.COL_LOANDETAILS_LOANAMOUNT, encryptor.encrypt(loanDetailsModel.getLoanAmount(), Utils.key));
			values.put(DBConstants.COL_LOANDETAILS_DISTRIBUTEDAMOUNT, encryptor.encrypt(loanDetailsModel.getDistributedAmount(), Utils.key));
			values.put(DBConstants.COL_LOANDETAILS_OUTSTANDINGAMOUNT, encryptor.encrypt(loanDetailsModel.getOutstandingAmount(), Utils.key));
			values.put(DBConstants.COL_LOANDETAILS_OTHER, encryptor.encrypt(loanDetailsModel.getOthers(), Utils.key));
			
			if ( isEdit ) {
				database.update(DBConstants.TBL_LOANDETAILS, values, DBConstants.ID + "=?",
						new String[] {String.valueOf(loanDetailsModel.getId())});
				return loanDetailsModel.getId();
			} else {
				return database.insert(DBConstants.TBL_LOANDETAILS, null, values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<DrivingLicenseDetailsModel> getAllDriverDetails() {
		final ArrayList<DrivingLicenseDetailsModel> drivingLicenseDetailsModels = new ArrayList<DrivingLicenseDetailsModel>();
		if ( database == null ) {
			open();
		}
		
		Cursor cursor = null;
		
		try {
			cursor = database.query(DBConstants.TBL_DRIVERLICENSEDETAILS, new String[] {"*"}, null,
					null, null, null, null);
			
			if ( cursor.getCount() > 0 ) {
				DrivingLicenseDetailsModel drivingLicenseDetailsModel;
				for (int i = 0; i < cursor.getCount(); ++i) {
					cursor.moveToNext();
					drivingLicenseDetailsModel = new DrivingLicenseDetailsModel();
					drivingLicenseDetailsModel.setId(cursor.getLong(cursor.getColumnIndex(DBConstants.ID)));
					drivingLicenseDetailsModel.setName(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_DRIVERLICENSEDETAILS_NAME)), Utils.key));
					drivingLicenseDetailsModel.setDrivingLicenseNumber(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_DRIVERLICENSEDETAILS_DRIVINGLICENSENUMBER)), Utils.key));
					drivingLicenseDetailsModel.setAddress(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_DRIVERLICENSEDETAILS_ADDRESS)), Utils.key));
					drivingLicenseDetailsModel.setIssuedOn(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_DRIVERLICENSEDETAILS_ISSUEDON)), Utils.key));
					drivingLicenseDetailsModel.setDateOfBirth(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_DRIVERLICENSEDETAILS_DATEOFBIRTH)), Utils.key));
					drivingLicenseDetailsModel.setTelephoneNumber(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_DRIVERLICENSEDETAILS_TELEPHONENUMBER)), Utils.key));
					drivingLicenseDetailsModel.setLicenceFor(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_DRIVERLICENSEDETAILS_LICENCEFOR)), Utils.key));
					drivingLicenseDetailsModel.setValidFrom(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_DRIVERLICENSEDETAILS_VALIDFROM)), Utils.key));
					drivingLicenseDetailsModel.setValidTill(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_DRIVERLICENSEDETAILS_VALIDTILL)), Utils.key));
					drivingLicenseDetailsModel.setFrontImage(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_DRIVERLICENSEDETAILS_FRONTIMAGE)), Utils.key));
					drivingLicenseDetailsModel.setOthers(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_DRIVERLICENSEDETAILS_OTHER)), Utils.key));
					drivingLicenseDetailsModels.add(drivingLicenseDetailsModel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( cursor != null && !cursor.isClosed() ) {
				cursor.close();
			}
		}
		drivingLicenseDetailsModels.trimToSize();
		return drivingLicenseDetailsModels;
	}
	
	public long insertOrUpdateDriverDetails( DrivingLicenseDetailsModel drivingLicenseDetailsModel, boolean isEdit ) {
		if ( database == null ) {
			open();
		}
		
		try {
			final ContentValues values = new ContentValues();
			values.put(DBConstants.COL_DRIVERLICENSEDETAILS_NAME, encryptor.encrypt(drivingLicenseDetailsModel.getName(), Utils.key));
			values.put(DBConstants.COL_DRIVERLICENSEDETAILS_DRIVINGLICENSENUMBER, encryptor.encrypt(drivingLicenseDetailsModel.getDrivingLicenseNumber(), Utils.key));
			values.put(DBConstants.COL_DRIVERLICENSEDETAILS_ADDRESS, encryptor.encrypt(drivingLicenseDetailsModel.getAddress(), Utils.key));
			values.put(DBConstants.COL_DRIVERLICENSEDETAILS_ISSUEDON, encryptor.encrypt(drivingLicenseDetailsModel.getIssuedOn(), Utils.key));
			values.put(DBConstants.COL_DRIVERLICENSEDETAILS_DATEOFBIRTH, encryptor.encrypt(drivingLicenseDetailsModel.getDateOfBirth(), Utils.key));
			values.put(DBConstants.COL_DRIVERLICENSEDETAILS_TELEPHONENUMBER, encryptor.encrypt(drivingLicenseDetailsModel.getTelephoneNumber(), Utils.key));
			values.put(DBConstants.COL_DRIVERLICENSEDETAILS_LICENCEFOR, encryptor.encrypt(drivingLicenseDetailsModel.getLicenceFor(), Utils.key));
			values.put(DBConstants.COL_DRIVERLICENSEDETAILS_VALIDFROM, encryptor.encrypt(drivingLicenseDetailsModel.getValidFrom(), Utils.key));
			values.put(DBConstants.COL_DRIVERLICENSEDETAILS_VALIDTILL, encryptor.encrypt(drivingLicenseDetailsModel.getValidTill(), Utils.key));
			values.put(DBConstants.COL_DRIVERLICENSEDETAILS_FRONTIMAGE, encryptor.encrypt(drivingLicenseDetailsModel.getFrontImage(), Utils.key));
			values.put(DBConstants.COL_DRIVERLICENSEDETAILS_OTHER, encryptor.encrypt(drivingLicenseDetailsModel.getOthers(), Utils.key));
			
			if ( isEdit ) {
				database.update(DBConstants.TBL_DRIVERLICENSEDETAILS, values, DBConstants.ID + "=?",
						new String[] {String.valueOf(drivingLicenseDetailsModel.getId())});
				return drivingLicenseDetailsModel.getId();
			} else {
				return database.insert(DBConstants.TBL_DRIVERLICENSEDETAILS, null, values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<PassportDetailsModel> getAllPassportDetails() {
		final ArrayList<PassportDetailsModel> passportDetailsModels = new ArrayList<PassportDetailsModel>();
		if ( database == null ) {
			open();
		}
		
		Cursor cursor = null;
		
		try {
			cursor = database.query(DBConstants.TBL_PASSPORTDETAILS, new String[] {"*"}, null,
					null, null, null, null);
			
			if ( cursor.getCount() > 0 ) {
				PassportDetailsModel passportDetailsModel;
				for (int i = 0; i < cursor.getCount(); ++i) {
					cursor.moveToNext();
					passportDetailsModel = new PassportDetailsModel();
					passportDetailsModel.setId(cursor.getLong(cursor.getColumnIndex(DBConstants.ID)));
					passportDetailsModel.setName(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_PASSPORTDETAILS_NAME)), Utils.key));
					passportDetailsModel.setPassportNumber(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_PASSPORTDETAILS_PASSPORTNUMBER)), Utils.key));
					passportDetailsModel.setType(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_PASSPORTDETAILS_TYPE)), Utils.key));
					passportDetailsModel.setCountryCode(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_PASSPORTDETAILS_COUNTRYCODE)), Utils.key));
					passportDetailsModel.setNationality(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_PASSPORTDETAILS_NATIONALITY)), Utils.key));
					passportDetailsModel.setGender(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_PASSPORTDETAILS_GENDER)), Utils.key));
					passportDetailsModel.setDateOfBirth(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_PASSPORTDETAILS_DATEOFBIRTH)), Utils.key));
					passportDetailsModel.setPlaceOfBirth(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_PASSPORTDETAILS_PLACEOFBIRTH)), Utils.key));
					passportDetailsModel.setPlaceOfIssue(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_PASSPORTDETAILS_PLACEOFISSUE)), Utils.key));
					passportDetailsModel.setValidFrom(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_PASSPORTDETAILS_VALIDFROM)), Utils.key));
					passportDetailsModel.setValidTill(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_PASSPORTDETAILS_VALIDTILL)), Utils.key));
					passportDetailsModel.setFatherName(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_PASSPORTDETAILS_FATHERNAME)), Utils.key));
					passportDetailsModel.setMotherName(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_PASSPORTDETAILS_MOTHERNAME)), Utils.key));
					passportDetailsModel.setAddress(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_PASSPORTDETAILS_ADDRESS)), Utils.key));
					passportDetailsModel.setPassportFileNumber(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_PASSPORTDETAILS_PASSPORTFILENUMBER)), Utils.key));
					passportDetailsModel.setPassportFrontImage(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_PASSPORTDETAILS_PASSPORTFRONTIMAGE)), Utils.key));
					passportDetailsModel.setPassportBackImage(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_PASSPORTDETAILS_PASSPORTBACKIMAGE)), Utils.key));
					passportDetailsModel.setOthers(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_PASSPORTDETAILS_OTHER)), Utils.key));
					passportDetailsModels.add(passportDetailsModel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( cursor != null && !cursor.isClosed() ) {
				cursor.close();
			}
		}
		passportDetailsModels.trimToSize();
		return passportDetailsModels;
	}
	
	public long insertOrUpdatePassportDetails( PassportDetailsModel passportDetailsModel, boolean isEdit ) {
		if ( database == null ) {
			open();
		}
		
		try {
			final ContentValues values = new ContentValues();
			values.put(DBConstants.COL_PASSPORTDETAILS_NAME, encryptor.encrypt(passportDetailsModel.getName(), Utils.key));
			values.put(DBConstants.COL_PASSPORTDETAILS_PASSPORTNUMBER, encryptor.encrypt(passportDetailsModel.getPassportNumber(), Utils.key));
			values.put(DBConstants.COL_PASSPORTDETAILS_TYPE, encryptor.encrypt(passportDetailsModel.getType(), Utils.key));
			values.put(DBConstants.COL_PASSPORTDETAILS_COUNTRYCODE, encryptor.encrypt(passportDetailsModel.getCountryCode(), Utils.key));
			values.put(DBConstants.COL_PASSPORTDETAILS_NATIONALITY, encryptor.encrypt(passportDetailsModel.getNationality(), Utils.key));
			values.put(DBConstants.COL_PASSPORTDETAILS_GENDER, encryptor.encrypt(passportDetailsModel.getGender(), Utils.key));
			values.put(DBConstants.COL_PASSPORTDETAILS_DATEOFBIRTH, encryptor.encrypt(passportDetailsModel.getDateOfBirth(), Utils.key));
			values.put(DBConstants.COL_PASSPORTDETAILS_PLACEOFBIRTH, encryptor.encrypt(passportDetailsModel.getPlaceOfBirth(), Utils.key));
			values.put(DBConstants.COL_PASSPORTDETAILS_PLACEOFISSUE, encryptor.encrypt(passportDetailsModel.getPlaceOfIssue(), Utils.key));
			values.put(DBConstants.COL_PASSPORTDETAILS_VALIDFROM, encryptor.encrypt(passportDetailsModel.getValidFrom(), Utils.key));
			values.put(DBConstants.COL_PASSPORTDETAILS_VALIDTILL, encryptor.encrypt(passportDetailsModel.getValidTill(), Utils.key));
			values.put(DBConstants.COL_PASSPORTDETAILS_FATHERNAME, encryptor.encrypt(passportDetailsModel.getFatherName(), Utils.key));
			values.put(DBConstants.COL_PASSPORTDETAILS_MOTHERNAME, encryptor.encrypt(passportDetailsModel.getMotherName(), Utils.key));
			values.put(DBConstants.COL_PASSPORTDETAILS_ADDRESS, encryptor.encrypt(passportDetailsModel.getAddress(), Utils.key));
			values.put(DBConstants.COL_PASSPORTDETAILS_PASSPORTFILENUMBER, encryptor.encrypt(passportDetailsModel.getPassportFileNumber(), Utils.key));
			values.put(DBConstants.COL_PASSPORTDETAILS_PASSPORTFRONTIMAGE, encryptor.encrypt(passportDetailsModel.getPassportFrontImage(), Utils.key));
			values.put(DBConstants.COL_PASSPORTDETAILS_PASSPORTBACKIMAGE, encryptor.encrypt(passportDetailsModel.getPassportBackImage(), Utils.key));
			values.put(DBConstants.COL_PASSPORTDETAILS_OTHER, encryptor.encrypt(passportDetailsModel.getOthers(), Utils.key));
			
			if ( isEdit ) {
				database.update(DBConstants.TBL_PASSPORTDETAILS, values, DBConstants.ID + "=?",
						new String[] {String.valueOf(passportDetailsModel.getId())});
				return passportDetailsModel.getId();
			} else {
				return database.insert(DBConstants.TBL_PASSPORTDETAILS, null, values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<SocialModel> getAllSocialDetails() {
		final ArrayList<SocialModel> socialModels = new ArrayList<SocialModel>();
		if ( database == null ) {
			open();
		}
		
		Cursor cursor = null;
		
		try {
			cursor = database.query(DBConstants.TBL_SOCIALDETAILS, new String[] {"*"}, null,
					null, null, null, null);
			
			if ( cursor.getCount() > 0 ) {
				SocialModel socialModel;
				for (int i = 0; i < cursor.getCount(); ++i) {
					cursor.moveToNext();
					socialModel = new SocialModel();
					socialModel.setId(cursor.getLong(cursor.getColumnIndex(DBConstants.ID)));
					socialModel.setName(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_SOCIALDETAILS_NAME)), Utils.key));
					socialModel.setEmailAddress(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_SOCIALDETAILS_EMAILADDRESS)), Utils.key));
					socialModel.setPassword(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_SOCIALDETAILS_PASSWORD)), Utils.key));
					socialModels.add(socialModel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( cursor != null && !cursor.isClosed() ) {
				cursor.close();
			}
		}
		socialModels.trimToSize();
		return socialModels;
	}
	
	public long insertOrUpdateSocialDetails( SocialModel socialModel, boolean isEdit ) {
		if ( database == null ) {
			open();
		}
		
		try {
			final ContentValues values = new ContentValues();
			values.put(DBConstants.COL_SOCIALDETAILS_NAME, 
					encryptor.encrypt(socialModel.getName(), Utils.key));
			values.put(DBConstants.COL_SOCIALDETAILS_EMAILADDRESS,
					encryptor.encrypt(socialModel.getEmailAddress(), Utils.key));
			values.put(DBConstants.COL_SOCIALDETAILS_PASSWORD,
					encryptor.encrypt(socialModel.getPassword(), Utils.key));
			
			if ( isEdit ) {
				database.update(DBConstants.TBL_SOCIALDETAILS, values, DBConstants.ID + "=?",
						new String[] {String.valueOf(socialModel.getId())});
				return socialModel.getId();
			} else {
				return database.insert(DBConstants.TBL_SOCIALDETAILS, null, values);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<NotesModel> getAllNotesDetails() {
		final ArrayList<NotesModel> notesModels = new ArrayList<NotesModel>();
		if ( database == null ) {
			open();
		}
		
		Cursor cursor = null;
		
		try {
			cursor = database.query(DBConstants.TBL_NOTESDETAILS, new String[] {"*"}, null,
					null, null, null, null);
			
			if ( cursor.getCount() > 0 ) {
				NotesModel notesModel;
				for (int i = 0; i < cursor.getCount(); ++i) {
					cursor.moveToNext();
					notesModel = new NotesModel();
					notesModel.setId(cursor.getLong(cursor.getColumnIndex(DBConstants.ID)));
					notesModel.setName(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_NOTESDETAILS_NAME)), Utils.key));
					notesModel.setDescription(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_NOTESDETAILS_DESCRIPTION)), Utils.key));
					notesModels.add(notesModel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( cursor != null && !cursor.isClosed() ) {
				cursor.close();
			}
		}
		notesModels.trimToSize();
		return notesModels;
	}
	
	public long insertOrUpdateNotesDetails( NotesModel notesModel, boolean isEdit ) {
		if ( database == null ) {
			open();
		}
		
		try {
			final ContentValues values = new ContentValues();
			values.put(DBConstants.COL_NOTESDETAILS_NAME, 
					encryptor.encrypt(notesModel.getName(), Utils.key));
			values.put(DBConstants.COL_NOTESDETAILS_DESCRIPTION,
					encryptor.encrypt(notesModel.getDescription(), Utils.key));
			
			if ( isEdit ) {
				database.update(DBConstants.TBL_NOTESDETAILS, values, DBConstants.ID + "=?",
						new String[] {String.valueOf(notesModel.getId())});
				return notesModel.getId();
			} else {
				return database.insert(DBConstants.TBL_NOTESDETAILS, null, values);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<OthersDetailsModel> getAllOtherDetails() {
		final ArrayList<OthersDetailsModel> othersDetailsModels = new ArrayList<OthersDetailsModel>();
		if ( database == null ) {
			open();
		}
		
		Cursor cursor = null;
		
		try {
			cursor = database.query(DBConstants.TBL_OTHERDETAILS, new String[] {"*"}, null,
					null, null, null, null);
			
			if ( cursor.getCount() > 0 ) {
				OthersDetailsModel othersDetailsModel;
				for (int i = 0; i < cursor.getCount(); ++i) {
					cursor.moveToNext();
					othersDetailsModel = new OthersDetailsModel();
					othersDetailsModel.setId(cursor.getLong(cursor.getColumnIndex(DBConstants.ID)));
					othersDetailsModel.setName(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_OTHERDETAILS_NAME)), Utils.key));
					othersDetailsModel.setDescription(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_OTHERDETAILS_DESCRIPTION)), Utils.key));
					othersDetailsModel.setImage1(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_OTHERDETAILS_IMAGE1)), Utils.key));
					othersDetailsModel.setImage2(encryptor.decrypt(cursor.getString(cursor.getColumnIndex(
							DBConstants.COL_OTHERDETAILS_IMAGE2)), Utils.key));
					othersDetailsModels.add(othersDetailsModel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( cursor != null && !cursor.isClosed() ) {
				cursor.close();
			}
		}
		othersDetailsModels.trimToSize();
		return othersDetailsModels;
	}
	
	public long insertOrUpdateOthersDetails( OthersDetailsModel othersDetailsModel, boolean isEdit ) {
		if ( database == null ) {
			open();
		}
		
		try {
			final ContentValues values = new ContentValues();
			values.put(DBConstants.COL_OTHERDETAILS_NAME, 
					encryptor.encrypt(othersDetailsModel.getName(), Utils.key));
			values.put(DBConstants.COL_OTHERDETAILS_DESCRIPTION,
					encryptor.encrypt(othersDetailsModel.getDescription(), Utils.key));
			values.put(DBConstants.COL_OTHERDETAILS_IMAGE1,
					encryptor.encrypt(othersDetailsModel.getImage1(), Utils.key));
			values.put(DBConstants.COL_OTHERDETAILS_IMAGE2,
					encryptor.encrypt(othersDetailsModel.getImage2(), Utils.key));
			
			if ( isEdit ) {
				database.update(DBConstants.TBL_OTHERDETAILS, values, DBConstants.ID + "=?",
						new String[] {String.valueOf(othersDetailsModel.getId())});
				return othersDetailsModel.getId();
			} else {
				return database.insert(DBConstants.TBL_OTHERDETAILS, null, values);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return -1;
	}
	
	private final Encryptor PADDING_ENCRYPTOR = new Encryptor() {

        @Override
        public SecretKey deriveKey(String password, byte[] salt) {
            return Crypto.deriveKeyPad(password);
        }

        @Override
        public String encrypt(String plaintext, String password) {
            key = deriveKey(password, null);

            return Crypto.encrypt(plaintext, key, null);
        }

        @Override
        public String decrypt(String ciphertext, String password) {
            SecretKey key = deriveKey(password, null);

            return Crypto.decryptNoSalt(ciphertext, key);
        }
    };
    
    abstract class Encryptor {
        SecretKey key;

        abstract SecretKey deriveKey(String passpword, byte[] salt);

        abstract String encrypt(String plaintext, String password);

        abstract String decrypt(String ciphertext, String password);

        String getRawKey() {
            if (key == null) {
                return null;
            }

            return Crypto.toHex(key.getEncoded());
        }
    }
}