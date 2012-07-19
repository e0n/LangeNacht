package gogo.gadgeto.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Database {
	
	private static Database INSTANCE = null;
	Set<String> selectedDriverNames;
	Set<String> selectedPayerNames;
	Set<String> availableDriverNames;
	String username;
	
	private Database() {
		username = "ph";
		selectedDriverNames= new HashSet<String>();
		selectedPayerNames = new HashSet<String>();
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
	
	public Set<String> getSelectedDriverNames() {
		return selectedDriverNames;		
	}
	
	public void setSelectedDriverNames(Set<String> newNames) {
		if (newNames != null) {
			selectedDriverNames = newNames;			
		}
	}
	
	public void toggleSelectedDriverName (String newName){
		boolean result = selectedDriverNames.add(newName);
		 if (!result) {
			 selectedDriverNames.remove(newName);
		 }
	}
	
	public String getUsername() {
		return username;
	}
	
	public String sendDistanceToDatabase(Set<String> driverNames, int distance, String username2) {
		String result;
		if ( driverNames != null ) {
			if ( username2 != null) {
				if ( distance != 0) {
					
					result = username2 + " inserted: ";
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

	public String sendFuelToDatabase(Set<String> selectedNames, int payment, String username2) {
		String result;
		if ( selectedNames != null ) {
			if ( username2 != null) {
				if ( payment != 0) {
					
					result = username2 + " inserted: ";
					Iterator<String> it = selectedNames.iterator();
					if (selectedNames.size() != 0) {
						result += it.next();
						while (it.hasNext()) {
							result += ", " + it.next();
						}
						result += " fueled for " + payment + "euro.";
					} else {
						result = "No Names selected.";
					}
					
				} else {
					result = "no payment entered.";
				}
			} else {
				result = "no username found.";
			}
		} else {
			result = "drivers set invalid.";
		}		
		return result;
	}

	public Set<String> getSelectedPayerNames() {
		return selectedPayerNames;
	}

	public void toggleSelectedPayerName(String toggledName) {
		boolean result = selectedPayerNames.add(toggledName);
		 if (!result) {
			 selectedPayerNames.remove(toggledName);
		 }		
	}

}
