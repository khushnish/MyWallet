package com.khushnish.mywallet.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.khushnish.mywallet.R;

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
		
//		final String schoolDetailsTable = "CREATE TABLE IF NOT EXISTS SchoolDetails (SchoolId TEXT NOT NULL , SchoolName TEXT, BellId TEXT, Number TEXT, Message TEXT DEFAULT '1234 ring 5s', AutoMode BOOL DEFAULT 0, SilentMode BOOL DEFAULT 0)";
//		final String gradesTable = "CREATE TABLE IF NOT EXISTS Grades (Id TEXT, Name TEXT, SchoolId TEXT, PlannedMorningBellTime TEXT, ActualMorningBellTime TEXT, PlannedAfternoonBellTime TEXT, ActualAfternoonBellTime TEXT)";
//		final String classTable = "CREATE TABLE IF NOT EXISTS Classes (Id TEXT, GradeId TEXT, Name TEXT, SchoolId TEXT)";
//		final String gradeTimeScheduleTable = "CREATE TABLE GradeTimeSchedule (Id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , GradeId TEXT, SchoolId TEXT, PlannedMorningBellTime TEXT, ActualMorningBellTime TEXT, PlannedAfternoonBellTime TEXT, ActualAfternoonBellTime TEXT)";
//		
//		this.database.execSQL(schoolDetailsTable);
//		this.database.execSQL(gradesTable);
//		this.database.execSQL(classTable);
//		this.database.execSQL(gradeTimeScheduleTable);
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
}