package kr.co.BabyDiary;


import java.util.ArrayList;

import android.app.*;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class IntentSelect extends ListActivity implements OnClickListener {
	DbHandler dbHandler;
	ArrayList<baby> items = new ArrayList<baby>();

	Button backBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.babyselect);
		init();

		backBtn = (Button) findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);

	}

	public void init() {
		dbHandler = new DbHandler(this);
		Cursor cursor = dbHandler.selectAll();

		while (cursor.moveToNext()) {
			baby data = new baby();
			data.setId(cursor.getString(0));
			data.setBabyname(cursor.getString(1));
			if(cursor.getString(2).equals("boy")){
				data.setGender("왕자님");
			}else {
				data.setGender("공주님");
			}
			data.setGender(cursor.getString(2));
			data.setBirthday(cursor.getString(3));
			data.setWeight(cursor.getString(4));
			data.setHeight(cursor.getString(5));
			data.setHead(cursor.getString(6));
			data.setBloodtype(cursor.getString(7));
			data.setClinicdata(cursor.getString(8));
			data.setChoice(cursor.getString(9));
			data.setPic(cursor.getString(10));

			items.add(data);
			
		}
		IconicProcess adapter = new IconicProcess(this, android.R.layout.simple_list_item_1, items);
		 setListAdapter(adapter);
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);
		//setListAdapter(adapter);
		 
		 if(items.size()==0){
			 nothingalert();
		 }

	}
	
	public void nothingalert() {
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("등록된 아기가 없습니다.");
		b.setPositiveButton("확인", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();

				
			}
		});
		b.show();
	}
	
	@Override
	public void onClick(View arg0) {
		finish();
		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		super.onListItemClick(l, v, position, id);
		dbHandler.choicefalse();
		baby data = items.get(position);
		dbHandler.choiceBaby(data.getId()); //불러온 _id에 true변환
		finish();
		
	}
	
	
	class IconicProcess extends ArrayAdapter<baby> {
    	ArrayList<baby> items = new ArrayList<baby>();
    	
    	public IconicProcess(Context context, int textViewResourceId, ArrayList<baby> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}//생성자
    	
    	@Override
    	public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if(v == null) {
				LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.row, null);
			}
			
			baby h = items.get(position);
			if (h!=null){
				ImageView icon = (ImageView)v.findViewById(R.id.icon);
				TextView text = (TextView)v.findViewById(R.id.text);
				
				if(h.getPic().equals("people")){
					icon.setImageResource(R.drawable.people);
				} else {
					try {
	    				Uri selPhotoUri = Uri.parse(h.getPic());
	                	AssetFileDescriptor afd = getContentResolver().openAssetFileDescriptor(selPhotoUri, "r");
	                	Bitmap selPhoto = BitmapFactory.decodeFileDescriptor(afd.getFileDescriptor());
	                	Bitmap resize = Bitmap.createScaledBitmap(selPhoto, 140, 120, true);//쫌더 작게 리사이증
	                	icon.setImageBitmap(resize);//이미지뷰에 뿌려줌
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
				text.setText("이름 : "+h.getBabyname()+"\n성별 : "+(h.getGender().equals("boy")?"왕자":"공주")+"\n출생 : "+h.getBirthday());
			}
			return v;
    	}
	}
}