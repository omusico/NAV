package activities;

import com.example.nav.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;


public class SplashScreen extends ActionBarActivity {
	
	Handler mHandler;
	Runnable mJumpRunnable;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_splashscreen);
	    getSupportActionBar().hide();
	    
	    long SPLASH_TIME = 1500; //1.5 seconds
	    
	    mJumpRunnable = new Runnable() {
	    public void run() { jump();  }
	    	 };
	    	 
	    mHandler = new Handler();
	    mHandler.postDelayed(mJumpRunnable, SPLASH_TIME);
	
	}
	
	private void jump() {
		if(isFinishing())
		  return;
		 startActivity(new Intent(this, MainActivity.class));
		 finish();
		}
	
}
