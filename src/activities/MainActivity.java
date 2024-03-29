package activities;

import adapters.DrawerListAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cengalabs.flatui.FlatUI;
import com.example.nav.R;

import fragments.About;
import fragments.AddTicket;
import fragments.Login;
import fragments.NavViewPager;

public class MainActivity extends ActionBarActivity {

	private CharSequence mDrawerTitle;
	private String[] title;
	private int[] icon;
	
	private DrawerListAdapter mMenuAdapter;
	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	
	private TextView mCustomTitle;
	
	// ******************************************
	// ****** START NAVIGATION DRAWER CODE ******
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		// Converts the default values (radius, size, border) to dp to be compatible with different
		// screen sizes. If you skip this there may be problem with different screen densities
		FlatUI.initDefaultValues(this);

		// Setting default theme to avoid to add the attribute "theme" to XML 
		// and to be able to change the whole theme at once
		FlatUI.setDefaultTheme(R.array.hotpink);    // for using custom theme as default

		// Getting action bar drawable and setting it.
		// Sometimes weird problems may occur while changing action bar drawable at runtime.
		// You can try to set title of the action bar to invalidate it after setting background.
		getActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(this, R.array.hotpink, false));
		getSupportActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(this, R.array.hotpink, false));
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayOptions(
        		ActionBar.DISPLAY_SHOW_HOME|
        		ActionBar.DISPLAY_SHOW_TITLE|
        		ActionBar.DISPLAY_HOME_AS_UP|
        		ActionBar.DISPLAY_USE_LOGO);
        
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
		
        mDrawerTitle = getTitle();
        
        title = new String[] { "Home", "About", "Log"};
		icon = new int[] { R.drawable.ic_action_home, R.drawable.ic_action_about, R.drawable.ic_action_login};
		
		mMenuAdapter = new DrawerListAdapter(MainActivity.this, title, icon);
		mDrawerList.setAdapter(mMenuAdapter);
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		
		mDrawerToggle = new ActionBarDrawerToggle (this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_navigation_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */) {  /** Called when a drawer has settled in a completely closed state. */
				public void onDrawerClosed(View view) {
            	super.onDrawerClosed(view);}
	            /** Called when a drawer has settled in a completely open state. */
	            public void onDrawerOpened(View drawerView) {
	            	getSupportActionBar().setTitle(mDrawerTitle);
					super.onDrawerOpened(drawerView);}};
					
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		if (savedInstanceState == null) {
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.content_frame, new Login(), "log");
			ft.commit();
		}
	}
	@Override public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case android.R.id.home:
			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
			break;
	    default:
	        return super.onOptionsItemSelected(item);
		}
		return super.onOptionsItemSelected(item);
	}
	private void selectItem(int position) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		switch (position) {
		case 0:
			ft.replace(R.id.content_frame, new NavViewPager(), "viewpager");
			ft.commit();
			break;
		case 1:
			ft.replace(R.id.content_frame, new About(), "about");
			ft.commit();
			break;
		case 2:
			//Working but we do not need to load the login page anymore after a user logins (Hidden Navigation Menu Implemented)!
			Fragment checkFrag = getSupportFragmentManager().findFragmentByTag("log");
			if(checkFrag == null){
				ft.replace(R.id.content_frame, new Login(), "log");
				ft.commit();
				break;
			} 
			else break;
		}
		mDrawerList.setItemChecked(position, true);
		mDrawerLayout.closeDrawer(mDrawerList);
	}
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }
	@Override protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    @Override public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
	@Override public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem addTicket = menu.findItem(R.id.addTicket);	
		addTicket.setOnMenuItemClickListener(new OnMenuItemClickListener() { @Override public boolean onMenuItemClick(MenuItem item) {
			AddTicket addTicket = new AddTicket();
			addTicket.show(getSupportFragmentManager(), "add");
			return true;
		}});
		
		return true;
	}
	@Override public void setTitle(CharSequence title) {
		mCustomTitle.setText(title);
	}
	// ****** END NAVIGATION DRAWER CODE ******
	// ****************************************
	
	// ****** START EXIT CODE ******
	// *****************************
	@Override public void onBackPressed() {
		if(getSupportFragmentManager().getBackStackEntryCount() == 0) exitApp();
		else super.onBackPressed();
	}
	public void exitApp(){
		new AlertDialog.Builder(this)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setTitle("Exit NAV")
        .setMessage("Exit NAV?")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){ @Override public void onClick(DialogInterface dialog, int which) {
            finish();    
         }})
	    .setNegativeButton("No", null)
	    .show();
	}
	// ****** END EXIT CODE ******
	// ***************************
	
}
