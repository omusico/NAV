package models;

import java.util.Date;
import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Ticket")
public class Ticket extends ParseObject {

	public Ticket() { // A default constructor is required.
	}
	
	//Getters
	public String getName(){ return getString("name");}
	public String getSerial(){ return getString("serial");}
	public Date getDateIn(){ return getCreatedAt();}
	public Date getDateOut(){ return (Date) get("dateout");}
	public String getLicense(){ return getString("license");}
	public String getType(){ return getString("type");}
	public String getColor(){ return getString("color");}
	public String getModel(){ return getString("model");}
	public String getMake(){ return getString("make");}
	public String getParkLocation(){ return getString("parklocation");}
	public String getKeyLocation(){ return getString("keylocation");}
	public String getStaffEnter(){ return getString("staffenter");}
	public String getStaffPickUp(){ return getString("staffpickup");}
	public boolean getActive(){ return getBoolean("active");}
	
	//Setters
	public void setName(String name){ put("name", name);}
	public void setSerial(String serial){ put("serial", serial);}
	public void setDateOut(String dateout){ put("dateout", dateout);}
	public void setLicense(String license){ put("license", license);}
	public void setType(String type){ put("type", type);}
	public void setColor(String color){ put("color", color);}
	public void setModel(String model){ put("model", model);}
	public void setMake(String make){ put("make", make);}
	public void setParkLocation(String parklocation){ put("parklocation", parklocation);}
	public void setKeyLocation(String keylocation){ put("keylocation", keylocation);}
	public void setStaffEnter(String staffenter){ put("staffenter", staffenter);}
	public void setStaffPickUp(String staffpickup){ put("staffpickup", staffpickup);}
	public void setActive(boolean active){ put("active", active);}
	
}
