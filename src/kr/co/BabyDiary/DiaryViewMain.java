package kr.co.BabyDiary;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DiaryViewMain  extends ListActivity implements OnItemClickListener{
	String date, babyid;
	DbHandlerCare dbHandlerCare;
	DbHandlerMedical dbHandlerMedical;
	DbHandlerGrow dbHandlerGrow;
	DbHandlerMemo dbHandlerMemo;
	ArrayList<DataDTO> list;
	ArrayList<String> adapterList;
	
	
	java.text.DateFormat fmtDate;
	Calendar cal;
	
	private DiaryCalendarAdapter mCalendarAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diary_view);
		
		SharedPreferences activity = getSharedPreferences("Baby",MODE_WORLD_READABLE);
    	babyid = activity.getString("BabyID", "안돼");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		setCalendar(cal);

		setDateMoveButtonHandler(R.id.prevYear, Calendar.YEAR, -1);
		setDateMoveButtonHandler(R.id.prevMonth, Calendar.MONTH, -1);
		setDateMoveButtonHandler(R.id.nextYear, Calendar.YEAR, 1);
		setDateMoveButtonHandler(R.id.nextMonth, Calendar.MONTH, 1);
		
		//초기화
		fmtDate = java.text.DateFormat.getInstance();
		list = new ArrayList<DataDTO>();
		adapterList = new ArrayList<String>();
		
		setAdapter();
		
	}
	
	private void setAdapter(){
		setAdapterList();
		ArrayAdapter adapter = new ArrayAdapter(this, R.layout.row_diary, adapterList);
		setListAdapter(adapter);		
	}
	
	private void setAdapterList(){
		String data="";
		String data2="";
		String temp="";		
		
		if(list.size()!=0){
			for(int i=0; i<list.size(); i++){
				
				DataDTO dto = list.get(i);
				if(dto.getType().equals("Care")){
					data = dto.getdata();
					temp = data + " 접종  완료";
				}else if(dto.getType().equals("Medical")){
					data = dto.getdata();
					temp = "증상은 " + data + " 이었습니다.";
				}else if(dto.getType().equals("Grow")){
					data = dto.getdata();
					data2 = dto.getdata2();
					temp = "키는 " + data + "cm 체중은 " + data2 +"kg 입니다"; 
				}else if(dto.getType().equals("Memo")){
					data = dto.getdata();
					temp = "'" + data + "' 메모가 있어요"; 
				}			
				adapterList.add(temp);
				
			}//for
		}else{
			temp = "일정이 없습니다.";
			adapterList.add(temp);
		}
		
		
	}//setAdapterList
	
	
	private void setCalendar(Calendar cal) {
		
		if (mCalendarAdapter == null)
			mCalendarAdapter = new DiaryCalendarAdapter(this, cal, babyid);
		else
			mCalendarAdapter.setBaseDate(cal);

		Button btn = (Button) findViewById(R.id.prevYear);
		btn.setTag(cal);
		btn = (Button) findViewById(R.id.prevMonth);
		btn.setTag(cal);
		btn = (Button) findViewById(R.id.nextYear);
		btn.setTag(cal);
		btn = (Button) findViewById(R.id.nextMonth);
		btn.setTag(cal);

		TextView tv = (TextView) findViewById(R.id.textview);
		
		String yyyyMM = String.format(getString(R.string.yearMonth), 
				Integer.toString(cal.get(Calendar.YEAR)), Integer.toString(cal.get(Calendar.MONTH) + 1));
		tv.setText(yyyyMM);

		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(mCalendarAdapter);
		gridview.setOnItemClickListener(this);
	}

	private void setDateMoveButtonHandler(int id, final int YearOrMonth, final int direction) {
		Button btn = (Button) findViewById(id);
		
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar cal = (Calendar) v.getTag();
				cal.add(YearOrMonth, direction);
				setCalendar(cal);
			}
		});
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

		String temp="";
		try{
			cal = (Calendar)v.getTag();
			if(position > 6){
				list.clear();
				adapterList.clear();
				totalData();	
				setAdapter();
	
			}	
		}catch(Exception e){
			//의도적인  Exception ex) 날짜 이외의 부분이 눌렸을 경우 발생하는 Exception의 처리 : 반응하지 않는다.
		}
	}//onItemClick
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		try{
		
			DataDTO dto = list.get(position);
			if(dto.getType().equals("Care")){
				//Toast.makeText(this, "1", 2000).show();
				Intent intent = new Intent(this, selCare.class);
				intent.putExtra("id", dto.getId());
				startActivity(intent);
			}else if(dto.getType().equals("Medical")){
				//Toast.makeText(this, "2", 2000).show();
				Intent intent = new Intent(this, BabyMain.class);
				intent.putExtra("id", dto.getId());
				startActivity(intent);
			}else if(dto.getType().equals("Grow")){
				//Toast.makeText(this, "3", 2000).show();
				Intent intent = new Intent(this, GrowthMain.class);
				intent.putExtra("id", dto.getId());
				startActivity(intent);
			}else if(dto.getType().equals("Memo")){
				//Toast.makeText(this, "4", 2000).show();
				Intent intent = new Intent(this, memo_view.class);
				intent.putExtra("id", dto.getId());
				startActivity(intent);
			}
		
		}catch(Exception e){
			//이외의 상황 발생 (일정이 없을때는 dto 생성되지 않았음 의도적인 Exception)
		}	
		
	}//onListItemClick
	
	public Cursor dbHandlerCare(String date){
		// 예방접종
		dbHandlerCare = DbHandlerCare.open(this);
		Cursor cursor = dbHandlerCare.diary_Select(date,babyid);
		return cursor;
	}//dbHandlerCare
	
	public Cursor dbHandlerMedical(String date){
		// 진료기록
		String date2 = fmtDate.format(cal.getTime());
		date=date2.substring(0,11);
		//Toast.makeText(this, date, 2000).show();
		dbHandlerMedical = DbHandlerMedical.open(this);
		Cursor cursor = dbHandlerMedical.diary_Select(date, babyid);
		return cursor;
		//return null;
	}//dbHandlerMedical
	
	public Cursor dbHandlerGrow(String date){
		// 성장발육
		dbHandlerGrow = DbHandlerGrow.open(this);
		Cursor cursor = dbHandlerGrow.diary_Select(date, babyid);
		return cursor;
	}//dbHandlerGrow
	
	public Cursor dbHandlerMemo(String date){
		// 메모장
		dbHandlerMemo = DbHandlerMemo.open(this);
		Cursor cursor = dbHandlerMemo.diary_Select(date);
		return cursor;		
	}//dbHandlerGrow
	
	public void totalData(){
		
		StringBuffer sb = new StringBuffer();
		sb.append(cal.get(Calendar.YEAR));
		sb.append("-");
		sb.append((cal.get(Calendar.MONTH)+1));
		sb.append("-");
		sb.append(cal.get(Calendar.DAY_OF_MONTH));
		
		getCursorData(dbHandlerCare(sb.toString()),"Care");
		getCursorData(dbHandlerMedical(sb.toString()),"Medical");
		getCursorData(dbHandlerGrow(sb.toString()),"Grow");
		getCursorData(dbHandlerMemo(sb.toString()),"Memo");
		
	}//totalData
	
	public void getCursorData(Cursor cursor, String type){
		
			while(cursor.moveToNext()){
				DataDTO dto = new DataDTO();
				dto.setType(type);
				dto.setId(cursor.getString(0));
				dto.setData(cursor.getString(1));
				dto.setData2(cursor.getString(2));
				list.add(dto);
			}
		
	}//getCursorData
	
	@Override
	protected void onResume() {
		super.onResume();
		SharedPreferences activity = getSharedPreferences("Baby",MODE_WORLD_READABLE);
    	babyid = activity.getString("BabyID", "아이 없음");
    	
	}
	
}//class

class DataDTO{
	
	private String type="";
	private String id="";
	private String data="";
	private String data2="";
	
	public void setId(String id){
		this.id = id;
	}
	public void setType(String type){
		this.type = type;
	}
	public void setData(String data){
		this.data = data;
	}
	public void setData2(String data2){
		this.data2 = data2;
	}
	
	public String getId(){
		return id;
	}
	public String getType(){
		return type;
	}
	public String getdata(){
		return data;
	}
	public String getdata2(){
		return data2;
	}
	
	
}//DataDTO



