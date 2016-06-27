package com.example.guessinggame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Aboutus extends Activity {
ImageView Casino;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutus);
		Casino = (ImageView) findViewById(R.id.ImageView02);
		
		
		Casino.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent about = new Intent(Aboutus.this, Login.class);

				startActivity(about);
				finish();

			}
						
		});
	}

}
