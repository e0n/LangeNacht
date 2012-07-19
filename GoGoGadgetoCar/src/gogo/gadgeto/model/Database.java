package gogo.gadgeto.model;

import java.util.HashSet;
import java.util.Set;

public class Database {
	
	private static Database INSTANCE = null;
	Set<String> selectedDriverNames;
	Set<String> availableDriverNames;
	
	private Database() {
		selectedDriverNames= new HashSet<String>();
		availableDriverNames = new HashSet<String>();
		availableDriverNames.add("Lars");
		availableDriverNames.add("Philip");
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

}
