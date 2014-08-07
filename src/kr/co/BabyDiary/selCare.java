package kr.co.BabyDiary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class selCare extends Activity implements OnClickListener {
	care data = new care("", "", "", "", "");
	TextView txtmonth, txtvac, txtbogun, txtetc, txtcomdate;
	DatePicker dp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.caresel);
		
		Intent intent = getIntent();
		String selId = intent.getExtras().getString("id");
		setCaredata(selId);
		
		/*data.setId(intent.getExtras().getString("id"));
		data.setMonth(intent.getExtras().getString("month"));
		data.setVac(intent.getExtras().getString("vac"));
		data.setBogun(intent.getExtras().getString("bogun"));
		data.setEtc(intent.getExtras().getString("etc"));
		data.setComplate(intent.getExtras().getString("complate"));
		data.setComdate(intent.getExtras().getString("comdate"));*/
		
		txtmonth = (TextView)findViewById(R.id.txtMonth);
		txtvac = (TextView)findViewById(R.id.txtVac);
		txtbogun = (TextView)findViewById(R.id.txtBogun);
		txtetc = (TextView)findViewById(R.id.txtEtc);
		txtcomdate = (TextView)findViewById(R.id.txtcomdate);

		txtmonth.setText(data.getMonth());
		txtvac.setText(data.getVac());
		txtbogun.setText(data.getBogun());
		txtetc.setText(data.getEtc());
		txtcomdate.setText(data.getComdate());
		
		Button btnModify = (Button)findViewById(R.id.btnModify);
		Button btnDelete = (Button)findViewById(R.id.btnDelete);
		Button btnBack = (Button)findViewById(R.id.btnBack);
		
		btnModify.setOnClickListener(this);
		btnDelete.setOnClickListener(this);
		btnBack.setOnClickListener(this);

	}
	
	public void setCaredata(String id){
		DbHandlerCare dbHandler = new DbHandlerCare(this);
		Cursor cursor = dbHandler.selectData(id);
		cursor.moveToNext();
		
		data.setId(cursor.getString(0));
		data.setMonth(cursor.getString(1));
		data.setVac(cursor.getString(2));
		data.setBogun(cursor.getString(3));
		data.setEtc(cursor.getString(4));
		data.setComplate(cursor.getString(5));
		data.setComdate(cursor.getString(6));
		
		cursor.close();
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btnBack){
			finish();
		}else if(v.getId()==R.id.btnModify){
			alertDate();
		}else if(v.getId()==R.id.btnDelete){
			alertComfirm();
		}
		
	}
	
	public void alertDate() {
		Context context = getApplicationContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("수정일자를 선택해주세요");
		View layout = inflater.inflate(R.layout.dateform, null);
		b.setView(layout);
		dp = (DatePicker)layout.findViewById(R.id.datePicker);
		b.setPositiveButton("수정", new DialogInterface.OnClickListener() {
			
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
		DbHandlerCare dbHandler = new DbHandlerCare(this);
		dbHandler.UpdateCom(selDate, data.getId());
		finish();
	}
	
	public void alertComfirm() {
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("일정을 삭제 하시겠습니까?");
		b.setPositiveButton("확인", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				DeleteCom();

				
			}
		});
		b.setNegativeButton("취소", null);
		b.show();
	
	}
	
	public void DeleteCom(){
		DbHandlerCare dbHandler = new DbHandlerCare(this);
		dbHandler.DeleteCom(data.getId());
		finish();
	}

}