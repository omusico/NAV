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
import android.widget.TextView;

import com.example.nav.R;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterInfo extends Fragment {
	
	private EditText edtUserName, edtEmail, edtFirstName, edtLastName, edtPhone, edtPassword, edtPasswordAgain;
	private Button btnCreateAccount;
	private TextView tvValidation;
	private ProgressDialog pDialog;
	private ParseUser user;
	private FragmentTransaction ft;
	
	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		user = new ParseUser();
		ft = getFragmentManager().beginTransaction();
	}
	
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_register_info, container, false);
		setupViews(view);
		setupListeners();
		return view;
	}

	private void setupListeners() {
		btnCreateAccount.setOnClickListener(new OnClickListener() { @Override public void onClick(View v) {
			String email = edtEmail.getText().toString().trim();
			String firstname = edtFirstName.getText().toString().trim();
			String lastname = edtLastName.getText().toString().trim();
			String phone = edtPhone.getText().toString().trim();
			String username = edtUserName.getText().toString().trim();
			String password = edtPassword.getText().toString().trim();
			String passwordAgain = edtPasswordAgain.getText().toString().trim();
			
			if(password.equals(passwordAgain) && checkEmpty(email) == true && checkEmpty(firstname) == true && 
					checkEmpty(lastname) == true && checkEmpty(phone) == true && 
					checkEmpty(password) == true && checkEmpty (passwordAgain) == true) {
				
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);
				user.put("phone", phone);
				user.put("firstname", firstname);
				user.put("lastname", lastname);
				
				
				pDialog = new ProgressDialog(getActivity());
		        pDialog.setMessage("Registering...");
		        pDialog.setIndeterminate(false);
		        pDialog.setCancelable(true);
		        pDialog.show();
		        
				user.signUpInBackground(new SignUpCallback() { 
					@Override public void done(com.parse.ParseException e) {
						if (e == null) {
							pDialog.dismiss();
						    ft.replace(R.id.content_frame, new Welcome(), "welcome");
						    ft.commit();
						} else {
						    tvValidation.setText(e.toString());
						}
						
					}});
			} else if (!password.equals(passwordAgain)){
				tvValidation.setText("Passwords must match!");
			} else {
				tvValidation.setText("Please fill in the blanks!");
			}
		}});
	}

	private void setupViews(View view) {
		edtUserName = (EditText) view.findViewById(R.id.edtUserName);
		edtEmail = (EditText) view.findViewById(R.id.edtEmail);
		edtFirstName = (EditText) view.findViewById(R.id.edtFirstName);
		edtLastName = (EditText) view.findViewById(R.id.edtLastName);
		edtPhone = (EditText) view.findViewById(R.id.edtPhone);
		edtPassword = (EditText) view.findViewById(R.id.edtPassword);
		edtPasswordAgain = (EditText) view.findViewById(R.id.edtPasswordAgain);
		btnCreateAccount = (Button) view.findViewById(R.id.btnCreateAccount);
	}
	
	private boolean checkEmpty(String chk) {
		if(chk.trim().length() > 0) return true;
		else return false;
	}
}
