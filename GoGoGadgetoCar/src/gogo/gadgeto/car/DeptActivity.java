package gogo.gadgeto.car;

import gogo.gadgeto.car.tasks.GetUserstatisticTask;

import java.util.Iterator;
import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class DeptActivity extends Activity {
	
	private ListView myListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept);
        
        Bundle bundle = this.getIntent().getExtras();
        String refuelid = bundle.getString("refuelid");
		
        getUserstatistics(refuelid);                 
    }
    
    private void getUserstatistics(String refuelid) {
    	new GetUserstatisticTask(this, refuelid).execute();
    }

	public void refreshView(Set<CashEntry> cashEntries) {
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
