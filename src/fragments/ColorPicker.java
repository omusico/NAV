package fragments;

import com.example.nav.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

public class ColorPicker extends DialogFragment implements OnClickListener {

	// The only reason you might override this method when using onCreateView() is
    // to modify any dialog characteristics. For example, the dialog includes a
    // title by default, but your custom layout might not need it. So here you can
    // remove the dialog title, but you must call the superclass to get the Dialog.
	@Override public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dialog = super.onCreateDialog(savedInstanceState);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		return dialog;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_color_picker, null);
		ImageView black = (ImageView) view.findViewById(R.id.black);
		ImageView silver = (ImageView) view.findViewById(R.id.silver);
		ImageView white = (ImageView) view.findViewById(R.id.white);
		ImageView red = (ImageView) view.findViewById(R.id.red);
		ImageView orange = (ImageView) view.findViewById(R.id.orange);
		ImageView gold = (ImageView) view.findViewById(R.id.gold);
		ImageView green = (ImageView) view.findViewById(R.id.green);
		ImageView neon = (ImageView) view.findViewById(R.id.neon);
		ImageView yellow = (ImageView) view.findViewById(R.id.yellow);
		ImageView navy = (ImageView) view.findViewById(R.id.navy);
		ImageView blue = (ImageView) view.findViewById(R.id.blue);
		ImageView teal = (ImageView) view.findViewById(R.id.teal);
		ImageView purple = (ImageView) view.findViewById(R.id.purple);
		ImageView violet = (ImageView) view.findViewById(R.id.violet);
		ImageView pink = (ImageView) view.findViewById(R.id.pink);
		ImageView brown = (ImageView) view.findViewById(R.id.brown);
		ImageView rose = (ImageView) view.findViewById(R.id.rose);
		ImageView lightpink = (ImageView) view.findViewById(R.id.lightpink);
		ImageView gradient = (ImageView) view.findViewById(R.id.gradient);
		
		// just realized that i could just append it on the back... above... arghh
		black.setOnClickListener(this);
		silver.setOnClickListener(this);
		white.setOnClickListener(this);
		red.setOnClickListener(this);
		orange.setOnClickListener(this);
		gold.setOnClickListener(this);
		green.setOnClickListener(this);
		neon.setOnClickListener(this);
		yellow.setOnClickListener(this);
		navy.setOnClickListener(this);
		blue.setOnClickListener(this);
		teal.setOnClickListener(this);
		purple.setOnClickListener(this);
		violet.setOnClickListener(this);
		pink.setOnClickListener(this);
		brown.setOnClickListener(this);
		rose.setOnClickListener(this);
		lightpink.setOnClickListener(this);
		gradient.setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onClick(View v) {
		
		Intent i = new Intent();
		Bundle extras = new Bundle();
		
		int id = v.getId();
		// Converted switch-case statements to if-else structure
		if (id == R.id.black) {
			extras.putString("color", "Black");
		} else if (id == R.id.silver) {
			extras.putString("color", "Silver");
		} else if (id == R.id.white) {
			extras.putString("color", "White");
		} else if (id == R.id.red) {
			extras.putString("color", "Red");
		} else if (id == R.id.orange) {
			extras.putString("color", "Orange");
		} else if (id == R.id.gold) {
			extras.putString("color", "Gold");
		} else if (id == R.id.green) {
			extras.putString("color", "Green");
		} else if (id == R.id.neon) {
			extras.putString("color", "Neon");
		} else if (id == R.id.yellow) {
			extras.putString("color", "Yellow");
		} else if (id == R.id.navy) {
			extras.putString("color", "Navy");
		} else if (id == R.id.blue) {
			extras.putString("color", "Blue");
		} else if (id == R.id.teal) {
			extras.putString("color", "Teal");
		} else if (id == R.id.purple) {
			extras.putString("color", "Purple");
		} else if (id == R.id.violet) {
			extras.putString("color", "Violet");
		} else if (id == R.id.pink) {
			extras.putString("color", "Hot Pink");
		} else if (id == R.id.brown) {
			extras.putString("color", "Brown");
		} else if (id == R.id.rose) {
			extras.putString("color", "Rose");
		} else if (id == R.id.lightpink) {
			extras.putString("color", "Pink");
		} else if (id == R.id.gradient) {
			extras.putString("color", "Other");
		} else {
		}
		
		i.putExtras(extras);
		getTargetFragment().onActivityResult(getTargetRequestCode(),Activity.RESULT_OK,i);
		dismiss();
	}
	
	

}
