package kr.co.BabyDiary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class selSche extends Activity implements OnClickListener {
	care data = new care("", "", "", "", "");
	DbHandlerCare dbHandler;
	TextView txtmonth, txtvac, txtbogun, txtetc;
	DatePicker dp;
	EditText edtMonth, edtVac, edtBogun, edtEtc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schedulesel);
		
		Intent intent = getIntent();
		data.setId(intent.getExtras().getString("id"));
		data.setMonth(intent.getExtras().getString("month"));
		data.setVac(intent.getExtras().getString("vac"));
		data.setBogun(intent.getExtras().getString("bogun"));
		data.setEtc(intent.getExtras().getString("etc"));
		
		txtmonth = (TextView)findViewById(R.id.txtMonth);
		txtvac = (TextView)findViewById(R.id.txtVac);
		txtbogun = (TextView)findViewById(R.id.txtBogun);
		txtetc = (TextView)findViewById(R.id.txtEtc);

		txtmonth.setText(data.getMonth());
		txtvac.setText(data.getVac());
		txtbogun.setText(data.getBogun());
		txtetc.setText(data.getEtc());
		
		Button btnComplate = (Button)findViewById(R.id.btnComplate);
		Button btnBack = (Button)findViewById(R.id.btnBack);
		
		btnComplate.setOnClickListener(this);
		btnBack.setOnClickListener(this);

	}
	
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btnBack){
			finish();
		}else if(v.getId()==R.id.btnComplate){
			alertDate();
		}
		
	}
	
	public void alertDate() {
		Context context = getApplicationContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("접종일자를 선택해주세요");
		View layout = inflater.inflate(R.layout.dateform, null);
		b.setView(layout);
		dp = (DatePicker)layout.findViewById(R.id.datePicker);
		b.setPositiveButton("등록", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String strMonth = "";
				String strDay ="";
				if((dp.getMonth()+1) <10){
					strMonth = "0"+(dp.getMonth()+1);
				}else{
					strMonth = String.valueOf(dp.getMonth()+1);
				}
				
				if((dp.getDayOfMonth()) <10){
					strDay = "0"+dp.getDayOfMonth();
				}else{
					strDay = String.valueOf(dp.getDayOfMonth());
				}
				
				//String selDate = dp.getYear() + "-"+(dp.getMonth()+1)+"-"+dp.getDayOfMonth();
				String selDate = dp.getYear() + "-"+strMonth+"-"+strDay;
				UpdateCom(selDate);

				
			}
		});
		b.setNegativeButton("취소", null);
		b.show();
	
	}
	
	public void UpdateCom(String selDate){
		dbHandler = new DbHandlerCare(this);
		dbHandler.UpdateCom(selDate, data.getId());
		finish();
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
    	
    	populateMenu(menu);
    	return super.onCreateOptionsMenu(menu);
    } //onCreateOptionsMenu
	
	public boolean onOptionsItemSelected(MenuItem item) {
    	// TODO Auto-generated method stub
    	return super.onOptionsItemSelected(item)||this.applyMenuChoice(item);
    } //onOptionsItemSelected
	
	private boolean applyMenuChoice(MenuItem item) {
		if(item.getItemId()==Menu.FIRST+1){
			modifydata();
		}else if(item.getItemId()==Menu.FIRST+2){
			alertComfirm();
		}
		return true;
	}
	
	private void populateMenu(Menu menu) {
    	menu.add(Menu.NONE, Menu.FIRST+1, Menu.NONE, "수정");
    	menu.add(Menu.NONE, Menu.FIRST+2, Menu.NONE, "삭제");
    } //populateMenu
	
	public void alertComfirm() {
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("일정을 삭제 하시겠습니까?");
		b.setPositiveButton("확인", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Deletedata();

				
			}
		});
		b.setNegativeButton("취소", null);
		b.show();
	
	}
	
	public void Deletedata() {
		dbHandler = new DbHandlerCare(this);
		dbHandler.deleteData(data.getId());
		finish();
	}
	
	public void modifydata() {
		Context context = getApplicationContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("수정할 내용을 변경해주세요");
		View layout = inflater.inflate(R.layout.insertdata, null);
		b.setView(layout);
		dbHandler = new DbHandlerCare(this);
		edtMonth = (EditText)layout.findViewById(R.id.edtMonth);
		edtVac = (EditText)layout.findViewById(R.id.edtVac);
		edtBogun = (EditText)layout.findViewById(R.id.edtBogun);
		edtEtc = (EditText)layout.findViewById(R.id.edtEtc);
		edtMonth.setText(data.getMonth());
		edtVac.setText(data.getVac());
		edtBogun.setText(data.getBogun());
		edtEtc.setText(data.getEtc());
		b.setPositiveButton("수정", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String month = edtMonth.getText().toString();
				String vac = edtVac.getText().toString();
				String bogun = edtBogun.getText().toString();
				String etc = edtEtc.getText().toString();
				dbHandler.updateData(month,vac,bogun,etc,data.getId());
				finish();	
			}
		});
		b.setNegativeButton("취소", null);
		b.show();
	}

}
