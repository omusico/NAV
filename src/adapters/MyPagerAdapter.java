package adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.astuetz.PagerSlidingTabStrip.IconTabProvider;
import com.example.nav.R;

import fragments.Inventory;
import fragments.Staff;
import fragments.Task;

public class MyPagerAdapter extends FragmentPagerAdapter implements IconTabProvider {

	private Context c;
	
	private final int ICONS[] = new int[] { R.drawable.ic_action_inventory, R.drawable.ic_action_staff, R.drawable.ic_action_task};

	Drawable ic_action_inventory, ic_action_staff, ic_action_task;

	public int getPageIconResId(int position) {
			return ICONS[position];
		}
	
	public MyPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override public int getCount() {
		return ICONS.length;
	}

	@Override public Fragment getItem(int index) {
		switch (index) {
        case 0:
        	return new Inventory();
        case 1:
            return new Staff();
        case 2:
            return new Task();
        }
        return null;
	}

}
