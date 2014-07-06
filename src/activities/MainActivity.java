package activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import com.example.nav.R;

public class MainActivity extends ActionBarActivity {

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override public void onBackPressed() {
		exitApp();
	}
	
	public void exitApp(){
		new AlertDialog.Builder(this)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setTitle("Exit Miggetter")
        .setMessage("This will exit and log you out!")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){ @Override public void onClick(DialogInterface dialog, int which) {
            finish();    
         }})
	    .setNegativeButton("No", null)
	    .show();
	}
	
}
