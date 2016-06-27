package com.example.guessinggame ;

import android.content.ContentValues;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper {
	static Context context;
	
	MyDbConn ob;
	static final String Key_DBNAME = "UserScore";
	static final String Key_TABLE = "UserRegister";
	static final String Key_FULLNAME = "fullname";
	static final String Key_USERNAME = "username";
	static final String Key_PASSWORD = "password";
	static final String Key_SCORE = "score";
	static final String Key_GENDER = "gender";
	static final String Key_ID = "id";
	static final int KEY_VERSION = 1;
	static SQLiteDatabase sqlOb;

	public static String Total_Score;
	
	public MyDbHelper(Context c) {
		context = c;
	}
 //public MyDbHelper mydb;

	
	public void openCon() {
		ob = new MyDbConn();
		sqlOb = ob.getWritableDatabase();
	}

	public void closeCon() {
		ob.close();
	}

	private class MyDbConn extends SQLiteOpenHelper {

		public MyDbConn() {
			super(context, Key_DBNAME, null, KEY_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			String q = "CREATE TABLE " + Key_TABLE + "(" + Key_ID
					+ " integer primary key autoincrement," + Key_FULLNAME
					+ " Text," + Key_USERNAME + " Text," + Key_PASSWORD
					+ " Text," + Key_GENDER + " Text," + Key_SCORE
					+ " INTEGER)";
			db.execSQL(q);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table if exists " + Key_TABLE);
			onCreate(db);

		}
	}
	
	public long insertData(String fullname, String user, String pass,
			String gender) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(Key_FULLNAME, fullname);
		contentValues.put(Key_USERNAME, user);
		contentValues.put(Key_PASSWORD, pass);
		contentValues.put(Key_GENDER, gender);
		contentValues.put(Key_SCORE, 0);
		long l = sqlOb.insert(Key_TABLE, null, contentValues);
		System.out.println("l i s " + l);
		return l;
	}

	public String checkLogin(String suser, String spass) {
		String query = "select * from " + Key_TABLE + " where " + Key_USERNAME
				+ "='" + suser + "' and " + Key_PASSWORD + "='" + spass + "'";
		Cursor c = sqlOb.rawQuery(query, null);
		String status = "";
		System.out.println("count is " + c.getCount());
		if (c.getCount() > 0)// checks how many no. of rows are present in table
								// if 0 then no data present
		{
			status = "success";
		} else {

			status = "failed";
		}

		return status;
	}

	public int updatePass(String s_old, String s_new, String s_user) {
		int s_code = 0;
		
		  String query = "update " + Key_TABLE + " set " + Key_PASSWORD + "='"
		  + s_new + "' where " + Key_USERNAME + "='" + s_user + "' and " +
		  Key_PASSWORD + "='" + s_old + "'";
		  System.out.println("query is "+query);
		  sqlOb.execSQL(query); 
	
		ContentValues cv = new ContentValues();
		cv.put(Key_PASSWORD, s_new); // These Fields should be your String
		cv.put(Key_USERNAME, s_user);								// values of actual column names

		s_code = sqlOb.update(Key_TABLE, cv, Key_USERNAME + "='" + s_user+ "'"+" AND "+Key_PASSWORD+"='"+s_new+"'", null);
		System.out.println("code is " + s_code);
		return s_code;

	}
	
	public void updateScore(String us_user) {
		
	String query1 = " Select "+ Key_SCORE + " from " + Key_TABLE + " where " + Key_USERNAME
			+ "='" + us_user + "'";
	
	Cursor upd = sqlOb.rawQuery(query1, null);
	
	int Initial_Score = Integer.parseInt(upd.getString(upd.getColumnIndex(Key_SCORE)));
	Total_Score = String.valueOf(Initial_Score);
	int a = 20;
	int b = Initial_Score + a;
	String us_score = String.valueOf(b);
	
	String query2 = "update " + Key_TABLE + " set " + Key_SCORE + "='"
	+ us_score + "' where " + Key_USERNAME + "='" + us_user + "'";
	
	System.out.println("query is "+query2);
	sqlOb.execSQL(query2); 
	
		ContentValues cv1 = new ContentValues();
		cv1.put(Key_SCORE, us_score); // These Fields should be your String
		cv1.put(Key_USERNAME, us_user);								// values of actual column names

		sqlOb.update(Key_TABLE, cv1, Key_USERNAME + "='" + us_user+ "'"+" AND "+Key_SCORE+"='"+us_score+"'", null);
		
	}
}

//	public int getscore(String g_user){
//		String query1 = "select Key_SCORE from " + Key_TABLE + " where " + Key_USERNAME
//				+ "='" + g_user + "'";
//		
//		
//		Cursor upd = sqlOb.rawQuery(query1, null);
//		
//		int Initial_Score = Integer.parseInt(upd.getString(upd.getColumnIndex(Key_SCORE)));
//		int a = 20;
//		int b = Initial_Score + a;
//		String us_score = String.valueOf(b);
//
//}
