package fragments;

import adapters.MyPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.nav.R;

public class NavViewPager extends Fragment {

	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private MyPagerAdapter adapter;
	
	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new MyPagerAdapter(getChildFragmentManager());
	}
	
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_viewpager, container, false);
		setupViews(view);
		setupValues();
		return view;
	}

	private void setupValues() {
		pager.setAdapter(adapter);
		tabs.setViewPager(pager);
	}

	private void setupViews(View view) {
		tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
		pager = (ViewPager) view.findViewById(R.id.pager);
	}
	
}
