package activities;

import adapters.DrawerListAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nav.R;
import com.parse.Parse;

import fragments.Login;

public class MainActivity extends ActionBarActivity {

	private CharSequence mDrawerTitle;
	private String[] title;
	private int[] icon;
	
	private DrawerListAdapter mMenuAdapter;
	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	
	private TextView mCustomTitle;
	
	private FragmentTransaction ft;
	
	// ******************************************
	// ****** START NAVIGATION DRAWER CODE ******
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ft = getSupportFragmentManager().beginTransaction();
		
		setContentView(R.layout.activity_main);
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
        
        title = new String[] { "Home, About", "Log"};
		icon = new int[] { R.drawable.ic_action_about, R.drawable.ic_action_login};
		
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
		switch (position) {
		case 0:
			ft.replace(R.id.content_frame, new NavViewPager(), "viewpager");
			ft.commit();
			break;
		case 1:
			//About Fragment Transaction here!
			break;
		case 2:
			ft.replace(R.id.content_frame, new Login(), "log");
			ft.commit();
			break;
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
        .setMessage("This will exit and log you out!")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){ @Override public void onClick(DialogInterface dialog, int which) {
            finish();    
         }})
	    .setNegativeButton("No", null)
	    .show();
	}
	// ****** END EXIT CODE ******
	// ***************************
	
}
