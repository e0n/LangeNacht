package gogo.gadgeto.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Database {
	
	private static Database INSTANCE = null;
	Set<String> selectedDriverNames;
	Set<String> selectedPayerNames;
	Set<String> availableDriverNames;
	List<NameValuePair> availableDriverNamesJson;
	String username;
	String carShareId;
	
	private Database() {
		username = "ph";
		carShareId = "111111";
		selectedDriverNames= new HashSet<String>();
		selectedPayerNames = new HashSet<String>();
		availableDriverNames = new HashSet<String>();
		availableDriverNames.add("Lars");
		availableDriverNames.add("ph");
		availableDriverNames.add("Tobi");
		availableDriverNames.add("Sam");
		availableDriverNames.add("Nils");
		availableDriverNames.add("Hanna");
		
		availableDriverNamesJson = new ArrayList<NameValuePair>();
		for (String name : availableDriverNames) {
			availableDriverNamesJson.add(new BasicNameValuePair("username", name));
		}
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
	
	public List<NameValuePair> getAvailableDriverNamesJson() {
		return availableDriverNamesJson;		
	}
	
	public Set<String> getSelectedDriverNames() {
		return selectedDriverNames;		
	}
	
	public void setSelectedDriverNames(Set<String> newNames) {
		if (newNames != null) {
			selectedDriverNames = newNames;			
		}
	}
	
	public String getCurrentCarShareId() {
		return carShareId; 
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

	public String sendFuelToDatabase(String payerName, int payment, String username2) {
		String result;
		if ( payerName != null ) {
			if ( username2 != null) {
				if ( payment != 0) {					
					result = username2 + " inserted: " + payerName;
					result += " fueled for " + payment + "euro.";					
				} else {
					result = "no payment entered.";
				}
			} else {
				result = "no username found.";
			}
		} else {
			result = "payer invalid.";
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
	
	public String sendJoinCarGroupRequest (String username, String groupId, String password) {
		String result = "Added " + username + " to group " + groupId + " with password " + password;
		return result;
	}

	public String[] getGroups(String username2) {
		String result[] = {"123","456","789","135","246"};
		return result;
	}

	public String sendLeaveGroupRequest(String username2, String leavingGroupId) {
		// TODO Auto-generated method stub
		return null;
	}

}
