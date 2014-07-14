package adapters;

import java.util.ArrayList;

import models.User;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nav.R;
import com.parse.ParseUser;

@SuppressLint("DefaultLocale")
public class StaffAdaptor extends ArrayAdapter<User> {

	public StaffAdaptor(Context context, ArrayList<User> users) {
		super(context, 0, users);
	}

	private class ViewHolder {
		TextView tvUser; 
		ImageView ivOnline;
	}
	
	private ViewHolder viewHolder;
	
	@Override public View getView(int position, View convertView, ViewGroup parent) {
		ParseUser user = getItem(position);
		
		if (convertView == null) {
			convertView = View.inflate(getContext(), R.layout.adapter_staff, null);
			viewHolder = new ViewHolder();
			viewHolder.tvUser = (TextView) convertView.findViewById(R.id.tvUser);
			viewHolder.ivOnline = (ImageView) convertView.findViewById(R.id.ivOnline);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.tvUser.setText(user.getUsername());
		
		if(user.getBoolean("online")){
			viewHolder.ivOnline.setBackgroundResource(R.drawable.circle_green);
		} else {
			viewHolder.ivOnline.setBackgroundResource(R.drawable.circle_silver);
		}
		
	    return convertView;
	}

}
