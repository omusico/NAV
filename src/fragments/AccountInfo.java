package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.example.nav.R;
import com.parse.ParseUser;

public class AccountInfo extends Fragment {
	
	private TextView tvEmail, tvFirstName, tvLastName, tvPhone;
	private ActionProcessButton btnLog;
	private ParseUser currentUser;
	
	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		currentUser = ParseUser.getCurrentUser();
	}
	
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		getActivity().getActionBar().show();
		View view = inflater.inflate(R.layout.fragment_account, container, false);
		setupViews(view);
		setupValues();
		setupListeners();
		return view;
	}

	private void setupListeners() {
		
		btnLog.setOnClickListener(new OnClickListener() { @Override public void onClick(View v) {
		  btnLog.setMode(ActionProcessButton.Mode.ENDLESS);
		  btnLog.setProgress(1);
		  currentUser.logOut();
		  FragmentTransaction ft = getFragmentManager().beginTransaction();
		  ft.replace(R.id.content_frame, new Login(), "login");
		  ft.commit();
		}});
	}

	private void setupValues() {
		if (currentUser != null) {
		  tvEmail.setText(currentUser.getEmail());
		  tvPhone.setText(currentUser.get("phone").toString());
		  tvFirstName.setText(currentUser.get("firstname").toString());
		  tvLastName.setText(currentUser.get("lastname").toString());
		} else {
		  FragmentTransaction ft = getFragmentManager().beginTransaction();
		  ft.replace(R.id.content_frame, new Login(), "login");
		  ft.commit();
		}
	}

	private void setupViews(View view) {
		tvEmail = (TextView) view.findViewById(R.id.tvEmail);
		tvFirstName = (TextView) view.findViewById(R.id.tvFirstName);
		tvLastName = (TextView) view.findViewById(R.id.tvLastName);
		tvPhone = (TextView) view.findViewById(R.id.tvPhone);
		btnLog = (ActionProcessButton) view.findViewById(R.id.btnLog);
	}
}
