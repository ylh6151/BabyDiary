package kr.co.BabyDiary;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabWidget;

public class Growthheight extends Activity{
	DbHandlerGrow dbHandlerGrow;
	DbHandler dbHandler;
	String birthdate, gender,babyid;
	ArrayList<Double> heightlist = new ArrayList<Double>();
	ArrayList<Integer> daylist= new ArrayList<Integer>();
	int maxwidth=0;//원점
	int maxheight=0;//원점 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.growthheight);
		SharedPreferences activity = getSharedPreferences("Baby",MODE_WORLD_READABLE);
    	babyid = activity.getString("BabyID", "0");
		selectbabyinfo();
		genderpic();
		select();
		chkmaxSize();
		drawpoint();

	}

	private void selectbabyinfo(){//<--아기 생일 정보
		dbHandler = new DbHandler(this); 
		Cursor cursor = dbHandler.selectbyID(babyid);
		
		while(cursor.moveToNext()){
			gender = cursor.getString(0);
			birthdate = cursor.getString(1);
		}
		cursor.close(); 
	}
	
	private void genderpic(){
		ImageView bgiv = (ImageView)findViewById(R.id.bgiv);
		
		if (gender.equals("girl")){
			bgiv.setBackgroundResource(R.drawable.girlheight);
			//bgiv.setImageResource(R.drawable.girlheight);
		}
		else{
			bgiv.setBackgroundResource(R.drawable.boyheight);
		}
		
	}
	
	private void drawpoint(){
		
		LinearLayout.LayoutParams[] param = new LinearLayout.LayoutParams[heightlist.size()];
		FrameLayout fl= (FrameLayout)findViewById(R.id.bgfl);
		LinearLayout[] ln = new LinearLayout[heightlist.size()];
		ImageView[] point= new ImageView[heightlist.size()];
		
		double xpoint, ypoint;//원점
		
				
		for(int i = 0; i<heightlist.size(); i++){
			double dotx=0;
			double doty=0;
			int realx,realy;
			param[i] = new LinearLayout.LayoutParams(
	    			ViewGroup.LayoutParams.WRAP_CONTENT,
	    			ViewGroup.LayoutParams.WRAP_CONTENT
	    	);   
			ln[i] = new LinearLayout(this);
			fl.addView(ln[i],param[i]);
			point[i] = new ImageView(this);
			point[i].setImageResource(R.drawable.pointdot);
			xpoint =(float)(maxwidth * 11/100);
				//Integer.parseInt(Double.toString(maxwidth * 11.5/100));
				//Integer.parseInt(Double.toString(dotx));
			//maxwidth * weightlist.get(i)/100;
			ypoint =(float)(maxheight * 71.8/100);
				//Integer.parseInt(Double.toString(maxheight * 79.5/100));
			doty = ypoint - (ypoint/70*(heightlist.get(i)-35));
			realy =(int) Math.round(doty); 
			dotx = xpoint + ((maxwidth-xpoint)/1080*daylist.get(i));
			realx = (int) Math.round(dotx);
				//Integer.parseInt(Double.toString(doty));
			//maxheight * daylist.get(i)/100;
			param[i].setMargins(realx,realy, 0,0);
			//param[i].setMargins(heightlist.get(i),daylist.get(i),0, 0);
			ln[i].addView(point[i],param[i]);
		}
	}
	
	private void select(){//리스트 불러오기
		daylist.clear();
		heightlist.clear();

		dbHandlerGrow = DbHandlerGrow.open(this); 
		Cursor cursor = dbHandlerGrow.selectGrowthAll(babyid);

		while(cursor.moveToNext()){
			/*	if((cursor.getString(2)).equals("0")){
					cursor.moveToNext();
				}
				*/
				heightlist.add(Double.parseDouble(cursor.getString(2)));
				String inputdate = cursor.getString(4);
				String diff = diffday(inputdate,birthdate);
				daylist.add(Integer.parseInt(diff));
				//daylist.add(Integer.parseInt(cursor.getString(3)));
				
			}//while 
			cursor.close(); 
	}
	
	private String diffday(String insertDate, String birthday){
		Calendar cal= Calendar.getInstance();
		int first, second;
		int diff=0;
		int year_start = Integer.parseInt( birthday.substring(0,4) );
		int month_start = Integer.parseInt( birthday.substring(5,7) ); 
		int date_start = Integer.parseInt( birthday.substring(8,10) );

		int year_end = Integer.parseInt( insertDate.substring(0,4) );
		int month_end = Integer.parseInt( insertDate.substring(5,7) ); 
		int date_end = Integer.parseInt( insertDate.substring(8,10) );

		cal.clear();
		cal.set ( year_start , month_start - 1 , date_start );
		first = cal.get(Calendar.DAY_OF_YEAR);

		cal.clear();
		cal.set ( year_end , month_end - 1 , date_end );
		second = cal.get(Calendar.DAY_OF_YEAR);

		if ( (year_start == year_end)  && 
				( ((month_start == month_end) && (date_start <= date_end)) ||
	                             (month_start < month_end) ) )
			{
				diff= (second - first );
			}
			else if ( year_start < year_end )
			{
	            diff= (  ( 365*(year_end - year_start) - first )+ second);			
			}
		
		return String.valueOf(diff);
	}
	
	public void chkmaxSize(){
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		maxwidth = displayMetrics.widthPixels;
		maxheight= displayMetrics.heightPixels;
	}
		
}
