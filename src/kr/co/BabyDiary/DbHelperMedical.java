package kr.co.BabyDiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelperMedical extends SQLiteOpenHelper{
	
	private static final String DB_NAME = "medical";
	private static final int DB_VERSION = 1;
	
	
	public DbHelperMedical(Context context) {
		super(context, DB_NAME,null,DB_VERSION);
	} //DbHelper
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table medical_recods(" +
					"no integer primary key autoincrement," +
					"babyid text not null," +
					"date text not null," +
					"symptom text not null," +
					"diagnosis text not null," +
					"medicineDay integer not null," +
					"injection text not null);";
		db.execSQL(sql);
		
	} //onCreate
	

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exist medical_recods" );
		onCreate(db);
		
	} //onUpgrade
	
}
