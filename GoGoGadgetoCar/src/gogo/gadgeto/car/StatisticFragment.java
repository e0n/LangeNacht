package gogo.gadgeto.car;

import java.util.Iterator;
import java.util.Set;

import gogo.gadgeto.model.Database;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StatisticFragment extends ListFragment{
	
	private Database database;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		database = Database.getInstance();
		
		Set<FuelEntry> fuelEntries = database.getFuelEntries(database.getUsername());
		
		FuelEntry fuel_data[] = new FuelEntry[fuelEntries.size()];
		Iterator<FuelEntry> it = fuelEntries.iterator();
		
		for (int fuelEntryIndex = 0; it.hasNext() ; fuelEntryIndex++) {
			fuel_data[fuelEntryIndex] = it.next();
		}
		
		setListAdapter(new FuelAdapter(getActivity(), R.layout.fuel_list_item, fuel_data));
		
		
		
		
		
		
		
		
//        Set<String> names = database.getAvailableDriverNames();
//        
//        CashEntry cash_data[] = new CashEntry[names.size()];
//        Iterator<String> it = names.iterator();
//        
//        for (int namesIndex = 0; it.hasNext() ; namesIndex++) {
//        	cash_data[namesIndex] = new CashEntry(it.next(), "" + (namesIndex-2));
//        }
//		
//        setListAdapter(new CashAdapter(getActivity(), R.layout.cash_list_item, cash_data));
		
	}
	
	@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
		Intent newIntent = new Intent(getActivity().getApplicationContext(), DeptActivity.class);
		startActivity(newIntent);	
    }
}
