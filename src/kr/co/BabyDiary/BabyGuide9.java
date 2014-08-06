package kr.co.BabyDiary;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BabyGuide9 extends Activity implements OnClickListener{
	
	EditText edtFile;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.babyguide);
    
        Button btnClose = (Button)findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);
        
        
       StringBuffer sb = new StringBuffer();
       
        try {
			Resources resources = getResources();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(resources.openRawResource(R.raw.text9), "UTF-8"));
			
        	//BufferedReader br = new BufferedReader(new FileInputStream("/sdcard/text1.txt"));
			String readLine = "";
			while((readLine = br.readLine()) != null ) {
				sb.append(readLine+"\n");
			}
			br.close();
			edtFile = (EditText)findViewById(R.id.edtFile);
			edtFile.setText(sb.toString());
			
		} catch (Exception e) {
			Toast.makeText(this, "에러발생", 3000).show();
		}
        
		
		 }//onCrate

	@Override
	public void onClick(View v) {
		
  			finish();
	}
	
	@Override
		protected void onPause() {
			super.onPause();
			finish();
		}
}