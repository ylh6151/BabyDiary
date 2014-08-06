package kr.co.BabyDiary;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class selfcareMain extends TabActivity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maincare);
                
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

		TabSpec firstTabSpec = tabHost.newTabSpec("tab01");
		TabSpec secondTabSpec = tabHost.newTabSpec("tab02");
		TabSpec thirdTabSpec = tabHost.newTabSpec("tab03");
		
		firstTabSpec.setIndicator("예방접종", getResources().getDrawable(R.drawable.shot)).setContent(
				new Intent(this, babyCare.class));
		secondTabSpec.setIndicator("일정표", getResources().getDrawable(R.drawable.report)).setContent(
				new Intent(this, careSchedule.class));
		thirdTabSpec.setIndicator("의료기관",getResources().getDrawable(R.drawable.hospital)).setContent(
				new Intent(this, careSearch.class));
		
		tabHost.addTab(firstTabSpec);
		tabHost.addTab(secondTabSpec);
		tabHost.addTab(thirdTabSpec);
		
    }
}