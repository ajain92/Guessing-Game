package com.example.guessinggame;

import java.util.Random;




import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
	
	int score = 100;
	int chance = 10;
	//Random r = new Random();
	int rand = 10;
			//r.nextInt(100 - 0) + 1;
	Button b1;
	private ViewFlipper viewFlipper;
	Button back;
    private float lastX;
    
    private ListView mainList;
	private MediaPlayer mp;
	private final String[] listContent = { "Sponge Bob", "Hard Ton","Tequila" };

	private final int[] resID = { R.drawable.spongebob, R.drawable.hard_ton, R.drawable.asd};
	
	
	Context d = App.getContext();
	public static Object value;
	
	
	
	//Creating play button for music
	public void playy (View v)
	{
		super.onStart();
		mp.start();
		
	}
	//Creating pause button for music
	public void pausee (View v)
	{
		super.onPause();
		mp.pause();
	}
	//Creating stop button for music
	public void stopp (View v)
	{
		super.onPause();
		mp.pause();
		mp.seekTo(0);
	}
	
	
	
	
public void playSong(int songIndex) {
		
		mp.reset();// stops any current playing song
		mp = MediaPlayer.create(getApplicationContext(), resID[songIndex]);// create's
		mp.setLooping(true);
		mp.start(); 
		
		
		final int ms = mp.getDuration();
		
		
		
		String asd = Integer.toString(ms);
		
		
		TextView textView = (TextView) findViewById(R.id.ms);
		final TextView percent = (TextView) findViewById(R.id.percent);
		
		final TextView curr = (TextView) findViewById(R.id.textView11);
		
		
		textView.setText(asd+" millisecond");


		
		
    	
    	
		
		final ProgressBar prog = (ProgressBar)findViewById(R.id.bar);
		
		 prog.setMax(mp.getDuration());
		 
		 
		
		new Thread(new Runnable() {
            public void run() {
            	
            	
            	int currentPosition = 0;
               

            	
                while (mp.getCurrentPosition() <= mp.getDuration()) {

                	try {
                        Thread.sleep(1000);
                        
                        percent.post(new Runnable()
                        {
            			    public void run() 
            			    {
            			    	int a = mp.getCurrentPosition()*100;
                             	int b = (a/ms)+1;
                             	String cp = Integer.toString(mp.getCurrentPosition());
                             	String auto = Integer.toString(b);
            			        percent.setText(auto+"%");
            			      
            			        curr.setText(cp+"milliseconds");
            			    } 
            			});
                       
                    	
                        	
                        currentPosition = mp.getCurrentPosition();
                		
                        
                    } catch (InterruptedException e) {
                        return;
                    } catch (Exception e) {
                        return;
                    }
                	
                	
                	
                    prog.setProgress(currentPosition);
                
                    
                }
                
            }
      }).start(); 
        
        
        
}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
		
		back=(Button) findViewById(R.id.back);
