package gogo.gadgeto.car;

public class FuelEntry {
	
	// date of fuel
	public String date;
	// travelled distance with last fuel
	public String distance;
	// costs of fuel
	public String costs;
	// fuelid for database
	public String fuelid;
	
	public FuelEntry() {
	}
	
	public FuelEntry(String date, String distance, String costs, String fuelid) {
		super();
		this.date = date;
		this.distance = distance;
		this.costs = costs;
		this.fuelid = fuelid;
	}

}
