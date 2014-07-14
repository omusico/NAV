package fragments;

import java.util.ArrayList;
import java.util.List;

import models.User;
import adapters.StaffAdaptor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.nav.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class Staff extends Fragment {

	private StaffAdaptor adapter;
	private ListView lvStaff;
	private ArrayList<User> users;
	
	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		users = new ArrayList<User>();
		adapter = new StaffAdaptor(getActivity(), users);
	}
	
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_staff, container, false);
		setupViews(view);
		setupValues();
		return view;
	}
	
	@SuppressWarnings("unchecked")
	private void setupValues() {
		ParseQuery query = ParseUser.getQuery();
		query.orderByDescending("online");
		query.findInBackground(new FindCallback<User>() { @Override public void done(List<User> users, ParseException e) {
			if (e == null) {
				adapter.clear();
	            adapter.addAll(users);
	        } else {
	            // No queries?
	        }
		}});
		lvStaff.setAdapter(adapter);
	}

	private void setupViews(View view) {
		lvStaff = (ListView) view.findViewById(R.id.lvStaff);
	}
	
}
