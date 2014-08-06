package kr.co.BabyDiary;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.database.Cursor;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class DiaryCalendarAdapter extends BaseAdapter{
//	private ScheduleDbAdapter mDbHelper;
	java.text.DateFormat fmtDate;
	private DbHandlerCare dbHandlerCare;
	private DbHandlerMedical dbHandlerMedical;
	private DbHandlerGrow dbHandlerGrow;
	private DbHandlerMemo dbHandlerMemo;
	private Context mContext;
	private Calendar mBaseDate;
	private Calendar c;
	private Cursor cursor;
	private String babyid;
	private int sum=0;
	private int mStartPos;
	private int mEndPos;
	private int mDaysInMonth;
	private final static int fontSize=18;
	private final static int cell_width=35;
	private final static int  cell_height=35;
	private static int CELL_WIDTH = 0;
	private static int CELL_HEIGH = 0;
	private static final int[] mWeekTitleIds = { 
		   R.string.sun
		   ,R.string.mon
		   ,R.string.tue
		   ,R.string.wed
		   ,R.string.thu
		   ,R.string.fri
		   ,R.string.sat 
	};
	private static final int[] mWeekColorIds = {
		   R.color.red
		   ,R.color.black
		   ,R.color.black
		   ,R.color.black
		   ,R.color.black
		   ,R.color.black
		   ,R.color.yellow
	};

	
	public DiaryCalendarAdapter(Context c, Calendar cal, String babyid) {
	//	mDbHelper = new ScheduleDbAdapter(c);
	//	mDbHelper.open();
        mContext = c;
        setBaseDate(cal);
        this.babyid = babyid;
        CELL_WIDTH = getPixel(mContext, cell_width);
        CELL_HEIGH = getPixel(mContext, cell_height);
        
        dbHandlerCare = DbHandlerCare.open(mContext);		
		dbHandlerGrow = DbHandlerGrow.open(mContext);		
		dbHandlerMedical = DbHandlerMedical.open(mContext);		
		dbHandlerMemo = DbHandlerMemo.open(mContext);
    }
	

    public void setBaseDate(Calendar cal) {
        mBaseDate = (Calendar)cal.clone();
        Calendar lastDayInMonth = (Calendar)cal.clone();
        lastDayInMonth.add(Calendar.MONTH, 1);
        lastDayInMonth.add(Calendar.DATE, -1);
        mDaysInMonth = lastDayInMonth.get(Calendar.DATE);
        mStartPos = 7 + mBaseDate.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
        mEndPos = mStartPos + mDaysInMonth;
    }
    
    @Override
	public int getCount() {
    	return mEndPos;
    }

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v;
		if (position < 7) {
			if (convertView == null) {
				v = new TextView(mContext);
				((TextView)v).setGravity(Gravity.CENTER);
				((TextView)v).setText(mWeekTitleIds[position]);
				((TextView)v).setTextColor(mContext.getResources().getColor(mWeekColorIds[position]));
				((TextView)v).setTextSize(getPixel(mContext, fontSize));
			}
			else {
				v = convertView;
			}
		}
		else if (position >= mStartPos && position <= mEndPos) {
			if (convertView == null) {
				v = new TextView(mContext);
				((TextView)v).setGravity(Gravity.CENTER);
				int nDay = getDayFromPosition(position);
				c = (Calendar)mBaseDate.clone();
				c.set(Calendar.DATE, nDay);
				v.setTag(c);
				
				((TextView)v).setText(Integer.toString(nDay));
				((TextView)v).setTextColor(mContext.getResources().getColor(mWeekColorIds[c.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY]));
				((TextView)v).setTextSize(getPixel(mContext, fontSize));

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String date1 = sdf.format(c.getTimeInMillis());
				String date2 = sdf.format(Calendar.getInstance().getTimeInMillis());
				//Cursor scheduleCursor = mDbHelper.checkSchedule(date1);
				
				
				if(date1.equals(date2)){	
					v.setBackgroundResource(R.drawable.today);
				}
				
				StringBuffer sb = new StringBuffer();
				sb.append(c.get(Calendar.YEAR));
				sb.append("-");
				sb.append((c.get(Calendar.MONTH)+1));
				sb.append("-");
				sb.append(c.get(Calendar.DAY_OF_MONTH));
				
				dbInit(sb.toString());
				
				
				if(sum > 0){	
					if(date1.equals(date2)){
						// 오늘 and 일정이 있는 경우 찍을 이미지
					} else{
						// 일정이 있는 경우
						switch (sum) {
						case 1:
							v.setBackgroundResource(R.drawable.check0001);
							break;							
						case 2:
							v.setBackgroundResource(R.drawable.check0010);						
							break;													
						case 3:
							v.setBackgroundResource(R.drawable.check0011);
							break;							
						case 4:
							v.setBackgroundResource(R.drawable.check0100);
							break;							
						case 5:
							v.setBackgroundResource(R.drawable.check0101);
							break;
						case 6:
							v.setBackgroundResource(R.drawable.check0110);
							break;
						case 7:
							v.setBackgroundResource(R.drawable.check0111);
							break;
						case 8:
							v.setBackgroundResource(R.drawable.check1000);
							break;
						case 9:
							v.setBackgroundResource(R.drawable.check1001);
							break;
						case 10:
							v.setBackgroundResource(R.drawable.check1010);
							break;
						case 11:
							v.setBackgroundResource(R.drawable.check1011);
							break;
						case 12:
							v.setBackgroundResource(R.drawable.check1100);
							break;
						case 13:
							v.setBackgroundResource(R.drawable.check1101);
							break;
						case 14:
							v.setBackgroundResource(R.drawable.check1110);
							break;
						case 15:
							v.setBackgroundResource(R.drawable.check1111);
							break;

						}
						
						//v.setBackgroundResource(R.drawable.check);
					}
				}
				
			}
			else {
				v = convertView;
			}
		} else {
			v = new TextView(mContext);
		}

		if (convertView == null) {
			v.setLayoutParams(new GridView.LayoutParams(CELL_WIDTH, CELL_HEIGH));
		}

		return v;
	}

	private int getDayFromPosition(int position) {
		return position - mStartPos + 1;
	}
	
	private void dbInit(String date){
		sum=0;
		
		
		checkDb(date);
	}//dbInit
	
	/*
	 *  type 1 : 파란색, 메모장, Memo
	 *  type 2 : 보라색, 성장발육, Grow
	 *  type 3 : 빨간색, 진료기록, Medical  :  시간 데이터 다르게 구해야 함
	 *  type 4 : 연두색, 예방접종, Care
	 */
	private void checkDb(String date){
		
		
		
			cursor = dbHandlerMemo.diary_Select(date);
			cursorCount(cursor, 1);
			cursor.close();
			
			cursor = dbHandlerGrow.diary_Select(date, babyid);
			cursorCount(cursor, 2);
			cursor.close();
			
			
			String year = Integer.toString(c.get(Calendar.YEAR)-2000);
			String month = Integer.toString(c.get(Calendar.MONTH)+1);
			String day = Integer.toString(c.get(Calendar.DAY_OF_MONTH));
			String temp = year + ". " + month + ". " + day + ". ";			
			
			cursor = dbHandlerMedical.diary_Select(temp, babyid);
			cursorCount(cursor, 3);
			cursor.close();
		
			cursor = dbHandlerCare.diary_Select(date, babyid);
			cursorCount(cursor, 4);
			cursor.close();
		
	}//CheckDb
	
	public static float getDisplayMetricsDensity(Context context)
	{
	    return context.getResources().getDisplayMetrics().density;
	}
	
	public static int getPixel(Context context, int p)
	{
	    float den = getDisplayMetricsDensity(context);
	    if(den != 1)
	    {
	        return (int)(p*den+0.5);
	    }
	 return p;
	}

	
	private void cursorCount(Cursor cursor, int type){
		if(cursor.getCount()!=0){
			switch (type) {
			case 1:
				sum+= 1;
				break;
			case 2:
				sum+= 2;
				break;
			case 3:
				sum+= 4;
				break;
			case 4:
				sum+= 8;
				break;
			}
		}
	}//cursorCount
}
