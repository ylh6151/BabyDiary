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

		db.execSQL(sql);   //DDL문  수행
		
		//insertdata(db); //데이타 입력 메소드
		
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
	//DB가 upgrade 되면 자동 호출
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exist babycare;");
		onCreate(db);
		db.execSQL("drop table if exist hospital;");
		onCreate(db);
	}
	
	public void insertdata(SQLiteDatabase db) {//데이타 입력
		String sql = "insert into babycare (month, vac, bogun, etc) values ('1개월이내', 'B형간염 1차', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('1개월이내', 'BCG(결핵)', 'O', 'BCG는 보건소에서도 가능하나 약간의 흉터가 남을 수 있음.');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('1개월', 'B형간염 2차', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('2개월', 'DTaP 1차', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('2개월', '소아마비 1차', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('2개월', '뇌수막염 Hib 1차', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('2개월', '폐구균 1차', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('2개월', '로타바이러스 1차', 'X', '먹는약');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('4개월', 'DTaP 2차', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('4개월', '소아마비 2차', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('4개월', '뇌수막염 Hib 2차', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('4개월', '폐구균 2차', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('4개월', '로타바이러스 2차', 'X', '먹는약');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('6개월', 'DTaP 3차', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('6개월', '소아마비 3차', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('6개월', '뇌수막염 Hib 3차', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('6개월', '폐구균 3차', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('6개월', '로타바이러스 3차', 'X', '먹는약');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('6개월', 'B형간염 3차', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('12~15개월', '수두', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('12~15개월', 'MMR(홍역/볼거리/풍진)1차', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('12~15개월', '뇌수막염 Hib 3차', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('12~15개월', '폐구균 4차', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('12~23개월', '일본뇌염 사백신 1차', 'O', '*사백신 선택시 총 5회 접종');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('12~23개월', '일본뇌염 사백신 2차', 'O', '1차 접종 후 1~2주 후');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('12~23개월', '일본뇌염 생백신 1차', 'X', '*생백신 선택시 총 3회 접종');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('12~23개월', 'A형 간염1차', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('15~18개월', 'DTap 4차', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('18-35개월', 'A형 간염2차', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('24-35개월', '일본뇌염 사백신 3차', 'O', '사백신 VS 생백신 중 선택 1');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('24-35개월', '일본뇌염 생백신 2차', 'X', '사백신 VS 생백신 중 선택 1');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('4~6세', 'DTap 5차', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('4~6세', '소아마비 4차', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('4~6세', 'MMR(홍역/볼거리/풍진)2차', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('6세', '일본뇌염 사백신 4차', 'O', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('6세', '일본뇌염 생백신 3차', 'X', '');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('11~12세', 'Tb(파상풍/디프테리아/백일해)', 'X', '*10년마다 추가접종');";
		db.execSQL(sql);
		sql = "insert into babycare (month, vac, bogun, etc) values ('12세', '일본뇌염 사백신 5차', 'O', '');";
		db.execSQL(sql);	
	}
	public void insertaddress(SQLiteDatabase db) {
		String sql = "insert into hospital (name, tel, address1, address2) values ('365서울연합의원','02-432-7580','서울','중랑구 망우본동 209-8 2층');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('365열린의원','02-976-3658','서울','노원구 하계1동 283번지 (3층)');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('가람내과의원','02-427-7575','서울','강동구 명일2동 48 4층 4006호');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('가족내과의원','02-529-7527','서울','강남구 개포2동 168-10 2층');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('강내과의원','051-637-0175','부산','남구 문현2동 539-36새마을금고 2층');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('강수연의원','051-752-3946','부산','수영구 수영동 59-3');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('건강플러스의원','051-890-8088','부산','부산진구 가야동 624-7 (가야홈플러스4층)');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('가야기독병원','053-622-2301','대구','달서구 송현동 183-12 183-12번지 의)가야기독병원');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('계수연합의원','053-561-7172','대구','서구 평리1동 766-12번지 766-12');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('가정메디칼의원','032-581-9661','인천','서구 석남3동 469 2층 469번지 2층 202호');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('강화병원','032-930-8100','인천','강화군 강화읍 갑곳리 173-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('강창규내과의원','042-935-6799','대전','유성구 송강동 195-12번지');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('경성의원','042-525-3928','대전','서구 갈마동 1418 경성쇼핑 311호');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('근화내과의원','042-637-8900','대전','동구 성남1동200-91번지 2층 2층');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('길가정의학과의원','042-273-7582','대전','동구 판암동 323-10');";
		db.execSQL(sql);
		//인천보건소
		sql = "insert into hospital (name, tel, address1, address2) values ('검단보건소','032-560-4991','인천','서구 연희동 7-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('대곡보건진료소','032-562-2820','인천','서구 대곡동 151-6');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('인천서구보건소','032-560-5010','인천','서구 심곡동 246-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('계양보건소','032-450-4901','인천','계양구 계산동 1079-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('장기보건소','032-513-9704','인천','계양구 장기동 75-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('부평구보건소','032-509-8200','인천','부평구 부평동 159-2');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('부평보건소','032-509-6550','인천','부평구 부평동 442-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('남동보건소','032-453-2865','인천','남동구 만수6동 1008-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('인천동구보건소','032-770-5720','인천','동구 송림동 109-0 동구청');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('인천동구보건소','032-760-9549','인천','동구 만석동 18-3');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('인천남구보건소','032-862-4006','인천','남구 도화동 357-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('옹진보건소','032-880-2480','인천','중구 신흥동3가 7-215');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('인천중구보건소','032-772-4001','인천','중구 신흥동2가 23-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('중구보건소','032-772-0059','인천','중구 전동 34-9');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('연수구보건소','032-810-7800','인천','연수구 청학동 465-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('덕적보건지소','032-880-2765','인천','옹진군 덕적면 진리 157-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('백령보건지소','032-880-2763','인천','옹진군 백령면 진촌리 695-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('백야보건진료소','032-834-9836','인천','옹진군 덕적면 백아리 27-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('선재보건진료소','032-889-4769','인천','옹진군 영흥면 선재리 67-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('소야보건진료소','032-831-7874','인천','옹진군 덕적면 소야리 120-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('승봉보건진료소','032-831-5507','인천','옹진군 자월면 승봉리 590-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('연평보건지소','032-880-2762','인천','옹진군 송림면 연평리 189-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('영흥보건지소','032-880-2767','인천','옹진군 영흥면 내리 227-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('자월보건지소','032-880-2766','인천','옹진군 자월면 자월리 1022-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('장봉보건진료소','032-751-7776','인천','옹진군 북도면 장봉리 1081-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('강화보건소','032-930-3550','인천','강화군 강화읍 남산리 214-4');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('강화하리보건진료소','032-932-3653','인천','강화군 삼산면 하리 235-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('건평보건소','032-937-1510','인천','강화군 양도면 건평리 272-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('교동보건지소','032-932-6513','인천','강화군 교동면 대룡리 11-43');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('길상보건지소','032-937-0212','인천','강화군 길상면 온수리 502-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('난정보건진료소','032-934-1676','인천','강화군 교동면 난정리 1272-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('내가보건지소','032-932-6015','인천','강화군 내가면 고천리 521-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('내서보건진료소','032-932-2819','인천','강화군 내가면 황청리 119-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('매음보건진료소','032-932-3564','인천','강화군 삼산면 매음리 330-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('불은보건지소','032-937-4339','인천','강화군 불은면 두운리 252-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('삼산보건지소','032-932-3020','인천','강화군 삼산면 석모리 161-6 1층');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('삼선보건진료소','032-932-4808','인천','강화군 교동면 삼선리 450-7');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('상용보건진료소','032-932-4381','인천','강화군 교동면 상용리 12-0' );";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서검보건진료소','032-932-3923','인천','강화군 삼산면 서검리 76-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서도보건지소','032-932-7008','인천','강화군 서도면 주문리 892-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('선동보건진료소','032-937-4092','인천','강화군 길상면 선두리 938-6');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('선원보건지소','032-933-4966','인천','강화군 선원면 금월리 235-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('송해보건지소','032-934-2980','인천','강화군 송해면 솔정리 358-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('신삼보건소','032-933-1193','인천','강화군 하점면 신삼리 787-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('양도보건지소','032-937-2104','인천','강화군 양도면 하일리 188-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('양오보건진료소','032-932-0591','인천','강화군 송해면 양오리 533-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('연리보건진료소','032-932-1412','인천','강화군 선원면 연리 782-2');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('오두보건진료소','032-937-6813','인천','강화군 불은면 오두리 315-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('인화보건진료소','032-934-0528','인천','강화군 양사면 인화리 360-6');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('하점보건지소','032-933-3418','인천','강화군 하점면 신봉리 25-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('화도보건지소','032-937-0256','인천','강화군 화도면 상방리 843-0');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('인화보건진료소','032-934-0528','인천','강화군 양사면 인화리 360-6');";
		db.execSQL(sql);
		//서울보건소
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시강남구보건소','02-3451-2500','서울','강남구 삼성동 66번지');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시강동구보건소','02-2224-0700','서울','강동구 성내1동 541-2');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시강북구보건소','02-901-0700','서울','강북구 번2동 232');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시강서구보건소','02-2600-5800','서울','강서구 염창동 275-12 (강서구 공항로 116)');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시관악구보건소','02-881-5556','서울','관악구 봉천4동 1570-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시광진구보건소',' 02-450-1579','서울','광진구 자양동 777번지 광진구보건소 3층 건강관리과');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시구로구보건소',' 02-860-2600','서울','구로구 구로5동 109-4');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시금천구보건소',' 02-2627-2212','서울','금천구 시흥1동 1020번지 금천구보건소');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시노원구보건소',' 02-950-3425','서울','노원구 상계동 701-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시도봉구보건소',' 02-2289-8441','서울','도봉구 쌍문2동 565 지역보건과');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시동대문구보건소','02-2127-5000','서울','동대문구 용두1동 39-9');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시동작구보건소','02-820-9494','서울','동작구 상도2동 176-3 문화원길 10');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시마포구보건소','02-3153-9072','서울','마포구 성산2동 난지도길30(성산동370번지)');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시서대문구보건소','02-330-1801','서울','서대문구 연희3동 165-2');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시서초구보건소','02-2155-8063','서울','서초구 서초2동 1376-3');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시성동구보건소','02-2286-7000','서울','성동구 홍익동 16-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시성북구보건소','02-920-1971','서울','성북구 하월곡동 46-1 성북구보건소 7층 건강관리과');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시송파구보건소','02-2147-3460','서울','송파구 신천동 29-5');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시양천구보건소','02-2620-3877','서울','양천구 신정6동 321-5');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시영등포구보건소','002-2670-4744','서울','영등포구 당산동 3가 385-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시용산구보건소','02-2199-8073','서울','용산구 원효로1가 25');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시종로구보건소','02-731-0208','서울','종로구 옥인동 45-30');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시은평구보건소','02-351-8208','서울','은평구 녹번동 84 은평구 보건소');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시중구보건소','02-2250-4400','서울','중구 무학동50-5 중구보건소 예방접종실');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('서울특별시중랑구보건소','02-490-3741','서울','중랑구 신내2동 662');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('예방접종보건소','02-380-1461','서울',' 은평구 녹번동 질병관리본부 예방접종관리과');";
		db.execSQL(sql);
		//경기도보건소
		sql = "insert into hospital (name, tel, address1, address2) values ('경기도가평군보건소','031-580-2815','경기','가평군 가평읍 읍내리 624-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('경기도고양시덕양구보건소','031-8075-4039','경기','고양시 덕양구 주교동 603');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('경기도고양시일산동구보건소','031-961-3751','경기','고양시 일산동구 마두동 1010번지 KT고양지사 내 1층');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('경기도과천시보건소','202-2150-3810','경기','과천시 중앙동 1-3');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('경기도고양시일산서구보건소','031-8075-4172','경기','고양시 일산서구 일산2동 542-26');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('경기도광명시보건소','02-898-8857','경기','광명시 하안1동 230');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('경기도광주시보건소','031-760-2110 ','경기','광주시 경안동 115번지');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('경기도구리시보건소','031-550-2552 ','경기','구리시 인창동 674-3');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('경기도군포시보건소','031-461-5464','경기','군포시 군포로 632번지 (부곡동 산126-1)');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('경기도김포시보건소','031-980-5484 ','경기','김포시 사우동 869');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('경기도남양주시보건소','031-590-2552 ','경기','남양주시 금곡동 185-10');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('경기도동두천시보건소','031-860-2551 ','경기','동두천시 생연동 714-9');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('경기도부천시소사구보건소','032-4625-4350','경기','부천시 소사구 소사본2동 64');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('경기도부천시오정구보건소','032-625-4473','경기','부천시 오정구 오정동 129');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('경기도부천시원미구보건소','032-320-3801','경기','부천시 원미구 중2동 1119');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('경기도성남시분당구보건소 ','031-729-5360 ','경기','성남시 분당구 야탑동 349 분당구보건소 2층 지역보건팀');";
		db.execSQL(sql);

		//강원 보건소
		sql = "insert into hospital (name, tel, address1, address2) values('강원도강릉시보건소','033-641-4000','강원','강릉시 내곡동 413');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('강원도고성군보건소','033-681-4000','강원','고성군 간성읍 신안2리 178');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('강원도동해시보건소','033-532-4000','강원','동해시 천곡동 840번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('강원도삼척시보건소','033-572-4000','강원','삼척시 남양동 347-4번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('강원도속초시보건소','033-633-4866','강원','속초시 수복로 72');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('강원도양구군보건소','033-4812-2400','강원','양구군 양구읍 상리 315');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('강원도양양군보건소','033-671-8733','강원','양양군 양양읍 연창리 203-5 양양군보건소');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('강원도영월군보건소','033-372-4000','강원','영월군 영월읍 하송리 236-2번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('강원도원주시보건소','033-737-4015','강원','원주시 일산동 211번지 원주건강문화센터 3층 보건사업과');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('강원도인제군보건소','033-461-2425','강원','인제군 인제읍 남북리789-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('강원도정선군보건소','033-560-2550','강원','정선군 정선읍 봉양리 80-5');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('강원도철원군보건소','033-450-5550','강원','철원군 갈말읍 군탄리 960-2');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('강원도춘천시보건소','033-250-3550','강원','춘천시 중앙로3가 67-1');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('강원도태백시보건소',  '033-552-4000','강원','태백시 황지동 244-3번지');";
		db.execSQL(sql);


		//부산 보건소
		sql = "insert into hospital (name, tel, address1, address2) values ('부산광역시부산진구보건소','051-808-5351','부산','진구 범천1동 849-10');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('부산광역시북구보건소','051-341-0117','부산','북구 화명동 1531-4');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('부산광역시사상구보건소','051-310-4887','부산','사상구 감전동 구청로 34');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('부산광역시사하구보건소','051-220-5703','부산','사하구 신평2동 647-5');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('부산광역시서구보건소','051-242-4000','부산','서구 부용동 2가 86');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('부산광역시수영구보건소','051-752-4000','부산','수영구 광안1동 661-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('부산광역시연제구보건소','051-665-4000','부산','연제구 연산2동 1555번지 연제구보건소');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('부산광역시영도구보건소','051-416-4000','부산','영도구 청학2동 48-3');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('부산광역시중구보건소','051-600-4741','부산','중구 대청동1가 1번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('부산광역시해운대구보건소','051-746-4000','부산','해운대구 좌동 1339');";
		db.execSQL(sql);
		

		//대구 보건소
		sql = "insert into hospital (name, tel, address1, address2) values ('대구광역시북구보건소','053-665-3216','대구','북구 침산3동 521-2');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('대구광역시서구보건소','053-663-3169','대구','서구 평리3동 1230-9');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('대구광역시수성구보건소','053-666-3111','대구','수성구 중동 266-5');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('대구광역시중구보건소','053-661-3121','대구','중구 태평로3가 390');";
		db.execSQL(sql);
		sql = "insert into hospital (name, tel, address1, address2) values ('대구광역시남구보건소','053-664-3601','대구','남구 대명2동 2003');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('대구광역시달서구보건소','053-667-3121','대구','달서구 월성동 281');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('대구광역시달성군보건소','053-611-4001','대구','달성군 현풍면 원교리 93-1번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('대구광역시동구보건소','053-955-3111','대구','동구 검사동 1005-8');";
		db.execSQL(sql);
		

		//광주 보건소
		sql = "insert into hospital (name, tel, address1, address2) values ('광주광역시광산구보건소','062-942-3011','광주','광산구 송정동 833-8 광산구청 보건관리팀 질병관리계');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('광주광역시남구보건소','062-650-7692','광주','남구 봉선2동 516번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('광주광역시동구보건소','062-608-2765','광주','동구 서석동 31번지 31 동구보건소');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('광주광역시북구보건소','062-410-8961','광주','북구 중흥동 359');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('광주광역시서구보건소','062-350-4157','광주','서구 농성동 269-6');";
		db.execSQL(sql);
		

		//대전 보건소
		sql = "insert into hospital (name, tel, address1, address2) values ('대전광역시대덕구보건소','042-608-5499','대전','대덕구 망골5길 10');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('대전광역시동구보건소','042-629-1107','대전','동구 삼성동 현암로22');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('대전광역시서구보건소','042-611-5367','대전','서구 만년동 340번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('대전광역시유성구보건소','042-611-5104','대전','유성구 장대동 장터2길 47 유성구보건소 보건의료담당');";
		db.execSQL(sql);


		//울산 보건소
		sql = "insert into hospital (name, tel, address1, address2) values ('울산광역시남구보건소','052-226-2805','울산','남구 삼산중로 180');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('울산광역시동구보건소','052-209-4137','울산','동구 화정동 화정동 222');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('울산광역시북구보건소','052-289-3450','울산','북구 연암동 1082번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('울산광역시울주군보건소','052-265-9095','울산','울주군 삼남면 교동리 1605-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('울산광역시중구보건소','052-211-4000','울산','중구 번영로 1110');";
		db.execSQL(sql);


		//충북 보건소
		sql = "insert into hospital (name, tel, address1, address2) values ('충청북도괴산군보건소','043-830-2664','충북','괴산군 괴산읍 동부리 641');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청북도단양군보건소','043-422-2974','충북','단양군 단양읍 별곡리 311');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청북도보은군보건소','043-540-3534','충북','보은군 보은읍교사리 91-3');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청북도영동군보건소','043-8740-5581','충북','영동군 영동읍 매천리 444-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청북도옥천군보건소','043-730-2103','충북','옥천군 옥천읍 삼양리 161-45');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청북도음성군보건소','043-871-3614','충북','음성군 음성읍 읍내리 406-4');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청북도제천시보건소','043-640-4011','충북','제천시 청전동 653번지 653번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청북도증평군보건소','043-835-3571','충북','증평군 증평읍 보건복지로 64-1(내성리57번지)');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청북도진천군보건소','043-539-4001','충북','진천군 벽암리 570');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청북도청원군보건소','043-251-4131','충북','청주시 상당구 지북동 208번지 -청원군보건소');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청북도청주시상당구보건소','043-200-4038','충북','청주시 상당구 대성로 172번길 21');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청북도청주시흥덕구보건소','043-200-4130','충북','청주시 흥덕구 사직1동 280 ( 사직1동 888)');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청북도충주시보건소','043-850-3534','충북','충주시 금능동 700 -충주시보건소 모자보건실');";
		db.execSQL(sql);
		

		//충남
		sql = "insert into hospital (name, tel, address1, address2) values ('충청남도계룡시보건소','042-840-2102','충남','계룡시 금암동 10-5');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청남도공주시보건소','041-855-5244','충남','공주시 신관동 571-8');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청남도금산군보건소','041-750-4335','충남','금산군 금성면 중앙로 46번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청남도논산시보건소','041-730-4126','충남','논산시 관촉동 382');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청남도당진군보건소','041-350-4011','충남','당진군 당진읍채운리 1040');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청남도보령시보건소','041-930-9006','충남','보령시 남포면 봉덕리 37-4 보건사업과 예방의약');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청남도부여군보건소','041-830-2482','충남','부여군 부여읍 구아리 137-2');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청남도서산시보건소','041-660-2550','충남','서산시 석림동 581-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청남도서천군보건소','041-953-4000','충남','서천군 서천읍 군사리 799');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청남도아산시보건소','041-544-4000','충남','아산시 모종동 573-2');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청남도연기군보건소','041-860-4973','충남','연기군 조치원읍교리 129-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청남도예산군보건소','041-339-8000','충남','예산군 예산읍 예산리 23-1 23-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청남도천안시보건소','041-521-2555','충남','천안시 불당동 234-1 천안시보건소 모자건강팀');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청남도청양군보건의료원','041-942-3401','충남','청양군 청양읍 읍내리 261-1 청양군보건의료원');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청남도태안군보건의료원','041-671-5300','충남','태안군 태안읍 평천리 698-6');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('충청남도홍성군보건소','041-632-2588','충남','홍성군 홍성읍 옥암리 62-3');";
		db.execSQL(sql);
	 

		//전북 보건소
		sql = "insert into hospital (name, tel, address1, address2) values ('전라북도고창군보건소','063-560-2377','전북','고창군 고창읍 율계리 101번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라북도군산시보건소','063-460-3245','전북','군산시 나운3동 153-8');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라북도김제시보건소','063-540-1300','전북','김제시 요촌동 423-2');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라북도남원시보건소','063-625-4000','전북','남원시 조산동 455');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라북도무주군보건의료원','063-322-2201','전북','무주군 무주읍 한풍루로 413');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라북도부안군보건소','063-584-1261','전북','부안군 부안읍 봉덕리 551-2');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라북도순창군보건의료원','063-650-5238','전북','순창군 순창읍 가남리 541');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라북도완주군보건소','063-291-2400','전북','완주군 삼례읍 신금리 416-6 -삼봉로 215-20');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라북도익산시보건소','063-858-4078','전북','익산시 무왕1로 147');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라북도임실군보건의료원','063-640-3125','전북','임실군 임실읍 270-8');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라북도장수군보건의료원','063-351-8000','전북','장수군 장수읍 장수리 425');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라북도전주시보건소','063-230-5100','전북','전주시 완산구 중앙동4가 30-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라북도정읍시보건소','063-533-8582','전북','정읍시 수성동 958-2');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라북도진안군보건소','063-433-4000','전북','진안군 진안읍 군상리 90-15');";
		db.execSQL(sql);


		
		//전남 보건소
		sql = "insert into hospital (name, tel, address1, address2) values ('전라남도강진군보건소','061-430-3531','전남','강진군 강진읍 동성리 66-3번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라남도고흥군보건소','061-830-5561','전남','고흥군 고흥읍 등암리 1258-21');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라남도곡성군보건의료원','061-362-8262','전남','곡성군 곡성읍 학정리 663');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라남도광양시보건소','061-797-4004','전남','광양시 광양읍 칠성리70');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라남도구례군보건의료원','061-780-2550','전남','구례군 구례읍 백련리 576');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라남도나주시보건소','061-333-3003','전남','나주시 이창동 740-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라남도담양군보건소','061-383-4000','전남','담양군 담양읍 만성리 135번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라남도목포시보건소','061-277-4000','전남','목포시 산정동 1676');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라남도무안군보건소','061-453-2400','전남','무안군 무안읍 성동리 712-2번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라남도보성군보건소','061-853-4000','전남','보성군 보성읍 보성리 832-3');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라남도순천시보건소','061-749-3491','전남','순천시 풍덕동 1264 1264');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라남도신안군보건소','061-243-8550','전남','목포시 만호동 3의4 3-4');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라남도여수시보건소','061-683-4000','전남','여수시 학동 174');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라남도영광군보건소','061-350-5552','전남','영광군 영광읍 남천리 326-5');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라남도영암군보건소','061-470-2550','전남','영암군 영암읍 오리정길 45');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('전라남도완도군보건의료원','061-550-6765','전남','완도군 완도읍 죽청리 483-8');";
		db.execSQL(sql);
	

		//경북 보건소
		sql = "insert into hospital (name, tel, address1, address2) values ('경상북도경산시보건소','053-810-6465','경북','경산시 중방동 708-5');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상북도경주시보건소','054-745-4000','경북','경주시 동천동 987번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상북도고령군보건소','054-954-1300','경북','고령군 고령읍 연조리 563-3');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상북도구미시보건소','054-450-6473','경북','구미시 지산동 853-12');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상북도구미시선산보건소','054-481-4000','경북','구미시 선산읍 동부리 544-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상북도군위군보건소','054-383-4000','경북','군위군 군위읍 중앙길 30-10번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상북도김천시보건소','054-433-4000','경북','김천시 신음동 1284');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상북도문경시보건소','054-555-8074','경북','문경시 점촌동 232번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상북도봉화군보건소','054-673-4000','경북','봉화군 봉화읍 내성리 285');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상북도상주시보건소','054-535-4000','경북','상주시 무양동 33-4');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상북도성주군보건소','054-933-2400','경북','성주군 성주읍 경산4리 436-1번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상북도안동시보건소','054-840-5950','경북','안동시 북문동 58번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상북도영덕군보건소','054-730-6473','경북','영덕군 영덕읍 화개리208-1');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상북도영양군보건소','054-683-2043','경북','영양군 영양읍 서부리 292-4번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상북도영주시보건소','054-631-4000','경북','영주시 휴천2동 466-6');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상북도영천시보건소','054-331-4000','경북','영천시 문내동152-1');";
		db.execSQL(sql);
	

		//경남 보건소
		sql = "insert into hospital (name, tel, address1, address2) values ('경상남도거제시보건소','055-639-3800','경남','거제시 양정동 981번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상남도거창군보건소','055-940-8355','경남','거창군 거창읍송정리 21번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상남도고성군보건소','055-670-4090','경남','고성군 고성읍 남포로79번길 103-3');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상남도김해시보건소','055-330-4481','경남','김해시 외동 1261-3번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상남도남해군보건소','055-860-8776','경남','남해군 북변리 458-2번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상남도밀양시보건소','055-359-7007','경남','밀양시 삼문동 159-1번지 밀양시보건소');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상남도사천시보건소','055-831-3526','경남','사천시 용현면 덕곡리 501번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상남도산청군보건의료원','055-970-7500','경남','산청군 산청읍 지리 179-7번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상남도양산시보건소','055-388-4000','경남','양산시 중부동 707-2번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상남도의령군보건소','055-570-4010','경남','의령군 의령읍 서동리 843-2번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상남도진주시보건소','055-749-2441','경남','진주시 남성동 3-18번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상남도창녕군보건소','055-530-6277','경남','창녕군 창녕읍 말흘리 창녕대로 172');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상남도창원시마산보건소','055-245-4000','경남','마산시 해운동 61-1번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상남도창원시진해보건소','055-225-6121','경남','진해시 풍호동 1번지 진해시보건소(예방접종실)');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상남도창원시창원보건소','055-225-4000','경남','창원시 의창구 신월동 창원보건소');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('경상남도통영시보건소','055-650-6125','경남','통영시 무전동 401-1번지 통영시보건소');";
		db.execSQL(sql);


		//제주 보건소
		sql = "insert into hospital (name, tel, address1, address2) values ('서귀포시동부보건소','064-764-1856','제주','서귀포시 남원읍 남원리 2359-1번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('서귀포시서귀포보건소','064-733-4091','제주','서귀포시 중앙로101번길(서홍동 447-3)번지 52');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('서귀포시서부보건소','064-760-6251','제주','서귀포시 대정읍 상모리 3862-1번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('제주시동부보건소','064-783-5042','제주','제주시 김녕리 1811번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('제주시서부보건소','064-796-4000','제주','제주시 한림읍 한림리 966-1번지');";
		db.execSQL(sql);
 		sql = "insert into hospital (name, tel, address1, address2) values ('제주시제주보건소','064-728-4095','제주','제주시 도남동 1038(연삼로 274)번지');";
		db.execSQL(sql);
		
	}
	
}
