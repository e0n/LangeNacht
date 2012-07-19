package gogo.gadgeto.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import android.widget.Toast;

public class Database {
	
	private static Database INSTANCE = null;
	Set<String> selectedDriverNames;
	Set<String> availableDriverNames;
	String username;
	
	private Database() {
		username = "ph";
		selectedDriverNames= new HashSet<String>();
		availableDriverNames = new HashSet<String>();
		availableDriverNames.add("Lars");
		availableDriverNames.add("ph");
		availableDriverNames.add("Tobi");
		availableDriverNames.add("Sam");
		availableDriverNames.add("Nils");
		availableDriverNames.add("Hanna");
	}
	
	public static Database getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Database();
		}
		return INSTANCE;
	}
	
	public Set<String> getAvailableDriverNames() {
		return availableDriverNames;		
	}
	
	public Set<String> getSelectedNames() {
		return selectedDriverNames;		
	}
	
	public void setSelectedNames(Set<String> newNames) {
		selectedDriverNames = newNames;
	}
	
	public void toggleSelectedName (String newName){
		boolean result = selectedDriverNames.add(newName);
		 if (!result) {
			 selectedDriverNames.remove(newName);
		 }
	}
	
	public String getUsername() {
		return username;
	}
	
	public String sendToDatabase(Set<String> driverNames, int distance, String username) {
		String result;
		if ( driverNames != null ) {
			if ( username != null) {
				if ( distance != 0) {
					
					result = username + " inserted: ";
					Iterator<String> it = driverNames.iterator();
					if (driverNames.size() != 0) {
						result += it.next();
						while (it.hasNext()) {
							result += ", " + it.next();
						}
						result += " drove " + distance + "km.";
					} else {
						result = "No Names selected.";
					}
					
				} else {
					result = "no distance entered.";
				}
			} else {
				result = "no username found.";
			}
		} else {
			result = "drivers set invalid.";
		}		
		return result;
	}

}
