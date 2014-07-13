package fragments;

import java.util.ArrayList;
import java.util.List;

import models.Ticket;
import adapters.InventoryAdaptor;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.nav.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

public class Inventory extends Fragment {

	private ListView lvInventory;
	private ArrayList<Ticket> tickets;
	private InventoryAdaptor adapter;
	private ProgressDialog pDialog;
	
	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tickets = new ArrayList<Ticket>();
		adapter = new InventoryAdaptor(getActivity(), tickets);
	}
	
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_inventory, container, false);
		setupViews(view);
		setupValues();
		return view;
	}

	private void setupValues() {
		pDialog = new ProgressDialog(getActivity());
		pDialog.show();
		pDialog.setCancelable(true);
		pDialog.setIndeterminate(true);
		pDialog.setMessage("Getting Tickets...");
		ParseQuery<Ticket> query = ParseQuery.getQuery("Ticket");
		query.findInBackground(new FindCallback<Ticket>() { @Override public void done(List<Ticket> tickets, ParseException e) {
			if (e == null) {
				adapter.clear();
	            adapter.addAll(tickets);
	            pDialog.dismiss();
	        } else {
	            // No queries?
	        	pDialog.dismiss();
	        }
		}});
		lvInventory.setAdapter(adapter);
	}

	private void setupViews(View view) {
		lvInventory = (ListView) view.findViewById(R.id.lvInventory);
	}
	
}
