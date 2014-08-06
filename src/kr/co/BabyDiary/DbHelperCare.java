package kr.co.BabyDiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelperCare extends SQLiteOpenHelper{
	private static final String DB_NAME = "care";
	private static final int DB_VERSION = 1;
	public DbHelperCare(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table babycare ("+
			"id integer primary key autoincrement, "+
			"month text, " +
			"vac text, " +
			"bogun text, " +
			"complate text, " +
			"comdate text, " +
			"etc text, " +
			"babyid text);";

		db.execSQL(sql);   //DDL��  ����
		
		//insertdata(db); //����Ÿ �Է� �޼ҵ�
		
		sql = "create table hospital ("+
			"id integer primary key autoincrement, "+
			"name text, "+
			"tel text, "+
			"address1 text, "+
			"address2 text);";
		db.execSQL(sql);
		insertaddress(db);
		
		
	}
	
	@Override
	//DB�� upgrade �Ǹ� �ڵ� ȣ��
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exist babycare;");
		onCreate(db);
		db.execSQL("drop table if exist hospital;");
		onCreate(db);
	}
	
	public void insertdata(SQLiteDatabase db) {//����Ÿ �Է�
		String sql = "insert into babycare (month, vac, bogun, etc) values ('1�����̳�', 'B������ 1��', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('1�����̳�', 'BCG(����)', 'O', 'BCG�� ���Ǽҿ����� �����ϳ� �ణ�� ���Ͱ� ���� �� ����.');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('1����', 'B������ 2��', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('2����', 'DTaP 1��', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('2����', '�ҾƸ��� 1��', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('2����', '�������� Hib 1��', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('2����', '�󱸱� 1��', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('2����', '��Ÿ���̷��� 1��', 'X', '�Դ¾�');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('4����', 'DTaP 2��', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('4����', '�ҾƸ��� 2��', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('4����', '�������� Hib 2��', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('4����', '�󱸱� 2��', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('4����', '��Ÿ���̷��� 2��', 'X', '�Դ¾�');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('6����', 'DTaP 3��', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('6����', '�ҾƸ��� 3��', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('6����', '�������� Hib 3��', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('6����', '�󱸱� 3��', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('6����', '��Ÿ���̷��� 3��', 'X', '�Դ¾�');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('6����', 'B������ 3��', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('12~15����', '����', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('12~15����', 'MMR(ȫ��/���Ÿ�/ǳ��)1��', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('12~15����', '�������� Hib 3��', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('12~15����', '�󱸱� 4��', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('12~23����', '�Ϻ����� ���� 1��', 'O', '*���� ���ý� �� 5ȸ ����');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('12~23����', '�Ϻ����� ���� 2��', 'O', '1�� ���� �� 1~2�� ��');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('12~23����', '�Ϻ����� ����� 1��', 'X', '*����� ���ý� �� 3ȸ ����');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('12~23����', 'A�� ����1��', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('15~18����', 'DTap 4��', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('18-35����', 'A�� ����2��', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('24-35����', '�Ϻ����� ���� 3��', 'O', '���� VS ����� �� ���� 1');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('24-35����', '�Ϻ����� ����� 2��', 'X', '���� VS ����� �� ���� 1');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('4~6��', 'DTap 5��', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('4~6��', '�ҾƸ��� 4��', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('4~6��', 'MMR(ȫ��/���Ÿ�/ǳ��)2��', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('6��', '�Ϻ����� ���� 4��', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('6��', '�Ϻ����� ����� 3��', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('11~12��', 'Tb(�Ļ�ǳ/�����׸���/������)', 'X', '*10�⸶�� �߰�����');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('12��', '�Ϻ����� ���� 5��', 'O', '');";
		db.execSQL(sql);	
	}
	public void insertaddress(SQLiteDatabase db) {
		String sql = "insert into hospital (name, tel, address1, address2) values ('365���￬���ǿ�','02-432-7580','����','�߶��� ���캻�� 209-8 2��');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('365�����ǿ�','02-976-3658','����','����� �ϰ�1�� 283���� (3��)');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('���������ǿ�','02-427-7575','����','������ ����2�� 48 4�� 4006ȣ');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('���������ǿ�','02-529-7527','����','������ ����2�� 168-10 2��');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�������ǿ�','051-637-0175','�λ�','���� ����2�� 539-36�������ݰ� 2��');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�������ǿ�','051-752-3946','�λ�','������ ������ 59-3');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�ǰ��÷����ǿ�','051-890-8088','�λ�','�λ����� ���ߵ� 624-7 (����Ȩ�÷���4��)');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('���߱⵶����','053-622-2301','�뱸','�޼��� ������ 183-12 183-12���� ��)���߱⵶����');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��������ǿ�','053-561-7172','�뱸','���� ��1�� 766-12���� 766-12');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�����޵�Į�ǿ�','032-581-9661','��õ','���� ����3�� 469 2�� 469���� 2�� 202ȣ');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��ȭ����','032-930-8100','��õ','��ȭ�� ��ȭ�� ������ 173-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��â�Գ����ǿ�','042-935-6799','����','������ �۰��� 195-12����');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�漺�ǿ�','042-525-3928','����','���� ������ 1418 �漺���� 311ȣ');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��ȭ�����ǿ�','042-637-8900','����','���� ����1��200-91���� 2�� 2��');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�氡�����а��ǿ�','042-273-7582','����','���� �Ǿϵ� 323-10');";
		db.execSQL(sql);
		//��õ���Ǽ�
		sql = "insert into hospital (name, tel, address1, address2) values ('�˴ܺ��Ǽ�','032-560-4991','��õ','���� ���� 7-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('���������','032-562-2820','��õ','���� �� 151-6');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��õ�������Ǽ�','032-560-5010','��õ','���� �ɰ 246-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��纸�Ǽ�','032-450-4901','��õ','��籸 ��굿 1079-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��⺸�Ǽ�','032-513-9704','��õ','��籸 ��⵿ 75-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('���򱸺��Ǽ�','032-509-8200','��õ','���� ���� 159-2');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('���򺸰Ǽ�','032-509-6550','��õ','���� ���� 442-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�������Ǽ�','032-453-2865','��õ','������ ����6�� 1008-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��õ�������Ǽ�','032-770-5720','��õ','���� �۸��� 109-0 ����û');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��õ�������Ǽ�','032-760-9549','��õ','���� ������ 18-3');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��õ�������Ǽ�','032-862-4006','��õ','���� ��ȭ�� 357-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�������Ǽ�','032-880-2480','��õ','�߱� ���ﵿ3�� 7-215');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��õ�߱����Ǽ�','032-772-4001','��õ','�߱� ���ﵿ2�� 23-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�߱����Ǽ�','032-772-0059','��õ','�߱� ���� 34-9');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('���������Ǽ�','032-810-7800','��õ','������ û�е� 465-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('������������','032-880-2765','��õ','������ ������ ���� 157-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��ɺ�������','032-880-2763','��õ','������ ��ɸ� ���̸� 695-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��ߺ��������','032-834-9836','��õ','������ ������ ��Ƹ� 27-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('���纸�������','032-889-4769','��õ','������ ����� ���縮 67-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�Ҿߺ��������','032-831-7874','��õ','������ ������ �Ҿ߸� 120-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�º����������','032-831-5507','��õ','������ �ڿ��� �º��� 590-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('���򺸰�����','032-880-2762','��õ','������ �۸��� ���� 189-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('���ﺸ������','032-880-2767','��õ','������ ����� ���� 227-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�ڿ���������','032-880-2766','��õ','������ �ڿ��� �ڿ��� 1022-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('������������','032-751-7776','��õ','������ �ϵ��� ����� 1081-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��ȭ���Ǽ�','032-930-3550','��õ','��ȭ�� ��ȭ�� ���긮 214-4');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��ȭ�ϸ����������','032-932-3653','��õ','��ȭ�� ���� �ϸ� 235-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('���򺸰Ǽ�','032-937-1510','��õ','��ȭ�� �絵�� ���� 272-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('������������','032-932-6513','��õ','��ȭ�� ������ ��渮 11-43');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��󺸰�����','032-937-0212','��õ','��ȭ�� ���� �¼��� 502-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�������������','032-934-1676','��õ','��ȭ�� ������ ������ 1272-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('������������','032-932-6015','��õ','��ȭ�� ������ ��õ�� 521-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�������������','032-932-2819','��õ','��ȭ�� ������ Ȳû�� 119-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�������������','032-932-3564','��õ','��ȭ�� ���� ������ 330-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('������������','032-937-4339','��õ','��ȭ�� ������ �ο 252-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��꺸������','032-932-3020','��õ','��ȭ�� ���� ���� 161-6 1��');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�Ｑ���������','032-932-4808','��õ','��ȭ�� ������ �Ｑ�� 450-7');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��뺸�������','032-932-4381','��õ','��ȭ�� ������ ��븮 12-0' );";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('���˺��������','032-932-3923','��õ','��ȭ�� ���� ���˸� 76-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('������������','032-932-7008','��õ','��ȭ�� ������ �ֹ��� 892-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�������������','032-937-4092','��õ','��ȭ�� ���� ���θ� 938-6');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('������������','032-933-4966','��õ','��ȭ�� ������ �ݿ��� 235-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('���غ�������','032-934-2980','��õ','��ȭ�� ���ظ� ������ 358-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�Żﺸ�Ǽ�','032-933-1193','��õ','��ȭ�� ������ �Ż︮ 787-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�絵��������','032-937-2104','��õ','��ȭ�� �絵�� ���ϸ� 188-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('������������','032-932-0591','��õ','��ȭ�� ���ظ� ����� 533-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�������������','032-932-1412','��õ','��ȭ�� ������ ���� 782-2');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('���κ��������','032-937-6813','��õ','��ȭ�� ������ ���θ� 315-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��ȭ���������','032-934-0528','��õ','��ȭ�� ���� ��ȭ�� 360-6');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('������������','032-933-3418','��õ','��ȭ�� ������ �ź��� 25-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('ȭ����������','032-937-0256','��õ','��ȭ�� ȭ���� ��渮 843-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��ȭ���������','032-934-0528','��õ','��ȭ�� ���� ��ȭ�� 360-6');";
		db.execSQL(sql);
		//���ﺸ�Ǽ�
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư���ð��������Ǽ�','02-3451-2500','����','������ �Ｚ�� 66����');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư���ð��������Ǽ�','02-2224-0700','����','������ ����1�� 541-2');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư���ð��ϱ����Ǽ�','02-901-0700','����','���ϱ� ��2�� 232');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư���ð��������Ǽ�','02-2600-5800','����','������ ��â�� 275-12 (������ ���׷� 116)');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư���ð��Ǳ����Ǽ�','02-881-5556','����','���Ǳ� ��õ4�� 1570-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư���ñ��������Ǽ�',' 02-450-1579','����','������ �ھ絿 777���� ���������Ǽ� 3�� �ǰ�������');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư���ñ��α����Ǽ�',' 02-860-2600','����','���α� ����5�� 109-4');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư���ñ�õ�����Ǽ�',' 02-2627-2212','����','��õ�� ����1�� 1020���� ��õ�����Ǽ�');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư���ó�������Ǽ�',' 02-950-3425','����','����� ��赿 701-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư���õ��������Ǽ�',' 02-2289-8441','����','������ �ֹ�2�� 565 �������ǰ�');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư���õ��빮�����Ǽ�','02-2127-5000','����','���빮�� ���1�� 39-9');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư���õ��۱����Ǽ�','02-820-9494','����','���۱� ��2�� 176-3 ��ȭ���� 10');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư���ø��������Ǽ�','02-3153-9072','����','������ ����2�� ��������30(���굿370����)');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư���ü��빮�����Ǽ�','02-330-1801','����','���빮�� ����3�� 165-2');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư���ü��ʱ����Ǽ�','02-2155-8063','����','���ʱ� ����2�� 1376-3');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư���ü��������Ǽ�','02-2286-7000','����','������ ȫ�͵� 16-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư���ü��ϱ����Ǽ�','02-920-1971','����','���ϱ� �Ͽ�� 46-1 ���ϱ����Ǽ� 7�� �ǰ�������');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư���ü��ı����Ǽ�','02-2147-3460','����','���ı� ��õ�� 29-5');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư���þ�õ�����Ǽ�','02-2620-3877','����','��õ�� ����6�� 321-5');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư���ÿ����������Ǽ�','002-2670-4744','����','�������� ��굿 3�� 385-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư���ÿ�걸���Ǽ�','02-2199-8073','����','��걸 ��ȿ��1�� 25');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư�������α����Ǽ�','02-731-0208','����','���α� ���ε� 45-30');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư�������򱸺��Ǽ�','02-351-8208','����','���� ����� 84 ���� ���Ǽ�');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư�����߱����Ǽ�','02-2250-4400','����','�߱� ���е�50-5 �߱����Ǽ� ����������');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('����Ư�����߶������Ǽ�','02-490-3741','����','�߶��� �ų�2�� 662');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�����������Ǽ�','02-380-1461','����',' ���� ����� ������������ ��������������');";
		db.execSQL(sql);
		//��⵵���Ǽ�
		sql = "insert into hospital (name, tel, address1, address2) values ('��⵵���򱺺��Ǽ�','031-580-2815','���','���� ������ ������ 624-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��⵵���ô��籸���Ǽ�','031-8075-4039','���','���� ���籸 �ֱ��� 603');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��⵵�����ϻ굿�����Ǽ�','031-961-3751','���','���� �ϻ굿�� ���ε� 1010���� KT������� �� 1��');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��⵵��õ�ú��Ǽ�','202-2150-3810','���','��õ�� �߾ӵ� 1-3');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��⵵�����ϻ꼭�����Ǽ�','031-8075-4172','���','���� �ϻ꼭�� �ϻ�2�� 542-26');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��⵵����ú��Ǽ�','02-898-8857','���','����� �Ͼ�1�� 230');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��⵵���ֽú��Ǽ�','031-760-2110 ','���','���ֽ� ��ȵ� 115����');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��⵵�����ú��Ǽ�','031-550-2552 ','���','������ ��â�� 674-3');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��⵵�����ú��Ǽ�','031-461-5464','���','������ ������ 632���� (�ΰ ��126-1)');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��⵵�����ú��Ǽ�','031-980-5484 ','���','������ ��쵿 869');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��⵵�����ֽú��Ǽ�','031-590-2552 ','���','�����ֽ� �ݰ 185-10');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��⵵����õ�ú��Ǽ�','031-860-2551 ','���','����õ�� ������ 714-9');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��⵵��õ�üһ籸���Ǽ�','032-4625-4350','���','��õ�� �һ籸 �һ纻2�� 64');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��⵵��õ�ÿ��������Ǽ�','032-625-4473','���','��õ�� ������ ������ 129');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��⵵��õ�ÿ��̱����Ǽ�','032-320-3801','���','��õ�� ���̱� ��2�� 1119');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('��⵵�����úд籸���Ǽ� ','031-729-5360 ','���','������ �д籸 ��ž�� 349 �д籸���Ǽ� 2�� ����������');";
		db.execSQL(sql);

		//���� ���Ǽ�
		sql = "insert into hospital (name, tel, address1, address2) values('�����������ú��Ǽ�','033-641-4000','����','������ ��� 413');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('�������������Ǽ�','033-681-4000','����','���� ������ �ž�2�� 178');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���������ؽú��Ǽ�','033-532-4000','����','���ؽ� õ� 840����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��������ô�ú��Ǽ�','033-572-4000','����','��ô�� ���絿 347-4����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���������ʽú��Ǽ�','033-633-4866','����','���ʽ� ������ 72');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('�������籸�����Ǽ�','033-4812-2400','����','�籸�� �籸�� �� 315');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��������籺���Ǽ�','033-671-8733','����','��籺 ����� ��â�� 203-5 ��籺���Ǽ�');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���������������Ǽ�','033-372-4000','����','������ ������ �ϼ۸� 236-2����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���������ֽú��Ǽ�','033-737-4015','����','���ֽ� �ϻ굿 211���� ���ְǰ���ȭ���� 3�� ���ǻ����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���������������Ǽ�','033-461-2425','����','������ ������ ���ϸ�789-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���������������Ǽ�','033-560-2550','����','������ ������ ���縮 80-5');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('������ö�������Ǽ�','033-450-5550','����','ö���� ������ ��ź�� 960-2');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��������õ�ú��Ǽ�','033-250-3550','����','��õ�� �߾ӷ�3�� 67-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�������¹�ú��Ǽ�',  '033-552-4000','����','�¹�� Ȳ���� 244-3����');";
		db.execSQL(sql);


		//�λ� ���Ǽ�
		sql = "insert into hospital (name, tel, address1, address2) values ('�λ걤���úλ��������Ǽ�','051-808-5351','�λ�','���� ��õ1�� 849-10');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('�λ걤���úϱ����Ǽ�','051-341-0117','�λ�','�ϱ� ȭ�� 1531-4');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('�λ걤���û�󱸺��Ǽ�','051-310-4887','�λ�','��� ������ ��û�� 34');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('�λ걤���û��ϱ����Ǽ�','051-220-5703','�λ�','���ϱ� ����2�� 647-5');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('�λ걤���ü������Ǽ�','051-242-4000','�λ�','���� �ο뵿 2�� 86');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('�λ걤���ü��������Ǽ�','051-752-4000','�λ�','������ ����1�� 661-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('�λ걤���ÿ��������Ǽ�','051-665-4000','�λ�','������ ����2�� 1555���� ���������Ǽ�');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('�λ걤���ÿ��������Ǽ�','051-416-4000','�λ�','������ û��2�� 48-3');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('�λ걤�����߱����Ǽ�','051-600-4741','�λ�','�߱� ��û��1�� 1����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('�λ걤�����ؿ�뱸���Ǽ�','051-746-4000','�λ�','�ؿ�뱸 �µ� 1339');";
		db.execSQL(sql);
		

		//�뱸 ���Ǽ�
		sql = "insert into hospital (name, tel, address1, address2) values ('�뱸�����úϱ����Ǽ�','053-665-3216','�뱸','�ϱ� ħ��3�� 521-2');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('�뱸�����ü������Ǽ�','053-663-3169','�뱸','���� ��3�� 1230-9');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('�뱸�����ü��������Ǽ�','053-666-3111','�뱸','������ �ߵ� 266-5');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('�뱸�������߱����Ǽ�','053-661-3121','�뱸','�߱� �����3�� 390');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('�뱸�����ó������Ǽ�','053-664-3601','�뱸','���� ���2�� 2003');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('�뱸�����ô޼������Ǽ�','053-667-3121','�뱸','�޼��� ������ 281');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('�뱸�����ô޼������Ǽ�','053-611-4001','�뱸','�޼��� ��ǳ�� ������ 93-1����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('�뱸�����õ������Ǽ�','053-955-3111','�뱸','���� �˻絿 1005-8');";
		db.execSQL(sql);
		

		//���� ���Ǽ�
		sql = "insert into hospital (name, tel, address1, address2) values ('���ֱ����ñ��걸���Ǽ�','062-942-3011','����','���걸 ������ 833-8 ���걸û ���ǰ����� ����������');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ֱ����ó������Ǽ�','062-650-7692','����','���� ����2�� 516����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ֱ����õ������Ǽ�','062-608-2765','����','���� ������ 31���� 31 �������Ǽ�');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ֱ����úϱ����Ǽ�','062-410-8961','����','�ϱ� ���ﵿ 359');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ֱ����ü������Ǽ�','062-350-4157','����','���� �󼺵� 269-6');";
		db.execSQL(sql);
		

		//���� ���Ǽ�
		sql = "insert into hospital (name, tel, address1, address2) values ('���������ô�������Ǽ�','042-608-5499','����','����� ����5�� 10');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���������õ������Ǽ�','042-629-1107','����','���� �Ｚ�� ���Ϸ�22');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���������ü������Ǽ�','042-611-5367','����','���� ���⵿ 340����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('�������������������Ǽ�','042-611-5104','����','������ ��뵿 ����2�� 47 ���������Ǽ� �����Ƿ���');";
		db.execSQL(sql);


		//��� ���Ǽ�
		sql = "insert into hospital (name, tel, address1, address2) values ('��걤���ó������Ǽ�','052-226-2805','���','���� ����߷� 180');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��걤���õ������Ǽ�','052-209-4137','���','���� ȭ���� ȭ���� 222');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��걤���úϱ����Ǽ�','052-289-3450','���','�ϱ� ���ϵ� 1082����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��걤���ÿ��ֱ����Ǽ�','052-265-9095','���','���ֱ� �ﳲ�� ������ 1605-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��걤�����߱����Ǽ�','052-211-4000','���','�߱� ������ 1110');";
		db.execSQL(sql);


		//��� ���Ǽ�
		sql = "insert into hospital (name, tel, address1, address2) values ('��û�ϵ����걺���Ǽ�','043-830-2664','���','���걺 ������ ���θ� 641');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�ϵ��ܾ籺���Ǽ�','043-422-2974','���','�ܾ籺 �ܾ��� ��� 311');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�ϵ����������Ǽ�','043-540-3534','���','������ ���������縮 91-3');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�ϵ����������Ǽ�','043-8740-5581','���','������ ������ ��õ�� 444-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�ϵ���õ�����Ǽ�','043-730-2103','���','��õ�� ��õ�� ��縮 161-45');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�ϵ����������Ǽ�','043-871-3614','���','������ ������ ������ 406-4');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�ϵ���õ�ú��Ǽ�','043-640-4011','���','��õ�� û���� 653���� 653����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�ϵ����򱺺��Ǽ�','043-835-3571','���','���� ������ ���Ǻ����� 64-1(������57����)');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�ϵ���õ�����Ǽ�','043-539-4001','���','��õ�� ���ϸ� 570');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�ϵ�û�������Ǽ�','043-251-4131','���','û�ֽ� ��籸 ���ϵ� 208���� -û�������Ǽ�');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�ϵ�û�ֽû�籸���Ǽ�','043-200-4038','���','û�ֽ� ��籸 �뼺�� 172���� 21');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�ϵ�û�ֽ���������Ǽ�','043-200-4130','���','û�ֽ� ����� ����1�� 280 ( ����1�� 888)');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�ϵ����ֽú��Ǽ�','043-850-3534','���','���ֽ� �ݴɵ� 700 -���ֽú��Ǽ� ���ں��ǽ�');";
		db.execSQL(sql);
		

		//�泲
		sql = "insert into hospital (name, tel, address1, address2) values ('��û�������ú��Ǽ�','042-840-2102','�泲','���� �ݾϵ� 10-5');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�������ֽú��Ǽ�','041-855-5244','�泲','���ֽ� �Ű��� 571-8');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�����ݻ걺���Ǽ�','041-750-4335','�泲','�ݻ걺 �ݼ��� �߾ӷ� 46����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�������ú��Ǽ�','041-730-4126','�泲','���� ���˵� 382');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�������������Ǽ�','041-350-4011','�泲','������ ������ä� 1040');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�������ɽú��Ǽ�','041-930-9006','�泲','���ɽ� ������ ������ 37-4 ���ǻ���� �����Ǿ�');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�����ο������Ǽ�','041-830-2482','�泲','�ο��� �ο��� ���Ƹ� 137-2');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û��������ú��Ǽ�','041-660-2550','�泲','����� ������ 581-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û������õ�����Ǽ�','041-953-4000','�泲','��õ�� ��õ�� ���縮 799');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�����ƻ�ú��Ǽ�','041-544-4000','�泲','�ƻ�� ������ 573-2');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�������ⱺ���Ǽ�','041-860-4973','�泲','���ⱺ ��ġ�������� 129-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�������걺���Ǽ�','041-339-8000','�泲','���걺 ������ ���긮 23-1 23-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û����õ�Ƚú��Ǽ�','041-521-2555','�泲','õ�Ƚ� �Ҵ絿 234-1 õ�Ƚú��Ǽ� ���ڰǰ���');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û����û�籺�����Ƿ��','041-942-3401','�泲','û�籺 û���� ������ 261-1 û�籺�����Ƿ��');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û�����¾ȱ������Ƿ��','041-671-5300','�泲','�¾ȱ� �¾��� ��õ�� 698-6');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��û����ȫ�������Ǽ�','041-632-2588','�泲','ȫ���� ȫ���� ���ϸ� 62-3');";
		db.execSQL(sql);
	 

		//���� ���Ǽ�
		sql = "insert into hospital (name, tel, address1, address2) values ('����ϵ���â�����Ǽ�','063-560-2377','����','��â�� ��â�� ���踮 101����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('����ϵ�����ú��Ǽ�','063-460-3245','����','����� ����3�� 153-8');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('����ϵ������ú��Ǽ�','063-540-1300','����','������ ���̵� 423-2');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('����ϵ������ú��Ǽ�','063-625-4000','����','������ ���굿 455');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('����ϵ����ֱ������Ƿ��','063-322-2201','����','���ֱ� ������ ��ǳ��� 413');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('����ϵ��ξȱ����Ǽ�','063-584-1261','����','�ξȱ� �ξ��� ������ 551-2');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('����ϵ���â�������Ƿ��','063-650-5238','����','��â�� ��â�� ������ 541');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('����ϵ����ֱ����Ǽ�','063-291-2400','����','���ֱ� ����� �űݸ� 416-6 -����� 215-20');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('����ϵ��ͻ�ú��Ǽ�','063-858-4078','����','�ͻ�� ����1�� 147');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('����ϵ��ӽǱ������Ƿ��','063-640-3125','����','�ӽǱ� �ӽ��� 270-8');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('����ϵ�����������Ƿ��','063-351-8000','����','����� ����� ����� 425');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('����ϵ����ֽú��Ǽ�','063-230-5100','����','���ֽ� �ϻ걸 �߾ӵ�4�� 30-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('����ϵ������ú��Ǽ�','063-533-8582','����','������ ������ 958-2');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('����ϵ����ȱ����Ǽ�','063-433-4000','����','���ȱ� ������ ���� 90-15');";
		db.execSQL(sql);


		
		//���� ���Ǽ�
		sql = "insert into hospital (name, tel, address1, address2) values ('���󳲵����������Ǽ�','061-430-3531','����','������ ������ ������ 66-3����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���󳲵����ﱺ���Ǽ�','061-830-5561','����','���ﱺ ������ ��ϸ� 1258-21');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���󳲵���������Ƿ��','061-362-8262','����','��� ��� ������ 663');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���󳲵�����ú��Ǽ�','061-797-4004','����','����� ������ ĥ����70');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���󳲵����ʱ������Ƿ��','061-780-2550','����','���ʱ� ������ ��ø� 576');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���󳲵����ֽú��Ǽ�','061-333-3003','����','���ֽ� ��â�� 740-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���󳲵���籺���Ǽ�','061-383-4000','����','��籺 ����� ������ 135����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���󳲵������ú��Ǽ�','061-277-4000','����','������ ������ 1676');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���󳲵����ȱ����Ǽ�','061-453-2400','����','���ȱ� ������ ������ 712-2����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���󳲵����������Ǽ�','061-853-4000','����','������ ������ ������ 832-3');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���󳲵���õ�ú��Ǽ�','061-749-3491','����','��õ�� ǳ���� 1264 1264');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���󳲵��žȱ����Ǽ�','061-243-8550','����','������ ��ȣ�� 3��4 3-4');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���󳲵������ú��Ǽ�','061-683-4000','����','������ �е� 174');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���󳲵����������Ǽ�','061-350-5552','����','������ ������ ��õ�� 326-5');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���󳲵����ϱ����Ǽ�','061-470-2550','����','���ϱ� ������ �������� 45');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���󳲵��ϵ��������Ƿ��','061-550-6765','����','�ϵ��� �ϵ��� ��û�� 483-8');";
		db.execSQL(sql);
	

		//��� ���Ǽ�
		sql = "insert into hospital (name, tel, address1, address2) values ('���ϵ����ú��Ǽ�','053-810-6465','���','���� �߹浿 708-5');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ϵ����ֽú��Ǽ�','054-745-4000','���','���ֽ� ��õ�� 987����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ϵ���ɱ����Ǽ�','054-954-1300','���','��ɱ� ����� ������ 563-3');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ϵ����̽ú��Ǽ�','054-450-6473','���','���̽� ���굿 853-12');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ϵ����̽ü��꺸�Ǽ�','054-481-4000','���','���̽� ������ ���θ� 544-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ϵ����������Ǽ�','054-383-4000','���','������ ������ �߾ӱ� 30-10����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ϵ���õ�ú��Ǽ�','054-433-4000','���','��õ�� ������ 1284');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ϵ�����ú��Ǽ�','054-555-8074','���','����� ���̵� 232����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ϵ���ȭ�����Ǽ�','054-673-4000','���','��ȭ�� ��ȭ�� ������ 285');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ϵ����ֽú��Ǽ�','054-535-4000','���','���ֽ� ���絿 33-4');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ϵ����ֱ����Ǽ�','054-933-2400','���','���ֱ� ������ ���4�� 436-1����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ϵ��ȵ��ú��Ǽ�','054-840-5950','���','�ȵ��� �Ϲ��� 58����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ϵ����������Ǽ�','054-730-6473','���','������ ������ ȭ����208-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ϵ����籺���Ǽ�','054-683-2043','���','���籺 ������ ���θ� 292-4����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ϵ����ֽú��Ǽ�','054-631-4000','���','���ֽ� ��õ2�� 466-6');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ϵ���õ�ú��Ǽ�','054-331-4000','���','��õ�� ������152-1');";
		db.execSQL(sql);
	

		//�泲 ���Ǽ�
		sql = "insert into hospital (name, tel, address1, address2) values ('��󳲵������ú��Ǽ�','055-639-3800','�泲','������ ������ 981����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��󳲵���â�����Ǽ�','055-940-8355','�泲','��â�� ��â�������� 21����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��󳲵��������Ǽ�','055-670-4090','�泲','���� ���� ������79���� 103-3');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��󳲵����ؽú��Ǽ�','055-330-4481','�泲','���ؽ� �ܵ� 1261-3����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��󳲵����ر����Ǽ�','055-860-8776','�泲','���ر� �Ϻ��� 458-2����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��󳲵��о�ú��Ǽ�','055-359-7007','�泲','�о�� �﹮�� 159-1���� �о�ú��Ǽ�');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��󳲵���õ�ú��Ǽ�','055-831-3526','�泲','��õ�� ������ ��� 501����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��󳲵���û�������Ƿ��','055-970-7500','�泲','��û�� ��û�� ���� 179-7����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��󳲵����ú��Ǽ�','055-388-4000','�泲','���� �ߺε� 707-2����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��󳲵��Ƿɱ����Ǽ�','055-570-4010','�泲','�Ƿɱ� �Ƿ��� ������ 843-2����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��󳲵����ֽú��Ǽ�','055-749-2441','�泲','���ֽ� ������ 3-18����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��󳲵�â�籺���Ǽ�','055-530-6277','�泲','â�籺 â���� ���긮 â���� 172');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��󳲵�â���ø��꺸�Ǽ�','055-245-4000','�泲','����� �ؿ 61-1����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��󳲵�â�������غ��Ǽ�','055-225-6121','�泲','���ؽ� ǳȣ�� 1���� ���ؽú��Ǽ�(����������)');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��󳲵�â����â�����Ǽ�','055-225-4000','�泲','â���� ��â�� �ſ��� â�����Ǽ�');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('��󳲵��뿵�ú��Ǽ�','055-650-6125','�泲','�뿵�� ������ 401-1���� �뿵�ú��Ǽ�');";
		db.execSQL(sql);


		//���� ���Ǽ�
		sql = "insert into hospital (name, tel, address1, address2) values ('�������õ��κ��Ǽ�','064-764-1856','����','�������� ������ ������ 2359-1����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('�������ü��������Ǽ�','064-733-4091','����','�������� �߾ӷ�101����(��ȫ�� 447-3)���� 52');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('�������ü��κ��Ǽ�','064-760-6251','����','�������� ������ ��� 3862-1����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ֽõ��κ��Ǽ�','064-783-5042','����','���ֽ� ��縮 1811����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ֽü��κ��Ǽ�','064-796-4000','����','���ֽ� �Ѹ��� �Ѹ��� 966-1����');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('���ֽ����ֺ��Ǽ�','064-728-4095','����','���ֽ� ������ 1038(����� 274)����');";
		db.execSQL(sql);
		
	}
	
}
