package fragments;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.nav.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class AddTicket extends DialogFragment implements OnClickListener {


	private TextView ticketdate;
	private Button ticketSubmit;
	private ScrollView scrolly;
	private EditText ticketSerial, ticketName, ticketModel, 
			 ticketColor, ticketLicense, ticketPark, ticketKey;
	private RadioButton sedanRadio, sportRadio, vanRadio, truckRadio, bikeRadio;
	private ImageView colorWheel;
	
	public static final int COLOR_PICKER = 1;
	
	@Override
	public void onResume() {
		super.onResume();
		Window window = getDialog().getWindow();
	    window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	    window.setGravity(Gravity.CENTER);
	}

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
		View view = inflater.inflate(R.layout.fragment_addticket, container, false);
	
		scrolly = (ScrollView) view.findViewById(R.id.scrollbar);
		ticketSerial = (EditText) view.findViewById(R.id.ticketserial);
		ticketName = (EditText) view.findViewById(R.id.ticketname);
		ticketdate = (TextView) view.findViewById(R.id.ticketdate);
		
		sedanRadio = (RadioButton) view.findViewById(R.id.sedanradio);
		sportRadio = (RadioButton) view.findViewById(R.id.sportradio);
		vanRadio =  (RadioButton) view.findViewById(R.id.vanradio);
		truckRadio = (RadioButton) view.findViewById(R.id.truckradio);
		bikeRadio = (RadioButton) view.findViewById(R.id.bikeradio);
		
		ticketModel = (EditText) view.findViewById(R.id.ticketmodel);
		ticketColor = (EditText) view.findViewById(R.id.ticketcolor);
		ticketLicense = (EditText) view.findViewById(R.id.ticketlicense);
		ticketPark = (EditText) view.findViewById(R.id.ticketparklocation);
		ticketKey = (EditText) view.findViewById(R.id.ticketkeylocation);
		ticketSubmit = (Button) view.findViewById(R.id.ticketsubmit);
		
		colorWheel = (ImageView) view.findViewById(R.id.colorwheel);
		
		colorWheel.setOnClickListener(this);
		ticketColor.setOnClickListener(this);
		
		scrolly.setVerticalScrollbarPosition(View.SCROLLBAR_POSITION_LEFT);
		String currentDate = new SimpleDateFormat("EEEE / MMMM-dd-yyyy / h:mm a", Locale.getDefault()).format(new Date());
		final String dateforSQL = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(new Date());
		ticketdate.setText(currentDate);
		
		ticketSubmit.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) {
		
			String ticket, name, date, cartype, model, color, license, park, key;
		
			ticket = ticketSerial.getText().toString().trim();
			name = ticketName.getText().toString().trim();
			date = dateforSQL;
			cartype = CarType();
			model = ticketModel.getText().toString().trim();
			color = ticketColor.getText().toString().trim();
			license = ticketLicense.getText().toString().trim();
			park = ticketPark.getText().toString().trim();
			key = ticketKey.getText().toString().trim();
			
			Toast.makeText(getActivity(), "Submit", Toast.LENGTH_SHORT).show();
			
		}});
		
		return view;
		
	}
	
	private String CarType() {
		String cartype;
		if(sedanRadio.isChecked()){
			cartype = "sedan";
		}
		else if(sportRadio.isChecked()){
			cartype = "coupe/sport";
		}
		else if(vanRadio.isChecked()){
			cartype = "van/suv";
		}
		else if(truckRadio.isChecked()){
			cartype = "truck";
		}
		else if(bikeRadio.isChecked()){
			cartype = "bike";
		} else {
			cartype = "";
		}
		return cartype;
	}

	@Override public void onClick(View v) {
		ColorPicker colorPicker = new ColorPicker();
		colorPicker.setTargetFragment(this, COLOR_PICKER);
		colorPicker.show(getFragmentManager(), "Color Picker");
	}
	
	@Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
	          switch(requestCode) {
	              case COLOR_PICKER:

	                  if (resultCode == Activity.RESULT_OK) {
	                      Bundle bundle=data.getExtras();
	                      String color = bundle.getString("color","");
	                      ticketColor.setText(color);

	                  } else if (resultCode == Activity.RESULT_CANCELED){
	                	  ticketColor.setText("");
	                  }
	                  break;
	          }
	}

}