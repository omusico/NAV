package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dd.processbutton.iml.ActionProcessButton;
import com.example.nav.R;
import com.parse.LogInCallback;
import com.parse.ParseUser;

public class Login extends Fragment {
	
	private EditText edtUserName, edtPassword;
	private Button btnSignUp;
	private ActionProcessButton btnLogin;
	private ParseUser currentUser;
	
	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		currentUser = ParseUser.getCurrentUser();
	}
	
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_login, container, false);
		if(currentUser == null){
			getActivity().getActionBar().hide();
			setupViews(view);
			setupListeners();
		} else {
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.content_frame, new AccountInfo(), "welcome");
			ft.commit();
		}
		return view;
	}

	private void setupListeners() {
		btnSignUp.setOnClickListener(new OnClickListener() { @Override public void onClick(View v) {
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.content_frame, new RegisterInfo(), "register");
			ft.addToBackStack("bRegister");
			ft.commit();
		}});
		btnLogin.setOnClickListener(new OnClickListener() { @Override public void onClick(View v) {
			btnLogin.setEnabled(false);
			btnLogin.setMode(ActionProcessButton.Mode.ENDLESS);
			btnLogin.setProgress(1);
			ParseUser.logInInBackground(edtUserName.getText().toString().trim(), edtPassword.getText().toString().trim(), new LogInCallback() {
			@Override public void done(ParseUser user, com.parse.ParseException e) {
				btnLogin.setProgress(100);
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.replace(R.id.content_frame, new AccountInfo(), "welcome");
				ft.commit();
				btnLogin.setEnabled(true);
			}});	
		}});
	}

	private void setupViews(View view) {
		btnSignUp = (Button) view.findViewById(R.id.btnSignUp);
		btnLogin = (ActionProcessButton) view.findViewById(R.id.btnLogin);
		edtUserName = (EditText) view.findViewById(R.id.edtUserName);
		edtPassword = (EditText) view.findViewById(R.id.edtPassword);
	}
	
}
