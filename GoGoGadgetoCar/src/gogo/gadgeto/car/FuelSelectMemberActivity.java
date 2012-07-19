package gogo.gadgeto.car;

import gogo.gadgeto.model.Database;

import java.util.Iterator;
import java.util.Set;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FuelSelectMemberActivity extends ListActivity {
	
	 private Set<String> availableNames;	// all available names in carsharing group
	 private Set<String> selectedPayments;	// selected names
	 private Database database;	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_fuel_select_member);
        
        database = Database.getInstance();
        selectedPayments = database.getSelectedPayerNames();
        availableNames = database.getAvailableDriverNames();
        
        // Insert all available names into array adapter
        String[] inserts = new String[availableNames.size()];
        Iterator<String> it = availableNames.iterator();
        for (int i = 0; it.hasNext(); i++) {
       	 inserts[i] = it.next();
        }
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,inserts);
        // Get the activity's ListView and set its choice mode as multiple choice
        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        setListAdapter(adapter);
        
        // Mark selected items in ListView         
        for(int positionIndex = 0; positionIndex < availableNames.size(); positionIndex++) {
       	 String currentName =  (String) getListView().getItemAtPosition(positionIndex);
       	 
       	 if (selectedPayments.contains(currentName)) {
       		 getListView().setItemChecked(positionIndex, true);
       	 }
       	 else {
       		 getListView().setItemChecked(positionIndex, false);
       	 }
        }
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
		 super.onListItemClick(l, v, position, id);
		
		 Toast.makeText(this,"Item Clicked", Toast.LENGTH_SHORT).show();
		 
		 // get selected name and toggle it
		 String toggledName = (String) getListView().getItemAtPosition(position);		 
		 database.toggleSelectedPayerName(toggledName);
	 }    
}
