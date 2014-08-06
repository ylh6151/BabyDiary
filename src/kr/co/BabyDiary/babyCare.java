package kr.co.BabyDiary;

import java.util.ArrayList;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class babyCare extends ListActivity{
	DbHandlerCare dbHandler;
	ArrayList<care> items;
	String babyid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.babycare);
		
		SharedPreferences activity = getSharedPreferences("Baby",MODE_WORLD_READABLE);
    	babyid = activity.getString("BabyID", "0");
    	
		dbHandler = DbHandlerCare.open(this);
		
		init(dbHandler);
	}
	
	public void init(DbHandlerCare dbHandler){
		Cursor cursor = dbHandler.selectCom(babyid);
		
		items = new ArrayList<care>();
		int count=0;
		
		
		while(cursor.moveToNext()){
			String id = cursor.getString(0);
			String month = cursor.getString(1);
			String vac = cursor.getString(2);
			String bogun = cursor.getString(3);
			String etc = cursor.getString(4);
			
			care data = new care(id, month, vac, bogun, etc);
			data.setComplate(cursor.getString(5));
			data.setComdate(cursor.getString(6));
			items.add(data);
			count++;
		}
		cursor.close();
		
		ScheduleAdapter adapter = new ScheduleAdapter(this, R.layout.scherouw, items);
		setListAdapter(adapter);
		
	}
	
	private class ScheduleAdapter extends ArrayAdapter<care>{
		private ArrayList<care> items;
		
		public ScheduleAdapter(Context context, int textViewResourceId, ArrayList<care> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}//»ý¼ºÀÚ
		
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
				r1.setText(c.getComdate());
				r2.setText(c.getVac());
			}
			return v;
		}
	}
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		care data = items.get(position);
		Intent intent = new Intent(this, selCare.class);
		intent.putExtra("id", data.getId());
		intent.putExtra("month", data.getMonth());
		intent.putExtra("vac", data.getVac());
		intent.putExtra("bogun", data.getBogun());
		intent.putExtra("etc", data.getEtc());
		intent.putExtra("complate", data.getComplate());
		intent.putExtra("comdate", data.getComdate());
		startActivity(intent);
		
	}
	
	@Override
	protected void onResume() {
		dbHandler = DbHandlerCare.open(this);
		init(dbHandler);
		super.onResume();
	}

}
