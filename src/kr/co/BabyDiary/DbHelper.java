package kr.co.BabyDiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper{
	private static final String DB_NAME = "test";
	private static final int DB_VERSION = 1;
	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table baby(" +
		"_id integer primary key autoincrement," +
		"babyName text not null," +
		"gender text not null," +
		"birthDay text not null," +
		"weight text not null," +
		"height text not null," +
		"head text not null," +
		"bloodType text not null," +
		"clinicData text not null,"+
		"choice text not null,"+
		"pic text)";

		db.execSQL(sql);   //DDL문  수행
	}
	
	@Override
	//DB가 upgrade 되면 자동 호출
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exist baby");
		onCreate(db);
	}
}