//		back.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent in = new Intent(MainActivity.this, WelcomeUser.class);
//				
//				startActivity(in);
//				finish();
//				
//			}
//		});
		
        
		
			
		
			
		
        
		mp = new MediaPlayer();
		mp.setLooping(true);
		mainList = (ListView) findViewById(R.id.listView1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		android.R.layout.simple_dropdown_item_1line, listContent);
		mainList.setAdapter(adapter);

		
		
		mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> adapterView, View view,
		int position, long id)
		{
		playSong(position);

		}
		});

		
		App.setContext(this);
		
		final EditText mEdit3 = (EditText)findViewById(R.id.editText3);
		final EditText mEdit4 = (EditText)findViewById(R.id.editText4);
		
		//Set Score and Chance value
		
		mEdit3.setText(String.valueOf(score));
		mEdit4.setText(String.valueOf(chance));
       
	}
	
	
	
	public boolean onTouchEvent(MotionEvent touchevent) {
    	switch (touchevent.getAction()) {
        
        case MotionEvent.ACTION_DOWN: 
        	lastX = touchevent.getX();
            break;
        case MotionEvent.ACTION_UP: 
            float currentX = touchevent.getX();
            
            // Handling left to right screen swap.
            if (lastX < currentX) {
            	
            	// If there aren't any other children, just break.
                if (viewFlipper.getDisplayedChild() == 0)
                	break;
                
                // Next screen comes in from left.
                viewFlipper.setInAnimation(this, R.anim.slide_in_from_left);
                // Current screen goes out from right. 
                viewFlipper.setOutAnimation(this, R.anim.slide_out_to_right);
                
                // Display next screen.
                viewFlipper.showNext();
             }
                                     
            // Handling right to left screen swap.
             if (lastX > currentX) {
            	 
            	 // If there is a child (to the left), kust break.
            	 if (viewFlipper.getDisplayedChild() == 1)
            		 break;
    
            	 // Next screen comes in from right.
            	 viewFlipper.setInAnimation(this, R.anim.slide_in_from_right);
            	// Current screen goes out from left. 
            	 viewFlipper.setOutAnimation(this, R.anim.slide_out_to_left);
                 
            	// Display previous screen.
                 viewFlipper.showPrevious();
             }
             break;
    	 }
         return false;
    }

	
	

	
	
	//Onclick event assigned for every guessing button
	public void onClick (View v) {
		
		//Creating canvas 
		
		Paint paint = new Paint();
        paint.setColor(Color.parseColor("#CD5C5C"));
        Bitmap bg = Bitmap.createBitmap(480, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bg); 
        //canvas.drawRect(50, 50, 200, 200, paint); 
        RelativeLayout ll = (RelativeLayout) findViewById(R.id.saatana);
        ll.setBackgroundDrawable(new BitmapDrawable(bg));   
        
        Paint wallpaint = new Paint();
		wallpaint.setColor(Color.BLACK);
		wallpaint.setStyle(Style.FILL);
		
		Paint yellow = new Paint();
		yellow.setColor(Color.BLUE);
		
		Paint paintred = new Paint();
		paintred.setColor(Color.RED);
		paintred.setTextSize(50);
		
		Paint gray =new Paint();
		gray.setColor(Color.LTGRAY);
		
        //Drawing each new part of hangman while guesses keep lowering
		
		if(chance<=10)
		{
			/*rope*/
			Canvas rope = new Canvas(bg);
			Path rope1 = new Path();
			
			rope1.reset();
			
			rope1.moveTo(195, 450);
			rope1.lineTo(195, 580);
			rope1.lineTo(205, 580);
			rope1.lineTo(205, 450);
			rope1.lineTo(195, 450);
			
			rope.drawPath(rope1, gray);
		}
		if(chance<=9)
		{
			/*face*/
			
			Canvas face = new Canvas(bg);
			face.drawCircle(202, 520, 50, paint);
			
			face.drawCircle(180, 500, 5, wallpaint);
			face.drawCircle(230, 500, 5, wallpaint);
		//	face.drawCircle(220, 250, 5, paint1);
			
			
		}
		if(chance<=8)
		{
			Canvas face = new Canvas(bg);
			Path nose = new Path();
			
			nose.reset();
			
			nose.moveTo(205, 520);
			nose.lineTo(200, 535);
			nose.lineTo(210, 535);
			nose.lineTo(205, 520);
			face.drawPath(nose, wallpaint);
			face.drawPath(nose, wallpaint);
		}
		if(chance<=7)
		{
			Canvas ear = new Canvas(bg);
			ear.drawCircle(155, 520, 15, paint);
			ear.drawCircle(250, 520, 15, paint);
		}
		if(chance<=6)
		{
			Canvas body = new Canvas(bg);
			Path wallpath = new Path();
			
			wallpath.reset();
			
			wallpath.moveTo(200, 570);
			wallpath.lineTo(200, 730);
			wallpath.lineTo(210, 730);
			wallpath.lineTo(210, 570);
			wallpath.lineTo(200, 570);
			body.drawPath(wallpath, yellow);
		}
		if(chance<=5)
		{
			Canvas hands = new Canvas(bg);
			Path hand = new Path();
			hand.reset();
			
			hand.moveTo(150, 650);
			hand.lineTo(200, 600);
			hand.lineTo(210, 600);
			hand.lineTo(260, 650);
			hand.lineTo(260, 640);
			hand.lineTo(210, 590);
			hand.lineTo(200, 590);
			hand.lineTo(150, 640);
			hand.lineTo(150, 650);
			
			hands.drawPath(hand, yellow);
			
		}
		if(chance<=4)
		{
			/*leg*/
			
			Canvas legs = new Canvas(bg);
			
			Path leg = new Path();
			leg.reset();
			
			leg.moveTo(150, 780);
			leg.lineTo(200, 730);
			leg.lineTo(210, 730);
			leg.lineTo(260, 780);
			leg.lineTo(260, 770);
			leg.lineTo(210, 720);
			leg.lineTo(200, 720);
			leg.lineTo(150, 770);
			leg.lineTo(150, 780);
			
			legs.drawPath(leg, yellow);
		}
		if(chance<=3)
		{
			Canvas hanger = new Canvas(bg);
			Path hanger1 = new Path();
			
			hanger1.reset();
			
			hanger1.moveTo(100, 450);
			hanger1.lineTo(100, 780);
			hanger1.lineTo(110, 780);
			hanger1.lineTo(110, 460);
			hanger1.lineTo(300, 460);
			hanger1.lineTo(300, 780);
			hanger1.lineTo(310, 780);
			hanger1.lineTo(310, 450);
			hanger1.lineTo(100, 450);
			
			hanger.drawPath(hanger1, wallpaint);
		}
		if(chance<=2)
		{
			/*DIE*/
			
			
			Canvas die = new Canvas(bg);
		//	Path d = new Path();
			
			//d.reset();
			
			
			RectF oval1 = new RectF(190, 575, 220, 585);
			die.drawOval(oval1, paintred );
			
			//die.drawPath(d, wallpaint);
		}
		
		if(chance<=1)
		{
			/*DIE*/
			Canvas Text = new Canvas(bg);	
			Text.drawText("DIED", 350, 600, paintred);
			
		}
		
		//Taking button text name and then converting that to int which we compare with random number
		//and with if call we determine button color
		Button b = (Button)v;
		String buttontext = b.getText().toString();
		int value=Integer.valueOf(buttontext);
		
		
		if(value < rand)
		{
		b.setBackgroundColor(Color.YELLOW);
			
		
		}
		
		else if(value > rand)
		{
			
			b.setBackgroundColor(Color.RED);

		}
		
		else 
		{
			b.setBackgroundColor(Color.GREEN);	
		
		}
		 EditText mEdit = (EditText)  findViewById(R.id.editText1);
		 EditText mEdit3 = (EditText) findViewById(R.id.editText3);
		 EditText mEdit4 = (EditText) findViewById(R.id.editText4);

		GuessingGame gg = new GuessingGame();
		
		gg.execute(mEdit,mEdit3,mEdit4,value);
		
		
		
		

		
}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
}
