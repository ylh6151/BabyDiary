package kr.co.BabyDiary;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class BabyDiaryMain extends Activity implements OnClickListener{
	
	DbHandler dbHandler;
	Cursor cursor = null;
	String name, sex, birth;
	ArrayList<String> items ;
	int no;
	TextView txtInfo;
	Bitmap selPhoto;//선택한 이미지
	
	Button babyInsert,babyModify,babySelete;
	Button inoculationBtn,treatmentBtn,growBtn,notepadBtn,babyGuideBtn,diaryViewBtn;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        babyInsert = (Button)findViewById(R.id.babyInsert);
        babyInsert.setOnClickListener(this);
        babyModify = (Button)findViewById(R.id.babyModify);
        babyModify.setOnClickListener(this);
        babySelete = (Button)findViewById(R.id.babySelete);
        babySelete.setOnClickListener(this);
        
        
        
        inoculationBtn = (Button)findViewById(R.id.inoculationBtn);
        inoculationBtn.setOnClickListener(this);
        treatmentBtn = (Button)findViewById(R.id.treatmentBtn);
        treatmentBtn.setOnClickListener(this);
        growBtn = (Button)findViewById(R.id.growBtn);
        growBtn.setOnClickListener(this);
        notepadBtn = (Button)findViewById(R.id.notepadBtn);
        notepadBtn.setOnClickListener(this);
        babyGuideBtn = (Button)findViewById(R.id.babyGuideBtn);
        babyGuideBtn.setOnClickListener(this);
        diaryViewBtn = (Button)findViewById(R.id.diaryViewBtn);
        diaryViewBtn.setOnClickListener(this); 
        
        ImageView imgView = (ImageView)findViewById(R.id.imgView);
        imgView.setOnClickListener(this);
        
        init();
        
	}
		@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			
			super.onResume();
	        
	        babyInsert = (Button)findViewById(R.id.babyInsert);
	        babyInsert.setOnClickListener(this);
	        babyModify = (Button)findViewById(R.id.babyModify);
	        babyModify.setOnClickListener(this);
	        babySelete = (Button)findViewById(R.id.babySelete);
	        babySelete.setOnClickListener(this);
	        
	        
	        inoculationBtn = (Button)findViewById(R.id.inoculationBtn);
	        inoculationBtn.setOnClickListener(this);
	        treatmentBtn = (Button)findViewById(R.id.treatmentBtn);
	        treatmentBtn.setOnClickListener(this);
	        growBtn = (Button)findViewById(R.id.growBtn);
	        growBtn.setOnClickListener(this);
	        notepadBtn = (Button)findViewById(R.id.notepadBtn);
	        notepadBtn.setOnClickListener(this);
	        babyGuideBtn = (Button)findViewById(R.id.babyGuideBtn);
	        babyGuideBtn.setOnClickListener(this);
	        diaryViewBtn = (Button)findViewById(R.id.diaryViewBtn);
	        diaryViewBtn.setOnClickListener(this);
	        
	        ImageView imgView = (ImageView)findViewById(R.id.imgView);
	        imgView.setOnClickListener(this);
	        
	        init();
	        
	        
	        
		}
        
        public void init() {
        
			
    		dbHandler = new DbHandler(this);	  
    		Cursor cursor = dbHandler.choicetrue();   	
    		txtInfo = (TextView)findViewById(R.id.txtInfo);
    		
    		if (cursor.moveToNext()){
    			String name = cursor.getString(1);
    			String sex = cursor.getString(2);
    			if(sex.equals("boy")) {
    				sex = "왕자님";
    			} else {
    				sex = "공주님";
    			}
    			
    			String birth = cursor.getString(3);
    			txtInfo.setText("이름 : "+name+"\n"+"성별 : "+sex+"\n"+"출생 : "+birth);
    			babyModify = (Button)findViewById(R.id.babyModify); 
    			
    			babyModify.setEnabled(true);
    	        inoculationBtn.setEnabled(true);
    	        treatmentBtn.setEnabled(true);
    	        growBtn.setEnabled(true);
    	        notepadBtn.setEnabled(true);
    	        babyGuideBtn.setEnabled(true);
    	        diaryViewBtn.setEnabled(true);
    			
    			SharedPreferences activity = getSharedPreferences("Baby",MODE_WORLD_WRITEABLE|MODE_WORLD_WRITEABLE);
    	    	Editor editor = activity.edit();
    	    	editor.putString("BabyID", cursor.getString(0));
    	    	editor.commit();
    		} 
    		 else {
    			txtInfo.setText("등록 또는 선택\n버튼을 눌러서\n등록해십시오");
    	        babyModify.setEnabled(false);
    	        inoculationBtn.setEnabled(false);
    	        treatmentBtn.setEnabled(false);
    	        growBtn.setEnabled(false);
    	        notepadBtn.setEnabled(false);
    	        babyGuideBtn.setEnabled(false);
    	        diaryViewBtn.setEnabled(false);

    		}
    		
   
    		cursor = dbHandler.selectUri();
    		if(cursor.moveToNext()){
    			if(cursor.getString(0).equals("people")){
    				//ImageButton imgView = (ImageButton)findViewById(R.id.imgView);
    				//Drawable dr = getResources().getDrawable(R.drawable.people);
    				Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.people);
    				Bitmap resize = Bitmap.createScaledBitmap(bm, 120, 100, true);//쫌더 작게 리사이증
    				ImageView imgView = (ImageView)findViewById(R.id.imgView);
                	imgView.setImageBitmap(resize);
    				
    				//imgView.setImageDrawable(dr);
    				//imgView.setBackgroundDrawable(dr);
        			//imgView.setBackgroundResource(R.drawable.people);
    			}
	    			
				else{
					try {
	    				Uri selPhotoUri = Uri.parse(cursor.getString(0));
	                	AssetFileDescriptor afd = getContentResolver().openAssetFileDescriptor(selPhotoUri, "r");
	                	selPhoto = BitmapFactory.decodeFileDescriptor(afd.getFileDescriptor());
	                	Bitmap resize = Bitmap.createScaledBitmap(selPhoto, 120, 100, true);//쫌더 작게 리사이증
	                	ImageView imgView = (ImageView)findViewById(R.id.imgView);
	                	imgView.setImageBitmap(resize);//이미지뷰에 뿌려줌
					} catch (Exception e) {

					}
				}
						
    		}
    		
    		

        }

    	
    public void onClick(View v){
    	
    	if(v.getId()==R.id.babyInsert) {
    		Intent intent = new Intent(this, IntentInsert.class);	
  		startActivity(intent);
    	}
    	else if(v.getId()==R.id.babyModify) {
    		Intent intent = new Intent(this, IntentModify.class);	
  		startActivity(intent);	
    	}
    	else if(v.getId()==R.id.babySelete) {
    		Intent intent = new Intent(this, IntentSelect.class);	
  		startActivity(intent);	
    	}
    	else if(v.getId()==R.id.inoculationBtn) {
    		Intent intent = new Intent(this, selfcareMain.class);
    		startActivity(intent);	
    	}
    	else if(v.getId()==R.id.treatmentBtn) {
    		Intent intent = new Intent(this, BabyMain.class);
    		startActivity(intent);	
    	}
    	else if(v.getId()==R.id.growBtn) {
    		Intent intent = new Intent(this, GrowthMain.class);
    		startActivity(intent);	
    	}
    		
    	else if(v.getId()==R.id.imgView&&txtInfo.getText().toString().substring(0, 2).equals("이름")){
			
			Intent intent = new Intent(Intent.ACTION_PICK) ;
			intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MediaStore.Images.Media.CONTENT_TYPE) ;
			startActivityForResult(intent, 0);
			// 사진선택 눌렀을 경우 갤러리 불러오기
		}
    	else if(v.getId()==R.id.notepadBtn){
    		Intent intent = new Intent(this, memo_list.class);
    		startActivity(intent);
    	}
    	else if(v.getId()==R.id.babyGuideBtn){
    		Intent intent = new Intent(this, BabyGuideList.class);
    		startActivity(intent);
    	}
    	else if(v.getId()==R.id.diaryViewBtn){
    		Intent intent = new Intent(this, DiaryViewMain.class);
    		startActivity(intent);
    	}
    	
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);		 

        if( requestCode == 0 ) {  
	        if(data != null) {
	            try {
	            	Uri selPhotoUri = data.getData();
	            	AssetFileDescriptor afd = getContentResolver().openAssetFileDescriptor(selPhotoUri, "r");
	            	selPhoto = BitmapFactory.decodeFileDescriptor(afd.getFileDescriptor());
	            	Bitmap resize = Bitmap.createScaledBitmap(selPhoto, 50, 50, true);//쫌더 작게 리사이증
	            	ImageView imgView = (ImageView)findViewById(R.id.imgView);
	            	imgView.setImageBitmap(resize);//이미지뷰에 뿌려줌
	            	dbHandler = new DbHandler(this);
	            	dbHandler.updateImg(selPhotoUri.toString());
	            } catch (Exception e) {
				} 
	        }
        }//이미지 선택 완료
	}
}    