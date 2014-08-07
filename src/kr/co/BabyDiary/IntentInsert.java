package kr.co.BabyDiary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class IntentInsert extends Activity implements OnClickListener,
		RadioGroup.OnCheckedChangeListener {
	
	DatePicker dp;
	DbHandler dbHandler;
	Cursor cursor = null;

	TextView genderView;
	EditText babyName, birthDay, weight, height, head,  clinicData;
	RadioGroup gender, rdoGuoupBlood;
	RadioButton rdoA, rdoB, rdoO, rdoAB;
	String sex = "", bloodType = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert);

		babyName = (EditText) findViewById(R.id.babyName);
		birthDay = (EditText) findViewById(R.id.birthDay);
		weight = (EditText) findViewById(R.id.weight);
		height = (EditText) findViewById(R.id.height);
		head = (EditText) findViewById(R.id.head);
		clinicData = (EditText) findViewById(R.id.clinicData);
		
		birthDay.setFocusable(false);
		birthDay.setOnClickListener(this);

		gender = (RadioGroup) findViewById(R.id.rdgroup);
		gender.setOnCheckedChangeListener(this);

		Button btnIns = (Button) findViewById(R.id.btnIns);
		Button btnBack = (Button) findViewById(R.id.btnBack);
	
		btnIns.setOnClickListener(this);
		btnBack.setOnClickListener(this);
		
		genderView = (TextView) findViewById(R.id.genderView);
		
		rdoGuoupBlood = (RadioGroup)findViewById(R.id.rdoGuoupBlood);
		rdoGuoupBlood.setOnCheckedChangeListener(this);
		rdoA = (RadioButton)findViewById(R.id.rdoA);
		rdoB = (RadioButton)findViewById(R.id.rdoB);
		rdoO = (RadioButton)findViewById(R.id.rdoO);
		rdoAB = (RadioButton)findViewById(R.id.rdoAB);

	}

	@Override
	public void onClick(View v) {
		babyName.setText(babyName.getText().toString());
		dbHandler = DbHandler.open(this);

		try {
			if (v.getId() == R.id.btnIns) {
				if (babyName.getText().toString().equals("")) {
					Toast.makeText(this, "�̸��� �Է��ϼ���", 3000).show();
					return;
				} else if (birthDay.getText().toString().equals("")) {
					Toast.makeText(this, "������ �Է��ϼ���", 3000).show();
					return;
				} else if (weight.getText().toString().equals("")) {
					Toast.makeText(this, "�������� �Է��ϼ���", 3000).show();
					return;
				} else if (height.getText().toString().equals("")) {
					Toast.makeText(this, "Ű�� �Է��ϼ���", 3000).show();
					return;
				} else if (head.getText().toString().equals("")) {
					Toast.makeText(this, "������ �Է��ϼ���", 3000).show();
					return;
				}
				
				//���� ���� �׷� ���� 
				
				
				else if (clinicData.getText().toString().equals("")) {
					Toast.makeText(this, "���������� �Է��ϼ���", 3000).show();
					return;
				}

				int chkgender = gender.getCheckedRadioButtonId();
				if (chkgender == R.id.boy)
					sex = "boy";
				if (chkgender == R.id.girl)
					sex = "girl";
				
				int blood = rdoGuoupBlood.getCheckedRadioButtonId();
				if(blood == R.id.rdoA){
					bloodType = "A";
				}else if(blood == R.id.rdoB){
					bloodType = "B";
				}else if(blood == R.id.rdoO){
					bloodType = "O";
				}else if(blood == R.id.rdoAB){
					bloodType = "AB";
				}
				
				
				
				dbHandler.choicefalse(); //db���� false�� ���� ����

				dbHandler.insert(babyName.getText().toString(), sex, birthDay
						.getText().toString(), weight.getText().toString(),
						height.getText().toString(), head.getText().toString(),
						bloodType, clinicData.getText().toString(),
						"true","people"); //�Է��� db���� true�� ����
				babyName.setText("");
				birthDay.setText("");
				weight.setText("");
				height.setText("");
				head.setText("");
				//bloodType.setText("");
				clinicData.setText("");
				
				Toast.makeText(this, "�Է¿� �����Ͽ����ϴ�.", 3000).show();
				finish();
			}
			else if (v.getId() == R.id.btnBack) {
				finish();
			} else if(v.getId()==R.id.birthDay){
				alertDate();
			}

		} catch (Exception e) {
			Log.e("tag", "����:" + e);
		}

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (group == gender) {
			if (checkedId == R.id.boy) {
				genderView.setText("����");
			} else if (checkedId == R.id.girl) {
				genderView.setText("����");
			}
		}

	}
	
	public void alertDate() {
		Context context = getApplicationContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("������ڸ� �������ּ���");
		View layout = inflater.inflate(R.layout.dateform, null);
		b.setView(layout);
		dp = (DatePicker)layout.findViewById(R.id.datePicker);
		b.setPositiveButton("���", new DialogInterface.OnClickListener() {
			
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
				setedtDate(selDate);

				
			}
		});
		b.setNegativeButton("���", null);
		b.show();
	
	}
	
	public void setedtDate(String selDate){
		birthDay.setText(selDate);
	}
}
