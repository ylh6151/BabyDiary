package kr.co.BabyDiary;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class BabyGuideList extends ListActivity{
	//TextView selection;
	String[] items = {"신생아~1개월","생후1개월~2개월","생후2개월~3개월","생후3개월~4개월","생후4개월~5개월",
    		"생후5개월~6개월","생후6개월~7개월","생후7개월~8개월","생후8개월~9개월","생후9개월~10개월","생후10개월~11개월",
    		"생후11개월~12개월","생후12개월~18개월","생후18개월~24개월"};  
	
	/* String[] item ={"text1","text2","text3","text4","text5","text6","text7","text8","text9",
			"text10","text11","text12","text13","text14"}; */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.babyguidelist);
	    setListAdapter(new ArrayAdapter<String>(this, R.layout.row_diary, items));
	    //selection = (TextView)findViewById(R.id.selection);  
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		super.onListItemClick(l, v, position, id);
		//selection.setText(items[position]);
		if(items[position].equals("신생아~1개월")) {
    		Intent intent = new Intent(this, BabyGuide1.class);
    		//intent.putExtra("text1", "1");
    		startActivity(intent);
  		}else if(items[position].equals("생후1개월~2개월")) {
    		Intent intent = new Intent(this, BabyGuide2.class);
    		//intent.putExtra("text2", "2");
      		startActivity(intent);
  		}else if(items[position].equals("생후2개월~3개월")) {
    		Intent intent = new Intent(this, BabyGuide3.class);	
    		//intent.putExtra("text3", "3");
      		startActivity(intent);
  		}else if(items[position].equals("생후3개월~4개월")) {
    		Intent intent = new Intent(this, BabyGuide4.class);	
    		//intent.putExtra("text4", "4");
      		startActivity(intent);
  		}else if(items[position].equals("생후4개월~5개월")) {
    		Intent intent = new Intent(this, BabyGuide5.class);	
    		//intent.putExtra("text5", "5");
      		startActivity(intent);
  		}else if(items[position].equals("생후5개월~6개월")) {
    		Intent intent = new Intent(this, BabyGuide6.class);	
    		//intent.putExtra("text6", "6");
      		startActivity(intent);
  		}else if(items[position].equals("생후6개월~7개월")) {
    		Intent intent = new Intent(this, BabyGuide7.class);	
    		//intent.putExtra("text7", "7");
      		startActivity(intent);
  		}else if(items[position].equals("생후7개월~8개월")) {
    		Intent intent = new Intent(this, BabyGuide8.class);	
    		//intent.putExtra("text8", "8");
      		startActivity(intent);
  		}else if(items[position].equals("생후8개월~9개월")) {
    		Intent intent = new Intent(this, BabyGuide9.class);	
    		//intent.putExtra("text9", "9");
      		startActivity(intent);
  		}else if(items[position].equals("생후9개월~10개월")) {
    		Intent intent = new Intent(this, BabyGuide10.class);	
    		//intent.putExtra("text10", "10");
      		startActivity(intent);
  		}else if(items[position].equals("생후10개월~11개월")) {
    		Intent intent = new Intent(this, BabyGuide11.class);	
    		//intent.putExtra("text11", "11");
      		startActivity(intent);
  		}else if(items[position].equals("생후11개월~12개월")) {
    		Intent intent = new Intent(this, BabyGuide12.class);	
    		//intent.putExtra("text12", "12");
      		startActivity(intent);
  		}else if(items[position].equals("생후12개월~18개월")) {
    		Intent intent = new Intent(this, BabyGuide13.class);	
    		//intent.putExtra("text13", "13");
      		startActivity(intent);
  		}else if(items[position].equals("생후18개월~24개월")) {
    		Intent intent = new Intent(this, BabyGuide14.class);	
    		//intent.putExtra("text14", "14");
      		startActivity(intent);
  		}
		
	}
 /* @Override
	  	protected void onPause() {
		super.onPause();
		finish();
		}
		//String selNum = item[position]; */
		
	}


