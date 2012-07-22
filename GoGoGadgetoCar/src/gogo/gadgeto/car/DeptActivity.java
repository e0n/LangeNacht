package gogo.gadgeto.car;

import gogo.gadgeto.model.Database;

import java.util.Iterator;
import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class DeptActivity extends Activity {
	
	private Database database;
	private ListView myListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept);
        
        database = Database.getInstance();        
        
        String fuelId = "34";
        Set<CashEntry> cashEntries = database.getCashEntries(database.getUsername(), fuelId);
        
        CashEntry cash_data[] = new CashEntry[cashEntries.size()];
		Iterator<CashEntry> it = cashEntries.iterator();
		
		for (int cashEntryIndex = 0; it.hasNext() ; cashEntryIndex++) {
			cash_data[cashEntryIndex] = it.next();
		}
        
        CashAdapter adapter = new CashAdapter(this, R.layout.cash_list_item, cash_data);
        
        myListView = (ListView) findViewById(R.id.statisticsListView);
        myListView.setAdapter(adapter);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_dept, menu);
        return true;
    }

    
}
