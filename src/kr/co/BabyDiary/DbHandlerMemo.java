package kr.co.BabyDiary;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DbHandlerMemo {
	private Context ctx;
	private DbHelperMemo helper;
	private SQLiteDatabase db;
	
	public DbHandlerMemo(Context ctx) {
		this.ctx = ctx;
		helper = new DbHelperMemo(ctx);
		db = helper.getWritableDatabase();  // DB가  open 된다.
	}
	
	public static DbHandlerMemo open(Context ctx) throws SQLException {
		DbHandlerMemo handler = new DbHandlerMemo(ctx);
		return handler;
	}
	
	public void close() {
		helper.close();
	}
	
	public Cursor selectAll() {
		String sql = "select id, title, content, date from memo order by date, id desc";
		Cursor cursor = db.rawQuery(sql, null);
		return cursor;
	}
	
	public void memoinsert(String title, String content, String date){
		String sql = "insert into memo (title, content, date) values (?,?,?)";
		db.execSQL(sql, new String[] {title, content, date});
	}
	
	public void memoupdate(String id, String title, String content, String date){
		String sql = "update memo set title=?, content=?, date=? where id = ?";
		db.execSQL(sql, new String[] {title, content, date, id});
	}
	
	public void memodelete(String id){
		String sql = "delete from memo where id = ?";
		db.execSQL(sql, new String[] {id});
	}
	
	public Cursor selectData(String id) {
		String sql = "select id, title, content, date from memo where id = ?";
		Cursor cursor = db.rawQuery(sql, new String[] {id});
		return cursor;
	}
	
	//Diary용 select
	public Cursor diary_Select(String date){
		String sql = "select id, title, content from memo where date=?";
		Cursor cursor = db.rawQuery(sql, new String[]{date});	
		return cursor;

	}//diary_Select
}
