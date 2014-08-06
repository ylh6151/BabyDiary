package kr.co.BabyDiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelperGrow extends SQLiteOpenHelper{
	private static final String DB_NAME = "grow";
	private static final int DB_VERSION = 1;
	public DbHelperGrow(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql= "create table growth("+
		"id integer primary key autoincrement," +
		"weight text," +
		"height text," +
		"head text," +
		"date text," +
		"babyid text);";
		
		db.execSQL(sql);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exist growth");
		onCreate(db);
	}
}
