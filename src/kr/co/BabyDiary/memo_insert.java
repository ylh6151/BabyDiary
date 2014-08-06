package kr.co.BabyDiary;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class memo_insert extends Activity implements OnClickListener {
	EditText edtMemoTitle, edtMemoContent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo_view);
		
		Button btnMemoSave = (Button)findViewById(R.id.btnMemoSave);
		Button btnMemoBack = (Button)findViewById(R.id.btnMemoBack);
		Button btnMemoErase = (Button)findViewById(R.id.btnMemoErase);
		
		btnMemoSave.setOnClickListener(this);
		btnMemoBack.setOnClickListener(this);
		btnMemoErase.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btnMemoSave){
			DbHandlerMemo dbHandler = DbHandlerMemo.open(this);
			edtMemoTitle = (EditText)findViewById(R.id.edtMemotitle);
			edtMemoContent = (EditText)findViewById(R.id.edtMemocontent);
			String title = edtMemoTitle.getText().toString();
			String content = edtMemoContent.getText().toString();
			String date = getdate();
			dbHandler.memoinsert(title, content, date);
			finish();
		}else if(v.getId()==R.id.btnMemoBack){
			finish();
		}else if(v.getId()==R.id.btnMemoErase){
			finish();
		}
		
	}
	
	public String getdate() {
		Date today = new Date(); 
        
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd"); 

        return date.format(today);		
	}

}
