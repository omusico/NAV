package fragments;

import models.User;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.example.nav.R;
import com.hardik.floatinglabel.FloatingLabelView;
import com.parse.SignUpCallback;

public class RegisterInfo extends Fragment {
	
	private FloatingLabelView edtFirstName, edtEmail, edtUserName, edtLastName, edtPhone, edtPassword, edtPasswordAgain;
	private ActionProcessButton btnCreateAccount;
	private TextView tvValidation;
	private User user;
	
	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		user = new User();
	}
	
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		getActivity().getActionBar().hide();
		View view = inflater.inflate(R.layout.fragment_register_info, container, false);
		setupViews(view);
		setupListeners();
		return view;
	}

	private void setupListeners() {
		btnCreateAccount.setOnClickListener(new OnClickListener() { @Override public void onClick(View v) {
			btnCreateAccount.setEnabled(false);
			String email = edtEmail.getText().toString().trim();
			String firstname = edtFirstName.getText().toString().trim();
			String lastname = edtLastName.getText().toString().trim();
			String phone = edtPhone.getText().toString().trim();
			String username = edtUserName.getText().toString().trim();
			String password = edtPassword.getText().toString().trim();
			String passwordAgain = edtPasswordAgain.getText().toString().trim();
			
			btnCreateAccount.setMode(ActionProcessButton.Mode.ENDLESS);
			btnCreateAccount.setProgress(1);
			
			if(password.equals(passwordAgain) && checkEmpty(email) == true && checkEmpty(firstname) == true && 
					checkEmpty(lastname) == true && checkEmpty(phone) == true && 
					checkEmpty(password) == true && checkEmpty (passwordAgain) == true) {
				
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);
				user.setPhone(phone);
				user.setFirstName(firstname);
				user.setLastName(lastname);
				user.setOnline(true);
		        
				user.signUpInBackground(new SignUpCallback() { 
					@Override public void done(com.parse.ParseException e) {
						if (e == null) {
							getFragmentManager().popBackStack("bRegister", getFragmentManager().POP_BACK_STACK_INCLUSIVE);
							btnCreateAccount.setProgress(100);
							FragmentTransaction ft = getFragmentManager().beginTransaction();
						    ft.replace(R.id.content_frame, new AccountInfo(), "welcome");
						    ft.commit();
						} else {
							btnCreateAccount.setProgress(-1);
							tvValidation.setText("Username already exists!");
							Handler mHandler = new Handler();
						    mHandler.postDelayed(new Runnable() { @Override public void run() {
						    	btnCreateAccount.setProgress(0);
						    	btnCreateAccount.setEnabled(true);
						    } }, 1000);
						    
						}
					}});
			} else if (!password.equals(passwordAgain)){
				tvValidation.setText("Passwords must match!");
				btnCreateAccount.setProgress(-1);
				Handler mHandler = new Handler();
			    mHandler.postDelayed(new Runnable() { @Override public void run() {
			    	btnCreateAccount.setProgress(0);
			    	btnCreateAccount.setEnabled(true);
			    } }, 1000);
			} else {
				tvValidation.setText("Please fill in the blanks!");
				btnCreateAccount.setProgress(-1);
				Handler mHandler = new Handler();
			    mHandler.postDelayed(new Runnable() { @Override public void run() {
			    	btnCreateAccount.setProgress(0);
			    	btnCreateAccount.setEnabled(true);
			    } }, 1000);
			}
		}});
	}

	private void setupViews(View view) {
		edtUserName = (FloatingLabelView) view.findViewById(R.id.edtUserName);
		edtEmail = (FloatingLabelView) view.findViewById(R.id.edtEmail);
		edtFirstName = (FloatingLabelView) view.findViewById(R.id.edtFirstName);
		edtLastName = (FloatingLabelView) view.findViewById(R.id.edtLastName);
		edtPhone = (FloatingLabelView) view.findViewById(R.id.edtPhone);
		edtPassword = (FloatingLabelView) view.findViewById(R.id.edtPassword);
		edtPasswordAgain = (FloatingLabelView) view.findViewById(R.id.edtPasswordAgain);
		btnCreateAccount = (ActionProcessButton) view.findViewById(R.id.btnCreateAccount);
		tvValidation = (TextView) view.findViewById(R.id.tvValidation);
	}
	
	private boolean checkEmpty(String chk) {
		if(chk.trim().length() > 0) return true;
		else return false;
	}
}
