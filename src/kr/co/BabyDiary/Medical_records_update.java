package kr.co.BabyDiary;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class Medical_records_update extends Activity implements  OnClickListener, OnCheckedChangeListener{

	TextView txtYearUp,txtInjectionUp;
	EditText edtSymptomUp,edtDiagnosisUp,edtMedicineDayUp;
	RadioGroup rdoGroupMedicineUp,rdoGroupInjectionUp;
	RadioButton rdoMedicineUp,rdoNotMedicineUp,rdoInjectionUp,rdoNotInjectionUp;
	DbHandlerMedical dbHandler;
	Button btnDateUp, btnUpdate,btnCanlceUp;
	String no;
	
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
		setContentView(R.layout.medical_show_update);
		
		txtYearUp = (TextView)findViewById(R.id.txtYearUp);
		txtInjectionUp = (TextView)findViewById(R.id.txtInjectionUp);
		
		edtSymptomUp = (EditText)findViewById(R.id.edtSymptomUp);
		edtDiagnosisUp = (EditText)findViewById(R.id.edtDiagnosisUp);
		edtMedicineDayUp = (EditText)findViewById(R.id.edtMedicineDayUp);
		
		rdoGroupInjectionUp = (RadioGroup)findViewById(R.id.rdoGroupInjectionUp);
		rdoGroupMedicineUp = (RadioGroup)findViewById(R.id.rdoGroupMedicineUp);
		rdoGroupInjectionUp.setOnCheckedChangeListener(this);
		rdoGroupMedicineUp.setOnCheckedChangeListener(this);
		
		rdoMedicineUp = (RadioButton)findViewById(R.id.rdoMedicineUp);
		rdoNotMedicineUp = (RadioButton)findViewById(R.id.rdoNotMedicineUp);
		rdoInjectionUp = (RadioButton)findViewById(R.id.rdoInjectionUp);
		rdoNotInjectionUp = (RadioButton)findViewById(R.id.rdoNotInjectionUp);
		
		btnDateUp = (Button)findViewById(R.id.btnDateUp);
		btnDateUp.setOnClickListener(this);
		btnUpdate = (Button)findViewById(R.id.btnUpdate);
		btnUpdate.setOnClickListener(this);
		btnCanlceUp = (Button)findViewById(R.id.btnCanlceUp);
		btnCanlceUp.setOnClickListener(this);
		
		
		Intent intent = getIntent();
		no = intent.getExtras().getString("no");
		
		dbHandler = DbHandlerMedical.open(this);
		Cursor cursorAll = dbHandler.selectAll(no);
		
		if(cursorAll.moveToNext()){
   		 String symptomUp = cursorAll.getString(0);
   		 String diagnosisUp = cursorAll.getString(1);
   		 String medicineDayUp = cursorAll.getString(2);
   		 String injectionUp = cursorAll.getString(3);
   		 String dateUp = cursorAll.getString(4);
   	 
   		 txtYearUp.setText(dateUp);
   		 edtSymptomUp.setText(symptomUp);
   		 edtDiagnosisUp.setText(diagnosisUp);
   		 edtMedicineDayUp.setText(medicineDayUp);
   		 txtInjectionUp.setText(injectionUp);
   		 	if(medicineDayUp.equals("0")){
   		 		rdoNotMedicineUp.setChecked(true);
   		 	}
   		 	else{
   		 		rdoMedicineUp.setChecked(true);
   		 		edtMedicineDayUp.setText(medicineDayUp);
   		 	}
   		 		
   		 	
   		 	if(injectionUp.equals("주사를 처방 받았습니다.")){
   		 		rdoInjectionUp.setChecked(true);
   		 	}
   		 	else{
   		 		rdoNotInjectionUp.setChecked(true);
   		 	}
   		 
   	 } //if
   	 
   	 cursorAll.close();

		
	} //onCreate
			
	@Override
	public void onClick(View v) {
		try {
				
			
			if(v.getId() == R.id.btnDateUp){
				new DatePickerDialog(Medical_records_update.this,d, 
						dateCalendar.get(Calendar.YEAR),
						dateCalendar.get(Calendar.MONTH),
						dateCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
			else if(v.getId() == R.id.btnUpdate){
				try {
					
					String date = txtYearUp.getText().toString();
					String symptom = edtSymptomUp.getText().toString();
					String diagnosis = edtDiagnosisUp.getText().toString();
					String medicineDay = edtMedicineDayUp.getText().toString();
					String injection = txtInjectionUp.getText().toString();
					dbHandler.updateMedical(date, symptom, diagnosis, medicineDay, injection, no);
					
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				Toast.makeText(this, "수정되었습니다.", 3000).show();
				finish();
			}
			
			
			else if(v.getId() == R.id.btnCanlceUp){
				finish();
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	} //onClick
	

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if(group == rdoGroupMedicineUp){
			if(checkedId == R.id.rdoMedicineUp){
				edtMedicineDayUp.setEnabled(true);
				//edtMedicineDayUp.setText("");
				
			}
			else{
				edtMedicineDayUp.setText("0");
				edtMedicineDayUp.setEnabled(false);
			}
		} //if - rdoGroupMedicineUp
		
		
		if(group == rdoGroupInjectionUp){
			if(checkedId == R.id.rdoInjectionUp){
				txtInjectionUp.setText("주사를 처방 받았습니다.");
			}
			else{
				txtInjectionUp.setText("주사 처방이 없습니다.");
			}
		
		} //if - rdoGroupInjectionUp
		
	} //onCheckedChanged
	private void updateLabel(){
		txtYearUp.setText(fmtDate.format(dateCalendar.getTime()));
	} //updateLabel
}
