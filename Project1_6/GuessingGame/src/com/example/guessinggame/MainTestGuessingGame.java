package com.example.guessinggame;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainTestGuessingGame extends Activity{
	 
	MainActivity ma = new MainActivity();
	
	Context c = App.getContext();
	//String user = "";
	public void guess(String result, String fscore) {

		AlertDialog.Builder builder = new AlertDialog.Builder(c);
	  	builder.setMessage(result + fscore);
	    //Toast.makeText(c, "Would you want to play Guessing Game?", Toast.LENGTH_LONG).show();
	    builder.setNegativeButton("NO",new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			
			Toast.makeText(c,"Good Bye", Toast.LENGTH_LONG).show();
		
	//		user = getIntent().getExtras().getString("username");
			
//			MyDbHelper db =new MyDbHelper(MainTestGuessingGame.this);
//			db.openCon();
//			db.updateScore(user);
//			//db.closeCon();	
//			
//			Intent in = new Intent(MainTestGuessingGame.this, GuessingGame.class);
//			in.putExtra("username", user);		
//	
			((Activity) c).finish();
		
		
			}
	    });
	    
	    builder.setPositiveButton("YES",
	    new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog,int which) {
	
	    Toast.makeText(c,"Welcome Back",Toast.LENGTH_LONG).show();
	
//	    user = getIntent().getExtras().getString("username");
//		
//		MyDbHelper db =new MyDbHelper(MainTestGuessingGame.this);
//		db.openCon();
//		db.updateScore(user);
//		//db.closeCon();	
//		
//		Intent in = new Intent(MainTestGuessingGame.this, GuessingGame.class);
//		in.putExtra("username", user);		

	    Random r = new Random();
	    ((MainActivity) c).score = 100;
	    ((MainActivity) c).chance = 10;
	    ((MainActivity) c).rand = r.nextInt(100 - 0) + 1;
	    
	    
	    
	    ((MainActivity) c).onCreate(new Bundle());  
	    }
	    });
	    builder.show();
		
	}
}


