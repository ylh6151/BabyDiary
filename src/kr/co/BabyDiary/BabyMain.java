package kr.co.BabyDiary;

import java.util.ArrayList;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class BabyMain extends ListActivity implements OnClickListener{
    
	Button btnAdd,  btnDateUp;
	DbHandlerMedical dbHandler;
	DbHandler nameHandler;
	String[] medical_record;
	TextView txtBaby, txtSymptom,txtDiagnosis,txtMedicineDay,txtInjection;
	String no, id="0";
	EditText edtSymptomUp,edtDiagnosisUp, edtMedicineDayUp;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmedical);
        dbHandler = DbHandlerMedical.open(this); 
        nameHandler = DbHandler.open(this);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        
        btnAdd.setOnClickListener(this);
        
        txtBaby = (TextView)findViewById(R.id.txtBaby);
        
        SharedPreferences activity = getSharedPreferences("Baby", MODE_WORLD_READABLE);
        String babyid = activity.getString("BabyID", "아이없음");
       // Toast.makeText(this, "선택된 아이 : "+babyid, 3000).show();
                
        
        Cursor cursor = dbHandler.select(babyid);
        Cursor cursorName = nameHandler.selectName(babyid); 
        cursorName.moveToNext();
        String babyName =  cursorName.getString(0); 
        txtBaby.setText("우리  " + babyName + "  진료기록");
        
        //아이이름을 표시
        
        medical_record = new String[cursor.getCount()];
        int count = 0;
        
        while (cursor.moveToNext()){
        	 int id = cursor.getInt(0);
          	String babyID = cursor.getString(1);
          	String date = cursor.getString(2);
        	
        	medical_record[count] = id +  " | " +  date  ;
        	
        	count ++;
        } //while
        cursor.close();
        
        ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.row_diary, medical_record);
        setListAdapter(adapter);
        
        
        
        Intent intent= getIntent();
        try {
        	id = intent.getExtras().getString("id");
		} catch (Exception e) {
		}
        
        if(id!="0"){
        	show_records(id);
        }
       
    } //onCreate

  
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	 dbHandler = DbHandlerMedical.open(this); 
    	 nameHandler = DbHandler.open(this);
    	 
         btnAdd = (Button)findViewById(R.id.btnAdd);
         
         btnAdd.setOnClickListener(this);
                
         SharedPreferences activity = getSharedPreferences("Baby", MODE_WORLD_READABLE);
         String babyid = activity.getString("BabyID", "아이없음");
       //  Toast.makeText(this, "선택된 아이 : "+babyid, 3000).show();
         super.onResume();
         
         Cursor cursor = dbHandler.select(babyid);
         Cursor cursorName = nameHandler.selectName(babyid); 
         cursorName.moveToNext();
         String babyName =  cursorName.getString(0); 
         txtBaby.setText("우리  " + babyName + "  진료기록");
         //아이이름을 표시
         
         medical_record = new String[cursor.getCount()];
         int count = 0;
         
         while (cursor.moveToNext()){
        	 int id = cursor.getInt(0);
         	String babyID = cursor.getString(1);
         	String date = cursor.getString(2);
         	
         	medical_record[count] = id + " | " +  date  ;
         	
         	count ++;
         } //while
         cursor.close();
         
         ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.row_diary, medical_record);
         setListAdapter(adapter);

    }
      
    @Override
    public void onClick(View v) {
    	if(v.getId() == R.id.btnAdd){
    		Intent intent = new Intent(this,Medical_records.class);
    		startActivity(intent);
    	}

    } //onClick
    
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	// TODO Auto-generated method stub
    	super.onListItemClick(l, v, position, id);

    	String  mr = medical_record[position];
    	
   	int a = mr.indexOf("|");
    	mr = mr.substring(0, a-1);
    	show_records(mr);
    	
   } //onListItemClick
    
    
     public void show_records(String position){
    	
    	Context context = getApplicationContext();
    	LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
    	AlertDialog.Builder b = new AlertDialog.Builder(this);
    	b.setTitle("진료 결과");
    	
    	View layout = inflater.inflate(R.layout.medical_show, null);
    	b.setView(layout);
    	
    	txtSymptom = (TextView)layout.findViewById(R.id.txtSymptom);
    	txtDiagnosis = (TextView)layout.findViewById(R.id.txtDiagnosis);
    	txtMedicineDay = (TextView)layout.findViewById(R.id.txtMedicineDay);
    	txtInjection = (TextView)layout.findViewById(R.id.txtInjection);
    	
    	dbHandler = new DbHandlerMedical(this);
    	Cursor cursorNo = dbHandler.selectNo(position);
        
    	if (cursorNo.moveToNext()){
        	String symptom = cursorNo.getString(0);
    		String diagnosis = cursorNo.getString(1);
    		String medicineDay = cursorNo.getString(2);
    		String injection = cursorNo.getString(3);
    		no = cursorNo.getString(4);
    	
    		txtSymptom.setText(symptom);
    		txtDiagnosis.setText(diagnosis);
    		txtMedicineDay.setText(medicineDay);
    		txtInjection.setText(injection);
    	} //if 
   
       cursorNo.close();
     
        b.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dbHandler.deleteMedical(no);
				finish();
			//Toast.makeText(this, "삭제 되었습니다.", 3000).show();
			}
		}); //삭제
        	
        b.setNeutralButton("수정", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//updateMedical(no);
				
				
				Intent intent = new Intent(BabyMain.this,Medical_records_update.class);
				intent.putExtra("no", no);
				startActivity(intent);
				
			}
		}); //수정
        
        b.setNegativeButton("확인",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		}); //확인
        
        b.show();
        
        
     } //show_records

}