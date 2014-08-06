package kr.co.BabyDiary;

import java.util.ArrayList;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class careSchedule extends ListActivity {
	DbHandlerCare dbHandler;
	ArrayList<care> items;
	EditText edtMonth, edtVac, edtBogun, edtEtc;

	String babyid ="0";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.caresche);
		
		SharedPreferences activity = getSharedPreferences("Baby",MODE_WORLD_READABLE);
		babyid = activity.getString("BabyID", "0");
		
		dbHandler = DbHandlerCare.open(this);
		
		init(dbHandler);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setContentView(R.layout.caresche);
		dbHandler = DbHandlerCare.open(this);
		
		init(dbHandler);
		
	}
	
	public void init(DbHandlerCare dbHandler){
		Cursor cursor = dbHandler.selectAll(babyid);
		if(cursor.getCount()==0)
			dbHandler.insertdata(babyid); //초기 자료 저장
		
		items = new ArrayList<care>();
		int count=0;
		
		
		while(cursor.moveToNext()){
			String id = cursor.getString(0);
			String month = cursor.getString(1);
			String vac = cursor.getString(2);
			String bogun = cursor.getString(3);
			String etc = cursor.getString(4);
			
			items.add(new care(id, month, vac, bogun, etc));
			count++;
		}
		cursor.close();
		
		ScheduleAdapter adapter = new ScheduleAdapter(this, R.layout.scherouw, items);
		setListAdapter(adapter);
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		care data = items.get(position);
		Intent intent = new Intent(this, selSche.class);
		intent.putExtra("id", data.getId());
		intent.putExtra("month", data.getMonth());
		intent.putExtra("vac", data.getVac());
		intent.putExtra("bogun", data.getBogun());
		intent.putExtra("etc", data.getEtc());
		startActivity(intent);
		
	}
	
	private class ScheduleAdapter extends ArrayAdapter<care>{
		private ArrayList<care> items;
		
		public ScheduleAdapter(Context context, int textViewResourceId, ArrayList<care> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}//생성자
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if(v == null) {
				LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.scherouw, null);
			}
			
			care c = items.get(position);
			if (c!=null){
				TextView r1 = (TextView)v.findViewById(R.id.txtMonth);
				TextView r2 = (TextView)v.findViewById(R.id.txtVac);
				r1.setText(c.getMonth());
				r2.setText(c.getVac());
			}
			return v;
		}
		
	}//어뎁터 재정의
	
	public boolean onCreateOptionsMenu(Menu menu) {
    	
    	populateMenu(menu);
    	return super.onCreateOptionsMenu(menu);
    } //onCreateOptionsMenu
	
	public boolean onOptionsItemSelected(MenuItem item) {
    	// TODO Auto-generated method stub
    	return super.onOptionsItemSelected(item)||this.applyMenuChoice(item);
    } //onOptionsItemSelected
	
	private boolean applyMenuChoice(MenuItem item) {
		inputdata();
		return true;
	}
	
	private void populateMenu(Menu menu) {
    	menu.add(Menu.NONE, Menu.FIRST+1, Menu.NONE, "등록");
    } //populateMenu
	
	public void inputdata() {
		Context context = getApplicationContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("새로추가할 내용을 입력해주세요");
		View layout = inflater.inflate(R.layout.insertdata, null);
		b.setView(layout);
		dbHandler = new DbHandlerCare(this);
		edtMonth = (EditText)layout.findViewById(R.id.edtMonth);
		edtVac = (EditText)layout.findViewById(R.id.edtVac);
		edtBogun = (EditText)layout.findViewById(R.id.edtBogun);
		edtEtc = (EditText)layout.findViewById(R.id.edtEtc);
		b.setPositiveButton("추가", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String month = edtMonth.getText().toString();
				String vac = edtVac.getText().toString();
				String bogun = edtBogun.getText().toString();
				String etc = edtEtc.getText().toString();
				dbHandler.insertData(month,vac,bogun,etc,babyid);
				onResume();				
			}
		});
		b.setNegativeButton("취소", null);
		b.show();
	}

}


