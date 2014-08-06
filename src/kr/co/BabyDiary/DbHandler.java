package kr.co.BabyDiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;



public class DbHandler {
	private Context ctx;
	private DbHelper helper;
	private SQLiteDatabase db;
	
	public DbHandler(Context ctx) {
		this.ctx = ctx;
		helper = new DbHelper(ctx);
		db = helper.getWritableDatabase();  // DB가  open 된다.
	}
	
	public static DbHandler open(Context ctx) throws SQLException {
		DbHandler handler = new DbHandler(ctx);
		return handler;
	}
	
	public void close() {
		helper.close();
	}
	
	public void choicefalse(){
		String sql = "update baby set choice ='false'";
		db.execSQL(sql);
		
	}
	
	public void choiceBaby(String no){
		String sql = "update baby set choice ='true' where _id = ?";
		db.execSQL(sql, new String[] {no});
		
	}
	
	public Cursor choicetrue(){ //choice true인 애기 대꾸오기
		
		String sql = "select _id, babyName, gender, birthDay, weight, height, head, bloodType, clinicData, choice  from baby where choice = 'true'";
		Cursor cursor = db.rawQuery(sql, null);
		return cursor;
		
	}
	
	
	public long insert(String babyName, String sex, String birthDay, String weight, String height, String head, String bloodType, String clinicData, String choice, String pic) {
		ContentValues values = new ContentValues();
		  //id는 자동증가 autoincrement
		values.put("babyName", babyName);
		values.put("gender", sex);  //gender, sex 해깔리지 말기
		values.put("birthDay", birthDay);
		values.put("weight", weight);
		values.put("height", height);
		values.put("head", head);
		values.put("bloodType", bloodType);
		values.put("clinicData", clinicData);
		values.put("choice", choice);
		values.put("pic", pic);
		
		//내장된 메소드 사용
		long result = db.insert("baby", null, values);
		return result;
	}
	
	public void update(String babyName, String sex, String birthDay, String weight, String height, String head, String bloodType, String clinicData, String id) {	
		String sql = "update baby set babyName=?, gender=?, birthDay=?, weight=?, height=?, head=?, bloodType=?, clinicData=? where _id=?";
		db.execSQL(sql, new String[] {babyName, sex, birthDay, weight, height, head, bloodType, clinicData, id});
	}
	
	public Cursor selectAll() {
		Cursor cursor = db.query(true, "baby", new String[]{"_id","babyName", "gender","birthDay", "weight", "height", "head", "bloodType", "clinicData","choice","pic"}, null, null, null, null, null, null);
		return cursor;
	}
	
	public Cursor selectbyID(String babyid) {
		/*Cursor cursor = db.query(true, "baby", new String[]{"_id","babyName", "gender","birthDay", "weight", "height", "head", "bloodType", "clinicData","choice","pic"}, null, null, null, null, null, null);
		return cursor;*/
		
		String sql = "select gender, birthDay from baby where _id=?";
		Cursor cursor = db.rawQuery(sql, new String[] {babyid});
		return cursor;
	}
	
	public Cursor selectBaby() {
		String sql = " select _id, babyname, gender, birthday, weight, height, head, bloodtype, clinicdata, choice, pic from baby where choice='true'";
		Cursor cursor = db.rawQuery(sql, null);
		return cursor;
		
	}
	public void delete() {
		String sql = "delete from baby where choice = 'true'";
		db.execSQL(sql);
	}
	
	
	public void updateImg(String uri) {
		String sql = "update baby set pic= ? where choice = 'true'";
		db.execSQL(sql, new String[] {uri});
	}
	public Cursor selectUri() {
		String sql = "select pic from baby where choice = 'true';";
		Cursor cursor = db.rawQuery(sql, null);
		return cursor;
	}
	
	
	public Cursor selectName(String _id){
		String sql = "select babyName from baby where _id = ?;";
		Cursor cursor = db.rawQuery(sql, new String[] {_id});
		return cursor;
	} // 진료 기록에 아이 이름 부르기 
	

}
