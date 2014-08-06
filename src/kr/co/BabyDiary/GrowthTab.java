package kr.co.BabyDiary;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class GrowthTab extends TabActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.growtab);
			
		   	TabHost tabHost = getTabHost();
		   /*	tabHost.addTab(tabHost.newTabSpec("tab0")
	        		.setIndicator("리스트")
	        		.setContent(new Intent(this, GrowthMain.class)));*/
	        tabHost.addTab(tabHost.newTabSpec("tab1")
	        		.setIndicator("체중",getResources().getDrawable(R.drawable.scale))
	        		.setContent(new Intent(this, Growthweight.class)));
	        		
	        tabHost.addTab(tabHost.newTabSpec("tab2")
	         	   .setIndicator("신장",getResources().getDrawable(R.drawable.ruler))
	         	   .setContent(new Intent(this, Growthheight.class)));
	        tabHost.addTab(tabHost.newTabSpec("tab3")
	         	   .setIndicator("두위",getResources().getDrawable(R.drawable.babyface))
	         	   .setContent(new Intent(this, GrowthHead.class)));
	}
	

	
}
