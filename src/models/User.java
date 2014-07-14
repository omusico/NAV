package models;

import com.parse.ParseClassName;
import com.parse.ParseUser;

@ParseClassName("User")
public class User extends ParseUser {
	
	public User() {
	}
	
	//Setters
	public void setFirstName(String firstname){ put("firstname", firstname);}
	public void setLastName(String lastname){ put("lastname", lastname);}
	public void setPhone(String phone){ put("phone", phone);}
	public void setOnline(boolean online){ put("online", online);}
	
}
