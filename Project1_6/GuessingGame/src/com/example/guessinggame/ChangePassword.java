package com.example.guessinggame ;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends Activity {
	Button changePass, reset;
	EditText oldpass, newpass, renewpass;
	String s_old = "", s_new = "", s_renew = "", user = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_change_password);
		user = getIntent().getExtras().getString("username");

		changePass = (Button) findViewById(R.id.btnUpdate);
		oldpass = (EditText) findViewById(R.id.oldpass);
		newpass = (EditText) findViewById(R.id.newpass);
		renewpass = (EditText) findViewById(R.id.renewpass);
		changePass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				s_old = oldpass.getText().toString();
				s_new = newpass.getText().toString();
				s_renew = renewpass.getText().toString();

				if (s_old.equals("") || s_new.equals("") || s_renew.equals("")) {

					Toast.makeText(ChangePassword.this, "All fields are required \nPlease fill all fields!!! ",
							Toast.LENGTH_LONG).show();

				} else if (!(s_new.equalsIgnoreCase(s_renew))) {

					Toast.makeText(ChangePassword.this, "New password did not match\nTry again!!! ", Toast.LENGTH_LONG)
							.show();
					oldpass.requestFocus();

				} else {

					MyDbHelper db = new MyDbHelper(ChangePassword.this);
					db.openCon();
					int status_code = db.updatePass(s_old, s_new, user);
					db.closeCon();
					if (status_code > 0) {

						Toast.makeText(ChangePassword.this, "Password Has Been Updated Successfully!!! " + status_code,
								Toast.LENGTH_LONG).show();
						Intent in = new Intent(ChangePassword.this, WelcomeUser.class);
						in.putExtra("username", user);
						startActivity(in);
						finish();

					} else {

						Toast.makeText(ChangePassword.this, "Failed to update the password\nTry After sometime!!! ",
								Toast.LENGTH_LONG).show();

					}

				}

			}
		});

		reset = (Button) findViewById(R.id.btnReset);
		reset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				oldpass.setText("");
				newpass.setText("");
				renewpass.setText("");
				oldpass.requestFocus();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.change_password, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
	
		return super.onOptionsItemSelected(item);
	}
}
