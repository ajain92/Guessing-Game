package com.example.guessinggame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	EditText user, pass;
	Button loginButton;
	TextView regButton;
	String suser, spass;
	Button Info;


	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		user = (EditText) findViewById(R.id.username);
		pass = (EditText) findViewById(R.id.password);
		loginButton = (Button) findViewById(R.id.btnLogin);
		regButton = (TextView) findViewById(R.id.link_to_register);
		Info = (Button) findViewById(R.id.imageView1);
		
		loginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				suser = user.getText().toString();
				spass = pass.getText().toString();
				if (suser.equals("") || spass.equals("")) {
					Toast.makeText(Login.this, "All Fields Are Required ", Toast.LENGTH_LONG).show();

				} else {
					MyDbHelper db = new MyDbHelper(Login.this);
					db.openCon();
					String status = db.checkLogin(suser, spass);
					if (status.equalsIgnoreCase("success")) {
						Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
						Intent in = new Intent(Login.this, WelcomeUser.class);
						in.putExtra("username", suser);
						startActivity(in);
						finish();

					} else {

						Toast.makeText(getApplicationContext(), "Username pr Password is incorrect\nTry Again!!!",
								Toast.LENGTH_LONG).show();
						user.requestFocus();
					}
					db.closeCon();
				}
			}
		});

		regButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent in = new Intent(Login.this, Register.class);

				startActivity(in);
				finish();

			}
						
		});

		
		Info.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent about = new Intent(Login.this, Aboutus.class);

				startActivity(about);
				finish();

			}
						
		});
			
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
