package com.example.guessinggame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;

import android.widget.EditText;
import android.widget.Toast;

public class GuessingGame extends Activity {

	int valinta2;
	Context c = App.getContext();
	int answer = ((MainActivity) c).rand;
	
	public void execute(EditText a,EditText cc,EditText mEdit4, int value)
	{
		 EditText mEdit = a;

		 EditText mEdit3 = cc;
		 	
		 

			if (((MainActivity) c).chance != 1) {
				if (value == ((MainActivity) c).rand) {
					
					
					((MainActivity) c).stopp(null);
					((MainActivity) c).score = ((MainActivity) c).score;
					((MainActivity) c).chance = ((MainActivity) c).chance;
					
					mEdit3.setText(String.valueOf(((MainActivity) c).score));
					mEdit4.setText(String.valueOf(((MainActivity) c).chance));

					ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
					toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
					

					
					
					MainTestGuessingGame mt = new MainTestGuessingGame();
					
					mt.guess("Congratulations! You won! Press yes to play again or no to quit. Your score is - ",String.valueOf(((MainActivity) c).score ));
//					String user = "";
//					 user = getIntent().getExtras().getString("username");
//					 
//					
//					MyDbHelper db =new MyDbHelper(GuessingGame.this);
//					db.openCon();
//					db.updateScore(user);
//					db.closeCon();	
//					
	//				Intent in = new Intent(GuessingGame.this, WelcomeUser.class);
//					in.putExtra("username", "aku");		


				
	
									
				} else {
					((MainActivity) c).score = ((MainActivity) c).score - 10;
					((MainActivity) c).chance = ((MainActivity) c).chance - 1;
					
					
					
					if (value < ((MainActivity) c).rand) {
						Toast.makeText(
							c,
								"The number you entered is less than the number chosen by the program",
								Toast.LENGTH_LONG).show();
								
					} else {
						Toast.makeText(
								c,
								"The number you entered is greater than the number chosen by the program",
								Toast.LENGTH_LONG).show();
					}  

					mEdit3.setText(String.valueOf(((MainActivity) c).score));
					mEdit4.setText(String.valueOf(((MainActivity) c).chance));

				}
			} else {
				
				
				mEdit3.setText(String.valueOf(((MainActivity) c).score-10));
				mEdit4.setText(String.valueOf(((MainActivity) c).chance-1));
				((MainActivity) c).stopp(null);
				MainTestGuessingGame mt = new MainTestGuessingGame();
				mt.guess("You lost! Number chosen by the game was " +answer+" Press Yes to play again or No to quit","");
				
					   
				
			}
			}
	
}
