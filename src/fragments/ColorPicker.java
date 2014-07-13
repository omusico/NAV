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
		
		switch (v.getId()){
			case R.id.black:
				extras.putString("color", "Black");
				break;
			case R.id.silver:
				extras.putString("color", "Silver");
				break;
			case R.id.white:
				extras.putString("color", "White");
				break;
			case R.id.red:
				extras.putString("color", "Red");
				break;
			case R.id.orange:
				extras.putString("color", "Orange");
				break;
			case R.id.gold:
				extras.putString("color", "Gold");
				break;
			case R.id.green:
				extras.putString("color", "Green");
				break;
			case R.id.neon:
				extras.putString("color", "Neon");
				break;
			case R.id.yellow:
				extras.putString("color", "Yellow");
				break;
			case R.id.navy:
				extras.putString("color", "Navy");
				break;
			case R.id.blue:
				extras.putString("color", "Blue");
				break;
			case R.id.teal:
				extras.putString("color", "Teal");
				break;
			case R.id.purple:
				extras.putString("color", "Purple");
				break;
			case R.id.violet:
				extras.putString("color", "Violet");
				break;
			case R.id.pink:
				extras.putString("color", "Hot Pink");
				break;
			case R.id.brown:
				extras.putString("color", "Brown");
				break;
			case R.id.rose:
				extras.putString("color", "Rose");
				break;
			case R.id.lightpink:
				extras.putString("color", "Pink");
				break;
			case R.id.gradient:
				extras.putString("color", "Other");
				break;	
			default:
				break;
		}
		
		i.putExtras(extras);
		getTargetFragment().onActivityResult(getTargetRequestCode(),Activity.RESULT_OK,i);
		dismiss();
	}
	
	

}
