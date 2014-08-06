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
		db = helper.getWritableDatabase();  // DB��  open �ȴ�.
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
	
	//Diary�� select
	public Cursor diary_Select(String date, String babyid){
		String sql = "select id, vac, complate from babycare where comdate=? and babyid=?";
		Cursor cursor = db.rawQuery(sql, new String[]{date, babyid});
		return cursor;
	}//diary_Select
	
	public void insertdata(String babyid) {//����Ÿ �Է�
		String sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('1�����̳�', 'B������ 1��', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('1�����̳�', 'BCG(����)', 'O', 'BCG�� ���Ǽҿ����� �����ϳ� �ణ�� ���Ͱ� ���� �� ����.','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('1����', 'B������ 2��', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('2����', 'DTaP 1��', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('2����', '�ҾƸ��� 1��', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('2����', '�������� Hib 1��', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('2����', '�󱸱� 1��', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('2����', '��Ÿ���̷��� 1��', 'X', '�Դ¾�','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('4����', 'DTaP 2��', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('4����', '�ҾƸ��� 2��', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('4����', '�������� Hib 2��', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('4����', '�󱸱� 2��', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('4����', '��Ÿ���̷��� 2��', 'X', '�Դ¾�','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('6����', 'DTaP 3��', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('6����', '�ҾƸ��� 3��', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('6����', '�������� Hib 3��', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('6����', '�󱸱� 3��', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('6����', '��Ÿ���̷��� 3��', 'X', '�Դ¾�','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('6����', 'B������ 3��', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('12~15����', '����', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('12~15����', 'MMR(ȫ��/���Ÿ�/ǳ��)1��', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('12~15����', '�������� Hib 3��', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('12~15����', '�󱸱� 4��', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('12~23����', '�Ϻ����� ���� 1��', 'O', '*���� ���ý� �� 5ȸ ����','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('12~23����', '�Ϻ����� ���� 2��', 'O', '1�� ���� �� 1~2�� ��','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('12~23����', '�Ϻ����� ����� 1��', 'X', '*����� ���ý� �� 3ȸ ����','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('12~23����', 'A�� ����1��', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('15~18����', 'DTap 4��', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('18-35����', 'A�� ����2��', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('24-35����', '�Ϻ����� ���� 3��', 'O', '���� VS ����� �� ���� 1','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('24-35����', '�Ϻ����� ����� 2��', 'X', '���� VS ����� �� ���� 1','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('4~6��', 'DTap 5��', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('4~6��', '�ҾƸ��� 4��', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('4~6��', 'MMR(ȫ��/���Ÿ�/ǳ��)2��', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('6��', '�Ϻ����� ���� 4��', 'O', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('6��', '�Ϻ����� ����� 3��', 'X', '','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('11~12��', 'Tb(�Ļ�ǳ/�����׸���/������)', 'X', '*10�⸶�� �߰�����','"+babyid+"');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc, babyid) values ('12��', '�Ϻ����� ���� 5��', 'O', '','"+babyid+"');";
		db.execSQL(sql);	
	}

}
