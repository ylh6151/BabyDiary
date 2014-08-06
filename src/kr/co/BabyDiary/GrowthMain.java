package kr.co.BabyDiary;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GrowthMain extends ListActivity implements OnClickListener{
	DbHandlerGrow dbHandlerGrow;
	DbHandler dbHandler;
	ImageButton btnAdd,btnGraph;
    EditText weight, height, head; 
    Button date;
    ArrayList<Growth> items = new ArrayList<Growth>();
    String id,babyid;
    String birthdate = null;
    DateFormat fmtDate = DateFormat.getDateInstance();
    Calendar dateCalen = Calendar.getInstance();
      
	DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			dateCalen.set(Calendar.YEAR, year);
			dateCalen.set(Calendar.MONTH, monthOfYear);
			dateCalen.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			updateLabel();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.growthmain);
        
		try{
		Intent intent = getIntent();
		String selId = intent.getExtras().getString("id");
		modify(selId);}
		catch(Exception e){}
		
        btnGraph= (ImageButton)this.findViewById(R.id.btnGraph);
		btnAdd= (ImageButton)this.findViewById(R.id.btnAdd);
		btnGraph.setOnClickListener(this);
		btnAdd.setOnClickListener(this);
		SharedPreferences activity = getSharedPreferences("Baby",MODE_WORLD_READABLE);
    	babyid = activity.getString("BabyID", "0");
		selectbabyinfo();
		
		selectall();
		
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btnGraph){//그래프 버튼->tab페이지로
			Intent intent = new Intent(this, GrowthTab.class);
			startActivity(intent);
		}
		if(v.getId() == R.id.btnAdd){//추가버튼->alertDialog 창뛰움
			add();			
		}		
	}
	
	private void selectbabyinfo(){//<--아기 생일 정보
		dbHandler = new DbHandler(this); 
		Cursor cursor = dbHandler.selectbyID(babyid);
		if(cursor.getCount() == 0) {
			//Toast.makeText(this, "아기 정보를 입력해 주세요", 3000);
			babyAddComfirm();
			//있던 리스트 삭제하기
		}
		
		while(cursor.moveToNext()){
			birthdate = cursor.getString(1);
			//Toast.makeText(this, birthdate, 3000);
		}
		
		
			
		cursor.close(); 
	}
	
	private void babyAddComfirm() {
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("먼저 아기 정보를 입력해 주세요.");
		b.setNeutralButton("확인", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
			finish();			
			}
		});
		
		AlertDialog ad = b.create();
	    ad.show();
	}
	
	private void selectall(){//리스트 불러오기
		items.clear();
		dbHandlerGrow = new DbHandlerGrow(this); 
		Cursor cursor = dbHandlerGrow.selectGrowthAll(babyid);
		
		if(cursor.getCount() == 0 & birthdate != null){
			add();
		}
		
		while(cursor.moveToNext()){
				String id=cursor.getString(0);
				String weight = cursor.getString(1);
				String height = cursor.getString(2);
				String head = cursor.getString(3);
				String date = cursor.getString(4);
								
				Growth growth = new Growth(id,weight,height,head,date);
				items.add(growth);
			}//while 
			cursor.close(); 
			
			//if(db 에 아무 자료 값이 없다면) ---> alertdialog 띄우기
			//if(db 에 자료 값이 있다면) ---> listadapter
			GrowthAdapter gadapter = new GrowthAdapter(this, R.layout.growthrow, items);
			setListAdapter(gadapter);
			
	}
	
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		int tableId = Integer.parseInt(items.get(position).getId());
		String strId = String.valueOf(tableId);
		modify(strId);
	}
	
	private void add(){//alertdiaglog 띄우기
	    	Context context = getApplicationContext();
	        LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
	        View layout = inflater.inflate(R.layout.log, null);
	    
	        AlertDialog.Builder aDialog = new AlertDialog.Builder(this);
	        aDialog.setTitle("<성장발육 수치입력>");
	        aDialog.setView(layout);
	        
	        weight = (EditText)layout.findViewById(R.id.edtWeight);
	        height = (EditText)layout.findViewById(R.id.edtTall);
	        head =(EditText)layout.findViewById(R.id.edtHead);
	        date=(Button)layout.findViewById(R.id.btnDate);//->datepicker alertdialog로 바꾸기

	    	date.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					new DatePickerDialog(GrowthMain.this, d, 
							dateCalen.get(Calendar.YEAR), 
							dateCalen.get(Calendar.MONTH), 
							dateCalen.get(Calendar.DAY_OF_MONTH)).show();						
				}
			});
	    	updateLabel();
	             
	        aDialog.setPositiveButton("저장", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {				
					/*if(weight.getText().toString().equals("")
							  || height.getText().toString().equals("")
							  || head.getText().toString().equals("")){
							
						Toast.makeText(GrowthMain.this, "모든 성장 수치값을 입력해주세요", 3000) .show();
					}
					else*/ 
					if(weight.getText().toString().equals("")){
						weight.setText("0");
					}
					if(height.getText().toString().equals("")){
						height.setText("0");
					}
					if(head.getText().toString().equals("")){
						head.setText("0");
					}
					
					if(weight.getText().toString().equals("0")
							  && height.getText().toString().equals("0")
							  && head.getText().toString().equals("0")){
						Toast.makeText(GrowthMain.this, "성장 수치값을 입력해주세요", 3000) .show();
					}
				/*	else if(Double.parseDouble(weight.getText().toString())<2 || Double.parseDouble(weight.getText().toString())>19 ){
						Toast.makeText(GrowthMain.this, "체중 입력 값 범위:2~19kg", 3000) .show();
					}
					else if(Double.parseDouble(height.getText().toString())<35 ||Double.parseDouble(height.getText().toString())>105 ){
						Toast.makeText(GrowthMain.this, "신장 입력 값 범위:35~105kg", 3000) .show();
					}
					else if(Double.parseDouble(head.getText().toString())<29|| Double.parseDouble(head.getText().toString())>54){
						Toast.makeText(GrowthMain.this, "두위 입력 값 범위:29~54kg", 3000) .show();
					}*/
										
					else {
						  long result = dbHandlerGrow.insertGrowth(weight.getText().toString(), height.getText().toString(),
								 				head.getText().toString(), date.getText().toString(),babyid);
						 //db에 저장하기
						  if(result !=0){
								 selectall();
								 Toast.makeText(GrowthMain.this,"저장되었습니다", 3000) .show();
						 }
					
					}
				}
			});
	        
	        aDialog.setNeutralButton("취소", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
				}
			});
	        
	        AlertDialog ad = aDialog.create();
	        ad.show();
	                
	    }

	private void modify(String strid){
		Context context = getApplicationContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.log, null);
        id = strid;
        
        AlertDialog.Builder aDialog = new AlertDialog.Builder(this);
        aDialog.setTitle("<성장발육 수치수정>");
        aDialog.setView(layout);
        
        weight = (EditText)layout.findViewById(R.id.edtWeight);
        height = (EditText)layout.findViewById(R.id.edtTall);
        head =(EditText)layout.findViewById(R.id.edtHead);
        date=(Button)layout.findViewById(R.id.btnDate);//->datepicker alertdialog로 바꾸기

		dbHandlerGrow = new DbHandlerGrow(this); 
		Cursor cursor = dbHandlerGrow.selectId(id);

		while(cursor.moveToNext()){
				weight.setText(cursor.getString(0));
				height.setText(cursor.getString(1));
				head.setText(cursor.getString(2));
				date.setText(cursor.getString(3));
			}//while 
		cursor.close(); 
	
		
		date.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				new DatePickerDialog(GrowthMain.this, d, 
						dateCalen.get(Calendar.YEAR), 
						dateCalen.get(Calendar.MONTH), 
						dateCalen.get(Calendar.DAY_OF_MONTH)) .show();						
			}
		});
    	updateLabel();
             
        aDialog.setPositiveButton("수정", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {				
				if(weight.getText().toString().equals("")){
					weight.setText("0");
				}
				if(height.getText().toString().equals("")){
					height.setText("0");
				}
				if(head.getText().toString().equals("")){
					head.setText("0");
				}
				
				if(weight.getText().toString().equals("")
						  && height.getText().toString().equals("")
						  && head.getText().toString().equals("")){
						
					Toast.makeText(GrowthMain.this, "성장 수치값을 입력해주세요", 3000) .show();
				}
			/*	else if(Double.parseDouble(weight.getText().toString())<2 || Double.parseDouble(weight.getText().toString())>19 ){
					Toast.makeText(GrowthMain.this, "체중 입력 값 범위:2~19kg", 3000) .show();
				}
				else if(Double.parseDouble(height.getText().toString())<35 ||Double.parseDouble(height.getText().toString())>105 ){
					Toast.makeText(GrowthMain.this, "신장 입력 값 범위:35~105kg", 3000) .show();
				}
				else if(Double.parseDouble(head.getText().toString())<29|| Double.parseDouble(head.getText().toString())>54){
					Toast.makeText(GrowthMain.this, "두위 입력 값 범위:29~54kg", 3000) .show();
				}*/
				else {
					 long result= dbHandlerGrow.updateGrowth(id, weight.getText().toString(), height.getText().toString(),
							 				head.getText().toString(), date.getText().toString(),babyid);
					 //db에 저장하기
					 if(result !=0){
						 selectall();
						 Toast.makeText(GrowthMain.this,"수정되었습니다", 3000) .show();
					 }
				}
			}
		});
        
        aDialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//아무것도 없으면 그냥 창 닫히고 아무 일 안함.
			}
		});
  
        aDialog.setNeutralButton("삭제", new DialogInterface.OnClickListener(){
        	
        	@Override
        	public void onClick(DialogInterface dialog, int which) {
        		long result = dbHandlerGrow.deleteId(id);
        		if(result != 0){
					 selectall();
					 Toast.makeText(GrowthMain.this,"삭제되었습니다", 3000).show();
        		}
        	}
        });
        
        AlertDialog ad = aDialog.create();
        ad.show();
	}
	
    private void updateLabel() {
       //	date.setText(fmtDate.format(dateCalen.getTime()));
    	String strDate;
    	if((dateCalen.get(Calendar.MONTH)+1)<10 && dateCalen.get(Calendar.DAY_OF_MONTH)<10){
    		strDate = dateCalen.get(Calendar.YEAR)+ "-0" + (dateCalen.get(Calendar.MONTH)+1)+ "-0" + dateCalen.get(Calendar.DAY_OF_MONTH); 
    	}
    	else if((dateCalen.get(Calendar.MONTH)+1)<10){
    		strDate = dateCalen.get(Calendar.YEAR)+ "-0" + (dateCalen.get(Calendar.MONTH)+1)+ "-" + dateCalen.get(Calendar.DAY_OF_MONTH); 
    	}
    	else if(dateCalen.get(Calendar.DAY_OF_MONTH)<10){
    		strDate = dateCalen.get(Calendar.YEAR)+ "-" + (dateCalen.get(Calendar.MONTH)+1)+ "-0" + dateCalen.get(Calendar.DAY_OF_MONTH); 		
    	}
    	else{
    		strDate = dateCalen.get(Calendar.YEAR)+ "-" + (dateCalen.get(Calendar.MONTH)+1)+ "-" + dateCalen.get(Calendar.DAY_OF_MONTH); 
    	}
    	date.setText(strDate);
    } //
	
	private class GrowthAdapter extends ArrayAdapter<Growth>{
		private ArrayList<Growth> items;
				
		public GrowthAdapter(Context context, int textViewResourceId, ArrayList<Growth> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if(v == null) {
				LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.growthrow, null);
			}
			
			Growth r = items.get(position);
			if (r!=null){
				TextView r1 = (TextView)v.findViewById(R.id.txtWeight);
				TextView r2 = (TextView)v.findViewById(R.id.txtHeight);
				TextView r3 = (TextView)v.findViewById(R.id.txtHead);
				TextView r4 = (TextView)v.findViewById(R.id.txtDate);
				TextView r5 = (TextView)v.findViewById(R.id.txtBirth);
				r1.setText(r.getWeight()+"kg");
				r2.setText(r.getHeight()+"cm");
				r3.setText(r.getHead()+"cm");
				r4.setText(r.getDate());
				r5.setText(diffday(r.getDate(),birthdate));
	
			}
			return v;
		}
		
	}
	
	private String diffday(String insertDate, String birthday){
		Calendar cal= Calendar.getInstance();
		int first, second;
		int diff=0;
		int year_start = Integer.parseInt( birthday.substring(0,4) );
		int month_start = Integer.parseInt( birthday.substring(5,7) ); 
		int date_start = Integer.parseInt( birthday.substring(8,10) );

		int year_end = Integer.parseInt( insertDate.substring(0,4) );
		int month_end = Integer.parseInt( insertDate.substring(5,7) ); 
		int date_end = Integer.parseInt( insertDate.substring(8,10) );

		cal.clear();
		cal.set ( year_start , month_start - 1 , date_start );
		first = cal.get(Calendar.DAY_OF_YEAR);

		cal.clear();
		cal.set ( year_end , month_end - 1 , date_end );
		second = cal.get(Calendar.DAY_OF_YEAR);

		if ( (year_start == year_end)  && 
				( ((month_start == month_end) && (date_start <= date_end)) ||
	                             (month_start < month_end) ) )
			{
				diff= (second - first );
			}
			else if ( year_start < year_end )
			{
	            diff= (  ( 365*(year_end - year_start) - first )+ second);			
			}
		
		return String.valueOf(diff);
	}
	
	class Growth {
		private String id, weight, height, head, date;
		
		public Growth(String id, String weight, String height, String head, String date) {
			this.id = id;
			this.weight = weight;
			this.height = height;
			this.head = head;
			this.date = date;
		}
		
		public String getId() {
			return id;
		}
		public String getWeight() {
			return weight;
		}
		public String getHeight() {
			return height;
		}
		public String getHead() {
			return head;
		}
		public String getDate() {
			return date;
		}
		
	}
	
}
