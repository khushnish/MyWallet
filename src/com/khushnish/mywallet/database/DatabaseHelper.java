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
import com.khushnish.mywallet.model.NotesModel;
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
		final String loanDetailsTable = "CREATE TABLE IF NOT EXISTS LoanDetails (ID INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , Name TEXT, LoanAccountNumer TEXT, BrnchName TEXT, LoanDate TEXT, SanctionDate TEXT, EMI TEXT, RateOfInterest TEXT, Tenure TEXT, LoanAmount TEXT, DistributedAmount TEXT, OutstandingAmount TEXT, Other TEXT)";
		final String drivingLicenceDetailsTable = "CREATE TABLE IF NOT EXISTS DriverLicenseDetails (ID INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , Name TEXT, DrivingLicenseNumber TEXT, Address TEXT, IssuedOn TEXT, DateOfBirth TEXT, TelephoneNumber TEXT, LicenceFor TEXT, ValidFrom TEXT, ValidTill TEXT, FrontImage TEXT, Other TEXT)";
		final String passportDetailsTable = "CREATE TABLE IF NOT EXISTS DriverLicenseDetails (ID INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , Name TEXT, PassportNumber TEXT, Type TEXT, CountryCode TEXT, Nationality TEXT, Gender TEXT, DateOfBirth TEXT, PlaceOfBirth TEXT, PlaceOfIssue TEXT, ValidFrom TEXT, ValidTill TEXT, FatherName TEXT, MotherName TEXT, Address TEXT, PassportFileNumber TEXT, PassportFrontImage TEXT, PassportBackImage TEXT, Other TEXT)";
		final String notesDetailsTable = "CREATE TABLE IF NOT EXISTS NotesDetails (ID INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , Name TEXT, Description TEXT)";
		final String socialDetailsTable = "CREATE TABLE IF NOT EXISTS SocialDetails (ID INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , Name TEXT, EmailAddress TEXT, Password TEXT)";
		final String otherDetailsTable = "CREATE TABLE IF NOT EXISTS OtherDetails (ID INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , Name TEXT, Details TEXT, Image1 TEXT, Image2 TEXT)";
		
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
		final ArrayList<NotesModel> socialModels = new ArrayList<NotesModel>();
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
					socialModels.add(notesModel);
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
