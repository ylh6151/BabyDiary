package kr.co.BabyDiary;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.RadioButton;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.content.*;


import java.text.*;
import java.util.Calendar;
import java.util.Date;;

public class Medical_records extends Activity implements OnDateChangedListener, OnClickListener, OnCheckedChangeListener{

	DbHandlerMedical dbHandler;
	private DatePicker mDate;
	Button btnComplete, btnCancle, btnDate;
	EditText edtSymptom,edtDiagnosis,edtMedicineDay;
	RadioButton rdoMedicine,rdoInjection, rdoNotMedicine, rdoNotInjection;
	RadioGroup rdoGroupMedicine, rdoGroupInjection;
	TextView txtYear,txtInjection;
	
	java.text.DateFormat fmtDate = java.text.DateFormat.getInstance();
	Calendar dateCalendar = Calendar.getInstance();
	
	DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			dateCalendar.set(Calendar.YEAR, year);
			dateCalendar.set(Calendar.MONTH, monthOfYear);
			dateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			updateLabel();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.medical_records);
		
		btnComplete = (Button)findViewById(R.id.btnComplete);
		btnCancle = (Button)findViewById(R.id.btnCanlce);
		btnDate = (Button)findViewById(R.id.btnDate);
		
		btnComplete.setOnClickListener(this);
		btnCancle.setOnClickListener(this);
		btnDate.setOnClickListener(this);
		
		edtSymptom = (EditText)findViewById(R.id.edtSymptom);
		edtDiagnosis = (EditText)findViewById(R.id.edtDiagnosis);
		edtMedicineDay = (EditText)findViewById(R.id.edtMedicineDay);
		
		rdoMedicine = (RadioButton)findViewById(R.id.rdoMedicine);
		rdoInjection =(RadioButton)findViewById(R.id.rdoInjection);
		rdoNotMedicine = (RadioButton)findViewById(R.id.rdoNotMedicine);
		rdoNotInjection = (RadioButton)findViewById(R.id.rdoNotInjection);
		
		rdoGroupMedicine = (RadioGroup)findViewById(R.id.rdoGroupMedicine);
		rdoGroupInjection = (RadioGroup)findViewById(R.id.rdoGroupInjection);
		rdoGroupMedicine.setOnCheckedChangeListener(this);
		rdoGroupInjection.setOnCheckedChangeListener(this);
		
		txtYear = (TextView)findViewById(R.id.txtYear);
		txtInjection = (TextView)findViewById(R.id.txtInjection);

	} //onCreate
	
	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub
		
	} //onDateChanged
	
	
	public void onClick(View v) {
		dbHandler = new DbHandlerMedical(this);  //SQLiteDataBase

		SharedPreferences activity = getSharedPreferences("Baby", MODE_WORLD_READABLE);
		String babyid = activity.getString("BabyID", "아이없음");
		
		try {
			
			
			if(v.getId() == R.id.btnComplete){
				if(txtYear.getText().toString().equals("Year/Month")){
					Toast.makeText(this, "날짜를 선택해 주세요", 3000) .show();
					return;
				}
				else if(edtSymptom.getText().toString().equals("")){
					Toast.makeText(this, "증상을 입력하세요", 3000) .show();
					return;
				}
				else if(edtDiagnosis.getText().toString().equals("")){
					Toast.makeText(this, "진단을 입력하세요", 3000) .show();
					return;
				} 
				else if(edtMedicineDay.getText().toString().equals("")){
					Toast.makeText(this, "약 처방일을 입력하세요", 3000) .show();
					return;
				}
				else if(txtInjection.getText().toString().equals("")){
					Toast.makeText(this, "주사 처방을 체크 하세요", 3000) .show();
					return;
				}
				
				long in = dbHandler.insert(babyid,
						txtYear.getText().toString(),
						edtSymptom.getText().toString(), 
						edtDiagnosis.getText().toString(), 
						Integer.parseInt(edtMedicineDay.getText().toString()),
						txtInjection.getText().toString());
				
				if(in>0)
				Toast.makeText(this, "입력되었습니다!!", 3000).show();
				finish();
			}
			
			else if(v.getId() == R.id.btnCanlce){
				finish();
			}
			
			else if(v.getId() == R.id.btnDate){
				
				new DatePickerDialog(Medical_records.this, d,
						dateCalendar.get(Calendar.YEAR),
						dateCalendar.get(Calendar.MONTH),
						dateCalendar.get(Calendar.DAY_OF_MONTH)).show();
				
				updateLabel();
			
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	} //onClick


	private void updateLabel(){
		//btnDate.setText(fmtDate.format(dateCalendar.getTime()));
		txtYear.setText(fmtDate.format(dateCalendar.getTime()));
	} //updateLabel

	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if(group == rdoGroupMedicine){
			if(checkedId == R.id.rdoMedicine){
				edtMedicineDay.setEnabled(true);
				edtMedicineDay.setText("");
				
			}
			else{
				edtMedicineDay.setText("0");
				edtMedicineDay.setEnabled(false);
			}
			
		} //if - rdoGroupMedicine
		
		
		if(group == rdoGroupInjection){
			if(checkedId == R.id.rdoInjection){
				txtInjection.setText("주사를 처방 받았습니다.");
			}
			else{
				txtInjection.setText("주사 처방이 없습니다.");
			}
			
		} //if - rdoGroupInjection
		
	} //onCheckedChanged
	
	
}
