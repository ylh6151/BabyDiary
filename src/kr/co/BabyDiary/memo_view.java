package kr.co.BabyDiary;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class memo_view extends Activity implements OnClickListener {
	memo mm;
	EditText edtMemoTitle, edtMemoContent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo_view);
		
		Intent intent = getIntent();
		
		String memoid = intent.getExtras().getString("id");
		selMemodata(memoid);
		
		/*String title = intent.getExtras().getString("title");
		String content = intent.getExtras().getString("content");
		String date = intent.getExtras().getString("date");
		
		mm = new memo(memoid, title, content, date);*/
		
		edtMemoTitle = (EditText)findViewById(R.id.edtMemotitle);
		edtMemoContent = (EditText)findViewById(R.id.edtMemocontent);
		
		edtMemoTitle.setText(mm.getTitle());
		edtMemoContent.setText(mm.getContent());
		
		Button btnMemoSave = (Button)findViewById(R.id.btnMemoSave);
		Button btnMemoBack = (Button)findViewById(R.id.btnMemoBack);
		Button btnMemoErase = (Button)findViewById(R.id.btnMemoErase);
		
		btnMemoSave.setOnClickListener(this);
		btnMemoBack.setOnClickListener(this);
		btnMemoErase.setOnClickListener(this);
	}
	
	public void selMemodata(String memoid){
		DbHandlerMemo dbHandler = DbHandlerMemo.open(this);
		Cursor cursor = dbHandler.selectData(memoid);
		cursor.moveToNext();
		String title = cursor.getString(1);
		String content = cursor.getString(2);
		String date = cursor.getString(3);
		
		mm = new memo(memoid, title, content, date);
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btnMemoSave){
			DbHandlerMemo dbHandler = DbHandlerMemo.open(this);
			String title = edtMemoTitle.getText().toString();
			String content = edtMemoContent.getText().toString();
			String date = getdate();
			String id = mm.getId();
			dbHandler.memoupdate(id, title, content, date);
			finish();
		}else if(v.getId()==R.id.btnMemoBack){
			finish();
		}else if(v.getId()==R.id.btnMemoErase){
			DbHandlerMemo dbHandler = DbHandlerMemo.open(this);
			dbHandler.memodelete(mm.getId());
			finish();
		}
		
	}
	
	public String getdate() {
		Date today = new Date(); 
        
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd"); 

        return date.format(today);		
	}

}
