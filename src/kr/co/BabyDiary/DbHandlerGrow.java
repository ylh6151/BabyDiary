package kr.co.BabyDiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DbHandlerGrow {

	private Context ctx;
	private DbHelperGrow helper;
	private SQLiteDatabase db;
	
	public DbHandlerGrow(Context ctx) {
		this.ctx = ctx;
		helper = new DbHelperGrow(ctx);
		db = helper.getWritableDatabase();  // DB가  open 된다.
	}
	
	public static DbHandlerGrow open(Context ctx) throws SQLException {
		DbHandlerGrow handler = new DbHandlerGrow(ctx);
		return handler;
	}
	
	public void close() {
		helper.close();
	}
	
	public long insertGrowth(String weight, String height, String head, String date, String babyid){
		ContentValues values = new ContentValues();
		values.put("weight", weight);
		values.put("height", height);
		values.put("head", head);
		values.put("date",date);
		values.put("babyid", babyid);
		
		
		long result = db.insert("growth", null, values);
		return result;
	}
	
	public long updateGrowth(String id, String weight, String height, String head, String date,String babyid){
		ContentValues values = new ContentValues();
		values.put("weight", weight);
		values.put("height", height);
		values.put("head", head);
		values.put("date",date);
		values.put("babyid", babyid);

		long result = db.update("growth", values, "id=?", new String[] {id});
		return result;
	}
	
	public long deleteId(String id){
	    long result = db.delete("growth",  "id=?", new String[] {id});
	    return result;
	}
	
	public Cursor selectGrowthAll(String babyid){//성장치수 db 다 불러오기
		/*Cursor cursor = db.query(false, "growth", new String[]{"id","weight","height","head","date"}, null, null, null, null, null, null);
		return cursor;*/
		String sql = "select id,weight,height,head,date from growth where babyid=?";
		Cursor cursor = db.rawQuery(sql, new String[] {babyid});
		return cursor;
	}
	
	public Cursor selectId(String id){
		//Cursor cursor = db.query(false, "growth", new String[] {"weight","height","head","date"}, "id="+id, null, null, null, null, null);
			
		//if(cursor!=null) cursor.moveToFirst();
		
			
		String sql = "select weight,height,head,date from growth where id= " +id;
		Cursor cursor = db.rawQuery(sql, null);
		return cursor;		
	} //select
	
	//Diary용 select
	public Cursor diary_Select(String date, String babyid){
		String sql = "select id, weight, height from growth where date=? and babyid=?";
		Cursor cursor = db.rawQuery(sql, new String[]{date, babyid});	
		return cursor;

	}//diary_Select
	//Diary용 select
}

