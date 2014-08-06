package kr.co.BabyDiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DbHandlerMedical {
	
	private Context ctx;
	private DbHelperMedical helper;
	private SQLiteDatabase db;
	
	
	public DbHandlerMedical(Context ctx) {
		this.ctx = ctx;
		helper = new DbHelperMedical(ctx);
		db = helper.getWritableDatabase(); //DB open
	} //DbHandler
	
	public static DbHandlerMedical open (Context ctx) throws SQLException{
		DbHandlerMedical handler = new DbHandlerMedical(ctx);
		return handler;
	} //DbHandler
	
	public void close(){
		helper.close();
	} //close
	
	public long insert(String babyid,String date, String symptom, String diagnosis,int medicineDay,String injection){
		ContentValues values = new ContentValues();
		values.put("babyid",babyid);
		values.put("date", date);
		values.put("symptom", symptom);
		values.put("diagnosis", diagnosis);
		values.put("medicineDay", medicineDay);
		values.put("injection", injection);
		
		
		
		long result = db.insert("medical_recods", null, values);
		return result;
	} //insert
	
	public Cursor select(String babyid){
		String sql = "select * from medical_recods where babyid = ?;";
		Cursor cursor = db.rawQuery(sql, new String[] {babyid});
		return cursor;
	} //select
	

	
	public Cursor selectNo(String no){
		String sql = "select symptom,diagnosis, medicineDay, injection,no from medical_recods where no =  ? ;";
		Cursor cursor = db.rawQuery(sql, new String[] {no});
		return cursor;
	} //select

	
	public Cursor selectAll(String no){
		String sql = "select symptom,diagnosis, medicineDay, injection,date from medical_recods where no =  ? ;";
		Cursor cursor = db.rawQuery(sql, new String[] {no});
		return cursor;
	} //select
	
	public void deleteMedical(String no){
		String sql = "delete from medical_recods where no =  ? ;";
		db.execSQL(sql, new String[] {no});
						
	} //deleteMedical
	
	public void updateMedical(String date, String symptom, String diagnosis, String medicineDay, String injection,String no){
		String sql = "update medical_recods set date=?, symptom=?, diagnosis=?, medicineDay=?, injection=? where  no = ?; " ;
		db.execSQL(sql, new String[] {date,symptom,diagnosis,medicineDay,injection,no});
	} //deleteMedical
	
	//Diary¿ë select
	public Cursor diary_Select(String date, String babyid){
			String sql = "select no, symptom, diagnosis from medical_recods where date like ? and babyid=?";
			Cursor cursor = db.rawQuery(sql, new String[]{date+"%", babyid});
			return cursor;
	}//diary_Select
}
