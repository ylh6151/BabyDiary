package kr.co.BabyDiary;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class memo_list extends ListActivity implements OnClickListener {
	DbHandlerMemo dbHandler;
	ArrayList<String> items = new ArrayList<String>();
	public memo[] mm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo_list);
		
		init();
		
		Button btnMemo_new = (Button)findViewById(R.id.btnNewmemo);
		btnMemo_new.setOnClickListener(this);
		
	}
	
	@Override
	protected void onResume() {
		
		init();
		
		Button btnMemo_new = (Button)findViewById(R.id.btnNewmemo);
		btnMemo_new.setOnClickListener(this);
		super.onResume();
	}
	
	public void init() {
		dbHandler = DbHandlerMemo.open(this);
		Cursor cursor = dbHandler.selectAll();
		mm = new memo[cursor.getCount()];
		int cnt=0;
		items.clear();
		while(cursor.moveToNext()){
			String id = cursor.getString(0);
			String title = cursor.getString(1);
			String content = cursor.getString(2);
			String date = cursor.getString(3);
			
			mm[cnt] = new memo(id, title, content, date);
			if(title.length()>8) title = title.substring(0, 8);
			items.add(date+" | "+title);
			cnt++;
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row_diary, items);
		setListAdapter(adapter);
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, memo_insert.class);
		startActivity(intent);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String memoid = mm[position].getId();
		String title = mm[position].getTitle();
		String content = mm[position].getContent();
		String date = mm[position].getDate();
		
		Intent intent = new Intent(this, memo_view.class);
		intent.putExtra("id", memoid);
		intent.putExtra("title", title);
		intent.putExtra("content", content);
		intent.putExtra("date", date);
		startActivity(intent);
	}
	
	

}
