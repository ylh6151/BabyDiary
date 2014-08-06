package kr.co.BabyDiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.RadioGroup;

public class DbHandlerCare {
	private Context ctx;
	private DbHelperCare helper;
	private SQLiteDatabase db;
	
	public DbHandlerCare(Context ctx) {
		this.ctx = ctx;
		helper = new DbHelperCare(ctx);
		db = helper.getWritableDatabase();  // DB가  open 된다.
	}
	
	public static DbHandlerCare open(Context ctx) throws SQLException {
		DbHandlerCare handler = new DbHandlerCare(ctx);
		return handler;
	}
	
	public void close() {
		helper.close();
	}
	
	public Cursor selectAll(String babyid) {
		String sql = "select id,month,vac,bogun,etc from babycare where babyid = ?";
		Cursor cursor = db.rawQuery(sql, new String[] {babyid});
		return cursor;
	}
	
	public Cursor selectCom(String babyid) {
		String sql = "select id, month, vac, bogun, etc, complate, comdate from babycare where complate is not null and babyid = ? order by comdate desc";
		Cursor cursor = db.rawQuery(sql, new String[] {babyid});
		return cursor;
	}
	
	public void UpdateCom(String date, String id) {
		String sql = "update babycare set complate = 'true', comdate= '"+date+"' where id = "+id;
		db.execSQL(sql);
	}
	
	public void DeleteCom(String id) {
		String sql = "update babycare set complate = null, comdate = null where id = "+id;
		db.execSQL(sql);
	}
	
	public Cursor searchDosi(String Dosi){
		String sql = "select name, tel, address1, address2 from hospital where address1 = ?";
		Cursor cursor = db.rawQuery(sql, new String[] {Dosi});
		return cursor;
	}
	public Cursor searchAddr(String Dosi, String address){
		String sql = "select name, tel, address1, address2 from hospital where address1 = ? and address2 like ?";
		Cursor cursor = db.rawQuery(sql, new String[] {Dosi,"%"+address+"%"});
		return cursor;
	}
	public Cursor searchName(String Dosi, String name){
		String sql = "select name, tel, address1, address2 from hospital where address1 = ? and name like ?";
		Cursor cursor = db.rawQuery(sql, new String[] {Dosi, "%"+name+"%"});
		return cursor;
	}
	public Cursor searchAll(String Dosi, String address, String name){
		String sql = "select name, tel, address1, address2 from hospital where address1 = ? and address2 like ? and name like ?";
		Cursor cursor = db.rawQuery(sql, new String[] {Dosi,"%"+address+"%","%"+name+"%"});
		return cursor;
	}
	public void insertData(String month,String vac,String bogun,String etc, String babyid){
		String sql = "insert into babycare (month, vac, bogun, etc, babyid) values (?,?,?,?,?);";
		db.execSQL(sql, new String[] {month,vac,bogun,etc,babyid});
	}
	
	public void updateData(String month,String vac,String bogun,String etc, String id){
		String sql = "update babycare set month=?, vac=?, bogun=?, etc=? where id = ?;";
		db.execSQL(sql, new String[] {month,vac,bogun,etc,id});
	}
	
	public void deleteData(String id) {
		String sql = "delete from babycare where id = ?";
		db.execSQL(sql, new String[] {id});
	}
	
	public Cursor selectData(String id) {
		String sql = "select id, month, vac, bogun, etc, complate, comdate from babycare where id = ?";
		Cursor cursor = db.rawQuery(sql, new String[] {id});
		return cursor;
	}
	
	//Diary용 select
	public Cursor diary_Select(String date, String babyid){
		String sql = "select id, vac, complate from babycare where comdate=? and babyid=?";
		Cursor cursor = db.rawQuery(sql, new String[]{date, babyid});
		return cursor;
	}//diary_Select
	
	public void insertdata(String babyid) {//데이타 입력
		String sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('1개월이내', 'B형간염 1차', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('1개월이내', 'BCG(결핵)', 'O', 'BCG는 보건소에서도 가능하나 약간의 흉터가 남을 수 있음.','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('1개월', 'B형간염 2차', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('2개월', 'DTaP 1차', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('2개월', '소아마비 1차', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('2개월', '뇌수막염 Hib 1차', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('2개월', '폐구균 1차', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('2개월', '로타바이러스 1차', 'X', '먹는약','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('4개월', 'DTaP 2차', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('4개월', '소아마비 2차', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('4개월', '뇌수막염 Hib 2차', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('4개월', '폐구균 2차', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('4개월', '로타바이러스 2차', 'X', '먹는약','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('6개월', 'DTaP 3차', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('6개월', '소아마비 3차', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('6개월', '뇌수막염 Hib 3차', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('6개월', '폐구균 3차', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('6개월', '로타바이러스 3차', 'X', '먹는약','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('6개월', 'B형간염 3차', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('12~15개월', '수두', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('12~15개월', 'MMR(홍역/볼거리/풍진)1차', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('12~15개월', '뇌수막염 Hib 3차', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('12~15개월', '폐구균 4차', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('12~23개월', '일본뇌염 사백신 1차', 'O', '*사백신 선택시 총 5회 접종','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('12~23개월', '일본뇌염 사백신 2차', 'O', '1차 접종 후 1~2주 후','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('12~23개월', '일본뇌염 생백신 1차', 'X', '*생백신 선택시 총 3회 접종','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('12~23개월', 'A형 간염1차', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('15~18개월', 'DTap 4차', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('18-35개월', 'A형 간염2차', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('24-35개월', '일본뇌염 사백신 3차', 'O', '사백신 VS 생백신 중 선택 1','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('24-35개월', '일본뇌염 생백신 2차', 'X', '사백신 VS 생백신 중 선택 1','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('4~6세', 'DTap 5차', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('4~6세', '소아마비 4차', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('4~6세', 'MMR(홍역/볼거리/풍진)2차', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('6세', '일본뇌염 사백신 4차', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('6세', '일본뇌염 생백신 3차', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('11~12세', 'Tb(파상풍/디프테리아/백일해)', 'X', '*10년마다 추가접종','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('12세', '일본뇌염 사백신 5차', 'O', '','"+babyid+"');";
		db.execSQL(sql);	
	}

}
