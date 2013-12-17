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
import com.khushnish.mywallet.model.CardModel;
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
		final String socialTable = "CREATE TABLE IF NOT EXISTS SocialDetails (ID INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , Name TEXT, EmailAddress TEXT, Password TEXT)";
		
		this.database.execSQL(cardDetailsTable);
		this.database.execSQL(socialTable);
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
