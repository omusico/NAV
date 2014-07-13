package adapters;

import java.util.ArrayList;
import models.Ticket;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.nav.R;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

@SuppressLint("DefaultLocale")
public class InventoryAdaptor extends ArrayAdapter<Ticket> {

	public InventoryAdaptor(Context context, ArrayList<Ticket> tickets) {
		super(context, 0, tickets);
	}

	private class ViewHolder {
		TextView tvName, tvSerial, tvModel, tvPark, tvKey; 
		ImageView ivType, vColor;
	}
	
	private ViewHolder viewHolder;
	
	@Override public View getView(int position, View convertView, ViewGroup parent) {
		Ticket ticket = getItem(position);
		
		if (convertView == null) {
			convertView = View.inflate(getContext(), R.layout.adapter_inventory, null);
			viewHolder = new ViewHolder();
			viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			viewHolder.tvSerial = (TextView) convertView.findViewById(R.id.tvSerial);
			viewHolder.tvPark = (TextView) convertView.findViewById(R.id.tvPark);
			viewHolder.tvKey = (TextView) convertView.findViewById(R.id.tvKey);
			viewHolder.tvModel = (TextView) convertView.findViewById(R.id.tvModel);
			viewHolder.ivType = (ImageView) convertView.findViewById(R.id.ivType);
			viewHolder.vColor = (ImageView) convertView.findViewById(R.id.vColor);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		getColor(ticket.getColor().toLowerCase());
		viewHolder.tvName.setText(ticket.getName());
		viewHolder.tvSerial.setText(ticket.getSerial());
		viewHolder.tvModel.setText(ticket.getModel());
		viewHolder.tvPark.setText(ticket.getParkLocation());
		viewHolder.tvKey.setText(ticket.getKeyLocation());
		getType(ticket.getType());
		
	    return convertView;
	}

	private void getType(String cartype) {
		if(cartype.equals("sedan")){
			viewHolder.ivType.setBackgroundResource(R.drawable.car_sedanon);
		}
		else if(cartype.equals("coupe/sport")){
			viewHolder.ivType.setBackgroundResource(R.drawable.car_sportscaron);
		}
		else if(cartype.equals("van/suv")){
			viewHolder.ivType.setBackgroundResource(R.drawable.car_vanon);
		}
		else if(cartype.equals("truck")){
			viewHolder.ivType.setBackgroundResource(R.drawable.car_truckon);
		}
		else if(cartype.equals("bike")){
			viewHolder.ivType.setBackgroundResource(R.drawable.car_bikeon);
		} else {
			viewHolder.ivType.setBackgroundResource(R.drawable.car_sedanon);
		}
	}

	private void getColor(String carcolor) {
		if(carcolor.equals("black")){
			viewHolder.vColor.setBackgroundResource(R.color.black);
		}
		if(carcolor.equals("silver")){
			viewHolder.vColor.setBackgroundResource(R.color.silver);
		}
		if(carcolor.equals("white")){
			viewHolder.vColor.setBackgroundResource(R.drawable.whitestrip);
		}
		if(carcolor.equals("red")){
			viewHolder.vColor.setBackgroundResource(R.color.red);
		}
		if(carcolor.equals("orange")){
			viewHolder.vColor.setBackgroundResource(R.color.orange);
		}
		if(carcolor.equals("gold")){
			viewHolder.vColor.setBackgroundResource(R.color.gold);
		}
		if(carcolor.equals("green")){
			viewHolder.vColor.setBackgroundResource(R.color.green);
		}
		if(carcolor.equals("neon")){
			viewHolder.vColor.setBackgroundResource(R.color.neon);
		}
		if(carcolor.equals("yellow")){
			viewHolder.vColor.setBackgroundResource(R.color.yellow);
		}
		if(carcolor.equals("navy")){
			viewHolder.vColor.setBackgroundResource(R.color.navy);
		}
		if(carcolor.equals("blue")){
			viewHolder.vColor.setBackgroundResource(R.color.blue);
		}
		if(carcolor.equals("teal")){
			viewHolder.vColor.setBackgroundResource(R.color.teal);
		}
		if(carcolor.equals("purple")){
			viewHolder.vColor.setBackgroundResource(R.color.purple);
		}
		if(carcolor.equals("violet")){
			viewHolder.vColor.setBackgroundResource(R.color.violet);
		}
		if(carcolor.equals("hot pink")){
			viewHolder.vColor.setBackgroundResource(R.color.pink);
		}
		if(carcolor.equals("brown")){
			viewHolder.vColor.setBackgroundResource(R.color.brown);
		}
		if(carcolor.equals("rose")){
			viewHolder.vColor.setBackgroundResource(R.color.rose);
		}
		if(carcolor.equals("pink")){
			viewHolder.vColor.setBackgroundResource(R.color.lightpink);
		}
		if(carcolor.equals("other")){
			viewHolder.vColor.setBackgroundResource(R.drawable.gradientstrip);
		}
	}

}
