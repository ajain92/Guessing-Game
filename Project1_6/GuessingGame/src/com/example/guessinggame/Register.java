package com.example.guessinggame ;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity implements OnClickListener {

	Button submit, reset;
	EditText fullName, username, pass;
	RadioButton rb1, rb2;
	RadioGroup rg;
	TextView reg_login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		reg_login = (TextView) findViewById(R.id.link_to_login);
		reg_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent in = new Intent(Register.this, Login.class);
				startActivity(in);
				finish();
			}
		});
		rg = (RadioGroup) findViewById(R.id.rgroup);

		reset = (Button) findViewById(R.id.btnReset);

		username = (EditText) findViewById(R.id.reg_email);
		fullName = (EditText) findViewById(R.id.reg_fullname);

		pass = (EditText) findViewById(R.id.reg_password);
		rb1 = (RadioButton) findViewById(R.id.male);
		rb2 = (RadioButton) findViewById(R.id.female);
		rg = (RadioGroup) findViewById(R.id.rgroup);
		submit = (Button) findViewById(R.id.btnRegister);
		submit.setOnClickListener(this);
		reset.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btnRegister) {
			String uname = username.getText().toString();
			String pass1 = pass.getText().toString();
			String sfullname = fullName.getText().toString();
			int id1 = rg.getCheckedRadioButtonId();

			RadioButton yu = (RadioButton) findViewById(id1);
			String gender = yu.getText().toString();

			if (uname.equalsIgnoreCase("") || pass1.equalsIgnoreCase("")
					|| gender.equalsIgnoreCase("")) {
				Toast.makeText(Register.this, "All fields Are Required ",
						Toast.LENGTH_LONG).show();

			} else {
				MyDbHelper o = new MyDbHelper(Register.this);
				o.openCon();
				long h = o.insertData(sfullname, uname, pass1, gender);
				if (h > 0) {
					Toast.makeText(Register.this,
							"Registration successful\nNow you can log in ",
							Toast.LENGTH_LONG).show();
					Intent in = new Intent(Register.this, Login.class);
					startActivity(in);
					finish();
				} else {
					Toast.makeText(Register.this, "Failed", 5000).show();
				}
				o.closeCon();
			}
		}

		if (v.getId() == R.id.btnReset) {

			username.setText("");
			pass.setText("");
			rb1.setChecked(false);
			rb2.setChecked(false);
			fullName.setText("");

		}

	}
}
