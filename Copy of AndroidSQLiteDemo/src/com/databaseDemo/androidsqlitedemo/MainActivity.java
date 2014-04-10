package com.databaseDemo.androidsqlitedemo;

import android.support.v7.app.ActionBarActivity;
import android.view.View.OnClickListener;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	Button save, load;
	EditText name, email;
	DataHandler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		save = (Button) findViewById(R.id.save);
		load = (Button) findViewById(R.id.load);
		name = (EditText) findViewById(R.id.name);
		email = (EditText) findViewById(R.id.email);
		
		
		
	save.setOnClickListener(new OnClickListener(){
		public void onClick(View v){
		
			String getName = name.getText().toString();
			String getEmail = email.getText().toString();
			handler = new DataHandler(getBaseContext());
			handler.open();
			long id = handler.insertData(getName, getEmail);
			Toast.makeText(getBaseContext(), "Data inserted", Toast.LENGTH_LONG).show();
			handler.close();
			
		}//end onClick v
	});//end

	load.setOnClickListener(new OnClickListener() {
		
		@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
				String getName,getEmail;
				getName = "";
				getEmail = "";
				
				handler = new DataHandler(getBaseContext());
				handler.open();
				Cursor C = handler.returnData();
					if(C.moveToFirst()){
						do{
							getName = C.getString(0);
							getEmail = C.getString(1);
						}while(C.moveToNext());
						
					}//end if
					handler.close();
					Toast.makeText(getBaseContext(),"NAME: "+getName+
				" And Email: "+getEmail, Toast.LENGTH_LONG).show();
			}//end onClick v
		});//end load.onClickListened
	}//end onCreate

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}
