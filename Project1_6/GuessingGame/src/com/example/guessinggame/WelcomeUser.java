package com.example.guessinggame ;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class WelcomeUser extends Activity {
	Button changePass, logout, playgame, score;
	String user = "";
	Context c = App.getContext();
	
	public void onClickPlay(View v) {
		Intent in = new Intent(WelcomeUser.this, GuessingGame.class);
		in.putExtra("username", user);
		startActivity(in);
		finish();
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_user);
		changePass = (Button) findViewById(R.id.changePass);
		user = getIntent().getExtras().getString("username");
		
		changePass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent in = new Intent(WelcomeUser.this, ChangePassword.class);
				in.putExtra("username", user);
				startActivity(in);
				finish();

			}
			
		});
		
		
		score=(Button) findViewById(R.id.score);
		score.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				
//				MyDbHelper db = new MyDbHelper(WelcomeUser.this);
//				db.openCon();
//				db.updateScore(user);
		//		String score=db.Total_Score;
				Toast.makeText(WelcomeUser.this, "Your Score is :"+ score, Toast.LENGTH_LONG).show();
		
			}
		});
				
		
		logout = (Button) findViewById(R.id.logout);
		logout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(WelcomeUser.this, "You have been successfully logged out!!!", Toast.LENGTH_LONG).show();
				Intent in = new Intent(WelcomeUser.this, Login.class);

				startActivity(in);
				finish();

			}
		});
		
		playgame = (Button) findViewById(R.id.playgame);
		playgame.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//Toast.makeText(WelcomeUser.this, "You have been successfully logged out!!!", Toast.LENGTH_LONG).show();
				user = getIntent().getExtras().getString("username");
				Intent in = new Intent(WelcomeUser.this, MainActivity.class);
				in.putExtra("username", user);
				startActivity(in);
				finish();

			}
		});

		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome_user, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		return super.onOptionsItemSelected(item);
	}
}
