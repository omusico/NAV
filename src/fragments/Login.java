package fragments;

import android.app.ProgressDialog;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.nav.R;
import com.parse.LogInCallback;
import com.parse.ParseUser;

public class Login extends Fragment {
	
	private EditText edtUserName, edtPassword;
	private Button btnSignUp, btnLogin;
	private FragmentTransaction ft;
	private ProgressDialog pDialog;
	private ParseUser currentUser;
	
	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		currentUser = ParseUser.getCurrentUser();
		ft = getFragmentManager().beginTransaction();
	}
	
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_login, container, false);
		if(currentUser == null){
			setupViews(view);
			setupListeners();
		} else {
			ft.replace(R.id.content_frame, new Welcome(), "welcome");
			ft.commit();
		}
		return view;
	}

	private void setupListeners() {
		btnSignUp.setOnClickListener(new OnClickListener() { @Override public void onClick(View v) {
			ft.replace(R.id.content_frame, new RegisterInfo(), "register");
			ft.addToBackStack("bRegister");
			ft.commit();
		}});
		btnLogin.setOnClickListener(new OnClickListener() { @Override public void onClick(View v) {
			pDialog = new ProgressDialog(getActivity());
	        pDialog.setMessage("Verifying...");
	        pDialog.setIndeterminate(false);
	        pDialog.setCancelable(true);
	        pDialog.show();
			ParseUser.logInInBackground(edtUserName.getText().toString().trim(), edtPassword.getText().toString().trim(), new LogInCallback() {
			@Override public void done(ParseUser user, com.parse.ParseException e) {
				pDialog.dismiss();
				ft.replace(R.id.content_frame, new Welcome(), "welcome");
				ft.commit();
			}});	
		}});
	}

	private void setupViews(View view) {
		btnSignUp = (Button) view.findViewById(R.id.btnSignUp);
		btnLogin = (Button) view.findViewById(R.id.btnLogin);
		edtUserName = (EditText) view.findViewById(R.id.edtUserName);
		edtPassword = (EditText) view.findViewById(R.id.edtPassword);
	}
	
}
