package activities;

import models.Ticket;
import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseObject;

public class NavApplication extends Application {

	private static Context context;
	
	@Override public void onCreate() {
		super.onCreate();
		NavApplication.context = this;
		
		//Register Parse Models
		ParseObject.registerSubclass(Ticket.class);
		//Parse application id and client key
		Parse.initialize(this, "DcaJgMRlLqEKR1IKJTDVuM2Si0OWLfghoQlYNyHa", "tvVa66moSgJe3HD1sfrLpccshas71JoOXsZX1Ro9");		
	}
}
