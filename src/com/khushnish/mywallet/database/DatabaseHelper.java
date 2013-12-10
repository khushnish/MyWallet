package com.khushnish.mywallet.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.khushnish.mywallet.R;
import com.khushnish.mywallet.model.CardModel;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private SQLiteDatabase database;
	
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, context.getString(R.string.db_name), null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		this.database = database;
		
		if (!this.database.isReadOnly()) {
			this.database.execSQL("PRAGMA foreign_keys = ON;");
		}
		
		final String cardDetailsTable = "CREATE TABLE IF NOT EXISTS CardDetails (ID INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , CardType INTEGER, OtherCardName TEXT, CardUserType INTEGER, BankName TEXT, BankAccountNumber TEXT, BankCustomerId TEXT, CardNumber TEXT, CardHolderName TEXT, CardCVVNumber INTEGER, CardATMPinNumber INTEGER, CardTransactionPassword TEXT, CardMobilePinNumber TEXT, ValidFromMonth INTEGER, ValidFromYear INTEGER, ValidTillMonth INTEGER, ValidTillYear INTEGER, ImageFront TEXT, ImageBack TEXT)";
		
		this.database.execSQL(cardDetailsTable);
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
					cardModel.setCardType(cursor.getInt(cursor.getColumnIndex(DBConstants.COL_CARDDETAILS_CARDTYPE)));
					cardModel.setOtherCardName(cursor.getString(cursor.getColumnIndex(DBConstants.COL_CARDDETAILS_OTHERCARDNAME)));
					cardModel.setCardUserType(cursor.getInt(cursor.getColumnIndex(DBConstants.COL_CARDDETAILS_CARDUSERTYPE)));
					cardModel.setBankName(cursor.getString(cursor.getColumnIndex(DBConstants.COL_CARDDETAILS_BANKNAME)));
					cardModel.setBankAccountNumber(cursor.getString(cursor.getColumnIndex(DBConstants.COL_CARDDETAILS_BANKACCOUNTNUMBER)));
					cardModel.setBankCustomerId(cursor.getString(cursor.getColumnIndex(DBConstants.COL_CARDDETAILS_BANKCUSTOMERID)));
					cardModel.setCardNumber(cursor.getString(cursor.getColumnIndex(DBConstants.COL_CARDDETAILS_CARDNUMBER)));
					cardModel.setCardHolderName(cursor.getString(cursor.getColumnIndex(DBConstants.COL_CARDDETAILS_CARDHOLDERNAME)));
					cardModel.setCardCvvNumber(cursor.getInt(cursor.getColumnIndex(DBConstants.COL_CARDDETAILS_CARDCVVNUMBER)));
					cardModel.setCardAtmPinNumber(cursor.getInt(cursor.getColumnIndex(DBConstants.COL_CARDDETAILS_CARDATMPINNUMBER)));
					cardModel.setCardTransactionPassword(cursor.getString(cursor.getColumnIndex(DBConstants.COL_CARDDETAILS_CARDTRANSACTIONPASSWORD)));
					cardModel.setValidFromMonth(cursor.getInt(cursor.getColumnIndex(DBConstants.COL_CARDDETAILS_VALIDFROMMONTH)));
					cardModel.setValidFromYear(cursor.getInt(cursor.getColumnIndex(DBConstants.COL_CARDDETAILS_VALIDFROMYEAR)));
					cardModel.setValidTillMonth(cursor.getInt(cursor.getColumnIndex(DBConstants.COL_CARDDETAILS_VALIDTILLMONTH)));
					cardModel.setValidTillYear(cursor.getInt(cursor.getColumnIndex(DBConstants.COL_CARDDETAILS_VALIDTILLYEAR)));
					cardModel.setImageFront(cursor.getString(cursor.getColumnIndex(DBConstants.COL_CARDDETAILS_IMAGEFRONT)));
					cardModel.setImageBack(cursor.getString(cursor.getColumnIndex(DBConstants.COL_CARDDETAILS_IMAGEBACK)));
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
			values.put(DBConstants.COL_CARDDETAILS_CARDTYPE, cardModel.getCardType());
			values.put(DBConstants.COL_CARDDETAILS_OTHERCARDNAME, cardModel.getOtherCardName());
			values.put(DBConstants.COL_CARDDETAILS_CARDUSERTYPE, cardModel.getCardUserType());
			values.put(DBConstants.COL_CARDDETAILS_BANKNAME, cardModel.getBankName());
			values.put(DBConstants.COL_CARDDETAILS_BANKACCOUNTNUMBER, cardModel.getBankAccountNumber());
			values.put(DBConstants.COL_CARDDETAILS_BANKCUSTOMERID, cardModel.getBankCustomerId());
			values.put(DBConstants.COL_CARDDETAILS_CARDNUMBER, cardModel.getCardNumber());
			values.put(DBConstants.COL_CARDDETAILS_CARDHOLDERNAME, cardModel.getCardHolderName());
			values.put(DBConstants.COL_CARDDETAILS_CARDCVVNUMBER, cardModel.getCardCvvNumber());
			values.put(DBConstants.COL_CARDDETAILS_CARDATMPINNUMBER, cardModel.getCardAtmPinNumber());
			values.put(DBConstants.COL_CARDDETAILS_CARDTRANSACTIONPASSWORD, cardModel.getCardTransactionPassword());
			values.put(DBConstants.COL_CARDDETAILS_VALIDFROMMONTH, cardModel.getValidFromMonth());
			values.put(DBConstants.COL_CARDDETAILS_VALIDFROMYEAR, cardModel.getValidFromYear());
			values.put(DBConstants.COL_CARDDETAILS_VALIDTILLMONTH, cardModel.getValidTillMonth());
			values.put(DBConstants.COL_CARDDETAILS_VALIDTILLYEAR, cardModel.getValidTillYear());
			values.put(DBConstants.COL_CARDDETAILS_IMAGEFRONT, cardModel.getImageFront());
			values.put(DBConstants.COL_CARDDETAILS_IMAGEBACK, cardModel.getImageBack());
			
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
}
