package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.nav.R;

public class RegisterCheck extends Fragment {

	private EditText edtCheckUser;
	private Button btnCheck;
	
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_register_check, container, false);
		setupViews(view);
		setupListeners();
		return view;
	}

	private void setupListeners() {
		btnCheck.setOnClickListener(new OnClickListener() { @Override public void onClick(View v) {
		}});
	}

	private void setupViews(View view) {
		edtCheckUser = (EditText) view.findViewById(R.id.edtCheckUser);
		btnCheck = (Button) view.findViewById(R.id.btnCheck);
	}
	
}
