package gogo.gadgeto.car;

import gogo.gadgeto.model.Database;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NewDistanceSelectDriverActivity extends ListActivity {

	private String[] inserts;

	  /** Called when the activity is first created. */
	     @Override
	     public void onCreate(Bundle savedInstanceState) {
	         super.onCreate(savedInstanceState);
	         
	         Database database = new Database();
	         inserts = database.getDriverNames();
	       
	         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,inserts);
	         // Get the activity's ListView and set its choice mode as multiple choice
	         getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	         setListAdapter(adapter);
	     }

	  protected void onListItemClick(ListView l, View v, int position, long id) {
	   // TODO Auto-generated method stub
	   super.onListItemClick(l, v, position, id);

	   Toast.makeText(this,"Item Clicked", Toast.LENGTH_SHORT).show();
	  
	  }
	
}
