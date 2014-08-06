package kr.co.BabyDiary;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class careSearch extends ListActivity implements OnItemSelectedListener {
	Spinner spin;
	EditText edt_hospital_name, edt_address;
	DbHandlerCare dbHandler;
	String selDosi;
	ArrayList<hospital> hositem;
	hospital hosdata;
	
	String[] items = {"서울특별시","부산광역시","대구광역시","인천광역시","광주광역시","대전광역시",
			"울산광역시","경기도","강원도","충북","충남","전북","전남","경북","경남","제주특별자치도"};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.caresearch);
		
		dbHandler = new DbHandlerCare(this);
		
		spin = (Spinner)findViewById(R.id.spinDosi);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(adapter);
		spin.setOnItemSelectedListener(this);
		
		Button btnSearch = (Button)findViewById(R.id.btnSerch);
		btnSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				edt_address = (EditText)findViewById(R.id.edtAddress);
				edt_hospital_name = (EditText)findViewById(R.id.edtHostpital_Name);
				String address = edt_address.getText().toString();
				String hospital_name = edt_hospital_name.getText().toString();
				if(address=="" && hospital_name=="") {
					Cursor cursor = dbHandler.searchDosi(selDosi);
					setListview(cursor);
				}else if(address!="" && hospital_name==""){
					Cursor cursor = dbHandler.searchAddr(selDosi, address);
					setListview(cursor);
				}else if(address=="" && hospital_name!=""){
					Cursor cursor = dbHandler.searchName(selDosi, hospital_name);
					setListview(cursor);
				}else if(address!="" && hospital_name!=""){
					Cursor cursor = dbHandler.searchAll(selDosi, address, hospital_name);
					setListview(cursor);
				}
				
			}
		});
		
		
	}
	
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		selDosi =items[position].substring(0, 2);
		Cursor cursor = dbHandler.searchDosi(selDosi);
		setListview(cursor);
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void setListview(Cursor cursor){
		hositem = new ArrayList<hospital>();
		while(cursor.moveToNext()){
			String name = cursor.getString(0).toString();
			String tel = cursor.getString(1).toString();
			String address1 = cursor.getString(2).toString();
			String address2 = cursor.getString(3).toString();
			hositem.add(new hospital(name, tel, address1, address2));
		}
		HospitalAdapter adapter = new HospitalAdapter(this, android.R.layout.simple_list_item_1, hositem);
		setListAdapter(adapter);
	}
	
	private class HospitalAdapter extends ArrayAdapter<hospital>{
		private ArrayList<hospital> items;
		
		public HospitalAdapter(Context context, int textViewResourceId, ArrayList<hospital> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}//생성자
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if(v == null) {
				LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.hospitalrow, null);
			}
			
			hospital h = items.get(position);
			if (h!=null){
				TextView r1 = (TextView)v.findViewById(R.id.txthos_name);
				TextView r2 = (TextView)v.findViewById(R.id.txthos_tel);
				TextView r3 = (TextView)v.findViewById(R.id.txthos_Address);
				r1.setText(h.getName());
				r2.setText(h.getTel());
				r3.setText(h.getAddress1()+" "+h.getAddress2());
			}
			return v;
		}
		
	}//어뎁터 재정의
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		hosdata = hositem.get(position);
		alertcomfirm();
	}
	
	public void alertcomfirm() {
		boolean flag = false;
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("전화 연결 하시겠습니까?");
		b.setPositiveButton("확인", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				tryCall();
				
			}
		});
		b.setNegativeButton("취소", null);
		b.show();
	}
	
	public void tryCall() {
		String tel = "tel:"+hosdata.getTel().replace("-", "");
		Intent intent = new Intent();
		intent.setAction(android.content.Intent.ACTION_CALL);
		intent.setData(Uri.parse(tel));
		startActivity(intent);
	}
	

}
