package kr.co.BabyDiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelperMemo extends SQLiteOpenHelper{
	private static final String DB_NAME = "memo";
	private static final int DB_VERSION = 1;
	public DbHelperMemo(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table memo ("+
			"id integer primary key autoincrement, "+
			"title text, " +
			"content text, " +
			"date text);";

		db.execSQL(sql);  
	}
	
	@Override
	//DB가 upgrade 되면 자동 호출
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exist memo;");
		onCreate(db);
	}
}
