package com.databaseDemo.androidsqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHandler {
	public static final String NAME = "name";
	public static final String EMAIL = "email";
	public static final String TABLE_NAME = "mytable";
	public static final String DATA_BASE_NAME = "mydatabase";
	public static int DATABASE_VERSION = 1;
	public static final String TABLE_CREATE = "create table mytable(name text not null, email text not null);";

	DataBaseHelper dbhelper;
	Context ctx;
	SQLiteDatabase db;
	
	public DataHandler(Context ctx){
		this.ctx = ctx;
		dbhelper = new DataBaseHelper(ctx);
	}//end DataHandler
	
	private static class DataBaseHelper extends SQLiteOpenHelper{
		
		public DataBaseHelper(Context ctx){
			super(ctx, DATA_BASE_NAME, null, DATABASE_VERSION);
		}//end DataBaseHelper
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try{
				db.execSQL(TABLE_CREATE);
			}catch(SQLException e){
				e.printStackTrace();}
		}//end onCreate
	
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
			db.execSQL("DROP TABLE IF EXIST mytable");
			onCreate(db);
		}//end onUpgrade
	}//end DataBaseHelper
	
			//Open Database method
			public DataHandler open(){
				db = dbhelper.getWritableDatabase();
				return this;
			}//end open()
			
			//Close Database
			public void close(){
				dbhelper.close();
			}//end close
			
			//Insert data into Database
			public long insertData(String name, String email){
				ContentValues content = new ContentValues();
				content.put(NAME, name);
				content.put(EMAIL, email);
				return db.insertOrThrow(TABLE_NAME, null, content);
			}//end Insert data
			
			//Retrieve Data
			public Cursor returnData(){
				return db.query(TABLE_NAME,	new String[]{NAME, EMAIL},null, null, null, null, null );
				
				
			}//end returnData
			
	}//end class
